package com.cnrmall.springboot.service.impl;

import com.cnrmall.springboot.dao.VoteDOMapper;
import com.cnrmall.springboot.entity.VoteDO;
import com.cnrmall.springboot.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired(required = false)
    private VoteDOMapper voteDOMapper;

    @Override
    public int insertVote(VoteDO voteDO) {
        int num = voteDOMapper.insertSelective(voteDO);
        return num;
    }

    public VoteDO selectByCondition(String nickname,String gender,String avatarUrl){
        VoteDO voteDOExit = voteDOMapper.selectByCondition(nickname,gender,avatarUrl);
        return voteDOExit;
    }
}
