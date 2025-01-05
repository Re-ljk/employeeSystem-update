<template>
  <div class="emp-management-container" align="center">
    <!--员工列表展示-->
    <el-table ref="multipleTable" :data="employeeList" tooltip-effect="dark" style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="序号" type="index" :sortable="true" width="150" align="center">
        <template v-slot:default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="150" align="center"></el-table-column>
      <el-table-column prop="gender" label="性别" width="150" align="center"></el-table-column>
      <el-table-column prop="entryDate" label="入职日期" width="150" align="center"></el-table-column>
      <el-table-column prop="deptName" label="部门名称" width="150" align="center"></el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template v-slot="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="editEmployee(scope.row)"
            style="margin-right: 5px;"
          ></el-button>
          <el-button
            type="primary"
            icon="el-icon-delete"
            size="mini"
            @click="deleteEmployee(scope.row.id)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <el-pagination
      background
      layout="prev, pager, next, total"
      :page-size="page.size"
      :current-page="page.current"
      :total="page.total"
      @current-change="handlePageChange"
    ></el-pagination>

    <div style="margin-top: 20px;">
      <el-button type="primary">添加员工</el-button>
      <el-button type="danger" @click="deleteSelected">删除选中</el-button>
    </div>
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
      employeeList: [], // 存储从后台获取的员工列表数据
      multipleSelection: [],
    };
  },
  mounted() {
    // 获取数据的方法
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
        this.page = response.data.data; // 后端返回的分页数据
        this.employeeList = this.page.records; // 员工列表
      } catch (error) {
        this.$message.error("查询列表失败");
      }
    },
    handlePageChange(page) {
      this.page.current = page;
      this.getEmployeeList();
    },
    deleteEmployee(id) {
      this.$confirm("确定删除该员工吗?", "删除员工", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          try {
            const response = await this.$axios.delete(`/api/employees/${id}`);
            if (response.data.success) {
              this.$message.success("删除成功");
              this.getEmployeeList(); // 重新加载员工列表
            } else {
              this.$message.error("删除失败");
            }
          } catch (error) {
            this.$message.error("删除失败，请稍后重试");
          }
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    deleteSelected() {
      // 删除选中逻辑
    },
    editEmployee() {
  // 编辑员工逻辑
}

  },
};
</script>

<style scoped>
.emp-management-container {
  margin-top: 50px;
}
</style>
