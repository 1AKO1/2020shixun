<template>
    <a-form-model layout="inline" :model="formInline" @submit="handleSubmit" @submit.native.prevent>


        <a-select
                label-in-value
                :default-value="{ key: '账户查询' }"
                style="width: 120px"
                @change="handleChange"
        >
            <a-select-option value="searchbyuid">
                账户
            </a-select-option>
            <a-select-option value="searchbynickName">
                昵称
            </a-select-option>
            <a-select-option value="searchbyAddress">
                地址
            </a-select-option>
        </a-select>

<!--        输入框-->
        <a-form-model-item>
            <a-input v-model="SearchInfo" placeholder="请输入查询名称">
                <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
            </a-input>
        </a-form-model-item>

        <a-form-model-item>


            <a-button
                    type="primary"
                    html-type="submit"
                    v-on:click="handleSubmit"
            >
                {{  searchLabel  }}
            </a-button>
        </a-form-model-item>

    </a-form-model>
</template>

<script>
    import axios from "axios"
    import qs from "qs";
    export default {
        name: "index",
        data(){
            return{
                SearchInfo: null,
                formInline: {
                    user: '',
                    password: '',
                },
                Myoption: null,
                searchLabel: '账户查询',
                bool: false
            }
        },

        methods: {
            handleSubmit() {
                // console.log(this.formInline);
                // console.log(e)
                const url = "http://localhost:8080/bilibili/commentlist"  // API
                const params = {  // 参数
                    page: this.SearchInfo,
                    limit: this.SearchInfo
                }
                let data = qs.stringify(params);

                axios.post(url, data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    console.log(response)
                })
                .catch(error => {
                    console.log(error)
                })

            },
            handleChange(value) {
                console.log(value)
                console.log(value.key);
                this.Myoption = value.key;
                this.searchLabel = value.label + "查询"
            },
        },
    }
</script>

<style scoped>

</style>