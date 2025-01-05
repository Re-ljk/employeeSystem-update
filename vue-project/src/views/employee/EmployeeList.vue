<template>
  <div class="emp-management-container">
    <!-- 顶部操作按钮 -->
    <div class="toolbar">
      <el-button type="primary" @click="gotoAdd">添加员工</el-button>
      <el-button type="danger" @click="deleteSelected">删除选中</el-button>
    </div>

    <!-- 员工列表展示 -->
    <el-table ref="multipleTable" :data="employeeList" tooltip-effect="dark" border style="width: 80%; margin: 0 auto;"
      @selection-change="handleSelectionChange" row-key="id">
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column label="序号" type="index" align="center" width="60"></el-table-column>
      <el-table-column prop="name" label="姓名" align="center"></el-table-column>
      <el-table-column prop="gender" label="性别" align="center"></el-table-column>
      <el-table-column prop="entryDate" label="入职日期" align="center"></el-table-column>
      <el-table-column prop="deptName" label="部门名称" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="150">
        <template #default="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="editEmployee(scope.row)"
            style="margin-right: 10px;"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteEmployee(scope.row.id)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination background layout="sizes, prev, pager, next, total" :page-size="page.size"
      :current-page="page.current" :total="page.total" @current-change="handlePageChange"
      style="text-align: center; margin-top: 20px;"></el-pagination>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      page: {
        records: [], // 后端返回的分页记录
        total: 0, // 数据总数
        size: 5, // 每页大小
        current: 1, // 当前页
      },
      employeeList: [], // 员工数据列表
      multipleSelection: [], // 批量选中的员工
    };
  },
  mounted() {
    this.getEmployeeList();
  },
  methods: {
    // 获取员工列表（分页）
    async getEmployeeList() {
      try {
        const response = await this.$axios.get("/api/employees/page", {
          params: {
            currentPage: this.page.current,
            pageSize: this.page.size,
          },
        });

        // 判断接口返回的数据是否正常
        if (response.data && response.data.code === 1) {
          const pageData = response.data.data;
          this.page = pageData;
          this.employeeList = pageData.records.map((employee) => ({
            ...employee,
            deptName: employee.dept ? employee.dept.name : "无部门", // 提取部门名称
            entryDate: employee.entryDate
              ? employee.entryDate.slice(0, 10) // 格式化入职日期
              : "未知",
          }));
        } else {
          this.$message.error(response.data.msg || "查询列表失败");
        }
      } catch (error) {
        console.error("查询列表失败:", error);
        this.$message.error("查询列表失败，请稍后重试");
      }
    },

    // 分页切换
    handlePageChange(page) {
      this.page.current = page;
      this.getEmployeeList();
    },

    // 选中员工变化
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },

    // 跳转到添加员工页面
    gotoAdd() {
      this.$router.push("/employee/add");
    },

    // 批量删除选中员工
    deleteSelected() {
      if (this.multipleSelection.length === 0) {
        this.$message.warning("请选择要删除的员工");
        return;
      }
      this.$confirm("确定删除选中的员工吗?", "批量删除", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          const ids = this.multipleSelection.map((item) => item.id);

          this.$axios
            .delete("/api/employees/delByIds", { data: ids })
            .then((res) => {
              if (res.data && res.data.code === 1) {
                this.$message.success("批量删除成功");
                this.getEmployeeList();
              } else {
                this.$message.error(res.data.msg || "批量删除失败");
              }
            })
            .catch((error) => {
              console.error("批量删除失败:", error);
              this.$message.error("批量删除失败，请稍后重试");
            });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },

    // 删除单个员工
    deleteEmployee(id) {
      this.$confirm("确定删除该员工吗?", "删除员工", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .delete(`/api/employees/delete/${id}`)
            .then((res) => {
              if (res.data && res.data.code === 1) {
                this.$message.success("删除成功");
                this.getEmployeeList();
              } else {
                this.$message.error(res.data.msg || "删除失败");
              }
            })
            .catch((error) => {
              console.error("删除失败:", error);
              this.$message.error("删除失败，请稍后重试");
            });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },

    // 编辑员工
    editEmployee(row) {
      this.$router.push({ name: "editEmployee", params: { id: row.id } });
    },
  },
};
</script>

<style scoped>
.emp-management-container {
  margin: 30px auto;
  text-align: center;
}

.toolbar {
  margin-bottom: 20px;
}

.toolbar .el-button {
  margin: 0 10px;
}

.el-pagination {
  margin-top: 20px;
}
</style>
