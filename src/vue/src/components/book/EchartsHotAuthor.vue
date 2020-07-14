<template>
    <div class="Echarts">
        <div id="hotAuthor" style="width:100%; height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: "EchartsHotAuthor",
        data(){
            return{
                analysis_dataList: [],
                analysis_authorList: [],
            }
        },
        methods: {
            myEcharts() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('hotAuthor'));

                var colors = ['#ff0000', '#f689a3', '#69beef'];
                var option = {
                    color: colors,

                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross'
                        }
                    },
                    grid: {
                        right: '10%',
                        left: '12%'
                    },
                    toolbox: {
                        feature: {
                            dataView: {show: true, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data: ['热度', '作品平均评价数', '作品平均评分']
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {
                                alignWithLabel: true
                            },
                            data: this.analysis_authorList
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '热度',
                            min: 80,
                            max: 350,
                            position: 'left',
                            axisLine: {
                                lineStyle: {
                                    color: colors[0]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 万'
                            }
                        },
                        {
                            type: 'value',
                            name: '评价数',
                            min: 1,
                            max: 26,
                            position: 'left',
                            offset: 80,
                            axisLine: {
                                lineStyle: {
                                    color: colors[1]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 万'
                            }
                        },
                        {
                            type: 'value',
                            name: '分数',
                            min: 7,
                            max: 9.2,
                            position: 'right',
                            offset: 50,
                            axisLine: {
                                lineStyle: {
                                    color: colors[2]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 分'
                            }
                        }
                    ],
                    series: this.analysis_dataList
                    // [
                    //     {
                    //         name: '蒸发量',
                    //         type: 'bar',
                    //         data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                    //     },
                    //     {
                    //         name: '降水量',
                    //         type: 'bar',
                    //         yAxisIndex: 1,
                    //         data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                    //     },
                    //     {
                    //         name: '平均温度',
                    //         type: 'line',
                    //         yAxisIndex: 2,
                    //         data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                    //     }
                    // ]
                };
                myChart.setOption(option);
            }
        },
        mounted() {
            let result_hot = [];
            let result_votes = [];
            let result_score = [];
            let result_author = [];
            axios.post("http://localhost:8080/doubanBook/analysis", qs.stringify({
                type: 'hotAuthor'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const resultDataList = response.data.data;
                    console.log(444);
                    console.log(resultDataList)

                    for(let index in resultDataList){
                        let resultData = resultDataList[index];
                        result_hot = result_hot.concat(resultData.hot / 10000);
                        result_votes = result_votes.concat(resultData.votes / 10000);
                        result_score = result_score.concat(resultData.score);

                        result_author = result_author.concat(resultData.author);
                    }
                    this.analysis_dataList = [
                        {
                            name: '热度',
                            type: 'line',
                            data: result_hot
                        },
                        {
                            name: '作品平均评价数',
                            type: 'line',
                            yAxisIndex: 1,
                            data: result_votes
                        },
                        {
                            name: '作品平均评分',
                            type: 'bar',
                            barWidth: 30,
                            yAxisIndex: 2,
                            data: result_score
                        }
                    ];
                    this.analysis_authorList = result_author;
                    console.log(this.analysis_authorList);
                    console.log(this.analysis_dataList);
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