package com.cn.employeesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.employeesystem.entity.Dept;
import org.springframework.stereotype.Service;


public interface DeptService {
    //分页查询所有员工信息
    public Page<Dept> findAll(int currentPage, int pageSize);

    //根据ID查询部门信息
    public Dept findDeptById(Long id);
    public boolean deleteDeptById(Long id);

    public boolean addDept(Dept dept);

    public boolean updateDept(Dept dept);


    Page<Dept> searchDeptsByDeptName(String name, int currentPage, int pageSize);
}