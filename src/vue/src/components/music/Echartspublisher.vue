<template>
    <div class="Echarts">
        <div id="publisher"  style="width:900px;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";
    export default {
        name: "EchartsCompare",
        data(){
            return{
                datalist:[],
                publisherlist:[],
                dictlist:[]
            }
        },
        methods:{
            myCharts(){
                var myChart = this.$echarts.init(document.getElementById('publisher'));

                var option = {
                    title: {
                        text: '发行商市场占有率',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: -50,
                        top: 20,
                        bottom: 20,
                        data: this.publisherlist,

                        selected: this.dictlist
                    },
                    series: [
                        {
                            name: '姓名',
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

            axios.post("http://localhost:8080/music/analysis", qs.stringify({
                type: 'publisher'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response =>{
                    const data = response.data.data1;
                    let i = 0;
                    for (let index in data){
                        let item = data[index];
                        datarr = datarr.concat({name:item.type,value:item.count})
                        publisharr = publisharr.concat(item.type)
                        dictarr[item.type] = i<10;
                        i++;
                    }
                    this.datalist = datarr;
                    this.publisherlist = publisharr;
                    this.dictlist = dictarr;
                    this.myCharts();
                }).catch(error=>{
                console.log(error)
            })
        }
    }
</script>