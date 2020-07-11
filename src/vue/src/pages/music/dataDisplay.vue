<template>
    <div>

        <a-page-header style="border-bottom: 1px solid rgb(235, 237, 240)"
                       title="音乐展示">
        </a-page-header>

        <a-form layout="inline">

            <a-form-item
                    lable="乐名"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入音乐名或关键字" v-model="name"></a-input>
            </a-form-item>

            <a-form-item
                    lable="歌手"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入歌手名或关键字" v-model="singer"></a-input>
            </a-form-item>

            <a-form-item
                    lable="时间"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入年份" v-model="time"></a-input>
            </a-form-item>

            <a-form-item
                    lable="标签"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入标签" v-model="tag"></a-input>
            </a-form-item>

            <a-form-item
                    lable="种类"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-select default-value="全部" style="width: 120px" @change="kindChange">
                    <a-select-option value="-1">
                        全部查询
                    </a-select-option>
                    <a-select-option value="0">
                        古典
                    </a-select-option>
                    <a-select-option value="1">
                        摇滚
                    </a-select-option>
                    <a-select-option value="2">
                        放克/灵歌/R&amp;B
                    </a-select-option>
                    <a-select-option value="3">
                        流行
                    </a-select-option>
                    <a-select-option value="4">
                        说唱
                    </a-select-option>
                    <a-select-option value="5">
                        民谣
                    </a-select-option>
                    <a-select-option value="6">
                        轻音乐
                    </a-select-option>
                    <a-select-option value="7">
                        原声
                    </a-select-option>
                    <a-select-option value="8">
                        电子
                    </a-select-option>
                    <a-select-option value="9">
                        拉丁
                    </a-select-option>
                    <a-select-option value="10">
                        爵士
                    </a-select-option>
                    <a-select-option value="11">
                        世界音乐
                    </a-select-option>
                    <a-select-option value="12">
                        布鲁斯
                    </a-select-option>
                    <a-select-option value="13">
                        Electronic電子
                    </a-select-option>
                    <a-select-option value="14">
                        Funk/Soul/R&amp;B
                    </a-select-option>
                    <a-select-option value="15">
                        雷鬼
                    </a-select-option>
                    <a-select-option value="16">
                        Soundtrack原聲
                    </a-select-option>
                </a-select>
            </a-form-item>

            <a-form-item :wrapper-col="buttonItemLayout.wrapperCol">
                <a-button type="primary" v-on:click="handleSubmit">
                    立即查询
                </a-button>
            </a-form-item>
        </a-form>

        <a-table :columns="columns" :data-source="data" :loading="loading" v-on:change="nextPage" :pagination = "pagination">
            <a slot="name" slot-scope="text">{{text}}</a>
            <span slot="customTitle"><a-icon type="smile-o">Name</a-icon></span>
            <span slot="tags" slot-scope="tags">
                <a-tag>
                    {{tags}}
                </a-tag>
            </span>
        </a-table>
    </div>
</template>

<script>
    import qs from "qs";
    import axios from "axios"

    const columns = [
        {
            title:'音乐名',
            dataIndex:'name',
            key:'name',
        },
        {
            title:'歌手',
            dataIndex:'singer',
            key:'singer',
        },
        {
            title:'发布时间',
            dataIndex:'time',
            key:'time',
        },
        {
            title:'评分',
            dataIndex:'rate',
            key:'rate',
        },
        {
            title:'标签',
            dataIndex:'tag',
            key:'tag',
        },
        {
            title:'种类',
            dataIndex:'kind',
            key:'kind',
        },
        {
            title:'同类歌曲推荐',
            dataIndex:'recommend',
            key:'recommend',
        },
    ];
    export default {
        name: "dataDisplay",

        data(){
            return{
                name: null,
                singer: null,
                time: null,
                rate: null,
                tag: null,
                kind: -1,
                recommend: null,

                data: [],
                columns,
                loading: false,
                page: 1,
                limit: 10,
                pagination:{
                    total: 0,
                }
            }
        },
        computed:{
            formItemLayout() {
                const { formLayout } = this;
                return formLayout === 'horizontal'
                    ? {
                        labelCol: { span: 4 },
                        wrapperCol: { span: 14 },
                    }
                    : {};
            },
            buttonItemLayout() {
                const { formLayout } = this;
                return formLayout === 'horizontal'
                    ? {
                        wrapperCol: { span: 14, offset: 4 },
                    }
                    : {};
            }
        },
        methods:{
            kindChange(e){
                this.kind = e;
            },
            handleSubmit(){
                this.loading = true;

                const data = qs.stringify({
                    name: this.name,
                    singer: this.singer,
                    time: this.time,
                    tag: this.tag,
                    kind: this.kind,
                    page: this.page,
                    limit: this.limit,
                    rate: this.rate,
                    recommend: this.recommend
                })

                let array = [];
                axios.post("http://localhost:8080/music/search",data,{headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                .then(response => {

                    let data = response.data.data;
                    for (var index in data){
                        let music= data[index]
                        array = array.concat({
                            key: music.id,
                            name: music.name,
                            singer: music.singer,
                            time: this.translate(music.time),
                            tag: music.tag,
                            kind: music.kind,
                            rate: music.rate,
                            recommend: music.recommend,
                        })
                    }

                    this.pagination.total = response.data.count
                    this.data = array
                    this.loading = false
                }).catch(error =>{
                    console.log(error)
                    this.loading = false
                })
            },
            translate(date) { // 时间戳转日期函数
                return new Date(parseInt(date) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
            }
        }
    }
</script>

<style scoped>

</style>