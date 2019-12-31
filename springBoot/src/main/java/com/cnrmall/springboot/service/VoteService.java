package com.cnrmall.springboot.service;

import com.cnrmall.springboot.entity.VoteDO;

public interface VoteService {
    /**
     * 实现投票的方法
     * */
    int insertVote(VoteDO voteDO);

    /**
     * 查询当前用户是否已经投过票
     * */
    VoteDO selectByCondition(String nickname,String gender,String avatarUrl);
}
