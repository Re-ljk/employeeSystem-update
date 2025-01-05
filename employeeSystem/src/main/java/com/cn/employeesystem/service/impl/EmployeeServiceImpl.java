package com.cn.employeesystem.service.impl;
import com.cn.employeesystem.entity.Employee;
import com.cn.employeesystem.entity.Page;
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
        List<Employee> list = employeeMapper.findAllEmployee();
        System.out.println(list);
        return list;
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeMapper.findEmployeeById(id);
    }
    @Override
    public boolean insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee){
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(String id){
        return employeeMapper.deleteEmployee(id);
    }



    @Override
    public Page<Employee> findEmployeesByPage(int currentPage, int pageSize) {
        //具体的分页的业务逻辑
        //1.获取总记录数
        int totalCount = employeeMapper.getTotalCount();
        //2.计算总页数
        int totalPage = (totalCount % pageSize == 0)? (totalCount / pageSize) : (totalCount / pageSize + 1);

        int offset = (currentPage - 1) * pageSize;//这个页从第几条开始
        Page<Employee>page = new Page<Employee>();//创建分页对象
        page.setCurrentPage(currentPage);//当前页
        page.setTotalPage(totalCount);//总记录数
        page.setPageSize(pageSize);//每页显示记录数
        page.setList(employeeMapper.findAllEmployee());//每页显示的数据
        page.setTotalPage(totalPage);//总页数
        return page;



    }
    @Override
    public boolean deleteItemsByIds(List<Integer> ids){
        return employeeMapper.deleteItemsByIds(ids);
    }
}

