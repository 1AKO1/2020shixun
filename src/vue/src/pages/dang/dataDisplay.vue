<template>
    <div>
        <a-row>
            <a-col :span="12" style="width: 50%">
                <a-card title="TOP图书分类" >
                    <EchartClasses/>
                </a-card>
            </a-col>
            <a-col :span="12" style="width: 50%">
                <a-card title="TOP图书出版信息">
                    <EchartsProssTime/>
                </a-card>
            </a-col>
        </a-row>
        <a-row>
            <a-col :span="8" style="width: 50%">
                <a-card title="TOP分类平均价格">
                    <EchartsAvgClass/>
                </a-card>
            </a-col>
            <a-col :span="8" style="width: 50%">
                <a-card title="评论与推荐">
                    <EchartCommentPr/>
                </a-card>
            </a-col>
        </a-row>
        <a-row>
            <a-col :span="6" style="width: 50%">
                <a-card title="4年TOP图书统计">
                    <EchartyearBook/>
                </a-card>
            </a-col>
        </a-row>
    </div>
</template>


<script>
    import EchartClasses from "../../components/dang/EchartClasses";
    import EchartsProssTime from "../../components/dang/EchartsProssTime";
    import EchartsAvgClass from "../../components/dang/EchartsAvgClass";
    import EchartCommentPr from "../../components/dang/EchartCommentPr";
    import EchartyearBook from "../../components/dang/EchartyearBook";
    export default {
        name: "dataDisplay",
        components: {EchartyearBook, EchartCommentPr, EchartsAvgClass, EchartsProssTime, EchartClasses}
    }
</script>

<style scoped>

</style>