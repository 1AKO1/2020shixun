<template>
    <a-dropdown>
        <a-menu slot="overlay">
            <a-menu-item key="1" v-on:click="directTo('/mainpage/usermanage')"> <a-icon type="team" />用户管理</a-menu-item>
            <a-menu-item key="2" v-on:click="directTo('/')"> <a-icon type="logout" />退出登录</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">{{ username }}<a-icon type="down" /> </a-button>
    </a-dropdown>
</template>

<script>
    // import axios from "axios"
    // import qs from "qs"
    import router from "../router"
    export default {
        name: "UserInfo",
        data(){
            return{
                username: '还没登陆'
            }
        },
        mounted() {
            // axios.post('http://localhost:8080/logic/user/currentUser', qs.stringify({}), {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
            // .then(response => {
            //     console.log(response)
            // }).catch(error => {
            //     console.log(error)
            // })
        },
        methods:{
            directTo(target){
                router.push(target)
            }
        }
    }
</script>

<style scoped>

</style>