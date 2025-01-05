import Vue from 'vue'
import Router from 'vue-router'
import EmployeeList from '@/views/employee/EmployeeList.vue'



Vue.use(Router);

const routes=[

    {
        path:'/',
        redirect:'/employee/list'  //配置项目启动时跳转的路径
    },
    {
        path:"/employee/list",
        component:EmployeeList
    }
    
]
const createRouter =()=> new Router({
    routes
}) 
const router = createRouter()
export function resetRouter(){
    const newRouter = createRouter()
    router.matcher =newRouter.matcher
}


export default router