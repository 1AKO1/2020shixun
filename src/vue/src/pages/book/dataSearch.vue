<template>
    <div>
        <!--        表头-->
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="豆瓣图书信息查询"
                sub-title="本站不禁IP哦~"
        />

        <a-form layout="inline">

            <a-form-item
                    label="书名"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--
                    v-model里 是和 data 绑定的变量名
                -->
                <a-input placeholder="请输入书名关键词" style="width: 230px" v-model="title"/>
            </a-form-item>
            <a-form-item
                    label="作者"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--
                    v-model里 是和 data 绑定的变量名
                -->
                <a-input placeholder="请输入作者姓名" style="width: 230px" v-model="author"/>
            </a-form-item>
            <!--        查询按钮-->
            <a-form-item :wrapper-col="buttonItemLayout.wrapperCol" style="margin-right: 100px">
                <a-button type="primary" v-on:click="handleSubmit">
                    立即查询
                </a-button>
            </a-form-item>
        </a-form>

        <a-collapse :bordered="false" :style="style_collapse">
            <template #expandIcon="props">
                <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
            </template>
<!--            取消箭头展示-->
<!--            :show-arrow="false"-->
            <a-collapse-panel
                    header="更多选项："

            >
                <a-form layout="inline">
                    <a-form-item
                            label="出版社"
                            :label-col="formItemLayout.labelCol"
                            :wrapper-col="formItemLayout.wrapperCol"
                    >
                        <a-input placeholder="请输入出版社名称" style="width: 200px" v-model="press"/>
                    </a-form-item>
                    <a-form-item
                            label="类别"
                            :label-col="formItemLayout.labelCol"
                            :wrapper-col="formItemLayout.wrapperCol"
                    >
                        <!--                @change中是当option改变时执行的函数
                                            可以自行打印参数（应该有默认参数）看看获得的是什么
                                            如果我记得没错 会获得 每个选项的 value 和 内容
                                            例如：
                                                {key： -1, content: 全部查询}
                      -->
                        <a-select mode="multiple"
                                  style="width: 420px"
                                  placeholder="请选择书籍类别（可多选）"
                                  @change="onChangeType"
                        >
                            <a-select-option v-for='type in options_type' :key="type">
                                {{type}}
                            </a-select-option>
                        </a-select>
                    </a-form-item>
                    <a-form-item
                            label="ISBN（精确查询）"
                            :label-col="formItemLayout.labelCol"
                            :wrapper-col="formItemLayout.wrapperCol"
                    >
                        <a-input placeholder="请输入ISBN号" style="width: 180px" v-model="isbn"/>
                    </a-form-item>
                </a-form>

                <a-form layout="inline">
                    <a-form-item
                            label="评价人数≥"
                            :label-col="formItemLayout.labelCol"
                            :wrapper-col="formItemLayout.wrapperCol"
                    >
                        <a-slider
                                v-decorator="['slider']"
                                :marks="{1000:'1千', 10000: '1万',50000: '5万', 100000: '10万'}"
                                style="width: 400px"
                                :default-value="0"
                                :max="100000"
                                :step="1000"
                                @change="onChangeVotes"
                        />
                    </a-form-item>

                    <a-form-item
                            label="豆瓣评分"
                            :label-col="formItemLayout.labelCol"
                            :wrapper-col="formItemLayout.wrapperCol"
                    >
                        <a-slider
                                range
                                :marks="{1:'很差', 3:'较差', 5:'还行', 7:'推荐', 9:'力荐'}"
                                style="width: 400px"
                                :max="10"
                                :step="0.1"
                                :default-value="[0, 10]"
                                @change="onChangeScore"
                        />

                    </a-form-item>

                </a-form>
            </a-collapse-panel>
        </a-collapse>

        <a-tabs default-active-key="1" @change="callback">
            <a-tab-pane key="1" tab="书籍信息">
                <a-table :columns="columns_book" :data-source="tableData_book0" :loading="loading" v-on:change="nextPage"
                         :pagination="pagination">
                    <a slot="name" slot-scope="text">{{ text }}</a>
                    <span slot="customTitle"><a-icon type="smile-o"/> Name</span>
