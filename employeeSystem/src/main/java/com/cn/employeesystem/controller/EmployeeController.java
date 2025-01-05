package com.cn.employeesystem.controller;

import com.cn.employeesystem.entity.Employee;
import com.cn.employeesystem.entity.Page;
import com.cn.employeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee 控制器，用于处理与员工相关的 HTTP 请求
 */
@RestController

@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 获取所有员工信息的接口
     *
     * @return 员工列表
     */
//    @GetMapping("/emps")
    @GetMapping
    public List<Employee> findAllEmp() {

        return employeeService.findAllEmployee();
    }
    @GetMapping("/findId/{id}")
    public Employee findEmployeeById(@PathVariable String id) {

        return employeeService.findEmployeeById(id);
    }
    @PostMapping("/addEmployee")
    public boolean addEmployee(@RequestBody Employee employee) {

        return employeeService.insertEmployee(employee);
    }
    @PostMapping("/updateEmployee")
    public boolean updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public boolean deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployee(id);
    }
    @GetMapping("/Page")
    public Page<Employee>getEmployeesByPage(@RequestParam int currentPage, int pageSize) {
        return employeeService.findEmployeesByPage(currentPage,pageSize);
    }
    @DeleteMapping("/delByIds")
    public boolean deleteItemsByIds(@RequestBody List<Integer> ids) {
        return employeeService.deleteItemsByIds(ids);
    }

}
