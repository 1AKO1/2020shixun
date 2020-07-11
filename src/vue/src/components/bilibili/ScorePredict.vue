<template>
    <a-card hoverable style="width: 400px; height: 400px">
        <h1>预测分数为：</h1>
        <a-rate :default-value="score" v-model="score" disabled style="margin: 95px 0"/>
        <a-card-meta title="分数预测" description="请输入你的评论，我们来帮你预测你可能评论的分数~">

        </a-card-meta>

        <template slot="actions" class="ant-card-actions">
            <a-input-search
                    placeholder="请输入你的评论~"
                    enter-button="立即预测"
                    size="large"
                    v-model="inputValue"
                    @search="onSearch"
            />
        </template>

    </a-card>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: "ScorePredict",
        data(){
            return{
                score: 0,
                inputValue: null
            }
        },
        methods:{
            onSearch(){
                axios.post("http://localhost:8080/bilibili/predict", qs.stringify({
                    comment: this.inputValue
                }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                    .then(response => {
                        let score = response.data.data;
                        this.score = score / 2;
                    }).catch(error => {
                        console.log(error);
                        alert("查询失败")
                })
            }
        }
    }
</script>

<style scoped>

</style>