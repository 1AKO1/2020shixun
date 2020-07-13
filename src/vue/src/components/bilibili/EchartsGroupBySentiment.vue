<template>
    <div class="Echarts">
        <div id="groupbySentiment" style="width:400px;height:400px;"></div>
    </div>
</template>

<script>
    import axios from 'axios'
    import qs from "qs"
    export default {
        name: "EchartsGroupBySentiment",
        data(){
            return{
                // eslint-disable-next-line vue/no-reserved-keys
                _datalist: [],
            }
        },
        methods:{
            myEcharts:function(){
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('groupbySentiment'));

                var option = {
                    title: {
                        text: '用户评论情绪情况',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        left: 'center',
                        top: 'bottom',
                        data: ["普通用户", "大会员", "神秘大会员"]
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '50%'],
                            data: this._datalist,
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



                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },
        mounted() {
            let array = []
            axios.post("http://localhost:8080/bilibili/simpleAnalysis", qs.stringify({
                type: 'sentiment'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const data = response.data.data
                    for(let index in data){
                        let item = data[index];
                        console.log(item)
                        array = array.concat({
                            value: item.count,
                            name: item.type === 0 ? "消极" : item.type === 1? "中性" : item.type === 2? "积极" : "未识别"
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