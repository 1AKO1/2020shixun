<template>
    <div class="Echarts">
        <div id="sentiment" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: 'EchartsSentiment',
        data(){
            return{
                datelist: [],
            }
        },
        methods:{
            myEcharts(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('sentiment'));

                var option = {
                    xAxis: {},
                    yAxis: {},
                    series: [{
                        symbolSize: 1,
                        data: this.datelist,
                        type: 'scatter',
                    }]
                };


                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },
        mounted() {
            let array = []
            axios.post("http://localhost:8080/bilibili/advanceAnalysis", qs.stringify({}),{headers:{'Content-Type':'application/x-www-form-urlencoded'}})
            .then(response => {
                // console.log(response)
                const data = response.data.data;
                for (let index in data){
                    let points = data[index]
                    // console.log(points)
                    array[index] = [points.count, points.type];
                }

                console.log(array)
                // console.log(this.datalist)
                this.datelist = array
                this.myEcharts();
            }).catch(error => {
                console.log(error)
            })
        }
    }
</script>

<style>
</style>