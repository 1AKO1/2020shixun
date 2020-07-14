<template>
    <div class="Echarts">
        <div id="score" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";

    export default {
        name: 'EchartsScore',
        data(){
            return{
                // eslint-disable-next-line vue/no-reserved-keys
                _datalist: []
            }
        },
        methods:{
            myEcharts(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('score'));

                 var option = {
                     title: {
                         text: '评论各分段占比',
                         subtext: '10分太多了！',
                         left: 'center'
                     },
                     tooltip: {
                         trigger: 'item',
                         formatter: '{a} <br/>{b} : {c} ({d}%)'
                     },
                     legend: {
                         left: 'center',
                         top: 'bottom',
                         data: ["2分", "4分", "6分", "8分", "10分"]
                     },
                     toolbox: {
                         show: true,
                         feature: {
                             mark: {show: true},
                             dataView: {show: true, readOnly: false},
                             magicType: {
                                 show: true,
                                 type: ['pie', 'funnel']
                             },
                             restore: {show: true},
                             saveAsImage: {show: true}
                         }
                     },
                     series: [
                         {
                             type: 'pie',
                             radius: [20, 110],
                             roseType: 'radius',
                             label: {
                                 show: false
                             },
                             emphasis: {
                                 label: {
                                     show: true
                                 }
                             },
                             data: this._datalist
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
                type: 'score'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const data = response.data.data
                    for(let index in data){
                        let item = data[index];
                        console.log(item)
                        array = array.concat({
                            value: item.count,
                            name: item.type + "分"
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

<style>
</style>