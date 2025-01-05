package com.cn.employeesystem.service;

import com.cn.employeesystem.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //登录方法
    public User login(String username, String password);
}
