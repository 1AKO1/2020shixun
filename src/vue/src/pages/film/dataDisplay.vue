<template>
    <div>
        <table style="width: 100%">
            <tr style="width: 100%">
                <td colspan="3">
                    <div style="background-color: cadetblue; padding: 10px" >
                        <a-row :gutter="10">
                            <a-col :span="6" style="width: 20%;">
                                <a-card title="电影总数:23476" :bordered="false" :head-style="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" :hoverable="true">
                                </a-card>
                            </a-col>
                            <a-col :span="6" style="width: 20%">
                                <a-card title="评论总数:3756w+" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                                </a-card>
                            </a-col>
                            <a-col :span="6" style="width: 20%">
                                <a-card title="平均片长:134min" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                                </a-card>
                            </a-col>
                            <a-col :span="6" style="width: 20%">
                                <a-card title="最高评分:9.7" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                                </a-card>
                            </a-col>
                            <a-col :span="6" style="width: 20%">
                                <a-card title="最低评分:2.1" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                                </a-card>
                            </a-col>
                        </a-row>
                    </div>
                </td>
            </tr>
            <tr style="width: 100%">
                <div style="background-color: cadetblue; padding: 10px" >
                    <a-row :gutter="10">
                        <a-col :span="6" style="width: 35%;">
                            <a-card title="电影类型分布" :bordered="false" :head-style="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" :hoverable="true">
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                            </a-card>
                        </a-col>
                        <a-col :span="6" style="width: 65%">
                            <a-card title="时间~上映数量" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                            </a-card>
                        </a-col>
                    </a-row>
                </div>
            </tr>
            <tr style="width: 100%">
                <div style="background-color: cadetblue; padding: 10px" >
                    <a-row :gutter="10">
                        <a-col :span="6" style="width: 65%;">
                            <a-card title="地区上映数量" :bordered="false" :head-style="{color:'#008b8b'}" :bodyStyle="{padding:'0'}" :hoverable="true">
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                            </a-card>
                        </a-col>
                        <a-col :span="6" style="width: 35%">
                            <a-card title="评分~片长" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                                <p>Card content</p>
                            </a-card>
                        </a-col>
                    </a-row>
                </div>
            </tr>
            <tr>
                <div style="background:cadetblue; padding:10px; width: 100%">
                    <a-card title="评分VS评论人数" :bordered="false" :headStyle="{color:'#008b8b'}" :bodyStyle="{padding:'0'}">
                        <p>Card content</p>
                        <p>Card content</p>
                        <p>Card content</p>
                    </a-card>
                </div>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        name: "dataDisplay"
    }
</script>

<style scoped>

</style>