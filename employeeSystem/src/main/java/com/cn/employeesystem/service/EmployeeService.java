package com.cn.employeesystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cn.employeesystem.entity.Employee;
import java.util.List;

public interface EmployeeService {

    // 查询所有员工
    List<Employee> findAllEmployee();

    // 根据 ID 查询员工
    Employee findEmployeeById(String id);

    // 插入新员工
    boolean insertEmployee(Employee employee);

    // 更新员工信息
    boolean updateEmployee(Employee employee);

    // 删除员工
    boolean deleteEmployee(String id);

    // 分页查询员工列表
    IPage<Employee> findEmployeesByPage(int currentPage, int pageSize);

    // 批量删除员工
    boolean deleteItemsByIds(List<Integer> ids);

    // 根据姓名搜索员工
    IPage<Employee> searchEmployeesByName(String name, int currentPage, int pageSize);

    // 分页查询员工信息和部门名称
    IPage<Employee> findEmployeesWithDeptName(int offset, int pageSize);
}
