<template>
    <div class="Echarts">
        <div id="authorBook" style="width:100%; height:400px;" ></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";
    export default {
        name: "EchartsAuthorBook",
        data(){
            return{
                analysisData_dataList:[],
                analysisData_authors:[],
                limit:[]
            }
        },
        methods:{
            myCharts(){
                var myChart = this.$echarts.init(document.getElementById('authorBook'));

                var option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 30,
                        top: 20,
                        bottom: 20,
                        data: this.analysisData_authors,

                        selected: this.limit
                    },
                    series: [
                        {
                            name: '作者名称:',
                            type: 'pie',
                            radius: '55%',
                            center: ['35%', '50%'],
                            data: this.analysisData_dataList,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };

                myChart.setOption(option);

            }


        },
        mounted() {
            let result_dataList = [];
            let result_authors = [];
            let limit = {};
            axios.post("http://localhost:8080/doubanBook/analysis", qs.stringify({
                type: 'author'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response =>{
                    const resultDataList = response.data.data;
                    console.log(111);
                    console.log(resultDataList);
                    let i = 0;
                    for (let index in resultDataList){
                        let resultData = resultDataList[index];
                        result_dataList = result_dataList.concat({
                            name:resultData.analysisData,
                            value:resultData.count
                        })
                        result_authors = result_authors.concat(resultData.analysisData)
                        limit[resultData.analysisData] = i < 6;
                        i++;
                    }
                    this.analysisData_dataList = result_dataList;
                    this.analysisData_authors = result_authors;
                    this.limit = limit;
                    console.log(111);
                    this.myCharts();
                }).catch(error=>{
                console.log(error)
            })
        }
    }
</script>

<!--<style scoped>-->

<!--</style>-->