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
        this.page = response.data.data; // 假设返回的data是包含分页信息的对象
        this.employeeList = this.page.records;
      } catch (error) {
        this.$message.error("查询列表失败");
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

    // 添加员工
    addEmployee() {
      this.$router.push({ name: "addEmployee" }); // 假设你有一个添加员工的页面
    },

// 删除选中员工（批量删除）
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
      // 获取选中员工的 ID 列表
      const ids = this.multipleSelection.map((item) => item.id);

      // 调用批量删除接口
      this.$axios
        .delete("/api/employees/delByIds", { data: ids }) // DELETE 请求传递数据
        .then((res) => {
          // 打印返回数据，方便调试
          console.log("批量删除接口返回:", res.data);

          // 根据后端返回的字段结构进行判断
          if (res.data && res.data.code === 1) {
            this.$message.success(res.data.data || "批量删除成功"); // 后端返回的 success 信息
            this.getEmployeeList(); // 刷新员工列表
          } else {
            this.$message.error(res.data.msg || "批量删除失败"); // 返回的错误信息
          }
        })
        .catch((error) => {
          // 捕获接口调用错误
          console.error("批量删除失败:", error);
          this.$message.error("批量删除失败，请稍后重试");
        });
    })
    .catch(() => {
      // 用户取消操作
      this.$message.info("已取消删除");
    });
}
,

// 删除单个员工
deleteEmployee(id) {
  this.$confirm("确定删除该员工吗?", "删除员工", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      // 调用删除接口
      this.$axios
        .delete(`/api/employees/delete/${id}`)
        .then((res) => {
          // 打印返回数据，方便调试
          console.log("删除接口返回:", res.data);

          // 根据后端返回的字段结构进行判断
          if (res.data && res.data.code === 1) {
            this.$message.success(res.data.data || "删除成功"); // 后端返回的 success 信息
            this.getEmployeeList(); // 刷新员工列表
          } else {
            this.$message.error(res.data.msg || "删除失败"); // 返回的错误信息
          }
        })
        .catch((error) => {
          // 捕获接口调用错误
          console.error("删除失败:", error);
          this.$message.error("删除失败，请稍后重试");
        });
    })
    .catch(() => {
      // 用户取消操作
      this.$message.info("已取消删除");
    });
}

,

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
