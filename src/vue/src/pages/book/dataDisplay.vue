<template >
    <div >
<!--        第一排-->
        <a-row :gutter="16">
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="书籍总数"
                            :value="16062"
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
                            :value="11189"
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
                            :value="556"
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
                            :value="1980"
                            class="demo-class"
                            :value-style="{ color: '#cf1322' }"
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
                            :value="2111"
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

<!--        第三排 作品数TOP25作者 各分类书籍平均评分-->
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 50%">
                <a-card hoverable body-style="center" title="作品数TOP25作者">
                    <EchartsAuthorBook align="center"/>
                    <br/>
                    <p style="color: darkgray"> “ 鲁迅先生威武 ” </p>
                </a-card>
            </a-col>
            <a-col :span="12" style="width: 50%">
                <a-card hoverable body-style="center" title="各分类书籍平均评分及星级占比" >
                    <EchartsTypeScore align="center"/>
                    <br/>
                    <p style="color: darkgray"> “ 文化类的平均评分很高，文学类则平均五星评价占比最高，生活类相对最不受欢迎~ ” </p>
                </a-card>
            </a-col>
        </a-row>

<!--        第四排 热度TOP10作者-->
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 100%">
                <a-card hoverable body-style="center" title="热度TOP10作者">
                    <EchartsHotAuthor align="center"/>
                    <br/>
                    <p style="color: darkgray"> “ TOP10里，热度最高的东野圭吾先生综合评分相对最低，而热度相对最低的钱钟书前辈却有着相对最高的平均评分~ 还等什么？紧跟潮流请选他们~ ” </p>
                </a-card>
            </a-col>
        </a-row>

<!--        第五排 出版数量随时间的变化-->
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 100%">
                <a-card hoverable body-style="center" title="出版数量随时间的变化">
                    <EchartsPubDate align="center"/>
                    <br/>
                    <p style="color: darkgray"> “ 不难看出书籍出版量逐年增长，且仔细分析可以发现几乎每年的1月都是出版的高峰期，远超其他月份，
                        而2020年的4、5月却有一大波书籍出版且超过1月出版数量，是因为疫情后的复工复产吗？ ” </p>
                </a-card>
            </a-col>
        </a-row>

<!--        第六排 出版社平均书评数与短评数-->
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 100%">
                <a-card hoverable body-style="center" title="出版社平均书评数与短评数">
                    <EchartsReviewPress align="center"/>
                    <br/>
                    <p style="color: darkgray"> “ 北京十月文艺出版社、上海译文出版社、南海出版公司、武汉大学出版社都是读者们的宠儿~ ” </p>
                </a-card>
            </a-col>
        </a-row>
    </div>
</template>


<script>
    import EchartsAuthorBook from "../../components/book/EchartsAuthorBook";
    import EchartsPubDate from "../../components/book/EchartsPubDate";
    import EchartsTypeScore from "../../components/book/EchartsTypeScore";
    import EchartsHotAuthor from "../../components/book/EchartsHotAuthor";
    import EchartsReviewPress from "../../components/book/EchartsReviewPress";

    export default {
        name: "dataDisplay",
        components: {EchartsHotAuthor, EchartsTypeScore, EchartsAuthorBook, EchartsPubDate, EchartsReviewPress}
    }
</script>

<style scoped>

</style>