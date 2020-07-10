import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import Login from './pages/Login'
// eslint-disable-next-line no-unused-vars
import Register from './pages/Register'
// eslint-disable-next-line no-unused-vars
import MainPage from "./pages/MainPage";
import Main from "./pages/Main";

//film 源哥页面
import FilmIndex from "./pages/film/index"
//music 刘博页面
import MusicIndex from "./pages/music/index"
//book 廖总页面
import BookIndex from "./pages/book/index"
//dang 薛佬页面
import DangIndex from "./pages/dang/index"
//bilibili 李弟弟页面
import BiLiBiLi from "./pages/bilibili/index"

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [
    {
        path:"/",
        name: "login",
        component: Login
    },
    {
        path: "/register",
        name: "Register",
        component: Register
    },{
        path: "/mainpage",
        name: "MainPage",
        component: MainPage,
        children: [
            {
                path: "main",//以“/”开头的嵌套路径会被当作根路径，所以子路由上不用加“/”;在生成路由时，主路由上的path会被自动添加到子路由之前，所以子路由上的path不用在重新声明主路由上的path了。
                name: 'Main',
                component: Main
            },
            //film  源哥路由
            {
                path: 'film',
                name: 'film',
                component: FilmIndex
            },
            //music 刘博路由
            {
                path: 'music',
                name: 'music',
                component: MusicIndex
            },
            //book  廖佬路由
            {
                path: 'book',
                name: 'book',
                component: BookIndex
            },
            //dang  薛佬路由
            {
                path: 'dang',
                name: 'dang',
                component: DangIndex
            },
            //bilibili 李弟弟路由
            {
                path: 'bilibili',
                name: 'bilibili',
                component: BiLiBiLi
            },
        ]
    }
]

const router = new VueRouter({
    routes
});
export default router;