import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import Login from './pages/Login'
// eslint-disable-next-line no-unused-vars
import Register from './pages/Register'
// eslint-disable-next-line no-unused-vars
import MainPage from "./pages/MainPage";

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [
    {
        path:"/",
        component: Login
    },
    {
        path: "/register",
        component: Register
    },{
        path: "/mainpage",
        component: MainPage
    }
]

const router = new VueRouter({
    routes
});
export default router;