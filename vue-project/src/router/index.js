import Vue from 'vue';
import Router from 'vue-router';
import EmployeeList from '@/views/employee/EmployeeList.vue';
import EditEmployee from '@/views/employee/EditEmployee.vue';
import AddEmployee from '@/views/employee/AddEmployee.vue';
Vue.use(Router);

const routes = [
  {
    path: '/',
    redirect: '/employee/list', // 配置项目启动时的跳转路径
  },
  {
    path: '/employee/list',
    component: EmployeeList,
  },
  {
    path: '/employee/add',
    component: AddEmployee,
  },
  {
    path: '/employee/edit/:id',
    component: EditEmployee,
    name: 'editEmployee', // 修正为字符串名称
    props: true, // 启用 props 传参
  },
];

const createRouter = () =>
  new Router({
    mode: 'history', // 使用 HTML5 History 模式，去掉 URL 中的 #
    routes,
  });

const router = createRouter();

// 重置路由的方法
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // 重新设置路由的 matcher
}

export default router;
