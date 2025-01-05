import Vue from 'vue';
import Router from 'vue-router';
import EmployeeList from '@/views/employee/EmployeeList.vue';
import EditEmployee from '@/views/employee/EditEmployee.vue';
import AddEmployee from '@/views/employee/AddEmployee.vue';

// 解决重复点击路由报错的问题
const routerPush = Router.prototype.push;
Router.prototype.push = function (location) {
  return routerPush.call(this, location).catch(err => err); // 修正箭头函数错误
};

// 使用 Vue Router
Vue.use(Router);

// 路由配置
const routes = [
  {
    path: '/',
    redirect: '/employee/list', // 配置项目启动时的跳转路径
  },
  {
    path: '/employee',
    redirect: '/employee/list', // 对 `/employee` 路径重定向到员工列表
  },
  {
    path: '/employee/list',
    component: EmployeeList,
    meta: { title: '员工列表' }, // 可选：meta 属性，用于扩展
  },
  {
    path: '/employee/add',
    component: AddEmployee,
    meta: { title: '添加员工' },
  },
  {
    path: '/employee/edit/:id',
    component: EditEmployee,
    name: 'editEmployee', // 路由名称，便于编程式导航
    props: true, // 启用 props 传参，允许从 URL 中解构参数
    meta: { title: '编辑员工' },
  },
];

// 创建路由实例
const createRouter = () =>
  new Router({
    mode: 'history', // 使用 HTML5 History 模式，去掉 URL 中的 #
    routes,
  });

const router = createRouter();

/**
 * 重置路由的方法：
 * 用于动态更新路由，比如权限控制时需要清除或重新加载某些路由。
 */
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // 重新设置路由的 matcher
}

export default router;
