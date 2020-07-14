<template>
    <div>
<!--        组件-->
        <a-layout id="components-layout-demo-custom-trigger" :style="contentStyleObj">

            <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
                <div class="logo">
                    高数
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
                            数据查询
                        </a-menu-item>
                        <a-menu-item key="filmAnalysis" v-on:click="directTo('filmAnalysis')">
                            数据分析
                        </a-menu-item>
                    </a-sub-menu>

                    <a-sub-menu key="20">
                    <span slot="title">
                        <a-icon type="reload" spin/>
                        <span>豆瓣音乐</span>
                    </span>
                        <a-menu-item key="music" v-on:click="directTo('music')">
                            数据查询
                        </a-menu-item>
                        <a-menu-item key="musicAnalysis" v-on:click="directTo('musicAnalysis')">
                            数据分析
                        </a-menu-item>
                    </a-sub-menu>

                    <a-sub-menu key="30">
                    <span slot="title">
                        <a-icon type="compass" spin/>
                        <span>豆瓣图书</span>
                    </span>
                        <a-menu-item key="book" v-on:click="directTo('book')">
                            数据查询
                        </a-menu-item>
                        <a-menu-item key="bookAnalysis" v-on:click="directTo('bookAnalysis')">
                            数据分析
                        </a-menu-item>
                    </a-sub-menu>

                    <a-sub-menu key="40">
                    <span slot="title">
                        <a-icon type="chrome" spin/>
                        <span>当当图书</span>
                    </span>
                        <a-menu-item key="dang" v-on:click="directTo('dang')">
                            数据查询
                        </a-menu-item>
                        <a-menu-item key="dangAnalysis" v-on:click="directTo('dangAnalysis')">
                            数据分析
                        </a-menu-item>
                    </a-sub-menu>

                    <a-sub-menu key="50">
                    <span slot="title">
                        <a-icon type="sync" spin />
                        <span>bilibili评论</span>
                    </span>
                        <a-menu-item key="bilibili" v-on:click="directTo('bilibili')">
                            评论列表
                        </a-menu-item>
                        <a-menu-item key="bilibiliSearch" v-on:click="directTo('bilibiliSearch')">
                            数据查询
                        </a-menu-item>
                        <a-menu-item key="bilibiliAnalysis" v-on:click="directTo('bilibiliAnalysis')">
                            数据分析
                        </a-menu-item>
                    </a-sub-menu>

                    <a-menu-item key="usermange" v-on:click="directTo('usermanage')">
                        <a-icon type="team" />
                        <span>用户管理</span>
                    </a-menu-item>
                </a-menu>
            </a-layout-sider>


            <a-layout>
                <a-layout-header style="background: #fff; padding: 0">
                    <a-icon
                            class="trigger"
                            :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                            @click="() => (collapsed = !collapsed)"
                    />
                    <div style="position: absolute; top: 0; right: 20px">
                        <UserInfo class="user-info" v-bind:uid="this.$route.params.uid"/>
                    </div>
                </a-layout-header>

                <a-layout-content
                        :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
                >
                    <router-view></router-view>
                </a-layout-content>
            </a-layout>

<!--            组件-->
            <div id="components-back-top-demo-custom">
                <a-back-top>
                    <div style="width: 175px; height: 175px; overflow: hidden ;">
                        <img src="../static/top_rocket.png" id="top" alt="">
                    </div>

<!--                    <div class="ant-back-top-inner">-->
<!--                        UP-->
<!--                    </div>-->
                </a-back-top>
            </div>
        </a-layout>
    </div>


</template>

<script>
    import router from "../router";
    import UserInfo from "../components/UserInfo";

    export default {
        name: "MainPage",
        components: {UserInfo},
        data() {
            return {
                collapsed: false,
                contentStyleObj:{
                    minHeight:''
                }
            };
        },
        methods: {
            directTo(target){ // 页面跳转
                console.log(target);
                router.push("/mainpage/"+target)
            },
            getHeight(){
                this.contentStyleObj.minHeight=window.innerHeight+'px';
            }
        },

        created(){
            window.addEventListener('resize', this.getHeight);
            this.getHeight()
        },
        destroyed(){
            window.removeEventListener('resize', this.getHeight)
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
        margin: 16px;
    }

    .logo{
        color: white;
        font-weight: bolder;
        font-size: 24px;
        height: 75px;
        overflow: hidden;
        text-align: center;
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
    .user-info{
        right: 10px;
    }
    img#top{
       transform: translate(-600px, -10px);
    }
    img#top:hover{
        animation: top_rocket 0.5s steps(4) infinite;
        /*animation: top_rocket 0.1s;*/
        /*animation-iteration-count: infinite;*/
    }

    @keyframes top_rocket
    {
        from{
            transform: translate(-600px, 0px);
        }
        to{
            transform: translate(0px, -25px);
        }
    }
</style>