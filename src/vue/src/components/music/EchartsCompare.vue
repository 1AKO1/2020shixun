<template>
    <div class="Echarts">
        <div id="compare"  style="width:500px;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";
    export default {
        name: "EchartsCompare",
        data(){
            return{
                datalist1: [],
                datalist2: [],
                kindlist: [],
            }
        },
        methods:{
            myCharts(){
                var myChart = this.$echarts.init(document.getElementById('compare'));

                var option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: [ '5分及以下', '5分以上']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    yAxis: [
                        {
                            type: 'category',
                            axisTick: {
                                show: false
                            },
                            data:this.kindlist
                        }
                    ],
                    series: [
                        {
                            name: '5分以上',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,
                                postion: "right"
                            },
                            data: this.datalist1
                        },
                        {
                            name: '5分以下',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,

                            },
                            data:this.datalist2
                        }
                    ]
                };

                myChart.setOption(option);

            }


        },
        mounted() {
            let data1arr = [];
            let data2arr = [];
            let kindarr = [];

            axios.post("http://localhost:8080/music/analysis", qs.stringify({
                type: 'compare'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response =>{
                    const data1 = response.data.data1;
                    for (let index in data1){
                        let item = data1[index];
                        data1arr = data1arr.concat(item.count);
                        kindarr = kindarr.concat(item.type);
                    }
                    const data2 = response.data.data2;
                    for (let index in data2){
                        let item = data2[index];
                        data2arr = data2arr.concat(-(item.count))
                    }

                    this.datalist1 = data1arr;
                    this.datalist2 = data2arr;
                    this.kindlist = kindarr;
                    this.myCharts();
                }).catch(error=>{
                console.log(error)
            })
        }
    }
</script>