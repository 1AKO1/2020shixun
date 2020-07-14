<template>
    <div class="Echarts">
        <div id="price" style="width:100%;height:400px;"></div>
    </div>
</template>

<script>
    export default {
        name: "Echartprice",
        methods:{
            myEchart(){
                var myChart = this.$echarts.init(document.getElementById('price'));

                var option = {
                    // title: {
                    //     text: '漏斗图',
                    //     subtext: '纯属虚构',
                    //     left: 'left',
                    //     top: 'bottom'
                    // },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c}%'
                    },
                    toolbox: {
                        orient: 'vertical',
                        top: 'center',
                        feature: {
                            dataView: {readOnly: false},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['3￥~30￥', '60￥~90￥', '30￥~60￥', '90￥以上']
                    },

                    series: [
                        {
                            name: '价格分布',
                            type: 'funnel',
                            width: '40%',
                            height: '45%',
                            left: '30%',
                            top: '50%',
                            data: [
                                {value: 14110, name: '3￥~30￥'},
                                {value: 19432 , name: '30￥~60￥'},
                                {value: 5161 , name: '60￥~90￥'},
                                {value: 3126, name: '90￥以上'},

                            ]
                        },
                        {
                            name: '价格分布',
                            type: 'funnel',
                            width: '40%',
                            height: '45%',
                            left: '30%',
                            top: '5%',
                            sort: 'ascending',
                            data: [
                                {value: 14110, name: '3￥~30￥'},
                                {value: 19432 , name: '30￥~60￥'},
                                {value: 5161 , name: '60￥~90￥'},
                                {value: 3126, name: '90￥以上'},

                            ]
                        },

                    ]
                };

                myChart.setOption(option);
            }
        },
        mounted() {
            this.myEchart();
        }
    }
</script>

<style scoped>

</style>