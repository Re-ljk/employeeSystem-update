<template>
  <div class="form-container">
    <el-form
      :model="employee"
      :rules="rules"
      ref="employeeForm"
      label-width="80px"
    >
      <!-- 姓名 -->
      <el-form-item label="姓名" prop="name">
        <el-input v-model="employee.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <!-- 年龄 -->
      <el-form-item label="年龄" prop="age">
        <el-input
          v-model.number="employee.age"
          type="number"
          placeholder="请输入年龄"
        ></el-input>
      </el-form-item>

      <!-- 性别 -->
      <el-form-item label="性别" prop="gender">
        <el-select v-model="employee.gender" placeholder="请选择性别">
          <el-option label="男" value="male"></el-option>
          <el-option label="女" value="female"></el-option>
        </el-select>
      </el-form-item>

      <!-- 入职日期 -->
      <el-form-item label="入职日期" prop="entryDate">
        <el-date-picker
          v-model="employee.entryDate"
          type="date"
          placeholder="选择入职日期"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :disabled-date="disableFutureDates"
        ></el-date-picker>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item style="text-align: center;">
        <el-button :loading="loading" type="primary" @click="onSubmit">
          添加员工
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
        name: "",
        age: "",
        gender: "",
        entryDate: "",
      },
      loading: false, // 加载状态
      rules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        age: [
          { required: true, message: "请输入年龄", trigger: "blur" },
          {
            type: "number",
            min: 18,
            max: 65,
            message: "年龄必须在18-65岁之间",
            trigger: "blur",
          },
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "change" }],
        entryDate: [
          { required: true, message: "请选择入职日期", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    // 禁止选择未来日期
    disableFutureDates(date) {
      return date > new Date();
    },

    // 提交表单
    onSubmit() {
      this.$refs.employeeForm.validate(async (valid) => {
        if (valid) {
          this.loading = true; // 启用加载
          try {
            const response = await this.$axios.post(
              "/api/employees/add",
              this.employee
            );
            if (response.data && response.data.code === 1) {
              this.$message.success("添加员工成功！");
              this.resetForm(); // 添加成功后重置表单
            } else {
              this.$message.error(response.data.msg || "添加员工失败");
            }
          } catch (error) {
            console.error("添加员工失败:", error);
            this.$message.error("添加员工失败，请稍后重试");
          } finally {
            this.loading = false; // 关闭加载
          }
        } else {
          this.$message.warning("请完善表单信息后再提交");
        }
      });
    },
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

el-form-item {
  margin-bottom: 20px;
}
</style>
