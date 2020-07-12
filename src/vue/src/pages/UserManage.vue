<template>
    <div>
        <!--        表头-->
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="用户管理"
                sub-title="嗯， 只是简简单单的用户管理"
        />

        <!--        查询按钮-->
        <a-form layout="inline">

            <a-form-item
                    label="用户名查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--
                    v-model里 是和 data 绑定的变量名
                -->
                <a-input placeholder="请输入用户名" v-model="uid"/>
            </a-form-item>

            <a-form-item
                    label="名称查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入用户名称" v-model="nickName"/>
            </a-form-item>



            <!--            和上面那个一样-->
            <a-form-item
                    label="状态查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >

                <a-select default-value="全部查询" style="width: 120px" @change="stateChange">
                    <a-select-option value="-1">
                        全部查询
                    </a-select-option>
                    <a-select-option value="0">
                        未激活
                    </a-select-option>
                    <a-select-option value="1">
                        正常
                    </a-select-option>
                    <a-select-option value="2">
                        屏蔽
                    </a-select-option>
                </a-select>

            </a-form-item>

            <!--        v-on 是绑定事件用的 我们这里绑定了click事件 为click绑定了一个函数
                        点击时触发
            -->
            <a-form-item :wrapper-col="buttonItemLayout.wrapperCol">
                <a-button type="primary" v-on:click="handleSubmit">
                    立即查询
                </a-button>
            </a-form-item>
        </a-form>

        <!--        数据列表-->
        <!--        这里没啥改的
                    第一行 我绑定了一个change事件， 这个是antdv自己写的，原生vue应该没有这个事件，翻页时触发， 可以获得当前页数
                           :pagination 是为当前组件的属性设置值（动态！） 和data的变量 pagination绑定
        -->
        <a-table :columns="columns" :data-source="data" :loading="loading" v-on:change="nextPage" :pagination = "pagination">
            <a slot="name" slot-scope="text">{{ text }}</a>
            <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
            <span slot="tags" slot-scope="tags">
                <a-tag :color=" tags === 0? '#00a1d6' : tags === 1 ? '#fb7299' : '#ff5c7c'">
<!--                    也就在这里和根据标签不同赋予了不同的颜色，别的地方没动-->
                    {{ tags }}
                </a-tag>
            </span>
<!--        recode 保存每一行的所有信息-->
            <span slot="action"  slot-scope="text, record">
                <a>修改密码{{record.uid}}</a>
                <a-divider type="vertical" />
                <a>修改状态</a>
                <a-divider type="vertical" />
                <a>注销用户</a>
            </span>
        </a-table>
    </div>
</template>

<script>
    import qs from "qs";
    import axios from "axios"

    // 这是表格的字段, 按照格式来~
    const columns = [
        {
            title: '用户名',
            dataIndex: 'uid',
            key: 'uid',
        },
        {
            title: '用户昵称',
            dataIndex: 'nickname',
            key: 'nickname',
        },{
            title: "年龄",
            dataIndex: "age",
            key: "age"
        },{
            title: "性别",
            dataIndex: "gender",
            key: "gender"
        }, {
            title: '邮箱',
            dataIndex: 'email',
            key: 'email',
        },
        {
            title: '地址',
            dataIndex: 'address',
            key: 'address',
        }, {
            title: '用户状态',
            key: 'tags',
            dataIndex: 'tags',
            scopedSlots: { customRender: 'tags' },
        },{
            title: 'Action',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        }
    ];

    // const data0 = [
    //     {
    //         key: 1,
    //         uid: "woc",
    //         nickname: "名字什么的能吃吗",
    //         email: "1418407007@qq.com",
    //         address: "天津市西青区滨水西道399号天津工业大学",
    //         age: 20,
    //         gender: "男",
    //         tags: 1
    //     }
    // ]
    export default {
        name: "UserManage",
        // 组件的状态（属性）（变量）
        data(){
            return{
                // 查询数据 我就查四个 前两个和input绑定（双向绑定） 后两个和select绑定（通过回调函数进行修改）
                uid: null,
                nickName: null,
                state: -1,  // 都是全部查询
                // 表格数据
                data: [],  // 请求结果我会放进去
                columns,   // 刚才说到的 表的字段
                loading: false, // 是否显示加载中图标， 不用管。
                page: 1,    // 当前页数
                limit: 10,  // 每页多少条
                pagination:{    // 分页的设置参数
                    total: 0,   // 把count 传给 total！
                }
            }
        },
        computed:{ //  computed是组件的计算属性 这里面不用管 antd写好了的，我们也不需要动
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
            stateChange(e) { // vip类型改变时 修改data
                this.state = e;
            },
            handleSubmit(){ // 点击立即查询后触发
                // this.loading = true; // 和loading有关的可以先忽略

                // 数据格式处理，必须做，不用问为什么
                // 记得在里面传入后端需要的参数
                const data = qs.stringify({
                    uid: this.uid,
                    nickName: this.nickName,
                    state: this.state,
                    page: this.page,
                    limit: this.limit
                })

                console.log(data)

                /*
                * 这里面我用了一个 array 存储结果，
                * 是因为 拿到的数据不符合我的要求， 不是我想要的格式
                * 所以我 遍历每一条评论，并对其进行修改
                *
                * table需要的 数据格式请先用`死数据`做测试，充分了解需要的数据格式之后再进行代码编写
                *
                * */
                // let array = []; // 保存处理后的查询结果
                axios.post("http://localhost:8080/logic/user/search", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        console.log(response)
                        // 下面是处理过程
                        // let data = response.data.data; //我想要的评论数据在这里面
                        // for (var index in data){        // 因为是对象类型，所以我遍历它的索引index（想知道数据长啥样，自己console.log）
                        //     let comment = data[index]   // 然后 通过index获取每一条评论
                        // }
                        // 总条数和每条评论不在一起， 在 response.data.count里面， 而且也不需要循环， 我们赋给total
                        this.pagination.total = response.data.count
                        // this.data = array // 把处理结果赋值给 data （这才是和表格绑定的数据！）
                        this.loading = false
                    }).catch(error => {
                    console.log(error)
                    this.loading = false
                })

            },
            nextPage(pagination){
                this.page = pagination.current; // 获取当前页码并进行更新
                // 和上面的函数一样（没一点不一样）
                this.loading = true;
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
                        this.loading = false
                    }).catch(error => {
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