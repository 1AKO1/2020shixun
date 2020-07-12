<template>
    <div>
        <a-row>
            <EchartsSinger />
        </a-row>
        <a-row>
            <Echartspublisher />
        </a-row>
        <a-row>
            <EchartsMedium />
        </a-row>
        <a-row>
            <a-col :span="12">
                <EchartsKind />
            </a-col>
            <a-col :span="12">
                <EchartsCompare />
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