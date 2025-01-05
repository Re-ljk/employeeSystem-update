package com.cn.employeesystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cn.employeesystem.entity.Employee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.employeesystem.entity.Result;
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
    @GetMapping
    public Result findAllEmp() {
        List<Employee> employees = employeeService.findAllEmployee();
        if (employees != null) {
            return Result.success(employees);
        } else {
            return Result.error("获取员工信息失败");
        }
    }

    @GetMapping("/findId/{id}")
    public Result findEmployeeById(@PathVariable String id) {
        Employee emp = employeeService.findEmployeeById(id);
        if (emp != null) {
            return Result.success(emp);
        } else {
            return Result.error("未找到对应的员工信息");
        }
    }

    @PostMapping("/add")
    public Result addEmployee(@RequestBody Employee employee) {
        boolean flag = employeeService.insertEmployee(employee);
        if (flag) {
            return Result.success("员工添加成功");
        } else {
            return Result.error("员工添加失败");
        }
    }

    @PutMapping("/update")
    public Result updateEmployee(@RequestBody Employee employee) {
        boolean flag = employeeService.updateEmployee(employee);
        if (flag) {
            return Result.success("员工更新成功");
        } else {
            return Result.error("员工更新失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteEmployee(@PathVariable String id) {
        boolean flag = employeeService.deleteEmployee(id);
        if (flag) {
            return Result.success("员工删除成功");
        } else {
            return Result.error("员工删除失败");
        }
    }


    @GetMapping("/page")
    public Result getEmployeesByPage(@RequestParam int currentPage, @RequestParam int pageSize) {
//        Page<Employee> page = employeeService.findEmployeesByPage(currentPage, pageSize);
        IPage<Employee> page = employeeService.findEmployeesWithDeptName(currentPage, pageSize);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("分页查询失败");
        }
    }

    @DeleteMapping("/delByIds")
    public Result deleteItemsByIds(@RequestBody List<Integer> ids) {
        boolean flag = employeeService.deleteItemsByIds(ids);
        if (flag) {
            return Result.success("批量删除成功");
        } else {
            return Result.error("批量删除失败");
        }
    }

    @GetMapping("/search")
    public Result searchEmployeesByName(@RequestParam String name, @RequestParam int currentPage, @RequestParam int pageSize) {

        IPage<Employee> page = employeeService.searchEmployeesByName(name, currentPage, pageSize);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("搜索失败");
        }
    }

}


/*进阶版本*/
//@RestController
//@RequestMapping("/api/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping
//    public Result<List<Employee>> findAllEmp() {
//        List<Employee> employees = employeeService.findAllEmployee();
//        return employees != null
//                ? Result.success(employees, "获取员工列表成功")
//                : Result.error("获取员工列表失败");
//    }
//
//    @GetMapping("/findId/{id}")
//    public Result<Employee> findEmployeeById(@PathVariable String id) {
//        Employee emp = employeeService.findEmployeeById(id);
//        return emp != null
//                ? Result.success(emp, "查询成功")
//                : Result.error("未找到对应员工");
//    }
//
//    @PostMapping("/add")
//    public Result<Void> addEmployee(@RequestBody Employee employee) {
//        boolean flag = employeeService.insertEmployee(employee);
//        return flag
//                ? Result.success(null, "添加成功")
//                : Result.error("添加失败");
//    }
//
//    @PutMapping("/update")
//    public Result<Void> updateEmployee(@RequestBody Employee employee) {
//        boolean flag = employeeService.updateEmployee(employee);
//        return flag
//                ? Result.success(null, "更新成功")
//                : Result.error("更新失败");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public Result<Void> deleteEmployee(@PathVariable String id) {
//        boolean flag = employeeService.deleteEmployee(id);
//        return flag
//                ? Result.success(null, "删除成功")
//                : Result.error("删除失败");
//    }
//
//    @GetMapping("/page")
//    public Result<Page<Employee>> getEmployeesByPage(@RequestParam int currentPage, @RequestParam int pageSize) {
//        Page<Employee> page = employeeService.findEmployeesByPage(currentPage, pageSize);
//        return Result.success(page, "分页查询成功");
//    }
//
//    @DeleteMapping("/delByIds")
//    public Result<Void> deleteItemsByIds(@RequestBody List<Integer> ids) {
//        boolean flag = employeeService.deleteItemsByIds(ids);
//        return flag
//                ? Result.success(null, "批量删除成功")
//                : Result.error("批量删除失败");
//    }
//
//    @GetMapping("/search")
//    public Result<Page<Employee>> searchEmployeesByName(@RequestParam String name, @RequestParam int currentPage, @RequestParam int pageSize) {
//        Page<Employee> page = employeeService.searchEmployeesByName(name, currentPage, pageSize);
//        return Result.success(page, "搜索成功");
//    }
//}
