<template>
  <div class="form-container">
    <el-form :model="employee" label-width="80px">
      <!-- 姓名 -->
      <el-form-item label="姓名">
        <el-input v-model="employee.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <!-- 性别 -->
      <el-form-item label="性别">
        <el-input v-model="employee.gender" placeholder="请输入性别"></el-input>
      </el-form-item>

      <!-- 入职日期 -->
      <el-form-item label="入职日期">
        <el-input v-model="employee.entryDate" placeholder="请输入入职日期"></el-input>
      </el-form-item>

      <!-- 部门名称 -->
      <el-form-item label="部门名称">
        <el-input v-model="employee.deptName" placeholder="请输入部门名称"></el-input>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item style="text-align: center;">
        <el-button type="primary" icon="el-icon-edit" @click="updateEmployee">
          更新员工
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      employee: {
        name: '',
        gender: '',
        entryDate: '',
        deptName: '',
      },
    };
  },
  methods: {
    // 获取员工ID并从后端获取员工数据
    async getEmployeeId() {
      const id = this.$route.params.id; // 获取路由中的ID参数
      console.log('员工ID:', id);

      try {
        // 发送 GET 请求从后端获取员工信息
        const response = await this.$axios.get(`/api/employees/findId/${id}`);
        
        // 如果响应成功，将返回的数据填充到 employee 对象中
        if (response.data && response.data.data) {
          this.employee = response.data.data;
          console.log('员工信息:', this.employee);
        } else {
          this.$message.error('未找到员工信息！');
        }
      } catch (error) {
        console.error('获取员工信息出错:', error);
        this.$message.error('获取员工信息失败，请稍后重试！');
      }
    },

    // 更新员工信息
    async updateEmployee() {
      try {
        // 发送更新请求到后端
        const response = await this.$axios.post('/api/employees/update', this.employee);

        // 根据后端返回状态显示提示信息
        if (response.data && response.data.success) {
          this.$message.success('员工信息更新成功！');
        } else {
          this.$message.error('更新失败，请检查输入！');
        }
      } catch (error) {
        console.error('更新员工信息出错:', error);
        this.$message.error('更新失败，请稍后重试！');
      }
    },
  },
  mounted() {
    // 在组件挂载时调用 getEmployeeId 获取数据
    this.getEmployeeId();
  },
};
</script>

<style scoped>
.form-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 占满整个页面高度 */
  background-color: #f5f5f5; /* 可选：背景颜色 */
  padding: 20px;
  box-sizing: border-box;
}

el-form {
  width: 100%; /* 占用可用宽度 */
  max-width: 400px; /* 设置最大宽度以控制表单大小 */
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 可选：添加阴影 */
}
</style>
