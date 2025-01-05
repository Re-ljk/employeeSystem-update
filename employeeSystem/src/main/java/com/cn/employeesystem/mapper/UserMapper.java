package com.cn.employeesystem.mapper;

import com.cn.employeesystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    //根据用户名和密码查询用户信息
    public User findUserByusernameAndPassword(@Param("username") String username,@Param("password") String password);

}
