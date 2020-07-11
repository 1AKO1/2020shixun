<template>
    <div>
        <!--        表头-->
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="图书信息查询"
                sub-title="当当各分类图书信息TOP榜"
        />

        <!--        查询按钮-->
        <a-form layout="inline">

            <a-form-item
                    label="书名查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--
                    v-model里 是和 data 绑定的变量名
                -->
                <a-input placeholder="请输入图书的关键词" style="width: 150px" v-model="content"/>
            </a-form-item>

            <a-form-item
                    label="作者查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入作者姓名" style="width: 130px" v-model="uname"/>
            </a-form-item>

            <a-form-item
                    label="出版社查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入出版社名称" style="width: 130px" v-model="uname"/>
            </a-form-item>

            <a-form-item
                    label="分类查询"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--                @change中是当option改变时执行的函数
                                    可以自行打印参数（应该有默认参数）看看获得的是什么
                                    如果我记得没错 会获得 每个选项的 value 和 内容
                                    例如：
                                        {key： -1, content: 全部查询}
                -->
                <a-cascader  :options="options" change-on-select style="width: 120px" @change="onChange" />

            </a-form-item>
            <!--            和上面那个一样-->
        </a-form>
        <a-form layout="inline">
            <a-form-item
                    label="折扣查询:≥"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                  <a-slider
                      v-decorator = "['slider']"
                      :marks = "{0:'0%',20:'20%',40: '40%',60: '60%', 80: '80%'}"
                      style="width: 250px"
                      />
            </a-form-item>

            <a-form-item
                    label="价格区间"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-slider
                     range
                     :marks="marks"
                     style="width: 250px"
                     :min="3"
                     :max="1000"
                     step="10"
                     :default-value="[3, 990]"
                />

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
            title: '书名',
            dataIndex: 'uname',
            key: 'uname',
        },
        {
            title: '作者',
            dataIndex: 'author',
            key: 'author',
        },

        // {
        //     title: '会员状态',
        //     key: 'tags',
        //     dataIndex: 'tags',
        //     scopedSlots: { customRender: 'tags' },
        // },
        {
            title: '推荐指数',
            dataIndex: 'progress',
            key: 'progress',
        },
        {
            title: '价格',
            dataIndex: 'price',
            key: 'price',
        },
        {
            title: '出版日期',
            dataIndex: 'date',
            key: 'date',
        },
    ];

    export default {
        name: "dataSearch",
        // 组件的状态（属性）（变量）
        data(){
            return{
                //line 193~226 级联选择
                options: [
                    {
                        value: '童书',
                        label: '童书',
                        children: [
                            {
                                value: '中国儿童文学',
                                label: '中国儿童文学',
                            },

                            {  value: '绘本/图画书',
                                label: '绘本/图画书',
                            },
                            {  value: '外国儿童文学',
                                label: '外国儿童文学',
                            },
                            {  value: '科普/百科',
                                label: '科普/百科',
                            },
                            {  value: '动漫/卡通',
                                label: '动漫/卡通',
                            },
                            {  value: '幼儿启蒙',
                                label: '幼儿启蒙',
                            },

                        ],
                    },
                    {
                        value: '中小学教辅',
                        label: '中小学教辅',
                        children: [
                            {
                                value: '中小学阅读',
                                label: '中小学阅读',
                            },
                            {
                                value: '语文作文',
                                label: '语文作文',
                            },

                            {
                                value: '高考',
                                label: '高考',
                            },
                            {
                                value: '高中通用',
                                label: '高中通用',
                            },
                            {
                                value: '出国留学指南',
                                label: '出国留学指南',
                            },
                            {
                                value: '学习方法',
                                label: '学习方法',
                            },


                        ],
                    },
                    {
                        value: '小说',
                        label: '小说',
                        children: [
                            {
                                value: '社会',
                                label: '社会',
                            },
                            {
                                value: '世界名著',
                                label: '世界名著',
                            },
                            {
                                value: '外国小说',
                                label: '外国小说',
                            },
                            {
                                value: '科幻',
                                label: '科幻',
                            },
                            {
                                value: '官场',
                                label: '官场',
                            },
                            {
                                value: '乡土',
                                label: '乡土',
                            },

                        ],
                    },
                    {
                        value: '文学',
                        label: '文学',
                        children: [
                            {
                                value: '中国现当代随笔',
                                label: '中国现当代随笔',
                            },
                            {
                                value: '外国随笔',
                                label: '外国随笔',
                            },
                            {
                                value: '中国古诗词',
                                label: '中国古诗词',
                            },
                            {
                                value: '名家作品',
                                label: '名家作品',
                            },
                            {
                                value: '文集',
                                label: '文集',
                            },
                            {
                                value: '中国古代随笔',
                                label: '中国古代随笔',
                            },


                        ],
                    },
                    {
                        value: '成功/励志',
                        label: '成功/励志',
                        children: [
                            {
                                value: '成功/激励',
                                label: '成功/激励',
                            },
                            {
                                value: '人生哲学',
                                label: '人生哲学',
                            },
                            {
                                value: '人际交往',
                                label: '人际交往',
                            },
                            {
                                value: '心灵与修养',
                                label: '心灵与修养',
                            },
                            {
                                value: '智商/智谋',
                                label: '智商/智谋',
                            },
                            {
                                value: '性格与习惯',
                                label: '性格与习惯',
                            },
                        ],
                    },
                    {
                        value: '历史',
                        label: '历史',
                        children: [
                            {
                                value: '中国史',
                                label: '中国史',
                            },
                            {
                                value: '历史普及读物',
                                label: '历史普及读物',
                            },
                            {
                                value: '世界史',
                                label: '世界史',
                            },
                            {
                                value: '历史随笔',
                                label: '历史随笔',
                            },
                            {
                                value: '史家名著',
                                label: '史家名著',
                            },
                            {
                                value: '文物考古',
                                label: '文物考古',
                            },
                        ],
                    },
                    {
                        value: '外语',
                        label: '外语',
                        children: [
                            {
                                value: '英语考试',
                                label: '英语考试',
                            },
                            {
                                value: '英语读物',
                                label: '英语读物',
                            },
                            {
                                value: '英语综合教程',
                                label: '英语综合教程',
                            },
                            {
                                value: '日语',
                                label: '日语',
                            },
                            {
                                value: '韩语',
                                label: '韩语',
                            },
                            {
                                value: '法语',
                                label: '法语',
                            },
                        ],
                    },
                    {
                        value: '亲子/家教',
                        label: '亲子/家教',
                        children: [
                            {
                                value: '家教方法',
                                label: '家教方法',

                            },
                            {
                                value: '家教理论',
                                label: '家教理论',

                            },
                            {
                                value: '培育男孩',
                                label: '培育男孩',

                            },
                            {
                                value: '亲子关系',
                                label: '亲子关系',

                            },
                            {
                                value: '培育女孩',
                                label: '培育女孩',

                            },
                            {
                                value: '成功案例',
                                label: '成功案例',

                            },
                        ],
                    },
                    {
                        value: '考试',
                        label: '考试',
                        children: [
                            {
                                value: '考研',
                                label: '考研',
                            },
                            {
                                value: '建筑工程类职称考试',
                                label: '建筑工程类职称考试',
                            },
                            {
                                value: '其他公职类考试',
                                label: '其他公职类考试',
                            },
                            {
                                value: '司法考试',
                                label: '司法考试',
                            },
                            {
                                value: '自学考试',
                                label: '自学考试',
                            },
                            {
                                value: '计算机考试',
                                label: '计算机考试',
                            },
                        ],
                    },
                    {
                        value: '艺术',
                        label: '艺术',
                        children: [
                            {
                                value: '绘画',
                                label: '绘画',

                            },
                            {
                                value: '书法/篆刻',
                                label: '书法/篆刻',

                            },
                            {
                                value: '音乐',
                                label: '音乐',

                            },
                            {
                                value: '设计',
                                label: '设计',

                            },
                            {
                                value: '摄影',
                                label: '摄影',

                            },
                            {
                                value: '雕塑',
                                label: '雕塑',

                            },
                        ],
                    },
                    {
                        value: '哲学/宗教',
                        label: '哲学/宗教',
                        children: [
                            {
                                value: '哲学',
                                label: '哲学',
                            },
                            {
                                value: '宗教',
                                label: '宗教',
                            },
                        ],
                    },
                    {
                        value: '青春文学',
                        label: '青春文学',
                        children: [
                            {
                                value: '校园',
                                label: '校园',

                            },
                            {
                                value: '大陆原创',
                                label: '大陆原创',

                            },
                            {
                                value: '轻小说',
                                label: '轻小说',

                            },
                            {
                                value: '其他国外青春文学',
                                label: '其他国外青春文学',

                            },
                            {
                                value: '古代言情',
                                label: '古代言情',

                            },
                        ],
                    },
                    {
                        value: '管理',
                        label: '管理',
                        children: [
                            {
                                value: '一般管理学',
                                label: '一般管理学',
                            },
                            {
                                value: '会计',
                                label: '会计',
                            },
                            {
                                value: '电子商务',
                                label: '电子商务',
                            },
                            {
                                value: '战略管理',
                                label: '战略管理',
                            },
                            {
                                value: '商业史传',
                                label: '商业史传',
                            },
                            {
                                value: '管理信息系统',
                                label: '管理信息系统',
                            },
                        ],
                    },
                ],

                //价格区间 选择
                marks: {
                    3: '3￥',
                    150: '150￥',
                    400: '400￥',
                    800: '800￥',
                 },

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
            onChange(value) {
                console.log(value);
            },
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
    .code-box-demo .ant-slider {
        margin-bottom: 16px;
    }
</style>