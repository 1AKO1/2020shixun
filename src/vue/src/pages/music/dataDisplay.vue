<template>
    <div >
        <a-row :gutter="16">
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="爬取总数"
                            :value="42061"
                            :value-style="{ color: '#cf1322' }"
                            style="margin-right: 50px"
                    >
                        <template #prefix>
                            <a-icon type="arrow-up" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="歌手总数"
                            :value="98.37"
                            :precision="2"
                            suffix="%"
                            class="demo-class"
                            :value-style="{ color: '#3f8600' }"
                    >
                        <template #prefix>
                            <a-icon type="arrow-down" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card hoverable>
                    <a-statistic
                            title="发行商"
                            :value="102859"
                            class="demo-class"
                            :value-style="{ color: '#FFA500' }"
                    >
                        <template #prefix>
                            <a-icon type="minus" />
                        </template>
                        <template #suffix>
                            <span>/条</span>
                        </template>

                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
        <a-row :gutter="[16,16]">
            <a-col :span="12">
                <a-card>
                    <a-statistic
                            title="音乐种类"
                            :value="41"
                            :value-style="{ color: '#000080' }"
                            style="margin-right: 50px"
                    >
                        <template #prefix>
                            <a-icon type="tag" />
                        </template>
                        <template #suffix>
                            <span>/种</span>
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="12">
                <a-card>
                    <a-statistic
                            title="音乐媒介"
                            :value="466"
                            class="demo-class"
                            :value-style="{ color: '#0000CD' }"
                    >

                        <template #prefix>
                            <a-icon type="rocket" />
                        </template>
                    </a-statistic>
                </a-card>
            </a-col>
        </a-row>
        <a-row :gutter="[16,16]">
            <a-col :span="24" >
                <a-card hoverable title="歌手人气榜" >
                    <EchartsSinger />
                </a-card>
            </a-col>
        </a-row>
        <a-row>
            <a-col :span="24" >
                <a-card hoverable title="发行商市场占有率">
                    <Echartspublisher />
                </a-card>
            </a-col>
        </a-row>
        <a-row>
            <a-col :span="24">
                <a-card hoverable title="最受欢迎发行媒介">
                    <EchartsMedium />
                </a-card>
            </a-col>
        </a-row>
        <a-row :gutter="[16,16]">
            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="各类型音乐人气&评分">
                    <EchartsKind />
                </a-card>
            </a-col>
            <a-col :span="12" style="width: 50%">
                <a-card hoverable title="各类型音乐评分对比">
                    <EchartsCompare />
                </a-card>
            </a-col>
        </a-row>
    </div>

</template>

<script>
    import EchartsSinger from "../../components/music/EchartsSinger";
    import EchartsCompare from "../../components/music/EchartsCompare";
    import Echartspublisher from "../../components/music/Echartspublisher";
    import EchartsMedium from "../../components/music/EchartsMedium";
    import EchartsKind from "../../components/music/EchartsKind";
    export default {
        name:"dataDisplay",
        components:{
            EchartsSinger,
            EchartsCompare,
            Echartspublisher,
            EchartsMedium,
            EchartsKind
        }
    }
</script>

<style scoped>

</style>