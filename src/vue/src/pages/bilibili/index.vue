<template>
    <div style="padding-bottom: 10px">
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="评论数据"
                sub-title="かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜"
        />
        <Comment v-for="commentItem in commentList" v-bind:key="commentItem.id" v-bind:commonInfo="commentItem"/>

        <a-button block v-on:click="IwantMore(page++, limit)">
            加载更多
        </a-button>
    </div>
</template>

<script>
    import Comment from "../../components/bilibili/Comment";
    import axios from "axios"
    // eslint-disable-next-line no-unused-vars
    import qs from "qs"
    export default {
        name: "index",
        components:{
            Comment
        },
        data(){
            return{
                page: 1,
                limit: 10,
                commentList: []
            }
        },
        mounted() {
            console.log("发送请求");
            let data = qs.stringify({
                page: this.page,
                limit: this.limit
            });
            this.page += 1;
            axios.post("http://localhost:8080/bilibili/commentlist", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
            .then((response) => {
                console.log(response)
                data = response.data.data;
                this.commentList = data;
                console.log(data)

            }).catch((error) => {
                console.log(error)
            })
        },
        methods: {
            IwantMore:function(page, limit){
                let data = qs.stringify({
                    page: page,
                    limit: limit
                });
                axios.post("http://localhost:8080/bilibili/commentlist", data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                    .then( response => {
                        data = response.data.data;
                        console.log(this);
                        console.log(data);
                        this.commentList = this.commentList.concat(data);

                    }).catch( error => {
                    console.log(error)
                })
            }
        }
    }
</script>

<style scoped>

</style>