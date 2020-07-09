<template>
    <a-layout id="components-layout-demo-custom-trigger">
        <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
            <div class="logo">
                <img src="../static/logo.png" alt="logo" style="width: 32px; height: 32px; display: inline">

            </div>
            <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">

                <a-menu-item key="main" v-on:click="directTo('main')">
                    <a-icon type="user" />
                    <span>项目主页</span>
                </a-menu-item>

                <a-sub-menu key="10">
                    <span slot="title">
                        <a-icon type="dribbble" spin/>
                        <span>豆瓣电影</span>
                    </span>
                    <a-menu-item key="film" v-on:click="directTo('film')">
                        Option 5
                    </a-menu-item>
                </a-sub-menu>

                <a-sub-menu key="20">
                    <span slot="title">
                        <a-icon type="reload" spin/>
                        <span>豆瓣音乐</span>
                    </span>
                    <a-menu-item key="music" v-on:click="directTo('music')">
                        Option 5
                    </a-menu-item>
                </a-sub-menu>

                <a-sub-menu key="30">
                    <span slot="title">
                        <a-icon type="compass" spin/>
                        <span>豆瓣图书</span>
                    </span>
                    <a-menu-item key="book" v-on:click="directTo('book')">
                        Option 5
                    </a-menu-item>
                </a-sub-menu>

                <a-sub-menu key="40">
                    <span slot="title">
                        <a-icon type="chrome" spin/>
                        <span>当当图书</span>
                    </span>
                    <a-menu-item key="dang" v-on:click="directTo('dang')">
                        Option 5
                    </a-menu-item>
                </a-sub-menu>

                <a-sub-menu key="50">
                    <span slot="title">
                        <a-icon type="sync" spin />
                        <span>bilibili评论</span>
                    </span>
                    <a-menu-item key="bilibili" v-on:click="directTo('bilibili')">
                        评论数据
                    </a-menu-item>
                </a-sub-menu>

            </a-menu>
        </a-layout-sider>
        <a-layout>
            <a-layout-header style="background: #fff; padding: 0">
                <a-icon
                        class="trigger"
                        :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                        @click="() => (collapsed = !collapsed)"
                />
            </a-layout-header>
            <a-layout-content
                    :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
            >
                <router-view></router-view>
            </a-layout-content>
        </a-layout>
        <div id="components-back-top-demo-custom">
            <a-back-top>
                <div class="ant-back-top-inner">
                    UP
                </div>
            </a-back-top>
        </div>
    </a-layout>

</template>

<script>
    import router from "../router";

    export default {
        name: "MainPage",

        data() {
            return {
                collapsed: false,
            };
        },
        methods: {
            directTo(target){ // 页面跳转
                console.log(target);
                router.push("/mainpage/"+target)
            }
        }
    }
</script>

<style>
    #components-layout-demo-custom-trigger .trigger {
        font-size: 18px;
        line-height: 64px;
        padding: 0 24px;
        cursor: pointer;
        transition: color 0.3s;
    }

    #components-layout-demo-custom-trigger .trigger:hover {
        color: #1890ff;
    }

    #components-layout-demo-custom-trigger .logo {
        height: 32px;
        background: rgba(255, 255, 255, 0.2);
        margin: 16px;
    }

    .logo{
        color: white;
        font-weight: bolder;
        font-size: 24px;
        height: 75px;
        overflow: hidden;
    }

    /*back to the top*/
    #components-back-top-demo-custom .ant-back-top {
        bottom: 100px;
    }
    #components-back-top-demo-custom .ant-back-top-inner {
        height: 40px;
        width: 40px;
        line-height: 40px;
        border-radius: 20px;
        background-color: #fb7299;
        color: #fff;
        text-align: center;
        font-size: 20px;
    }
</style>