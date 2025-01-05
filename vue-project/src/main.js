import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'

Vue.use(ElementUI);  // 使用 Element UI
Vue.config.productionTip = false

// 设置前端跨域
axios.defaults.baseURL = 'http://localhost:8080'

axios.defaults.timeout = 5000

// 将 axios 添加到 Vue 原型上，方便在组件中使用
Vue.prototype.$axios = axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
