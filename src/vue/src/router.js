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
import FilmAnalysis from "./pages/film/dataDisplay"
//music 刘博页面
import MusicIndex from "./pages/music/index"
import MusicAnalysis from "./pages/music/dataDisplay"
//book 廖总页面
import BookIndex from "./pages/book/index"
import BookAnalysis from "./pages/book/dataDisplay"
//dang 薛佬页面
import DangIndex from "./pages/dang/dangSearch"
import DangAnalysis from "./pages/dang/dataDisplay"
//bilibili 李弟弟页面
import BiLiBiLi from "./pages/bilibili/index"
import BiLiBiLiSearch from "./pages/bilibili/dataSearch"
import BiLiBiLiAnalysis from "./pages/bilibili/dateDisplay"

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
            {
                path: 'filmAnalysis',
                name: 'filmAnalysis',
                component: FilmAnalysis
            },
            //music 刘博路由
            {
                path: 'music',
                name: 'music',
                component: MusicIndex
            },
            {
                path: 'musicAnalysis',
                name: 'musicAnalysis',
                component: MusicAnalysis
            },
            //book  廖佬路由
            {
                path: 'book',
                name: 'book',
                component: BookIndex
            },            {
                path: 'bookAnalysis',
                name: 'bookAnalysis',
                component: BookAnalysis
            },
            //dang  薛佬路由
            {
                path: 'dang',
                name: 'dang',
                component: DangIndex
            },
            {
                path: 'dangAnalysis',
                name: 'dangAnalysis',
                component: DangAnalysis
            },
            //bilibili 李弟弟路由
            {
                path: 'bilibili',
                name: 'bilibili',
                component: BiLiBiLi
            },{
                path: 'bilibiliSearch',
                name: 'bilibiliSearch',
                component: BiLiBiLiSearch
            },{
                path: "bilibiliAnalysis",
                name: 'bilibiliAnalysis',
                component: BiLiBiLiAnalysis
            }
        ]
    }
]

const router = new VueRouter({
    routes
});
export default router;