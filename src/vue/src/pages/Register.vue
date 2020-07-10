<template>
    <div class="container">
        <div class="top">
            <div class="header">
                <a href="/">
                    <!--                    <img src="../../assets/info.svg" alt="logo" class="logo">-->
                    <span class="title">诸位大佬项目展示系统</span>
                </a>
            </div>
            <div class="desc">
                基础后端管理系统
            </div>
        </div>
        <div class="main">
            <a-form
                    id="formLogin"
                    class="user-layout-login"
                    ref="formLogin"
                    :form="form"

            >
                <a-alert v-if="isLoginError" type="error" message="密码不一致" showIcon></a-alert>
                <a-form-item>
                    <a-input
                            size="large"
                            type="text"
                            v-model="userName"
                            placeholder="请输入账户名"
                            v-decorator="[
                                'loginAccount',
                                {rules: [{required: true,message:'账户名称不能为空！'},{max:20,message:'账号长度不能超过20个字符！'}]
                                    ,validateTrigger: 'blur'}
                            ]">
                        <a-icon slot="prefix" type="user" :style="{color: 'rgb(24,144,255)'}"/>
                    </a-input>

                </a-form-item>
                <a-form-item>

                    <a-input
                            size="large"
                            type="text"
                            v-model="nickName"
                            placeholder="请输入昵称"
                            v-decorator="[
                                'loginNickName',
                                {rules: [{required: true,message:'你真的不给自己起个昵称吗？'},{max:20,message:'账号长度不能超过20个字符！'}]
                                    , validateTrigger: 'blur'}
                            ]">
                        <a-icon slot="prefix"  type="crown" :style="{color: 'rgb(24,144,255)'}" />
                    </a-input>

                </a-form-item>
                <a-form-item>
                    <a-input
                            size="large"
                            type="password"
                            v-model="userPassword"
                            placeholder="请输入密码"
                            v-decorator="[
                                'loginPassword',
                                {rules: [{required: true,message:'密码不能为空！'},{max:20,message:'密码长度不能超过20个字符！'}]
                                , validateTrigger: 'blur'}
                            ]">
                        <a-icon slot="prefix" type="lock" :style="{color: 'rgb(24,144,255)'}"/>
                    </a-input>
                </a-form-item>
                <a-form-item>
                    <a-input
                            size="large"
                            type="password"
                            v-model="rePassword"
                            placeholder="请确认密码"
                            v-decorator="['Re', { rules: [{ required: true, message: '请确认密码' }, {max:20,message:'密码长度不能超过20个字符！'}] }]">
                        <a-icon slot="prefix" type="lock" :style="{color: 'rgb(24,144,255)'}"/>
                    </a-input>
                </a-form-item>
                <a-form-item>
                    <a-select
                            v-decorator="[
                                'gender',
                                { rules: [{ required: true, message: 'Please select your gender!' }] },
                            ]"
                            placeholder="请选择性别"
                            size="large"
                            @change="handleSelectChange"
                            >
                        <a-select-option value="男">
                            男
                        </a-select-option>
                        <a-select-option value="女">
                            女
                        </a-select-option>
                    </a-select>
                </a-form-item>

                <a-form-item>
                    <a-input
                            size="large"
                            type="text"
                            v-model="userEmail"
                            placeholder="请输入邮箱"
                            v-decorator="[
                                'userEmail',
                                {rules: [{required: true,message:'存个邮箱呗~'}]
                                    , validateTrigger: 'blur'}
                            ]">
                        <a-icon slot="prefix"  type="mail" :style="{color: 'rgb(24,144,255)'}" />
                    </a-input>
                </a-form-item>

                <a-form-item>
                    <a-input
                            size="large"
                            type="text"
                            v-model="userAddress"
                            placeholder="请输入地址"
                            v-decorator="[
                                'userAddress',
                                {rules: [{required: true,message:'存个地址呗~'}]
                                    , validateTrigger: 'blur'}
                            ]">
                        <a-icon slot="prefix"  type="home" :style="{color: 'rgb(24,144,255)'}" />
                    </a-input>
                </a-form-item>

                <a-form-item>
                    <a-input
                            size="large"
                            type="text"
                            v-model="userAge"
                            placeholder="请输入年龄"
                            v-decorator="[
                                'userAge',
                                {rules: [{required: true,message:'告诉我你多大呗~'}]
                                    ,validateTrigger: 'blur'}
                            ]">
                        <a-icon slot="prefix"  type="reddit" :style="{color: 'rgb(24,144,255)'}" />
                    </a-input>
                </a-form-item>

                <a-form-item style="margin-top: 24px;">
                    <a-button
                            size="large"
                            type="primary"
                            htmlType="submit"
                            class="login-button"
                            @click="register"
                    >立即注册
                    </a-button>
                </a-form-item>
            </a-form>
        </div>
        <div class="footer">
            <!--            <div class="links">-->
            <!--                <a href="_self">帮助</a>-->
            <!--                <a href="_self">隐私</a>-->
            <!--                <a href="_self">条款</a>-->
            <!--            </div>-->
            <div class="copyright">
                <a-icon type="copyright"/>
                队友说
                <a-icon type="man"/>
                的都对
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    // import router from "../router";
    import qs from 'qs';

    export default {
        name: 'Register',
        data() {
            return {
                form: this.$form.createForm(this),
                isLoginError: false,
                // loginAccount: null,
                userName:null,
                nickName:null,
                userPassword:null,
                rePassword:null,
                userGender:null,
                userEmail:null,
                userAddress:null,
                userAge:null,
                // userPassword: null,
            }
        },
        methods: {
            register: function () {
                if (this.rePassword !== this.userPassword){
                    this.isLoginError = true
                }
                let data = qs.stringify({
                    uid: this.userName,
                    pwd: this.userPassword,
                    nickName: this.nickName,
                    email: this.userEmail,
                    address: this.userAddress,
                    sex: this.userGender,
                    age: this.userAge

                });
                // this.$router.push("/mainpage") 不知道哪位大神在这边加了一个跳转 BY 薛某
                axios.post('http://localhost:8080/logic/user/add', data,
                    { headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }).then(response => {
                    data = response.data;
                    if (1000 === data.code){
                        this.$router.push("/")
                    }
                    else {
                        alert("用户已存在")
                    }
                }).catch( error => {
                    console.log(error)
                })
                //     .then(function (dat) {
                //     if (dat.data === "0")
                //         alert("用户不存在或密码错误")
                //     else if (dat.data === "2")
                //         router.push("/mainpage")
                // }).catch(function () {
                //     console.log("传输失败")
                // })
            },
            handleSelectChange: function (value) {
                this.userGender = value
            }
        }
    }

