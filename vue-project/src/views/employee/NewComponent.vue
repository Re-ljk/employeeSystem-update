<template>
	<div class="emp-management-container" align="center">
	  <!-- 员工列表展示 -->
	  <el-table
		ref="multipleTable"
		:data="employeeList"
		tooltip-effect="dark"
		style="width: 100%"
		@selection-change="handleSelectionChange"
		row-key="id"
	  >
		<el-table-column type="selection" width="55"></el-table-column>
		<el-table-column label="序号" type="index" :sortable="true" width="150" align="center">
		  <template v-slot="scope">{{ scope.row.id }}</template>
		</el-table-column>
		<el-table-column prop="name" label="姓名" width="150" align="center"></el-table-column>
		<el-table-column prop="gender" label="性别" width="150" align="center"></el-table-column>
		<el-table-column prop="entryDate" label="入职日期" width="150" align="center"></el-table-column>
		<el-table-column prop="deptName" label="部门名称" width="150" align="center"></el-table-column>
		<el-table-column label="操作" width="150" align="center">
		  <template v-slot="scope">
			<el-button type="primary" icon="el-icon-edit"></el-button>
			<!-- 使用 scope.row.id -->
			<el-button type="danger" icon="el-icon-delete" @click="deleteEmployee(scope.row.id)"></el-button>
		  </template>
		</el-table-column>
	  </el-table>
  
	  <el-button type="primary" @click="addEmployee">添加员工</el-button>
	  <el-button type="danger" @click="deleteSelectedEmployees">删除选中</el-button>
	</div>
  </template>
  
  <script setup lang="ts">
  import { ref, defineProps, defineEmits } from 'vue'
  import { ElMessage } from 'element-plus'  // 导入 ElMessage 用于显示提示
  
  // 通过 defineProps 获取父组件传递的 props
  const props = defineProps<{
	employeeList: any[];
	handleselectionchange: (selection: any[]) => void;
	deleteEmployee: (id: string) => void;
	addEmployee: () => void;
  }>()
  
  // 定义 emit 用于向父组件发送删除选中项的事件
  const emit = defineEmits<{
	(event: 'delete-selected-employees', selectedIds: string[]): void;
  }>()
  
  // 处理选择变化，获取选中的员工
  const selectedEmployees = ref<any[]>([])
  
  const handleSelectionChange = (selection: any[]) => {
	selectedEmployees.value = selection
	props.handleselectionchange(selection)
  }
  
  // 删除选中的员工
  const deleteSelectedEmployees = () => {
	if (selectedEmployees.value.length === 0) {
	  ElMessage.error("请选择要删除的员工")  // 使用 ElMessage 显示提示
	  return
	}
	const selectedIds = selectedEmployees.value.map((employee: any) => employee.id)
	emit('delete-selected-employees', selectedIds)
  }
  </script>
  
  <style scoped>
  .emp-management-container {
	margin-top: 100px;
  }
  </style>
  