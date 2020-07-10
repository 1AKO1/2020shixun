<template>
    <div>
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="评论分析"
                sub-title="かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜"
        />
        <div class="easyBox">
            <div class="easyItem">
                <EchartsScore />
            </div>
            <div class="easyItem">
                <EchartsProgress />
            </div>
            <div class="easyItem">
                <EchartsVip />
            </div>
        </div>

    </div>
</template>

<script>
    import EchartsScore from "../../components/bilibili/EchartsScore";
    import EchartsProgress from "../../components/bilibili/EchartsProgress";
    import EchartsVip from "../../components/bilibili/EchartsVip";
    export default {
        name: "dateDisplay",
        components:{
            EchartsScore,
            EchartsProgress,
            EchartsVip
        }
    }
</script>

<style scoped>
    .easyBox{
        min-width: 1200px;
        margin: 10px 0;
        display: flex;
        justify-content: space-between;
    }
    .easyItem{
        border: 1px solid #001529;
    }
</style>