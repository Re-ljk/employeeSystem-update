package com.cn.employeesystem.service;

import com.cn.employeesystem.entity.Employee;
import com.cn.employeesystem.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAllEmployee();

//    Employee findEmployeeById(int id);
    public Employee findEmployeeById(String id);

    public  boolean insertEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);


    public boolean deleteEmployee(String id);

//    Page<Employee> findAllEmployee(int currentPage, int pageSize);
//
//    Page<Employee> findEmployeeByPage(int currentPage, int pageSize);

    Page<Employee> findEmployeesByPage(int currentPage, int pageSize);


    public boolean deleteItemsByIds(@Param("ids") List<Integer> ids);
}
