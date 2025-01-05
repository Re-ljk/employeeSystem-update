<template>
  <div class="emp-management-container">
    <!-- 顶部操作按钮 -->
    <div class="toolbar">
      <el-button type="primary" @click="addEmployee">添加员工</el-button>
      <el-button type="danger" @click="deleteSelected">删除选中</el-button>
    </div>

    <!-- 员工列表展示 -->
    <el-table
      ref="multipleTable"
      :data="employeeList"
      tooltip-effect="dark"
      border
      style="width: 80%; margin: 0 auto;"
      @selection-change="handleSelectionChange"
      row-key="id"
    >
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column label="序号" type="index" align="center" width="60"></el-table-column>
      <el-table-column prop="name" label="姓名" align="center"></el-table-column>
      <el-table-column prop="gender" label="性别" align="center"></el-table-column>
      <el-table-column prop="entryDate" label="入职日期" align="center"></el-table-column>
      <el-table-column prop="deptName" label="部门名称" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="editEmployee(scope.row)"
            style="margin-right: 10px;"
          ></el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="deleteEmployee(scope.row.id)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      background
      layout="sizes, prev, pager, next, total"
      :page-size="page.size"
      :current-page="page.current"
      :total="page.total"
      @current-change="handlePageChange"
      style="text-align: center; margin-top: 20px;"
    ></el-pagination>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      page: {
        records: [],
        total: 0,
        size: 5,
        current: 1,
      },
      employeeList: [],
      multipleSelection: [],
    };
  },
  mounted() {
    this.getEmployeeList();
  },
  methods: {
    async getEmployeeList() {
      try {
        const response = await this.$axios.get("/api/employees/page", {
          params: {
            currentPage: this.page.current,
            pageSize: this.page.size,
          },
        });
        this.page = response.data.data;
        this.employeeList = this.page.records;
      } catch (error) {
        this.$message.error("查询列表失败");
      }
    },
    handlePageChange(page) {
      this.page.current = page;
      this.getEmployeeList();
    },
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },
    addEmployee() {
      this.$message.info("点击了添加员工按钮");
    },
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
            .post("/api/employees/batchDelete", ids)
            .then((res) => {
              if (res.data.success) {
                this.$message.success("删除成功");
                this.getEmployeeList();
              } else {
                this.$message.error("删除失败");
              }
            })
            .catch(() => {
              this.$message.error("删除失败，请稍后重试");
            });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    deleteEmployee(id) {
      this.$confirm("确定删除该员工吗?", "删除员工", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios.delete(`/api/employees/${id}`).then((res) => {
            if (res.data.success) {
              this.$message.success("删除成功");
              this.getEmployeeList();
            } else {
              this.$message.error("删除失败");
            }
          });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    editEmployee(row) {
      this.$message.info(`编辑员工：${row.name}`);
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
