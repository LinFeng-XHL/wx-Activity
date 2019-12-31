package com.cnrmall.springboot.service.impl;

import com.cnrmall.springboot.entity.User;
import com.cnrmall.springboot.service.UserService;
import com.cnrmall.springboot.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //引入dao层
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> listUser() {
        List<User> listUser = userMapper.findList();
        return listUser;
    }

    @Override
    public List<User> findListByValid() {
        List<User> listByValid = userMapper.findListByValid();
        return listByValid;
    }

    @Override
    public User findUserById(int id) {
        User user = userMapper.findListById(id);
        return user;
    }

    @Override
    public User findUserByPhone(String phone) {
        User user = userMapper.findListByPhone(phone);
        return user;
    }

    @Override
    public void insertUser(User user) {
        userMapper.add(user);
    }

    @Override
    public int updateUser(Integer id) {
        int num = userMapper.updateByUserId(id);
        return num;
    }

    @Override
    public void deleteUser(int id) {
        userMapper.delete(id);
    }

    @Override
    public List<User> findListByOrder() {
        List<User> listByOrder = userMapper.findListByOrder();
        return listByOrder;
    }
}
