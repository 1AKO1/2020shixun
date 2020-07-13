<template>
    <div>
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="评论分析"
                sub-title="かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜"
        />
<!--        第一层-->
        <div class="easyBox">
            <div class="easyItem">
                <img src="../../static/kaguya.jpg" alt="" style="width: 400px; height: 400px">
            </div>
            <div class="easyItem">
                <ScorePredict />
            </div>
        </div>
<!--        第二层-->
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
<!--        第三层-->
        <div class="easyBox">
            <div style="width: 100%">
                <EchartsCommentRise />
            </div>
        </div>
<!--        第四层-->
        <div class="easyBox">
            <div style="width: 100%">
                <EchartsWordFrequency />
            </div>
        </div>

        <div class="easyBox">
            <div style="width: 100%">
                <EchartsSentiment />
            </div>
        </div>

    </div>
</template>

<script>
    import ScorePredict from "../../components/bilibili/ScorePredict";

    import EchartsScore from "../../components/bilibili/EchartsScore";
    import EchartsProgress from "../../components/bilibili/EchartsProgress";
    import EchartsVip from "../../components/bilibili/EchartsVip";

    import EchartsCommentRise from "../../components/bilibili/EchartsCommentRise";
    import EchartsWordFrequency from "../../components/bilibili/EchartsWordFrequency";
    import EchartsSentiment from "../../components/bilibili/EchartsSentiment";
    export default {
        name: "dateDisplay",
        components:{
            EchartsScore,
            EchartsProgress,
            EchartsVip,
            EchartsCommentRise,
            EchartsWordFrequency,
            ScorePredict,
            EchartsSentiment
        }
    }
</script>

<style scoped>
    .easyBox{
        margin: 10px 0;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        width: 100%;
    }
    .easyItem{
        border: 1px solid #001529;
        margin: 5px;
    }
</style>