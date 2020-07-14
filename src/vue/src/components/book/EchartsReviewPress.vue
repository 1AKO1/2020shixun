<template>
    <div class="Echarts">
        <div id="reviewPress" style="width:100%; height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: "EchartsReviewPress",
        data(){
            return{
                analysis_pressList: [],
                analysis_reviews: [],
                analysis_shortComms: [],
            }
        },
        methods: {
            myEcharts() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('reviewPress'));

                var option = {
                    dataZoom: [
                        {
                            realtime: true,
                            start: 0,
                            end: 30,
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
                            type: 'cross',
                            label: {
                                backgroundColor: '#559edb'
                            }
                        }
                    },
                    legend: {
                        data: ['平均书评数', '平均短评数']
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: this.analysis_pressList
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '平均书评数',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {},
                            data: this.analysis_reviews
                        },
                        {
                            name: '平均短评数',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {},
                            data: this.analysis_shortComms
                        },

                    ]
                };
                myChart.setOption(option);
            }
        },
        mounted() {
            let result_pressList = [];
            let result_reviews = [];
            let result_shortComms = [];
            axios.post("http://localhost:8080/doubanBook/analysis", qs.stringify({
                type: 'reviewPress'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const resultDataList = response.data.data;
                    console.log(555);
                    console.log(resultDataList)
                    for(let index in resultDataList){
                        let resultData = resultDataList[index];

                        result_pressList = result_pressList.concat(resultData.press);
                        result_reviews = result_reviews.concat(resultData.reviews);
                        result_shortComms = result_shortComms.concat(resultData.shortComms);
                    }
                    this.analysis_pressList = result_pressList;
                    this.analysis_reviews = result_reviews;
                    this.analysis_shortComms = result_shortComms;
                    console.log(this.analysis_pressList);
                    console.log(this.analysis_reviews);
                    console.log(this.analysis_shortComms);
                    console.log(555);
                    this.myEcharts();
                }).catch(error => {
                console.log(error)
            })

        }
    }

</script>

<style scoped>

</style>