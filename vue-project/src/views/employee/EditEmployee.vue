<template>
    <div>
      <el-form :model="employee" label-width="80px">
        <!-- 姓名 -->
        <el-form-item label="姓名">
          <el-input v-model="employee.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
  
        <!-- 性别 -->
        <el-form-item label="性别">
          <el-radio-group v-model="employee.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
  
        <!-- 入职日期 -->
        <el-form-item label="入职日期">
          <el-date-picker
            v-model="employee.entryDate"
            type="date"
            placeholder="选择入职日期"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
  
        <!-- 部门 -->
        <el-form-item label="部门名称">
          <el-select v-model="employee.deptId" placeholder="请选择部门">
            <el-option
              v-for="dept in departments"
              :key="dept.id"
              :label="dept.name"
              :value="dept.id"
            ></el-option>
          </el-select>
        </el-form-item>
  
        <!-- 提交与重置 -->
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        employee: {
          id: '', // 员工ID（一般自动生成）
          name: '', // 姓名
          gender: '', // 性别
          entryDate: '', // 入职日期
          deptId: '', // 部门ID
        },
        departments: [
          { id: 1, name: '技术部' },
          { id: 2, name: '人事部' },
          { id: 3, name: '市场部' },
          { id: 4, name: '财务部' },
        ],
      };
    },
    methods: {
      // 提交表单
      submitForm() {
        console.log('提交数据:', this.employee);
        this.$message.success('提交成功！');
      },
  
      // 重置表单
      resetForm() {
        this.employee = {
          id: '',
          name: '',
          gender: '',
          entryDate: '',
          deptId: '',
        };
      },
    },
  };
  </script>
  
  <style scoped>
  </style>
  