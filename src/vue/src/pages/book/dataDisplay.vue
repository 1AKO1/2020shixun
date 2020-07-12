<template>
    <div>
        <!--        表头-->
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="豆瓣图书信息查询"
                sub-title="本站不禁IP哦~"
        />

        <!--        查询按钮-->
        <a-form layout="inline">

            <a-form-item
                    label="书名"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--
                    v-model里 是和 data 绑定的变量名
                -->
                <a-input placeholder="请输入图书的关键词" style="width: 150px" v-model="bookname"/>
            </a-form-item>

            <a-form-item
                    label="作者"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入作者姓名" style="width: 130px" v-model="author"/>
            </a-form-item>
        </a-form>
        <!--            和上面那个一样-->
        <a-form>
            <a-form-item
                    label="出版社"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-input placeholder="请输入出版社名称" style="width: 130px" v-model="presshouse"/>
            </a-form-item>

            <a-form-item
                    label="图书类别"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <!--                @change中是当option改变时执行的函数
                                    可以自行打印参数（应该有默认参数）看看获得的是什么
                                    如果我记得没错 会获得 每个选项的 value 和 内容
                                    例如：
                                        {key： -1, content: 全部查询}
              -->
                <a-cascader :options="options"
                            change-on-select
                            style="width: 120px"
                            :default-value="['all']"
                            @change="onChangeFenlei"/>
            </a-form-item>

        </a-form>

        <a-form layout="inline">
            <a-form-item
                    label="折扣查询:≥"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
            >
                <a-slider
                        v-decorator="['slider']"
                        :marks="{0:'0%',20:'20%',40: '40%',60: '60%', 80: '80%'}"
                        style="width: 250px"
                        @change="onChangeDiscount"
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
                        :step="10"
                        :default-value="[3, 990]"
                        @change="onChangeBetween"
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
        <a-table :columns="columns" :data-source="data" :loading="loading" v-on:change="nextPage"
                 :pagination="pagination">
            <a slot="name" slot-scope="text">{{ text }}</a>
            <span slot="customTitle"><a-icon type="smile-o"/> Name</span>
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
    export default {
        name: "dataDisplay"
    }
</script>

<style scoped>

</style>