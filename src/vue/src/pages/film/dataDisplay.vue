<template>
    <div>
<!--        <a-form layout="inline">-->
<!--            <a-page-header-->
<!--                    style="border-bottom: 1px solid rgb(235, 237, 240)"-->
<!--                    title="数据展示"-->
<!--                    sub-title="豆瓣电影图表展示"-->
<!--            />-->

<!--            <a-form-item-->
<!--                    label="电影数量"-->
<!--                    style="border-bottom: 1px solid rgb(235, 237, 240)"-->
<!--                    :label-col="formItemLayout.labelCol"-->
<!--                    :wrapper-col="formItemLayout.wrapperCol"-->
<!--            >-->
<!--                &lt;!&ndash;-->
<!--                    v-model里 是和 data 绑定的变量名-->
<!--                &ndash;&gt;-->
<!--                <a-form-item  style="border-bottom: 1px solid rgb(235, 237, 240)" placeholder="" v-model="movieCount"/><span class = "cu"> {{movieCount}}</span>-->
<!--            </a-form-item>-->

<!--            <a-form-item-->
<!--                    label="平均片长"-->
<!--                    :label-col="formItemLayout.labelCol"-->
<!--                    :wrapper-col="formItemLayout.wrapperCol"-->
<!--            >-->
<!--                <a-form-item  style="border-bottom: 1px solid rgb(235, 237, 240)" placeholder="" v-model="movieLength"/><span class = "cu">{{movieLength}}</span>-->
<!--            </a-form-item>-->

<!--            <a-form-item-->
<!--                    label="总评论数"-->
<!--                    :label-col="formItemLayout.labelCol"-->
<!--                    :wrapper-col="formItemLayout.wrapperCol"-->
<!--            >-->
<!--                <a-form-item style="border-bottom: 1px solid rgb(235, 237, 240)" placeholder="" v-model="movieCommentCount"/><span class = "cu">{{movieCommentCount}}</span>-->
<!--            </a-form-item>-->
<!--            <a-form-item-->
<!--                    label="最高评分(最低分)"-->
<!--                    :label-col="formItemLayout.labelCol"-->
<!--                    :wrapper-col="formItemLayout.wrapperCol"-->
<!--            >-->
<!--                <a-form-item  placeholder="" v-model="movieMaxScore"/><span class = "cu">{{movieMaxScore}}({{movieMinScore}})</span>-->
<!--            </a-form-item>-->
<!--        </a-form>-->
        <a-row :gutter="[16,16]">
            <a-col :span="6" style="width: 20%">
                <a-card hoverable>
                    <a-statistic
                            title="电影总数"
                            :value="23476"
                            :value-style="{ color: '#000080' }"
                            style="margin-right: 10px"
                    >
                        <template #prefix>
                            <a-icon type="tag" />
                        </template>
                        <template #suffix>
                            <span></span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6" style="width: 20%">
                <a-card hoverable>
                    <a-statistic
                            title="总评论数"
                            :value="3756"
                            class="demo-class"
                            :value-style="{ color: '#0000CD' }"
                    >
                        <template #prefix>
                            <a-icon type="tags" />
                        </template>
                        <template #suffix>
                            <span>w+</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6" style="width: 20%">
                <a-card hoverable>
                    <a-statistic
                            title="平均片长"
                            :value="134"
                            :precision="0"
                            suffix="min"
                            class="demo-class"
                            :value-style="{ color: '#3f8600' }"
                    >
                        <template #prefix>
                            <a-icon type="rocket"  :rotate="180"  />
                        </template>
                        <template #suffix>
                            <span>min</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6" style="width: 20%">
                <a-card hoverable>
                    <a-statistic
                            title="最高评分"
                            :value="9.7"
                            :precision="1"
                            suffix=""
                            class="demo-class"
                            :value-style="{ color: '#cf1322' }"
                    >
                        <template #prefix>
                            <a-icon type="arrow-up" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="6" style="width: 20%">
                <a-card hoverable>
                    <a-statistic
                            title="最低评分"
                            :value="2.1"
                            :precision="1"
                            suffix=""
                            class="demo-class"
                            :value-style="{ color: '#cf1322' }"
                    >
                        <template #prefix>
                            <a-icon type="arrow-down" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
        <table style="width: 100%">
            <tr style="width: 100%">
                <div>
                    <a-row :gutter="10">
                        <a-col :span="6" style="width: 40%;">
                            <a-card title="电影类型分布" bordered :head-style="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" hoverable>
                                <div class="easyBox">
                                    <div style="width: 100%" >
                                        <EchartsScore />
                                    </div>
                                </div>
                            </a-card>
                            <template #prefix>
                                <a-icon type="arrow-up" />
                            </template>
                        </a-col>
                        <a-col :span="6" style="width: 60%">
                            <a-card title="时间~上映数量" bordered :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" hoverable>
                                <div class="easyBox">
                                    <div style="width: 100%">
                                        <EchartsVip />
                                    </div>
                                </div>
                                <template #prefix>
                                    <a-icon type="arrow-up" />
                                </template>
                            </a-card>
                        </a-col>
                    </a-row>
                </div>
                <br/>
            </tr>
            <tr style="width: 100%">
                <div >
                    <a-row :gutter="10">
                        <a-col :span="6" style="width: 65%;">
                            <a-card title="地区上映数量" bordered :head-style="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" hoverable>
                                <div class="easyBox">
                                    <div style="width: 100%">
                                        <EchartsWordFrequency />
                                    </div>
                                </div>
                                <template #prefix>
                                    <a-icon type="arrow-up" />
                                </template>
                            </a-card>
                        </a-col>
                        <a-col :span="6" style="width: 35%">
                            <a-card title="评分~片长" bordered :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" hoverable>
                                <div class="easyBox">
                                    <div style="width: 100%">
                                        <EchartsProgress />
                                    </div>
                                </div>
                                <template #prefix>
                                    <a-icon type="arrow-up" />
                                </template>
                            </a-card>
                        </a-col>
                    </a-row>
                </div>
                <br/>
            </tr>
            <tr>