<!--                    给豆瓣评分设置标签样式-->
                    <span slot="tags" slot-scope="score">
                        <a-tag :color="score > 8.0 ? '#DC143C' : score > 6.0 ? '#fb7299' : score > 4.0 ? '#00a1d6' : score > 2.0 ? '#0066CC' : score > 0.0 ? '#0033CC' : '#000000'">
                            {{ score }}
                        </a-tag>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane key="2" tab="热评" force-render>
                <a-table :columns="columns_hotComm" :data-source="tableData_hotComm0" :loading="loading" v-on:change="nextPage"
                         :pagination="pagination">
                    <a slot="name" slot-scope="text">{{ text }}</a>
                    <span slot="customTitle"><a-icon type="smile-o"/> Name</span>
<!--                    给评论分数设置标签样式-->
                    <span slot="tags" slot-scope="hotCommScore">
                        <a-tag :color="hotCommScore === '力荐' ? '#DC143C' : hotCommScore === '推荐' ? '#fb7299' : hotCommScore === '还行' ? '#00a1d6' : hotCommScore === '较差' ? '#0066CC' : hotCommScore === '很差' ? '#0033CC' : '#000000'">
                            {{ hotCommScore }}
                        </a-tag>
                    </span>
                </a-table>

            </a-tab-pane>
        </a-tabs>

    </div>

</template>

