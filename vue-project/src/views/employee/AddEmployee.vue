<template>
  <div class="form-container">
    <el-form
      :model="employee"
      :rules="rules"
      ref="employeeForm"
      label-width="100px"
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
          placeholder="请选择入职日期"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :disabled-date="disableFutureDates"
        ></el-date-picker>
      </el-form-item>

      <!-- 部门名称 -->
      <el-form-item label="部门名称" prop="deptId">
        <el-select v-model="employee.deptId" placeholder="请选择部门">
          <el-option
            v-for="dept in departmentList"
            :key="dept.id"
            :label="dept.name"
            :value="dept.id"
          ></el-option>
        </el-select>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item style="text-align: center;">
        <el-button
          :loading="loading"
          type="primary"
          @click="onSubmit"
          plain
        >
          添加员工
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import dayjs from "dayjs"; // 用于日期格式化

export default {
  data() {
    return {
      employee: {
        name: "", // 姓名
        age: null, // 年龄
        gender: "", // 性别
        entryDate: "", // 入职日期
        deptId: null, // 部门ID
      },
      departmentList: [], // 部门数据列表
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
        deptId: [{ required: true, message: "请选择部门", trigger: "change" }],
      },
    };
  },
  mounted() {
    this.fetchDepartments(); // 页面加载时获取部门数据
  },
  methods: {
    // 禁止选择未来日期
    disableFutureDates(date) {
      return date > new Date(); // 禁止选择今天之后的日期
    },

    // 模拟加载部门数据
    async fetchDepartments() {
      // 模拟异步加载
      setTimeout(() => {
        this.departmentList = [
          { id: 1, name: "开发部" },
          { id: 2, name: "教研部" },
          { id: 3, name: "教务部" },
          { id: 4, name: "设计部" },
          { id: 5, name: "运营部" },
        ];
      }, 500); // 模拟 500ms 延迟
    },

    // 提交表单
    async onSubmit() {
      this.$refs.employeeForm.validate(async (valid) => {
        if (valid) {
          this.loading = true; // 启用加载状态
          try {
            // 格式化日期为 ISO8601
            const formattedEmployee = {
              ...this.employee,
              entryDate: dayjs(this.employee.entryDate).toISOString(),
            };

            // 发送请求
            const response = await this.$axios.post(
              "/api/employees/add",
              formattedEmployee
            );

            if (response.data && response.data.code === 1) {
              this.$message.success("添加员工成功！");
              this.resetForm(); // 清空表单
            } else {
              this.$message.error(response.data.msg || "添加员工失败");
            }
          } catch (error) {
            console.error("添加员工失败:", error);
            this.$message.error("添加员工失败，请稍后重试");
          } finally {
            this.loading = false; // 关闭加载状态
          }
        } else {
          this.$message.warning("请完善表单信息后再提交");
        }
      });
    },

    // 重置表单
    resetForm() {
      this.employee = {
        name: "",
        age: null,
        gender: "",
        entryDate: "",
        deptId: null,
      };
      this.$refs.employeeForm.resetFields();
    },
  },
};
</script>

<style scoped>
.form-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f9f9f9;
  padding: 20px;
  box-sizing: border-box;
}

.el-form {
  width: 100%;
  max-width: 400px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.el-form-item {
  margin-bottom: 20px;
}

.el-button {
  width: 100%;
}
</style>
