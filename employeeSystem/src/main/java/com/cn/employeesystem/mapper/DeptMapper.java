package com.cn.employeesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.employeesystem.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    //查询所有员工信息
    //@Select("select * from dept")

    public List<Dept> findAll(@Param("offset") int offset,
                              @Param("pageSize") int pageSize);
    public int getTotalCount();
    //根据ID查询部门信息
    public Dept findDeptById(Long id);

    //根据部门ID删除
//    public boolean deleteDeptById(Long id);

    public boolean deleteDeptById(Long id);
    //更新部门信息
    public boolean updateDept(Dept dept);
    //根据部门名称模糊查询
    public List<Dept> searchDeptsByDeptName(String deptName);

}