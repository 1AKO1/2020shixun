import Vue from 'vue'
import App from './App.vue'
// eslint-disable-next-line no-unused-vars
import VueRouter from 'vue-router'
import router from "./router"
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'


Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(Antd)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
