<template>
    <div class="Echarts">
        <div id="ProssTime" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: "EchartsProssTime",
        data(){
            return{
                datelist: [],
                mydata: []
            }
        },
        methods:{
            myEcharts(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('ProssTime'));

                var option = {

                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: this.datelist
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        // name: '分类名称:',
                        radius: '55%',
                        center: ['40%', '50%'],
                        data: this.mydata ,
                        type: 'line',
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
            let datearray = []; // 存储日期列表
            let dataarray = []; // 存储数据列表
            axios.post("http://localhost:8080/dangdang/dangAnalysis", qs.stringify({
                type: 'prosstime'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const data = response.data.data
                    for(let index in data){
                        let item = data[index];
                        console.log(item)
                        datearray = datearray.concat(item.type)
                        dataarray = dataarray.concat(item.count)
                    }
                    this.datelist = datearray;
                    this.mydata = dataarray;
                    this.myEcharts();
                    console.log(this.datelist);
                    console.log(this.mydata);
                }).catch(error => {
                console.log(error)
            })

        }
    }
</script>

<style scoped>

</style>