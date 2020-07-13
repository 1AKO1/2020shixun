<template>
    <div class="Echarts">
        <div id="score" style="width:400px;height:400px;"></div>
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
                         text: '各类型比率',
                         left: 'center',
                         top: 20,
                        
                     },

                     tooltip: {
                         trigger: 'item',
                         formatter: '{a} <br/>{b} : {c} ({d}%)'
                     },

                
                     series: [
                         {
                             name: '各种类型平均评分',
                             type: 'pie',
                             radius: '55%',
                             center: ['50%', '50%'],
                             data: this._datalist,

                         }
                     ]
                 };


                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },
        mounted() {
            let array = []
            axios.post("http://localhost:8080/douban/simpleAnalysis", qs.stringify({
                type: 'score'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response => {
                    const data = response.data.data
                    for(let index in data){
                        let item = data[index];
                    
                        array = array.concat({
                            value: item.score,
                            name: item.movieType
                        })
                    }
                
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