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
    import axios from "axios"
    import qs from "qs"
    import router from "../router"
    export default {
        name: "UserInfo",
        props: ['uid'],
        data(){
            return{
                username: 'userName'
            }
        },
        mounted() {
            console.log(this.uid)
            axios.post('http://localhost:8080/logic/user/currentUser', qs.stringify({
                uid: this.uid
            }), {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response)
                const data = response.data;
                if (data.code === 1000){
                    this.username = data.data.nickName
                }else {
                    this.$message.error("当前未登录，请重新登录")
                    this.$router.push("/")
                }
            }).catch(error => {
                console.log(error)
            })
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