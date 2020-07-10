<template>
    <div>
<!--        表头-->
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="评论查询"
                sub-title="かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜"
        />

<!--        查询按钮-->
        <a-form layout="inline">
            <a-form-item
                    label="评论查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入评论的关键词" v-model="content"/>
            </a-form-item>

            <a-form-item
                    label="名称查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入用户名称" v-model="uname"/>
            </a-form-item>

            <a-form-item
                    label="大会员查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >

                <a-select default-value="全部查询" style="width: 120px" @change="vipChange">
                    <a-select-option value="-1">
                        全部查询
                    </a-select-option>
                    <a-select-option value="0">
                        非大会员
                    </a-select-option>
                    <a-select-option value="1">
                        大会员
                    </a-select-option>
                    <a-select-option value="2" >
                        神秘大会员
                    </a-select-option>
                </a-select>

            </a-form-item>

            <a-form-item
                    label="进度查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >

                <a-select default-value="全部查询" style="width: 120px" @change="progressChange">
                    <a-select-option value="'all'">
                        全部查询
                    </a-select-option>
                    <a-select-option value="没看">
                        没看
                    </a-select-option>
                    <a-select-option value="看到PV">
                        看到PV
                    </a-select-option>
                    <a-select-option value="看到角色印象PV" >
                        看到角色印象PV
                    </a-select-option>
                    <a-select-option value="看到第1话" >
                        看到第1话
                    </a-select-option>
                    <a-select-option value="看到第2话" >
                        看到第2话
                    </a-select-option>
                    <a-select-option value="看到第3话" >
                        看到第3话
                    </a-select-option>
                    <a-select-option value="看到第4话" >
                        看到第4话
                    </a-select-option>
                    <a-select-option value="看到第5话" >
                        看到第5话
                    </a-select-option>
                    <a-select-option value="看到第6话" >
                        看到第6话
                    </a-select-option>
                    <a-select-option value="看到第7话" >
                        看到第7话
                    </a-select-option>
                    <a-select-option value="看到第8话" >
                        看到第8话
                    </a-select-option>
                    <a-select-option value="看到第9话" >
                        看到第9话
                    </a-select-option>
                    <a-select-option value="看到第10话" >
                        看到第10话
                    </a-select-option>
                    <a-select-option value="看到第11话" >
                        看到第11话
                    </a-select-option>
                    <a-select-option value="看到第12话" >
                        看到第12话
                    </a-select-option>
                </a-select>

            </a-form-item>

            <a-form-item :wrapper-col="buttonItemLayout.wrapperCol">
                <a-button type="primary" v-on:click="handleSubmit">
                    立即查询
                </a-button>
            </a-form-item>
        </a-form>

<!--        数据列表-->
        <a-table :columns="columns" :data-source="data" :loading="loading" v-on:change="nextPage" :pagination = "pagination">
            <a slot="name" slot-scope="text">{{ text }}</a>
            <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
            <span slot="tags" slot-scope="tags">
                <a-tag
                    :color=" tags === '普通用户'? '#00a1d6' : tags === '大会员' ? '#fb7299' : '#ff5c7c'"
                >
                    {{ tags }}
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
            title: '用户名',
            dataIndex: 'uname',
            key: 'uname',
        },
        {
            title: '评论内容',
            dataIndex: 'content',
            key: 'content',
        },
        {
            title: '会员状态',
            key: 'tags',
            dataIndex: 'tags',
            scopedSlots: { customRender: 'tags' },
        },
        {
            title: '看番进度',
            dataIndex: 'progress',
            key: 'progress',
        },
        {
            title: '评论日期',
            dataIndex: 'date',
            key: 'date',
        },
    ];


    export default {
        name: "dataSearch",
        data(){
            return{
                // 查询数据
                uname: null,
                content: null,
                progress: 'all',   // 起初保存默认值
                vipStatus: -1,  // 都是全部查询
                // 表格数据
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
            vipChange(e) {
                this.vipStatus = e;
            },
            progressChange(e){
                this.progress = e;
            },
            handleSubmit(){
                const data = qs.stringify({
                    uname: this.uname,
                    content: this.content,
                    vipStatus: this.vipStatus,
                    progress: this.progress,
                    page: this.page,
                    limit: this.limit
                })
                let array = []; // 保存处理后的查询结果
                axios.post("http://localhost:8080/bilibili/search", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    let data = response.data.data;
                    for (var index in data){
                        let comment = data[index]
                        array = array.concat({
                            key: comment.id,
                            uname: comment.uname,
                            content: comment.content,
                            tags: comment.vipStatus === 0 ? "普通用户" : comment.vipStatus === 1 ? "大会员" : "神秘大会员",
                            progress: comment.progress,
                            date: this.translate(comment.ctime)
                        })
                    }
                    this.pagination.total = response.data.count
                    this.data = array
                }).catch(error => {
                    console.log(error)
                })

            },
            nextPage(pagination){
                this.page = pagination.current;
                // 和上面的函数一样
                const data = qs.stringify({
                    uname: this.uname,
                    content: this.content,
                    vipStatus: this.vipStatus,
                    progress: this.progress,
                    page: this.page,
                    limit: this.limit
                })
                let array = []; // 保存处理后的查询结果
                axios.post("http://localhost:8080/bilibili/search", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        let data = response.data.data;
                        for (var index in data){
                            let comment = data[index]
                            array = array.concat({
                                key: comment.id,
                                uname: comment.uname,
                                content: comment.content,
                                tags: comment.vipStatus === 0 ? "普通用户" : comment.vipStatus === 1 ? "大会员" : "神秘大会员",
                                progress: comment.progress,
                                date: this.translate(comment.ctime)
                            })
                        }
                        this.pagination.total = response.data.count
                        this.data = array
                    }).catch(error => {
                    console.log(error)
                })
            },

            translate(date) { // 时间戳转日期
                return new Date(parseInt(date) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
            }
        }
    }
</script>

<style scoped>

</style>