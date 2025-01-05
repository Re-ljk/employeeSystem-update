package com.cn.employeesystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.employeesystem.entity.Dept;
import com.cn.employeesystem.entity.Result;
import com.cn.employeesystem.service.DeptService;
import jdk.jfr.MetadataDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    //查询所有部门信息
    @GetMapping
//    public Page<Dept> findAllDepts(@RequestParam int currentPage,
//                                   @RequestParam int pageSize){
//        return deptService.findAll(currentPage,pageSize);
//    }

    public Result findAllDepts(@RequestParam int currentPage,
                               @RequestParam int pageSize){
        Page<Dept> page = deptService.findAll(currentPage, pageSize);
        if(page.getRecords() != null && page.getRecords().isEmpty()){
            return Result.success(page);

        }
        else {
            return Result.error("查询失败");
        }
    }

    @GetMapping("/{id}")
    public Result findDeptById(@PathVariable("id") Long id){
        Dept dept = deptService.findDeptById(id);
        if(dept != null){
            return Result.success(dept);
        }
        else {
            return Result.error("查询失败");
        }
    }
    @DeleteMapping("{id}")
    public Result deleteDeptById(@PathVariable Long id){
        boolean flag = deptService.deleteDeptById(id);
        if(flag){
            return Result.success("删除成功");

        }
        else {
            return Result.error("删除失败");
        }
    }

    @PostMapping()
    public Result addDept(@RequestBody Dept dept){
        boolean flag = deptService.addDept(dept);
        if(flag){
            return Result.success("添加成功");
        }
        else{
            return Result.error("添加失败");
        }

    }
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
       boolean flag = deptService.updateDept(dept);
        if(flag){
            return Result.success("更新成功");
        }
        else{
            return Result.error("更新失败");
        }

    }
    @GetMapping("/search")
    public Result searchDeptsByDeptName(@RequestParam String name, @RequestParam int currentPage, @RequestParam int pageSize) {
        Page<Dept> page = deptService.searchDeptsByDeptName(name, currentPage, pageSize);

        // 判断 page.getRecords() 是否为空
        if (!page.getRecords().isEmpty()) {
            return Result.success(page);  // 如果不为空，返回查询结果
        } else {
            return Result.error("未找到相关部门信息");  // 如果为空，返回查询失败的提示
        }
    }

}