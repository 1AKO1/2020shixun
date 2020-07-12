<!--页面结构-->
<template>
    <div>
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="评论数据"
                sub-title="かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜"
        />

        <MyButton  />

        <a-button block>
            加载更多
        </a-button>
    </div>
</template>


<!--一些属性和方法-->
<script>
    import MyButton from '../../components/MyButton'
    export default {
        name: "index",
        components:{
            MyButton
        },
        data(){
            return{
                formInline: {
                    user: '',
                    password: '',

                },
                list: [
                    {id : 1, msg: "wahahah1"},
                    {id : 2, msg: "wahahah2"},
                    {id : 3, msg: "wahahah3"},
                    {id : 4, msg: "wahaha4h"}
                ]
            }
        },
        methods:{
            handleSubmit(e) {
                console.log(this.formInline);
                console.log(e)
            },
            test(props){
                console.log(props)
            }
        }
    }
</script>

<!--页面的样式-->
<style scoped>

</style>