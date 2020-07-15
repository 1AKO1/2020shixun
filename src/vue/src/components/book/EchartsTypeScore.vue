<template>
    <div class="Echarts">
        <div id="typeScore" style="width:100%; height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: "EchartsTypeScore",
        data(){
            return{
                analysis_dataList:[],
                analysis_type:[]

            }
        },
        methods: {
            myEcharts() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('typeScore'));

                var selected_item ={
                    '流行': false,
                    '经管': false,
                };
                var option = {
                    tooltip: {},
                    legend: {
                        selected: selected_item,
                        data: this.analysis_type
                    },
                    radar: {
                        // shape: 'circle',
                        name: {
                            textStyle: {
                                color: '#fff',
                                backgroundColor: '#999',
                                borderRadius: 3,
                                padding: [3, 5]
                            }
                        },
                        indicator: [
                            {name: '平均评分', min: 7, max: 8.2},
                            {name: '平均五星占比(%)', min: 28, max: 48},
                            {name: '平均四星占比(%)', min: 27.5, max: 40},
                            {name: '平均三星占比(%)', min: 7, max: 21},
                            {name: '平均二星占比(%)', min: 0.75, max: 4},
                            {name: '平均一星占比(%)', min: 0.3, max: 1.5},
                            // {name: '平均评分', min: 7.2, max: 8.2},
                            // {name: '平均五星占比(%)', min: 32, max: 48},
                            // {name: '平均四星占比(%)', min: 32, max: 40},
                            // {name: '平均三星占比(%)', min: 9.5, max: 22},
                            // {name: '平均二星占比(%)', min: 0.95, max: 4},
                            // {name: '平均一星占比(%)', min: 0.4, max: 1.5},
                        ]
                    },
                    series: [{
                        name: '六个核桃（x）',
                        type: 'radar',
                        // areaStyle: {normal: {}},
                        data: this.analysis_dataList
                    }]
                };
                myChart.setOption(option);
            }
        },
        mounted() {
            let result_dataList = [];
            let result_type = [];
            axios.post("http://localhost:8080/doubanBook/analysis", qs.stringify({
                type: 'typeScore'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const resultDataList = response.data.data;
                    console.log(222);
                    console.log(resultDataList)
                    for(let index in resultDataList){
                        let resultData = resultDataList[index];
                        let t = [];
                        t = t.concat(resultData.score);
                        t = t.concat(resultData.fiveStar * 100);
                        t = t.concat(resultData.fourStar * 100);
                        t = t.concat(resultData.threeStar * 100);
                        t = t.concat(resultData.twoStar * 100);
                        t = t.concat(resultData.oneStar * 100);

                        result_type = result_type.concat(resultData.type);
                        result_dataList = result_dataList.concat({value:t, name:resultData.type});
                    }
                    this.analysis_type = result_type;
                    this.analysis_dataList = result_dataList;
                    console.log(this.analysis_type);
                    console.log(this.analysis_dataList);
                    console.log(222);
                    this.myEcharts();
                }).catch(error => {
                console.log(error)
            })

        }
    }

</script>

<style scoped>

</style>