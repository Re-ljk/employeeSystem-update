package com.cn.employeesystem;

import com.cn.employeesystem.entity.Employee;
import com.cn.employeesystem.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




import java.util.List;
@SpringBootTest
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
class EmployeeSystemApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {

    }
    @Test
    public void testFindAllEmployee() {
        List<Employee> list = employeeMapper.findAllEmployee();
        System.out.println(list);


    }
}
