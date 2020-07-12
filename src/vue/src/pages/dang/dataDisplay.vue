<template>
    <div>
        <a-card title="图书分类">
            <EchartClasses/>
        </a-card>
<!--        <a-row>-->
<!--            <EchartClasses/>-->
<!--        </a-row>-->



    </div>




</template>

<script>
    import EchartClasses from "../../components/dang/EchartClasses";
    export default {
        name: "dataDisplay",
        components: {EchartClasses}
    }
</script>

<style scoped>

</style>