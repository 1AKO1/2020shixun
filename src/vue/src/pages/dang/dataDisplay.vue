<template >
    <div >
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
                            :value="102859"
                            class="demo-class"
                            :value-style="{ color: '#FFA500' }"
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
            <a-col :span="6">
                <a-card hoverable>
                    <a-statistic
                            title="一级分类书目"
                            :value="41"
                            :value-style="{ color: '#000080' }"
                            style="margin-right: 50px"
                    >
                        <template #prefix>
                            <a-icon type="tag" />
                        </template>
                        <template #suffix>
                            <span>/种</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6">
                <a-card hoverable>
                    <a-statistic
                            title="二级分类书目"
                            :value="466"
                            class="demo-class"
                            :value-style="{ color: '#0000CD' }"
                    >
                        <template #prefix>
                            <a-icon type="tags" />
                        </template>
                        <template #suffix>
                            <span>/种</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6">
                <a-card hoverable>
                    <a-statistic
                            title="最低售价"
                            :value="3.2"
                            :precision="2"
                            suffix="￥"
                            class="demo-class"
                            :value-style="{ color: '#3f8600' }"
                    >
                        <template #prefix>
                            <a-icon type="rocket"  :rotate="180"  />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6">
                <a-card hoverable>
                    <a-statistic
                            title="最高售价"
                            :value="990.4"
                            :precision="2"
                            suffix="￥"
                            class="demo-class"
                            :value-style="{ color: '#cf1322' }"
                    >
                        <template #prefix>
                            <a-icon type="rocket" />
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
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="4年TOP图书统计">
                    <EchartyearBook/>
                </a-card>
            </a-col>

            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="价格区间占比">
                    <Echartprice/>
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
    import Echartprice from "../../components/dang/Echartprice";
    export default {
        name: "dataDisplay",
        components: {Echartprice, EchartyearBook, EchartCommentPr, EchartsAvgClass, EchartsProssTime, EchartClasses}
    }
</script>

<style scoped>

</style>