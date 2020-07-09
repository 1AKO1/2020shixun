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
    export default {
        name: "UserInfo",
        data(){
            return{
                username: '还没登陆'
            }
        }
    }
</script>

<style scoped>

</style>