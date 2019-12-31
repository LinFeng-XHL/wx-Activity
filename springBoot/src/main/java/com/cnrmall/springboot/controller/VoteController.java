package com.cnrmall.springboot.controller;
import com.cnrmall.springboot.controller.viewobject.VoteVO;
import com.cnrmall.springboot.entity.VoteDO;
import com.cnrmall.springboot.error.BusinessException;
import com.cnrmall.springboot.error.EmBusinessError;
import com.cnrmall.springboot.response.CommonReturnType;
import com.cnrmall.springboot.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/vote")
public class VoteController {


    @Autowired
    private VoteService voteService;

    /**
     * 用户投票功能
     * */
    @PostMapping(value="/vote/insertVote")
    @ResponseBody
    public CommonReturnType insertVote(@ModelAttribute VoteVO voteVO) throws BusinessException {


        if (voteVO==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        VoteDO voteDO = convertVoteVOFromVoteDO(voteVO);
        //插入投票数据之前判断一下是否已经投过票
        VoteDO voteDOExit = voteService.selectByCondition(voteDO.getNickname(),voteDO.getGender(),voteDO.getAvatarurl());
        if (voteDOExit!=null){//若是不等于null.则表示投过票，即使按钮没有及时变为只读点击也会提示无法投票
            throw new BusinessException(EmBusinessError.VOTE_ISEXIST);
        }else{//若是等于null,可以成功投票
            voteService.insertVote(voteDO);
        }
        return CommonReturnType.create(null);

    }


    /**
     * 查询用户是否重复投票
     * */
    @PostMapping(value="/vote/selectByCondition")
    @ResponseBody
    public boolean selectByCondition(@ModelAttribute VoteVO voteVO) throws BusinessException {



        /**
         * 直接调用查询后台数据是否重复
         * */

        VoteDO voteDO = convertVoteVOFromVoteDO(voteVO);

        VoteDO voteDOExit = voteService.selectByCondition(voteDO.getNickname(),voteDO.getGender(),voteDO.getAvatarurl());

        if (voteDOExit==null){//若是查询不到相关数据，则表示当前用户未进行投票，可以投票，返回true
            return true;
        }else{//若是查询到相关数据，则表示当前用户已经投票，不可以投票，返回false
            return false;
        }

    }


    /**
     * voteVO  转换为  voteDO
     * */
    public VoteDO convertVoteVOFromVoteDO(VoteVO voteVO){

        if (voteVO==null){
            return null;
        }

        VoteDO voteDO=new VoteDO();
        voteDO.setAvatarurl(voteVO.getAvatarUrl());
        voteDO.setGender(voteVO.getGender());
        voteDO.setVoteid(voteVO.getVoteid());
        voteDO.setVoteDate(voteVO.getVoteDate());
        voteDO.setPhone(voteVO.getPhone());
        voteDO.setOpenid(voteVO.getOpenid());
        voteDO.setNickname(voteVO.getNickname());

        return voteDO;
    }






}
