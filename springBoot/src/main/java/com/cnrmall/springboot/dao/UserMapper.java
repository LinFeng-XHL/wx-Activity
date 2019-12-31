package com.cnrmall.springboot.dao;

import com.cnrmall.springboot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {


    /**
     * 小程序查询审核通过用户列表
     * */
    List<User> findListByValid();

    /**
     * 查询用户列表
     * */
    List<User> findList();


    /**
     * 根据ID查询单个用户
     * */
    User findListById(int id);


    /**
     * 查询用户以及投票数量
     * */
    List<User> findListByOrder();


    /**
     * 根据用户手机号查询单个用户
     * */
    User findListByPhone(String phone);

    /**
     * 添加用户
     * */
    void add(User user);


    /**
     * 修改用户
     * */
    int updateByUserId(Integer id);


    /**
     * 删除用户列表
     * */
    void delete(int id);





}
