package com.cn.employeesystem;

import com.cn.employeesystem.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2//配置Swagger
//public class EmpSystemApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(EmpSystemApplication.class, args);
//    }
//}
public class EmployeeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSystemApplication.class, args);
    }

    public Employee findEmployeeById(int id) {
        return null;
    }


}
