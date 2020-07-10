<template>
    <div class="Echarts">
        <div id="progress" style="width:400px;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios"
    import qs from "qs"
    export default {
        name: "EchartsProgress",
        data(){
            return{
                // eslint-disable-next-line vue/no-reserved-keys
                _datalist: []
            }
        },
        methods:{
            myEcharts(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('progress'));

                var option = {
                    backgroundColor: '#2c343c',

                    title: {
                        text: '观看进度比率',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#ccc'
                        }
                    },

                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },

                    visualMap: {
                        show: false,
                        min: 80,
                        max: 600,
                        inRange: {
                            colorLightness: [0, 1]
                        }
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '50%'],
                            data: this._datalist.sort(function (a, b) { return a.value - b.value; }),
                            roseType: 'radius',
                            label: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            },
                            labelLine: {
                                lineStyle: {
                                    color: 'rgba(255, 255, 255, 0.3)'
                                },
                                smooth: 0.2,
                                length: 10,
                                length2: 20
                            },
                            itemStyle: {
                                color: '#66ccff',
                                shadowBlur: 200,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function () {
                                return Math.random() * 200;
                            }
                        }
                    ]
                };


                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },
        mounted() {
            let array = []
            axios.post("http://localhost:8080/bilibili/simpleAnalysis", qs.stringify({
                type: 'progress'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const data = response.data.data
                    for(let index in data){
                        let item = data[index];
                        console.log(item)
                        array = array.concat({
                            value: item.count,
                            name: item.type
                        })
                    }
                    console.log(array)
                    this._datalist = array
                    this.myEcharts();
                    console.log(this._datalist)
                }).catch(error => {
                console.log(error)
            })

        }
    }
</script>

<style scoped>

</style>