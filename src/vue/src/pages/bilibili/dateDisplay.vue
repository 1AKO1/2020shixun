<template>
    <div>
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="评论分析"
                sub-title="かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜"
        />
        <br />
        <a-row :gutter="16" style="margin-bottom: 10px">
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="爬取数据总量"
                            value="48235"
                            :value-style="{ color: '#3f8600' }"
                            style="margin-right: 50px"
                    >
                        <template #prefix>
                            <a-icon type="arrow-up" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>

            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="字段数量"
                            :value="18"
                            class="demo-class"
                            :value-style="{ color: '#66ccff' }"
                    >
                        <template #prefix>
                            <a-icon type="tags" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="番剧评分"
                            :value="9.9"
                            class="demo-class"
                            :value-style="{ color: '#fb7299' }"
                    >
                        <template #prefix>
                            <a-icon type="smile" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
<!--        第一层-->

        <a-row :gutter="16" style="margin-bottom: 10px">
            <a-col :span="8">
                <a-card title="评论词云" bordered hoverable>
                    <img src="../../static/kaguya.jpg" alt="" style="width: 400px; height: 400px; margin: 0 50px">
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card title="用户评论情绪" bordered hoverable>
                    <EchartsGroupBySentiment />
                </a-card>
            </a-col>
            <a-col :span="8">
                <ScorePredict />
            </a-col>

        </a-row>

        <a-row :gutter="16" style="margin-bottom: 10px">
            <a-col :span="8">
                <a-card title="评论各分段占比" bordered hoverable>
                    <EchartsScore />
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card title="用户观看进度" bordered hoverable>
                    <EchartsProgress />
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card title="用户付费情况" bordered hoverable>
                    <EchartsVip />
                </a-card>
            </a-col>

        </a-row>
<!--        第三层-->

        <a-row :gutter="16" style="margin-bottom: 10px">
            <a-col :span="24">
                <a-card title="每日评论数" bordered hoverable>
                    <EchartsCommentRise />
                </a-card>
            </a-col>
        </a-row>

<!--        第四层-->
        <a-row :gutter="16" style="margin-bottom: 10px">
            <a-col :span="24">
                <a-card title="动漫评论高频词TOP20" bordered hoverable>
                    <EchartsWordFrequency />
                </a-card>
            </a-col>
        </a-row>

        <a-row :gutter="16" style="margin-bottom: 10px">
            <a-col :span="24">
                <a-card title="用户评论情绪与时间关系" bordered hoverable>
                    <EchartsSentiment />
                </a-card>
            </a-col>
        </a-row>

    </div>
</template>

<script>
    import ScorePredict from "../../components/bilibili/ScorePredict";

    import EchartsScore from "../../components/bilibili/EchartsScore";
    import EchartsProgress from "../../components/bilibili/EchartsProgress";
    import EchartsVip from "../../components/bilibili/EchartsVip";
    import EchartsGroupBySentiment from "../../components/bilibili/EchartsGroupBySentiment";

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
            EchartsSentiment,
            EchartsGroupBySentiment
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
        margin: 10px 5px;
        width: 500px;
    }

</style>