package com.cn.employeesystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private Integer id; // id,
    private String name; // 姓名
    private String gender; // 性别
    private Integer deptId; // 部门id
    private LocalDateTime entryDate;
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    @TableField(exist = false)
    private Dept dept; // 维护一对一关系，关联部门信息
}
