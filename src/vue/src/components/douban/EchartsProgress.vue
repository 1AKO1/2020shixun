<template>
    <div class="Echarts">
        <div id="timeScore" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: 'EchartsProgress',
        data(){
            return{
                datelist: [],
                mydata: []
            }
        },
        methods:{
            myEcharts(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('timeScore'));

                var option = {
                    title: {
                        text: '片长与评分关系',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#000'
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: this.datelist
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: this.mydata,
                        type: 'scatter',

                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },
        mounted() {
            let datearray = []; // 存储日期列表
            let dataarray = []; // 存储数据列表
            axios.post("http://localhost:8080/douban/simpleAnalysis", qs.stringify({
                type: 'timeScore'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const data = response.data.data
                    for(let index in data){
                        let item = data[index];
                        console.log(item)
                        datearray = datearray.concat(item.score)
                        dataarray = dataarray.concat(item.timeLength)
                    }
                    this.datelist = datearray;
                    this.mydata = dataarray;
                    this.myEcharts();
                    console.log(this.datelist);
                    console.log(this.mydata);
                }).catch(error => {
                console.log(error)
            })

        }
    }
</script>

<style>
</style>