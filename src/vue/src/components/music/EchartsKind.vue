<template>
    <div class="Echarts">
        <div id="kind"  style="width:500px;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";
    export default {
        name: "EchartsKind",
        data(){
            return{
                datalist1: [],
                datalist2: [],
                kindlist: []
            }
        },
        methods:{
            myCharts(){
                var myChart = this.$echarts.init(document.getElementById('kind'));

                var option = {
                    legend: {
                        data: ['总评分', '总投票']
                    },
                    toolbox: {
                         y: 'bottom',
                        feature: {
                            magicType: {
                                type: ['stack', 'tiled']
                            },
                            dataView: {},
                            saveAsImage: {
                                pixelRatio: 2
                            }
                        }
                    },
                    tooltip: {},
                    xAxis: {
                        data: this.kindlist,
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                    },
                    series: [{
                        name: '人气',
                        type: 'bar',
                        data: this.datalist1,
                        animationDelay: function (idx) {
                            return idx * 10;
                        }
                    }, {
                        name: '评分',
                        type: 'bar',
                        data: this.datalist2,
                        animationDelay: function (idx) {
                            return idx * 10 + 100;
                        }
                    }],
                    animationEasing: 'elasticOut',
                    animationDelayUpdate: function (idx) {
                        return idx * 5;
                    }
                };
                myChart.setOption(option);

            }


        },
        mounted() {
            let data1arr = [];
            let data2arr = [];
            let kindarr = [];

            axios.post("http://localhost:8080/music/analysis", qs.stringify({
                type: 'kind'
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
                        data2arr = data2arr.concat(item.count)
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