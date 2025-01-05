package com.cn.employeesystem.service.impl;

import com.cn.employeesystem.entity.User;
import com.cn.employeesystem.mapper.UserMapper;
import com.cn.employeesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(String username, String password) {
        return userMapper.findUserByusernameAndPassword(username,password);
    }
}