<!--                background:cadetblue;-->
                <div style="width: 100%">
                    <a-card title="评分VS评论人数" bordered :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" hoverable>
                        <div class="easyItem" >
                            <div style="width: 100%">
                                <EchartsCommentRise />
                            </div>
                        </div>
                        <template #prefix>
                            <a-icon type="arrow-up" />
                        </template>
                    </a-card>
                </div>
            </tr>
        </table>
    </div>
</template>

<script>
    import EchartsScore from "../../components/douban/EchartsScore";
    import EchartsProgress from "../../components/douban/EchartsProgress";
    import EchartsVip from "../../components/douban/EchartsVip";

    import EchartsCommentRise from "../../components/douban/EchartsCommentRise";
    import EchartsWordFrequency from "../../components/douban/EchartsWordFrequency";
    import qs from "qs";
    import axios from "axios"
    export default {
        name: "dataDisplay",
        components:{
            EchartsScore,
            EchartsProgress,
            EchartsCommentRise,
            EchartsWordFrequency,
            EchartsVip

        },
        data(){
            return{
                // 查询数据 我就查四个 前两个和input绑定（双向绑定） 后两个和select绑定（通过回调函数进行修改）
                movieCount: null,
                movieLength: null,
                movieMaxScore: null,   // 起初保存默认值
                movieMinScore: null,
                movieCommentCount: null,  // 都是全部查询
                // 表格数据
                data: [],  // 请求结果我会放进去

                loading: false // 是否显示加载中图标， 不用管。

            }
        },
        computed:{ //  computed是组件的计算属性 这里面不用管 antd写好了的，我们也不需要动
            formItemLayout() {
                const { formLayout } = this;
                return formLayout === 'horizontal'
                    ? {
                        labelCol: { span: 4 },
                        wrapperCol: { span: 14 },
                    }
                    : {};
            },
            buttonItemLayout() {
                const { formLayout } = this;
                return formLayout === 'horizontal'
                    ? {
                        wrapperCol: { span: 14, offset: 4 },
                    }
                    : {};
            },
        },
        //生命周期钩子
        mounted() {
            this.loading = true; // 和loading有关的可以先忽略

            // 数据格式处理，必须做，不用问为什么
            // 记得在里面传入后端需要的参数
            const data = qs.stringify({
                movieName: this.movieName,

            })


            let array = []; // 保存处理后的查询结果
            axios.post("http://localhost:8080/douban/query", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    // 下面是处理过程
                    let data = response.data.data; //我想要的评论数据在这里面

                    for (var index in data){        // 因为是对象类型，所以我遍历它的索引index（想知道数据长啥样，自己console.log）
                        let comment = data[index]   // 然后 通过index获取每一条评论
                        array = array.concat({      // 将每一条处理后的结果追加到 array数组中
                            key: comment.id,        // 这里面是我需要的数据格式 ！！！！！！！！！！ 自己也搞明白自己需要的！！！ very impotent！
                            movieLength: comment.movieLength,
                            movieCount: comment.movieCount,
                            movieCommentCount: comment.movieCommentCount,
                            movieMaxScore: comment.movieMaxScore,
                            movieMinScore: comment.movieMinScore,


                        })
                    }
                    // 总条数和每条评论不在一起， 在 response.data.count里面， 而且也不需要循环， 我们赋给total

                    //this.data = array // 把处理结果赋值给 data （这才是和表格绑定的数据！）
                    let result = array[0];
                    this.movieCommentCount = result.movieCommentCount;
                    this.movieCount = result.movieCount;
                    this.movieLength = result.movieLength;
                    this.movieMaxScore = result.movieMaxScore;
                    this.movieMinScore = result.movieMinScore;
                    console.log(result);
                    this.loading = false
                }).catch(error => {
                console.log(error)
                this.loading = false
            })
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
        margin: 10px 0;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        width: 100%;
        /*border: 1px solid #001529;*/
        /*display: inline-block;*/
        /*margin: 2px;*/
        /*padding : 3px;*/

    }
    .cu{

        display: inline-block;

        font-weight:bold;
        font-size : 20px;

    }
</style>
