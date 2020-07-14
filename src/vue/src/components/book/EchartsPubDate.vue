<template>
    <div class="Echarts">
        <div id="pubDate" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: "EchartsPubDate",
        data(){
            return{
                analysisData_dateList:[],
                analysisData_countList:[]
            }
        },
        methods:{
            myEcharts(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('pubDate'));

                var option = {

                    dataZoom: [
                        {
                            realtime: true,
                            start: 48,
                            end: 100,
                            zoomOnMouseWheel: true,
                            xAxisIndex: 0
                        },
                        {
                            type: 'inside',
                            show: true,
                            start: 0,
                            end: 100,
                            left: "center",
                            // zoomOnMouseWheel:false,
                            yAxisIndex: 0
                        }
                    ],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    xAxis: {
                        type: 'category',
                        name: '出版时间',
                        data: this.analysisData_dateList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        radius: '55%',
                        center: ['40%', '50%'],
                        type: 'line',
                        smooth: true,
                        data: this.analysisData_countList,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },
        mounted() {
            let result_dateList = []; // 存储日期列表
            let result_dateCount = []; // 存储数据列表
            axios.post("http://localhost:8080/doubanBook/analysis", qs.stringify({
                type: 'pubDate'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const resultDataList = response.data.data
                    console.log(444);
                    console.log(resultDataList)
                    for(let index in resultDataList) {
                        let resultData = resultDataList[index];
                        result_dateList = result_dateList.concat(resultData.analysisData);
                        result_dateCount = result_dateCount.concat(resultData.count);
                    }
                    this.analysisData_dateList = result_dateList;
                    this.analysisData_countList = result_dateCount;
                    console.log(this.analysisData_dateList);
                    console.log(this.analysisData_countList);
                    console.log(444);
                    this.myEcharts();
                }).catch(error => {
                console.log(error)
            })

        }
    }
</script>

<style scoped>

</style>