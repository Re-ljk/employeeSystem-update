# 一、Swagger2介绍 

前后端分离开发模式中，api文档是最好的沟通方式。

Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。

及时性 (接口变更后，能够及时准确地通知相关前后端开发人员)规范性 (并且保证接口的规范性，如接口的地址，请求方式，参数及响应格式和错误信息)一致性 (接口信息一致，不会出现因开发人员拿到的文档版本不一致，而出现分歧)可测性 (直接在接口文档上进行测试，以方便理解业务)



# 二、配置Swagger2

**1、在项目的pom.xml中引入相关依赖**

```
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <scope>provided </scope>
        </dependency>

        <!--mybatis-plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <scope>provided </scope>
        </dependency>

        <!--lombok用来简化实体类：需要安装lombok插件-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided </scope>
        </dependency>

        <!--swagger-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>
    </dependencies>
```





## 2、在模块中创建swagger的配置类

创建包com.cn.empsystem.config，创建类SwaggerConfig

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("员工管理系统")
                .description("本文档描述了员工信息管理的接口定义")
                .version("1.0")
                .contact(new Contact("hello", "http://localhost:8080", ""))
                .build();
    }
}
```

## 3、在启动类上添加注解，进行测试

```java
@SpringBootApplication
@EnableSwagger2//配置Swagger
public class EmpSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpSystemApplication.class, args);
    }
}
```



4由于SpringBoot2.6.x与Swagger2.7.0版本冲突，所以需要配置WebMvcConfigurationSupport

```java
package com.cn.empsystem.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {        registry.addResourceHandler("/**").addResourceLocations(
            "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html", "doc.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}

```

4.打开如下网站：

http://localhost:8080/swagger-ui.html







