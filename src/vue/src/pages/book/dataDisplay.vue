<template >
    <div >
<!--        第一排-->
        <a-row :gutter="16">
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="书籍总数"
                            :value="12000"
                            :value-style="{ color: '#cf1322' }"
                            style="margin-right: 50px"
                    >
                        <template #prefix>
                            <a-icon type="book" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="作者总数"
                            :value="3000"
                            class="demo-class"
                            :value-style="{ color: '#fb7299' }"
                    >
                        <template #prefix>
                            <a-icon type="edit" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="标签总数"
                            :value="1000"
                            class="demo-class"
                            :value-style="{ color: '#FFA500' }"
                    >
                        <template #prefix>
                            <a-icon type="tags" />
                        </template>

                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
<!--        第二排-->
        <a-row :gutter="[16,16]">
            <a-col :span="6">
                <a-card>
                    <a-statistic
                            title="最早的出版"
                            value="1900-01-01"
                            :value-style="{ color: '#00a1d6' }"
                            style="margin-right: 50px"
                    >
                        <template #prefix>
                            <a-icon type="monitor" />
                        </template>
                        <template #suffix>
                            <span>"鲁迅自编文集（共十九册）" - 鲁迅</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6">
                <a-card>
                    <a-statistic
                            title="最近的出版"
                            value="2020-09"
                            class="demo-class"
                            :value-style="{ color: '#0066CC' }"
                    >
                        <template #prefix>
                            <a-icon type="calendar" />
                        </template>
                        <template #suffix>
                            <span>"梦游人（全三册）" - [奥] 赫尔曼·布洛赫</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6">
                <a-card>
                    <a-statistic
                            title="评分为7以下的书籍"
                            :value="10000"
                            class="demo-class"
                            :value-style="{ color: '#CF1322' }"
                    >
                        <template #prefix>
                            <a-icon type="frown"/>
                        </template>
                        <template #suffix>
                            <span>"父亲的力量" - 郭涛 评分：2.4（4241）</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6">
                <a-card>
                    <a-statistic
                            title="评分为9以上的书籍"
                            :value="4000"
                            class="demo-class"
                            :value-style="{ color: '#3F8600' }"
                    >
                        <template #prefix>
                            <a-icon type="like" />
                        </template>
                        <template #suffix>
                            <span>"红楼梦" - [清] 曹雪芹 评分：9.6（292790）</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="作者占比" >
                    <EchartsAuthorBook/>
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
    import EchartsAuthorBook from "../../components/book/EchartsAuthorBook";
    import EchartsProssTime from "../../components/dang/EchartsProssTime";
    import EchartsAvgClass from "../../components/dang/EchartsAvgClass";
    import EchartCommentPr from "../../components/dang/EchartCommentPr";
    import EchartyearBook from "../../components/dang/EchartyearBook";
    export default {
        name: "dataDisplay",
        components: {EchartsAuthorBook, EchartyearBook, EchartCommentPr, EchartsAvgClass, EchartsProssTime}
    }
</script>

<style scoped>

</style>