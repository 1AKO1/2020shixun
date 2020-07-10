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
                <!--
                    v-model里 是和 data 绑定的变量名
                -->
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
<!--                @change中是当option改变时执行的函数
                    可以自行打印参数（应该有默认参数）看看获得的是什么
                    如果我记得没错 会获得 每个选项的 value 和 内容
                    例如：
                        {key： -1, content: 全部查询}
-->
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
<!--            和上面那个一样-->
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
                    <a-select-option value="看到角色歌印象PV" >
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
                <a-tag :color=" tags === '普通用户'? '#00a1d6' : tags === '大会员' ? '#fb7299' : '#ff5c7c'">
<!--                    也就在这里和根据标签不同赋予了不同的颜色，别的地方没动-->
                    {{ tags }}
                </a-tag>
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
        // 组件的状态（属性）（变量）
        data(){
            return{
                // 查询数据 我就查四个 前两个和input绑定（双向绑定） 后两个和select绑定（通过回调函数进行修改）
                uname: null,
                content: null,
                progress: 'all',   // 起初保存默认值
                vipStatus: -1,  // 都是全部查询
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
            vipChange(e) { // vip类型改变时 修改data
                this.vipStatus = e;
            },
            progressChange(e){ // progress类型改变时 修改data
                this.progress = e;
            },
            handleSubmit(){ // 点击立即查询后触发
                this.loading = true; // 和loading有关的可以先忽略

                // 数据格式处理，必须做，不用问为什么
                // 记得在里面传入后端需要的参数
                const data = qs.stringify({
                    uname: this.uname,
                    content: this.content,
                    vipStatus: this.vipStatus,
                    progress: this.progress,
                    page: this.page,
                    limit: this.limit
                })

                /*
                * 这里面我用了一个 array 存储结果，
                * 是因为 拿到的数据不符合我的要求， 不是我想要的格式
                * 所以我 遍历每一条评论，并对其进行修改
                *
                * table需要的 数据格式请先用`死数据`做测试，充分了解需要的数据格式之后再进行代码编写
                *
                * */
                let array = []; // 保存处理后的查询结果
                axios.post("http://localhost:8080/bilibili/search", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    // 下面是处理过程
                    let data = response.data.data; //我想要的评论数据在这里面
                    for (var index in data){        // 因为是对象类型，所以我遍历它的索引index（想知道数据长啥样，自己console.log）
                        let comment = data[index]   // 然后 通过index获取每一条评论
                        array = array.concat({      // 将每一条处理后的结果追加到 array数组中
                            key: comment.id,        // 这里面是我需要的数据格式 ！！！！！！！！！！ 自己也搞明白自己需要的！！！ very impotent！
                            uname: comment.uname,
                            content: comment.content,
                            tags: comment.vipStatus === 0 ? "普通用户" : comment.vipStatus === 1 ? "大会员" : "神秘大会员",
                            progress: comment.progress,
                            date: this.translate(comment.ctime)
                        })
                    }
                    // 总条数和每条评论不在一起， 在 response.data.count里面， 而且也不需要循环， 我们赋给total
                    this.pagination.total = response.data.count
                    this.data = array // 把处理结果赋值给 data （这才是和表格绑定的数据！）
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