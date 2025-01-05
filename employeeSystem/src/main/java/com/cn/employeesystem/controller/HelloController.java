package com.cn.employeesystem.controller;

import com.cn.employeesystem.EmployeeSystemApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 使用 @RestController 注解将该类标记为 RESTful 控制器
@RestController
public class HelloController {

    // 使用 @GetMapping 注解将 hello 方法映射到 /hello 路径的 GET 请求
    @GetMapping("/hello")
    public String hello() {
        // 该方法返回一个字符串 "Hello World"，作为响应内容
        return "Hello World";
    }
}