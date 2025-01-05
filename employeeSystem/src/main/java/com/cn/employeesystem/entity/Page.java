package com.cn.employeesystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page<T> {
    private int pageNo;//总记录数
    private int totalPage;//总页数
    private int currentPage;//当前页
    private int pageSize;//每页显示的条数

    private List<T> list;//每页显示的具体数据
//    public int getPageNo() {
//        return pageNo;
//    }
}
