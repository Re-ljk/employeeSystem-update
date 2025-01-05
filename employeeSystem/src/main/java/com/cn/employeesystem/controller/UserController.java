package com.cn.employeesystem.controller;

import com.cn.employeesystem.entity.User;
import com.cn.employeesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public User login(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}
