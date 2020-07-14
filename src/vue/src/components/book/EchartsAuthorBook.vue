<template>
    <div class="Echarts">
        <div id="authorBook" style="width:550px;height:400px;" ></div>
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

                        selected: this.dictlist
                    },
                    series: [
                        {
                            name: '作者名称:',
                            type: 'pie',
                            radius: '55%',
                            center: ['40%', '50%'],
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

            axios.post("http://localhost:8080/dangdang/analysis", qs.stringify({
                type: 'classes'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response =>{
                    const resultDataList = response.data.data;
                    console.log(response);
                    let i = 0;
                    for (let index in resultDataList){
                        let resultData = resultDataList[index];
                        result_dataList = result_dataList.concat({
                            name:resultData.author,
                            value:resultData.count
                        })
                        result_authors = result_authors.concat(resultData.author)
                        limit[resultData.author] = i < 10;
                        i++;
                    }
                    this.analysisData_dataList = result_dataList;
                    this.analysisData_authors = result_authors;
                    this.limit = limit;
                    this.myCharts();
                }).catch(error=>{
                console.log(error)
            })
        }
    }
</script>

<!--<style scoped>-->

<!--</style>-->