</script>
<style lang="less" scoped>

    *{
        margin: 0;
        padding: 0;
    }
    .container {
        width: 100%;
        height: 100%;
        background: #f0f2f5 url(../assets/login.svg) no-repeat ;
        background-size: 100%;
        padding: 30px 0 218px;
        position: absolute;

        .top {
            text-align: center;

            .header {
                height: 44px;
                line-height: 44px;

                .logo {
                    height: 44px;
                    vertical-align: top;
                    margin-right: 16px;
                    border-style: none;
                }

                .title {
                    font-size: 33px;
                    color: rgba(0, 0, 0, .85);
                    font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
                    font-weight: 600;
                    position: relative;
                    top: 2px;
                }

            }

            .desc {
                font-size: 14px;
                color: rgba(0, 0, 0, .45);
                margin-top: 12px;
                margin-bottom: 40px;
            }

        }

        .main {
            min-width: 260px;
            width: 368px;
            margin: 0 auto;

            .user-layout-login {
                label {
                    font-size: 14px;
                }

                .getCaptcha {
                    display: block;
                    width: 100%;
                    height: 40px;
                }

                .forge-password {
                    font-size: 14px;
                }

                button.login-button {
                    padding: 0 15px;
                    font-size: 16px;
                    height: 40px;
                    width: 100%;
                }

                .user-login-other {
                    text-align: left;
                    margin-top: 24px;
                    line-height: 22px;

                    .item-icon {
                        font-size: 24px;
                        color: rgba(0, 0, 0, 0.2);
                        margin-left: 16px;
                        vertical-align: middle;
                        cursor: pointer;
                        transition: color 0.3s;

                        &:hover {
                            color: #1890ff;
                        }
                    }

                    .register {
                        float: right;
                    }
                }
            }

        }

        .footer {
            position: absolute;
            width: 100%;
            bottom: 0;
            padding: 0 16px;
            margin: 48px 0 24px;
            text-align: center;

            .links {
                margin-bottom: 8px;
                font-size: 14px;
            }

            .copyright {
                color: rgba(0, 0, 0, .45);
                font-size: 14px;
            }

        }
    }


</style>
