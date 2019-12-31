package com.cnrmall.springboot.service;
import com.cnrmall.springboot.entity.User;

import java.util.List;
public interface UserService {

    /**
     * 查询用户列表
     * */
    List<User> listUser();

    /**
     * 查询审核通过的用户列表
     * */
    List<User> findListByValid();


    /**
     * 根据ID查询单个用户
     * */
    User findUserById(int id);

    /**
     * 根据手机号查询单个用户
     * */
    User findUserByPhone(String phone);

    /**
     * 添加用户
     * */
    void insertUser(User user);


    /**
     * 修改用户
     * */
    int updateUser(Integer id);


    /**
     * 删除用户列表
     * */
    void deleteUser(int id);


    /**
     * 查询用户列表(含有投票数量以及按照票数做了排序)
     * */
    List<User> findListByOrder();

}
