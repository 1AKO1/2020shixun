<template>
    <div id="Echarts">
        <div id="yearBook" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    import axios from "axios";
    import qs from "qs"

    export default {
        name: "EchartyearBook",
        data(){
            return{
                // eslint-disable-next-line vue/no-reserved-keys
                _datalist: []
            }
        },
        methods:{
            myEcharts(){
                var myChart = this.$echarts.init(document.getElementById('yearBook'));

                var  option = {

                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        left: 'center',
                        top: 'bottom',
                        data: ['rose1', 'rose2', 'rose3', 'rose4']
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
                            name: '上榜数量',
                            type: 'pie',
                            radius: [30, 110],
                            roseType: 'area',
                            data: this._datalist
                        }
                    ]
                };
                myChart.setOption(option);
            }
        },
        mounted() {
            let array = []
            axios.post("http://localhost:8080/dangdang/dangAnalysis", qs.stringify({
                type: 'yearBook'
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