<script>
    import qs from "qs";
    import axios from "axios"

    // 表格的字段，按格式来
    const columns_book = [
        {
            title:'书名',
            dataIndex:'title',
            key:'title',
        },
        {
            title:'作者',
            dataIndex:'author',
            key:'author',
        },
        {
            title:'出版社',
            dataIndex:'press',
            key:'press',
        },
        {
            title:'类型',
            dataIndex:'type',
            key:'type',
        },
        {
            title:'评分',
            dataIndex:'score',
            key:'score',
            scopedSlots: { customRender: 'tags'},
        },
        {
            title:'评分人数',
            dataIndex:'votes',
            key:'votes',
        },
    ];
    const columns_hotComm = [
        {
            title:'书名',
            dataIndex:'title',
            key:'title',
        },
        {
            title:'评论内容',
            dataIndex:'hotCommContent',
            key:'hotCommContent',
        },
        {
            title:'评论人',
            dataIndex:'hotCommPeople',
            key:'hotCommPeople',
        },
        {
            title:'评分',
            dataIndex:'hotCommScore',
            key:'hotCommScore',
            scopedSlots: { customRender: 'tags'},
        },
        {
            title:'点赞人数',
            dataIndex:'hotCommVotes',
            key:'hotCommVotes',
        },
    ];

    // 表格测试数据，同样的，按表的字段格式来
    const tableData_book0 = [
        {
            key: 1,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 10.0,
            votes: 7777777,
        },
        {
            key: 2,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 8.0,
            votes: 7777777,
        },
        {
            key: 3,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 6.0,
            votes: 7777777,
        },
        {
            key: 4,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 4.0,
            votes: 7777777,
        },
        {
            key: 5,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 2.0,
            votes: 7777777,
        },
        {
            key: 6,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 0.0,
            votes: 7777777,
        },
        {
            key: 7,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 10.0,
            votes: 7777777,
        },
        {
            key: 8,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 8.0,
            votes: 7777777,
        },
        {
            key: 9,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 6.0,
            votes: 7777777,
        },
        {
            key: 10,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 4.0,
            votes: 7777777,
        },
        {
            key: 11,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 2.0,
            votes: 7777777,
        },
        {
            key: 12,
            title: '神威天将军：从插秧到我扎我自己',
            author: '李嘉威',
            press: 'Timi出版社',
            type: '超神之路',
            score: 0.0,
            votes: 7777777,
        },
    ];
    const tableData_hotComm0 = [
        {
            key: 1,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '力荐',
            hotCommVotes: 77777,
        },
        {
            key: 2,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '推荐',
            hotCommVotes: 77777,
        },
        {
            key: 3,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '还行',
            hotCommVotes: 77777,
        },
        {
            key: 4,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '较差',
            hotCommVotes: 77777,
        },
        {
            key: 5,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '很差',
            hotCommVotes: 77777,
        },
        {
            key: 6,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '没眼看',
            hotCommVotes: 77777,
        },
        {
            key: 7,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '力荐',
            hotCommVotes: 77777,
        },
        {
            key: 8,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '推荐',
            hotCommVotes: 77777,
        },
        {
            key: 9,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '还行',
            hotCommVotes: 77777,
        },
        {
            key: 10,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '较差',
            hotCommVotes: 77777,
        },
        {
            key: 11,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '很差',
            hotCommVotes: 77777,
        },
        {
            key: 12,
            title: '神威天将军：从插秧到我扎我自己',
            hotCommContent: '李嘉威牛逼，李嘉威冲鸭冲鸭冲鸭冲鸭~',
            hotCommPeople: 'ljw',
            hotCommScore: '没眼看',
            hotCommVotes: 77777,
        },
    ];

    export default {
        name: "dataSearch",

        // 组件的状态、属性或者变量
        data() {
            return {
                // 类型多选选择
                options_type:[
                    "文学","流行","文化","生活","经管","科技"
                ],

                // 折叠面板背景颜色数据
                style_collapse: 'background: #FFFFFF; border: 0',

                // 查询数据
                title: null,
                author: null,
                press: null,
                numOfType:null,
                type: [],
                isbn: null,
                minVotes: 0,
                minScore: 0.0,
                maxScore: 10.0,

                // 表格数据
                tableData_book: [],  // 请求结果我会放进去
                tableData_hotComm: [],

                // 表格测试数据
                tableData_book0,
                tableData_hotComm0,

                // 表格字段
                columns_book,
                columns_hotComm,

                // 分页参数和数据
                loading: false, // 是否显示加载中图标， 不用管。
                page: 1,    // 当前页数
                limit: 10,  // 每页多少条
                pagination: {    // 分页的设置参数
                    total: 0,   // 把count 传给 total！
                }
            }
        },

        //  computed是组件的计算属性 这里面不用管 antd写好了的，我们也不需要动
        computed: {
            formItemLayout() {
                const {formLayout} = this;
                return formLayout === 'horizontal'
                    ? {
                        labelCol: {span: 4},
                        wrapperCol: {span: 14},
                    }
                    : {};
            },
            buttonItemLayout() {
                const {formLayout} = this;
                return formLayout === 'horizontal'
                    ? {
                        wrapperCol: {span: 14, offset: 4},
                    }
                    : {};
            }
        },

        // 生命周期钩子
        mounted() {
            this.loading = true;

            const searchData = qs.stringify( {
                title: this.title,
                author: this.author,
                press: this.press,
                numOfType: this.numOfType,
                type: this.type,
                isbn: this.isbn,
                minVotes: this.minVotes,
                minScore: this.minScore,
                maxScore: this.maxScore,

                page: this.page,
                limit: this.limit
            })

            // 保存处理后的查询结果
            let result_book = [];
            let result_hotComm = [];
            axios.post("http://localhost:8080/doubanBook/search", searchData, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    // 处理过程：
                    // response对应服务器端返回的回应结果，
                    // 第一个data是其一个属性，表示服务器端回应函数的返回值，在服务器端里对应TableData，
                    // 第二个data则是TableData类的一个属性，臭弟弟李嘉威起名太随意了
                    let callbackDataList = response.data.data;
                    for (var index in callbackDataList){
                        let callbackData = callbackDataList[index]
                        result_book = result_book.concat({
                            key: callbackData.id,
                            title: callbackData.title,
                            author: callbackData.author,
                            press: callbackData.press,
                            type: callbackData.type,
                            score: callbackData.score,
                            votes: callbackData.votes,
                        })
                        result_hotComm = result_hotComm.concat({
                            title: callbackData.title,
                            hotCommContent: callbackData.hotCommContent,
                            hotCommPeople: callbackData.hotCommPeople,
                            hotCommScore: callbackData.hotCommScore,
                            hotCommVotes: callbackData.hotCommVotes,
                        })
                    }
                    // 服务器端TableData类中的count属性
                    this.pagination.total = response.data.count
                    // 把处理后的查询结果赋给全局变量中的tableData（与表格绑定的数据）
                    this.tableData_book = result_book
                    this.tableData_hotComm = result_hotComm
                    this.loading = false
                }).catch(error => {
                console.log(error)
                this.loading = false
            })
        },

        methods: {

            // 选择分类时，获取所选分类
            onChangeType(typeList) {
                this.numOfType = typeList.length
                this.type = typeList;
                // console.log(this.numOfType)
                // console.log(this.type);
                // console.log(this.type[0]);
            },

            // 滑动评价人数时，修改最小评价人数
            onChangeVotes(value) {
                this.minVotes = value;
                // console.log(this.minVotes);
            },

            // 滑动评分时，修改评分区间数据
            onChangeScore(scoreRange) {
                this.minScore = scoreRange[0];
                this.maxScore = scoreRange[1];
                // console.log(this.minScore);
                // console.log(this.maxScore);
            },

            // 点击 立即查询 后触发
            handleSubmit() {
                // console.log(this.title);
                // console.log(this.author);
                // console.log(this.press);
                // console.log(this.numOfType);
                // console.log(this.type);
                // console.log(this.isbn);
                // console.log(this.minVotes);
                // console.log(this.minScore);
                // console.log(this.maxScore);

                this.loading = true;

                const searchData = qs.stringify( {
                    title: this.title,
                    author: this.author,
                    press: this.press,
                    numOfType: this.numOfType,
                    type: this.type,
                    isbn: this.isbn,
                    minVotes: this.minVotes,
                    minScore: this.minScore,
                    maxScore: this.maxScore,

                    page: this.page,
                    limit: this.limit
                })

                // 保存处理后的查询结果
                let result_book = [];
                let result_hotComm = [];
                axios.post("http://localhost:8080/doubanBook/search", searchData, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        // 处理过程：
                        // response对应服务器端返回的回应结果，
                        // 第一个data是其一个属性，表示服务器端回应函数的返回值，在服务器端里对应TableData，
                        // 第二个data则是TableData类的一个属性，臭弟弟李嘉威起名太随意了
                        let callbackDataList = response.data.data;
                        for (var index in callbackDataList){
                            let callbackData = callbackDataList[index]
                            result_book = result_book.concat({
                                //  唯一标识 不在表格中展示

                                key: callbackData.id,
                                // 书籍表格展示数据
                                title: callbackData.title,
                                author: callbackData.author == '' ? '佚名' : callbackData.author,
                                press: callbackData.press == '' ? '无' : callbackData.press,
                                type: callbackData.type,
                                score: callbackData.score,
                                votes: callbackData.votes,
                            })
                            result_hotComm = result_hotComm.concat({
                                // 同上
                                key: callbackData.id,

                                // 评论表格展示数据
                                title: callbackData.title,
                                hotCommContent: callbackData.hotCommContent,
                                hotCommPeople: callbackData.hotCommPeople,
                                hotCommScore: callbackData.hotCommScore == '' ? '没眼看' : callbackData.hotCommScore,
                                hotCommVotes: callbackData.hotCommVotes,
                            })
                        }
                        // 服务器端TableData类中的count属性
                        this.pagination.total = response.data.count
                        // 把处理后的查询结果赋给全局变量中的tableData（与表格绑定的数据）
                        this.tableData_book = result_book
                        this.tableData_hotComm = result_hotComm
                        this.loading = false
                    }).catch(error => {
                        console.log(error)
                    this.loading = false
                })
            },

            // 下一页操作
            nextPage(pagination){
                // 获取当前页码并进行更新
                this.page = pagination.current;

                // 与handleSubmit函数一样
                this.loading = true;

                const searchData = qs.stringify( {
                    title: this.title,
                    author: this.author,
                    press: this.press,
                    numOfType: this.numOfType,
                    type: this.type,
                    isbn: this.isbn,
                    minVotes: this.minVotes,
                    minScore: this.minScore,
                    maxScore: this.maxScore,

                    page: this.page,
                    limit: this.limit
                })

                // 保存处理后的查询结果
                let result_book = [];
                let result_hotComm = [];
                axios.post("http://localhost:8080/doubanBook/search", searchData, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        // 处理过程：
                        // response对应服务器端返回的回应结果，
                        // 第一个data是其一个属性，表示服务器端回应函数的返回值，在服务器端里对应TableData，
                        // 第二个data则是TableData类的一个属性，臭弟弟李嘉威起名太随意了
                        let callbackDataList = response.data.data;
                        for (var index in callbackDataList){
                            let callbackData = callbackDataList[index]
                            result_book = result_book.concat({
                                key: callbackData.id,
                                title: callbackData.title,
                                author: callbackData.author,
                                press: callbackData.press,
                                type: callbackData.type,
                                score: callbackData.score,
                                votes: callbackData.votes,
                            })
                            result_hotComm = result_hotComm.concat({
                                title: callbackData.title,
                                hotCommContent: callbackData.hotCommContent,
                                hotCommPeople: callbackData.hotCommPeople,
                                hotCommScore: callbackData.hotCommScore,
                                hotCommVotes: callbackData.hotCommVotes,
                            })
                        }
                        // 服务器端TableData类中的count属性
                        this.pagination.total = response.data.count
                        // 把处理后的查询结果赋给全局变量中的tableData（与表格绑定的数据）
                        this.tableData_book = result_book
                        this.tableData_hotComm = result_hotComm
                        this.loading = false
                    }).catch(error => {
                    console.log(error)
                    this.loading = false
                })
            }
        }
    };
</script>

<style scoped>

</style>