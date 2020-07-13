<template>
    <div class="Echarts">
        <div id="classes" style="width:900px;height:400px;" ></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";
    export default {
        name: "EchartClasses",
        data(){
            return{
                datalist:[],
                classes:[],
                dictlist:[]
            }
        },
        methods:{
            myCharts(){
                var myChart = this.$echarts.init(document.getElementById('classes'));

                var option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 50,
                        top: 20,
                        bottom: 20,
                        data: this.classes,

                        selected: this.dictlist
                    },
                    series: [
                        {
                            name: '分类名称',
                            type: 'pie',
                            radius: '55%',
                            center: ['40%', '50%'],
                            data: this.datalist,
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
            let datarr = [];
            let publisharr = [];
            let dictarr = {};

            axios.post("http://localhost:8080/dangdang/dangAnalysis", qs.stringify({
                type: 'classes'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response =>{
                    const data = response.data.data;
                    console.log(response);
                    let i = 0;
                    for (let index in data){
                        let item = data[index];
                        datarr = datarr.concat({name:item.type,value:item.count})
                        publisharr = publisharr.concat(item.type)
                        dictarr[item.type] = i<10;
                        i++;
                    }
                    this.datalist = datarr;
                    this.classes = publisharr;
                    this.dictlist = dictarr;
                    this.myCharts();
                }).catch(error=>{
                console.log(error)
            })
        }
    }
</script>

<!--<style scoped>-->

<!--</style>-->