<template>
    <div>
        <a-row :gutter="16">
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="爬取总数"
                            :value="42061"
                            :value-style="{ color: '#cf1322' }"
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
                            title="平均推荐百分比"
                            :value="98.37"
                            :precision="2"
                            suffix="%"
                            class="demo-class"
                            :value-style="{ color: '#3f8600' }"
                    >
                        <template #prefix>
                            <a-icon type="arrow-down" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="平均评论数"
                            :value="400000"
                            class="demo-class"
                            :value-style="{ color: '#3f8600' }"
                    >
                        <template #prefix>
                            <a-icon type="minus" />
                        </template>
                        <template #suffix>
                            <span>/条</span>
                        </template>

                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="TOP图书分类" >
                    <EchartClasses/>
                </a-card>
            </a-col>
            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="TOP图书出版信息">
                    <EchartsProssTime/>
                </a-card>
            </a-col>
        </a-row>
        <a-row :gutter="[16,16]">
            <a-col :span="8" style="width: 50%">
                <a-card hoverable title="TOP分类平均价格">
                    <EchartsAvgClass/>
                </a-card>
            </a-col>
            <a-col :span="8" style="width: 50%">
                <a-card hoverable title="评论与推荐">
                    <EchartCommentPr/>
                </a-card>
            </a-col>

        </a-row>
        <a-row>
            <a-col :span="8" style="width: 50%">
                <a-card hoverable title="4年TOP图书统计">
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