<template>
    <a-dropdown>
        <a-menu slot="overlay">
            <a-menu-item key="1"> <a-icon type="team" />管理用户</a-menu-item>
            <a-menu-item key="2"> <a-icon type="logout" />退出登录</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">{{ username }}<a-icon type="down" /> </a-button>
    </a-dropdown>
</template>

<script>
    import axios from "axios"
    import qs from "qs"
    export default {
        name: "UserInfo",
        data(){
            return{
                username: '还没登陆'
            }
        },
        mounted() {
            axios.post('http://localhost:8080/logic/user/currentUser', qs.stringify({}), {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response)
            }).catch(error => {
                console.log(error)
            })
        }
    }
</script>

<style scoped>

</style>