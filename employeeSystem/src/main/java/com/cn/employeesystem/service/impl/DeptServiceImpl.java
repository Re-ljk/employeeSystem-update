package com.cn.employeesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.employeesystem.entity.Dept;

import com.cn.employeesystem.mapper.DeptMapper;
import com.cn.employeesystem.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Page<Dept> findAll(int currentPage, int pageSize) {
        int offset = (currentPage-1)*pageSize;
        List<Dept> depts = deptMapper.findAll(offset,pageSize);
        //查询总记录数
        int totalCount = deptMapper.getTotalCount();
        //计算总页数
        int totalPage = ((totalCount%pageSize)==0)?
                (totalCount/pageSize):((totalCount/pageSize)+1);
        Page<Dept> page = new Page<>();
        page.setTotal(totalCount);
        page.setRecords(depts);
        page.setPages(totalPage);

        return page;
    }

    @Override
    public Dept findDeptById(Long id) {
//        return deptMapper.findDeptById(id);
        return deptMapper.selectById(id);
    }


    @Override
    public boolean deleteDeptById(Long id) {
        return deptMapper.deleteById(id)>0;
    }
    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.insert(dept)>0;
    }
    @Override
    public boolean updateDept(Dept dept) {
        return deptMapper.updateById(dept)>0;
    }

    @Override
    public Page<Dept> searchDeptsByDeptName(String deptName, int currentPage, int pageSize) {
        // 创建分页对象，传入当前页和每页条目数
        Page<Dept> page = new Page<>(currentPage, pageSize);

        // 使用 QueryWrapper 构建查询条件，进行模糊查询
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", deptName); // 查询 name 列中包含 deptName 的记录

        // 执行分页查询，并返回结果
        // 这里不需要手动计算总记录数和总页数，MyBatis-Plus 会自动处理
        return deptMapper.selectPage(page, queryWrapper);
    }

//    /*手动计算*/
//    @Override
//    public Page<Dept> searchDeptsByDeptName(String deptName, int currentPage, int pageSize) {
//        // 创建分页对象，传入当前页和每页条目数
//        Page<Dept> page = new Page<>(currentPage, pageSize);
//
//        // 获取总记录数
//        int totalCount = deptMapper.getTotalCount();
//
//        // 计算总页数
//        int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
//
//        // 使用 QueryWrapper 构建查询条件，进行模糊查询
//        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name", deptName); // 查询 name 列中包含 deptName 的记录
//
//        // 计算当前页的起始位置（偏移量）
//        int offset = (currentPage - 1) * pageSize;
//
//        // 获取当前页的数据
//        List<Dept> depts = deptMapper.findAll(offset, pageSize);
//
//        // 设置分页信息
//        page.setCurrent(currentPage); // 设置当前页
//        page.setTotal(totalCount); // 设置总记录数
//        page.setSize(pageSize); // 设置每页记录数
//        page.setPages(totalPage); // 设置总页数
//        page.setRecords(depts); // 设置当前页的数据记录
//
//        return page;
//    }


//    public Page<Dept> searchDeptsByDeptName(String deptName, int currentPage, int pageSize){
//            // 创建分页对象，传入当前页和每页条目数
//            Page<Dept> page = new Page<>(currentPage, pageSize);
//
//            // 使用 QueryWrapper 构建查询条件，获取符合条件的总记录数
//            Long totalCount = deptMapper.selectCount(new QueryWrapper<Dept>().like("name", deptName));
//
//            // 计算总页数，如果总记录数能被每页条数整除，则页数为总记录数除以每页条数，否则加 1
//            Long totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
//
//            // 创建 QueryWrapper 对象，设置查询条件，进行模糊查询
//            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
//            queryWrapper.like("name", deptName); // 查询 name 列中包含 deptName 的记录
//
//            // 设置分页信息
//            page.setCurrent(currentPage); // 设置当前页
//            page.setTotal(totalCount); // 设置总记录数
//            page.setSize(pageSize); // 设置每页记录数
//
//            // 执行分页查询，并返回结果
//            return deptMapper.selectPage(page, queryWrapper);
//        }


    }

