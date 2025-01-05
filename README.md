
---

# **员工信息管理系统 (EmployeeSystem)**  

> **最后修改时间：** 2024 年 1 月 5 日  
> **作者：**  Jiangkuanli
---

## **项目结构**  
- **后端代码：** `employeeSystem`  
- **前端代码：** `vue-project`  

---

## **项目阶段与成果展示**  

### **功能实现与界面展示**  
> **展示时间：** 2024 年 1 月 5 日  

1. **主界面效果图**  
   ![主界面效果](https://github.com/user-attachments/assets/52b859c7-d532-4a30-a499-45185b78d147)  

2. **添加员工界面**  
   ![员工表单界面](https://github.com/user-attachments/assets/c957142a-f826-4835-afc7-8c2b784a8c54)  

3. **编辑员工界面**  
   ![部门列表效果](https://github.com/user-attachments/assets/78936e80-6a22-45ee-81dd-fac5df37bc39)  

---

## **项目安装与运行**  

### **环境要求**  
- **Node.js：** >= 14.0  
- **Java：** >= 8  
- **MySQL：** >= 5.7  

---

### **后端运行**  

1. **配置数据库信息：**  
   在 `application.yml` 文件中，更新数据库配置：  
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC
       username: root
       password: yourpassword
   ```

2. **启动后端服务：**  
   在后端代码目录下运行以下命令：  
   ```bash
   mvn spring-boot:run
   ```

---

### **前端运行**  

1. **安装全局 Vue CLI：**  
   ```bash
   npm install -g @vue/cli
   ```

2. **创建 Vue 工程：**  
   ```bash
   vue create vue-project
   ```

3. **进入 Vue 项目目录：**  
   ```bash
   cd vue-project
   ```

4. **安装必要依赖：**  
   ```bash
   npm install
   npm install element-ui --save
   npm install vue-router@3 --save
   ```

5. **启动开发服务器：**  
   ```bash
   npm run serve
   ```

---

## **正在改进中**  
目前项目正在持续改进中..........  

---
