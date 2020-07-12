<template>
    <div class="Echarts">
        <div id="medium"  style="width:900px;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs";
    export default {
        name: "EchartsMedium",
        data(){
            return{
                datalist:[],
                mediumlist:[]
            }
        },
        methods:{
            myCharts(){
                var myChart = this.$echarts.init(document.getElementById('medium'));

                var option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 10,
                        data: this.mediumaquamarine
                    },
                    title: {
                        left: 'center',
                        text: '最受欢迎的发行媒介',
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: this.datalist
                        }
                    ]
                };


                myChart.setOption(option);

            }


        },
        mounted() {
            let datarr = [];
            let mediumarr = [];
            axios.post("http://localhost:8080/music/analysis", qs.stringify({
                type: 'medium'
            }, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}))
                .then(response =>{
                    const data = response.data.data1;
                    for (let index in data){
                        let item = data[index];
                        datarr = datarr.concat({value:item.count,name:item.type})
                        mediumarr = mediumarr.concat(item.type)
                    }
                    this.datalist = datarr;
                    this.mediumlist = mediumarr;
                    this.myCharts();
                }).catch(error=>{
                console.log(error)
            })
        }
    }
</script>