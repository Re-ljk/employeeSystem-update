package com.cn.employeesystem.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.employeesystem.entity.Employee;
//import com.cn.employeesystem.entity.Page;
import com.cn.employeesystem.mapper.EmployeeMapper;
import com.cn.employeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAllEmployee() {
//        List<Employee> list = employeeMapper.findAllEmployee();
        List<Employee> list = employeeMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    @Override
    public Employee findEmployeeById(String id) {//根据id查询信息
        return employeeMapper.selectById(id);//findEmployeeById(id);
    }
    @Override
    public boolean insertEmployee(Employee employee) {//添加员工信息
        return employeeMapper.insert(employee)>0;  //insertEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {//更新员工信息
        return employeeMapper.updateById(employee)>0  ;
        //updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(String id) {//删除员工信息
        return employeeMapper.deleteById(id)>0;
        //deleteEmployee(id);
    }

    @Override
    public IPage<Employee> findEmployeesByPage(int currentPage, int pageSize) {
        // 获取总记录数
        int totalCount = employeeMapper.getTotalCount();

        // 计算总页数
        int totalPage = (totalCount % pageSize == 0) ?
                (totalCount / pageSize) :
                (totalCount / pageSize) + 1;

        // 计算查询的起始位置
        int offset = (currentPage - 1) * pageSize;

        // 创建 Page 对象（MyBatis-Plus 提供的分页实现类）
        IPage<Employee> page = new Page<>(currentPage, pageSize);

        // 执行分页查询
        page = employeeMapper.selectPage(page, null);

        // 设置分页数据
        page.setCurrent(currentPage);  // 当前页
        page.setTotal(totalCount);     // 总记录数
        page.setSize(pageSize);        // 每页显示记录数
        page.setRecords(employeeMapper.findEmployeesByPage(offset)); // 当前页记录
        page.setPages(totalPage);      // 总页数

        return page;
    }
    @Override
    public boolean deleteItemsByIds(List<Integer> ids){
        return employeeMapper.deleteItemsByIds(ids);
    }

    @Override
    public Page<Employee> searchEmployeesByName(String name, int currentPage, int pageSize) {
        // MyBatis-Plus分页插件的逻辑
        Page<Employee> page = new Page<>(currentPage, pageSize);

        // 构建查询条件（模糊查询）
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);

        // 执行分页查询
        return employeeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<Employee> findEmployeesWithDeptName(int currentPage, int pageSize) {
        // 查询的起始位置
        int offset = (currentPage - 1) * pageSize;

        // 执行分页查询员工与部门信息
        List<Employee> list = employeeMapper.findEmployeesWithDeptName(offset, pageSize);

        // 获取总记录数
        int totalCount = employeeMapper.getTotalCount();

        // 计算总页数
        int totalPage = (totalCount % pageSize == 0) ?
                (totalCount / pageSize) : (totalCount / pageSize) + 1;

        // 创建分页对象
        Page<Employee> page = new Page<>(currentPage, pageSize);
        page.setRecords(list);  // 设置当前页数据
        page.setTotal(totalCount);  // 设置总记录数
        page.setPages(totalPage);   // 设置总页数

        return page;
    }
}

