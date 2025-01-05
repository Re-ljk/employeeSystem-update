package com.cn.employeesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.employeesystem.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface EmployeeMapper extends BaseMapper<Employee> {
    //查询所有员工信息
    public List<Employee> findAllEmployee();
    //根据员工的id查询一名员工信息
//    public Employee findEmployeeById(int id);
    public Employee findEmployeeById(String id);
    //添加员工
    public boolean insertEmployee(Employee employee);
    //更新员工信息
    public boolean updateEmployee(Employee employee);
    //删除员工信息
    public boolean deleteEmployee(String id);
    public int getTotalCount();
    //根据分页查询员工信息
    public List<Employee> findEmployeesByPage(@Param("offset") int offset);//List代表集合
    public boolean deleteItemsByIds(@Param("ids") List<Integer> ids);

    //使用分页查询所有员工信息，包含部门名称
    public List<Employee> findEmployeesWithDeptName(@Param("offset") int offset, @Param("pageSize") int pageSize);



}
