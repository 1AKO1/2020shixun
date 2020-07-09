<template>
    <a-dropdown>
        <a-menu slot="overlay" @click="handleMenuClick">
            <a-menu-item key="1"> <a-icon type="user" />1st menu item </a-menu-item>
            <a-menu-item key="2"> <a-icon type="user" />2nd menu item </a-menu-item>
            <a-menu-item key="3"> <a-icon type="user" />3rd item </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> Button <a-icon type="down" /> </a-button>
    </a-dropdown>
</template>

<script>
    export default {
        name: "UserInfo"
    }
</script>

<style scoped>

</style>