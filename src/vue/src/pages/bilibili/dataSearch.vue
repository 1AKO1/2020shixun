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

                <a-select default-value="-1" style="width: 120px" @change="vipChange">
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

                <a-select default-value="-1" style="width: 120px" @change="progressChange">
                    <a-select-option value="-1">
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
        <a-table :columns="columns" :data-source="data" :loading="loading" v-on:change="getPage">
            <a slot="name" slot-scope="text">{{ text }}</a>
            <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
            <span slot="tags" slot-scope="tags">
                <a-tag
                    color="pink"
                >
                    {{ tags }}
                </a-tag>
            </span>
        </a-table>
    </div>
</template>

<script>
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
    ];

    const data = [
        {
            key: '1',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '2',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '3',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '4',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '5',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '6',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '7',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '8',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '9',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '10',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '11',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '12',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '13',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '14',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '15',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '16',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '17',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '18',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '19',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },
        {
            key: '20',
            uname: 'B站网友',
            content: "恶心人！看番体验极差！每周都得熬夜看，看的时候还跟个蛆似的扭来扭去，嘴角一直不明原因抽搐，看完嘴都发酸！半个小时都静不下来！严重影响我的睡眠！大家千万不要跟节奏看！这番极易诱发糖尿病，失眠，甚至面瘫！",
            tags: "大会员",
            progress: '看到PV'
        },


    ];


    export default {
        name: "dataSearch",
        data(){
            return{
                // 查询数据
                uname: null,
                content: null,
                progress: -1,   // 起初保存默认值
                vipStatus: -1,  // 都是全部查询
                // 表格数据
                data,
                columns,
                loading: false,
                current: 1,
                pageSize: 10
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
                const data = {
                    uname: this.uname,
                    content: this.content,
                    vipStatus: this.vipStatus,
                    progress: this.progress
                }
                console.log(data)
            },
            getPage(pagination){
                this.current = pagination.current;
                console.log(this.current)
            }
        }
    }
</script>

<style scoped>

</style>