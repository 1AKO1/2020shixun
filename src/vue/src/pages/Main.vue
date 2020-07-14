<template>
    <div>
        <a-page-header
                style="border-bottom: 1px solid rgb(235, 237, 240)"
                title="项目主页"
                sub-title="关于项目的所有信息都在这里"
        />
        <br />

        <a-collapse v-model="activeKey">
            <a-collapse-panel key="1" header="项目信息介绍">
                <table style="width: 100%">
                    <tr style="width: 100%">
                        <td style="width: 50%">
                            <div style="width: 100%">
                                <a-row :gutter="16" style="margin-bottom: 10px">
                                    <a-col :span="8" style="width: 100%">
                                        <a-card title="项目名" bordered hoverable>
                                            <p style="font-size: 20px">高数-您的高级数据信息服务平台</p>
                                        </a-card>
                                    </a-col>
                                </a-row>
                                <a-row :gutter="16" style="margin-bottom: 10px">
                                    <a-col :span="8" style="width: 100%">
                                        <a-card title="项目成员" bordered hoverable>
                                            <p style="font-size: 15px ">组长：廖嘉伟</p>
                                            <p style="font-size: 15px">组员：段成源 刘博 薛彦涵 李嘉威</p>
                                        </a-card>
                                    </a-col>
                                </a-row>
                            </div>
                        </td>
                        <td style="width: 50%">
                            <div style="width: 100%; height: 100%">
                                <a-row :gutter="16" style="margin-bottom: 10px; height: 100%">
                                    <a-col :span="8" style="width: 100%; height: 100%">
                                        <a-card title="项目概述" style="height: 100%" bordered hoverable>
                                            <p>在当下这个信息爆炸的时代，互联网作为人们工作、生活、学习都无法脱离的平台，每天都会产生海量数据，音乐、电影、书籍、评论种种信息虽然琐碎，担当大量获取分析后将会得出许多有用的结论。</p>
                                            <p>本项目采用了前后端分离的开发方式，前段采用Vue框架进行开发，后端使用SpringBoot框架开发，通过scrapy爬虫针对豆瓣电影、音乐、图书和当当图书、B站动漫评论进行数据收集并分析，结果保存于远程mariaDB数据库中。提供简洁明了的数据查询服务，便于查询所需条件的数据，也提供关于数据内容的分析服务，并将其以图表的方式简洁直观的展示出来，方便用户观看。用户可根据分析所获结果，快捷的寻找满意的电影、音乐、书籍等资源。</p>
                                        </a-card>
                                    </a-col>
                                </a-row>
                            </div>
                        </td>
                    </tr>
                </table>
                <a-row :gutter="16"  style="margin-bottom: 10px">
                    <a-col :span="8">
                        <a-card title="前端" bordered hoverable>
                            <div style="width:269px; margin: 0 auto">
                                <img src="../static/logo.png" alt="" style="width: 120px;">
                                <span style="margin-left: 20px; font-size: 50px; line-height: 50px">Vue.js</span>
                            </div>
                        </a-card>
                    </a-col>
                    <a-col :span="8">
                        <a-card title="后端" bordered hoverable>
                            <img src="../static/springboot.svg" alt="">
                        </a-card>
                    </a-col>
                    <a-col :span="8">
                        <a-card title="数据库" bordered hoverable>
                            <img src="../static/mariadb.svg" alt="">
                        </a-card>
                    </a-col>

                </a-row>

                <a-row :gutter="16">
                    <a-col :span="24">
                        <a-card title="数据网站" bordered hoverable>
                            <img src="../static/bilibili.png" alt="" style="width: 270px; margin-left: 10px">
                            <img src="../static/dangdang.png" alt="" style="width: 270px; margin-left: 10px">
                            <img src="../static/moive.png" alt="" style="width: 270px; margin-left: 10px">
                            <img src="../static/music.png" alt="" style="width: 270px; margin-left: 10px">
                            <img src="../static/book.png" alt="" style="width: 270px; margin-left: 10px">
                        </a-card>
                    </a-col>
                </a-row>


            </a-collapse-panel>

            <a-collapse-panel key="2" header="组员信息汇总">
                <a-descriptions title="" bordered>
                    <a-descriptions-item label="姓名">
                        廖嘉伟
                    </a-descriptions-item>
                    <a-descriptions-item label="职务">
                        组长
                    </a-descriptions-item>
                    <a-descriptions-item label="学号">
                        YES
                    </a-descriptions-item>
                    <a-descriptions-item label="项目心得" :span="3">
                        <a-badge status="processing" text="我爱写代码，以后的代码全交给我来写" />
                    </a-descriptions-item>
                    <a-descriptions-item label="爬虫爬取数量">
                        $80.00
                    </a-descriptions-item>
                    <a-descriptions-item label="数据表字段数">
                        $20.00
                    </a-descriptions-item>
                    <a-descriptions-item label="爬取信息网站">
                        $60.00
                    </a-descriptions-item>
                    <a-descriptions-item label="任务">
                        Data disk type: MongoDB
                        <br />
                        Database version: 3.4
                        <br />
                        Package: dds.mongo.mid
                        <br />
                        Storage space: 10 GB
                        <br />
                        Replication factor: 3
                        <br />
                        Region: East China 1<br />
                    </a-descriptions-item>
                </a-descriptions>

                <a-divider dashed />

                <a-descriptions title="" bordered>
                    <a-descriptions-item label="姓名">
                        刘博
                    </a-descriptions-item>
                    <a-descriptions-item label="职务">
                        组员
                    </a-descriptions-item>
                    <a-descriptions-item label="学号">
                        1811650612
                    </a-descriptions-item>
                    <a-descriptions-item label="项目心得" :span="3">
                        <a-badge status="processing" text="通过项目了解到了许多网络爬虫、前后端涉及的知识，同时也更加认识到了自己
                            在这网站这方面还存在极大地不足，仍需要充实自己" />
                    </a-descriptions-item>
                    <a-descriptions-item label="爬虫爬取数量">
                        11000
                    </a-descriptions-item>
                    <a-descriptions-item label="数据表字段数">
                        17
                    </a-descriptions-item>
                    <a-descriptions-item label="爬取信息网站">
                        豆瓣音乐
                    </a-descriptions-item>
                    <a-descriptions-item label="任务">
                        爬虫：爬取豆瓣音乐相关数据
                        <br />
                        后端：豆瓣音乐、用户管理接口
                        <br />
                        前端：豆瓣音乐前端页面<br />
                    </a-descriptions-item>
                </a-descriptions>

                <a-divider dashed />

                <a-descriptions title="" bordered>
                    <a-descriptions-item label="姓名">
                        段成源
                    </a-descriptions-item>
                    <a-descriptions-item label="职务">
                        组员
                    </a-descriptions-item>
                    <a-descriptions-item label="学号">
                        1811650530
                    </a-descriptions-item>
                    <a-descriptions-item label="项目心得" :span="3">
                        <a-badge status="processing" text="通过本次项目，学习到爬虫机制，后端数据接口和vue框架等的使用，收获颇丰" />
                    </a-descriptions-item>
                    <a-descriptions-item label="爬虫爬取数量">
                        23476
                    </a-descriptions-item>
                    <a-descriptions-item label="数据表字段数">
                        19
                    </a-descriptions-item>
                    <a-descriptions-item label="爬取信息网站">
                        豆瓣电影
                    </a-descriptions-item>
                    <a-descriptions-item label="任务">
                        爬虫：爬取豆瓣电影相关数据
                        <br />
                        后端：豆瓣电影数据接口
                        <br />
                        前端：豆瓣电影数据展示页面<br />
                        前端：豆瓣电影数据查询页面<br />
                    </a-descriptions-item>
                </a-descriptions>

                <a-divider dashed />

                <a-descriptions title="" bordered>
                <a-descriptions-item label="姓名">
                    李嘉威
                </a-descriptions-item>
                <a-descriptions-item label="职务">
                    组员
                </a-descriptions-item>
                <a-descriptions-item label="学号">
                    1811650610
                </a-descriptions-item>
                <a-descriptions-item label="项目心得" :span="3">
                    <a-badge status="processing" text="通过本次实训，让我明白了前后端之间的联系方式，这对以后的学习一定很有帮助。" />
                </a-descriptions-item>
                <a-descriptions-item label="爬虫爬取数量">
                    48235
                </a-descriptions-item>
                <a-descriptions-item label="数据表字段数">
                    18
                </a-descriptions-item>
                <a-descriptions-item label="爬取信息网站">
                    BiliBili
                </a-descriptions-item>
                <a-descriptions-item label="任务">
                    爬虫：爬取番剧评论数据
                    <br />
                    后端：bilibili数据查询与分析
                    <br />
                    前端：项目框架搭建、注册、用户管理、bilibili数据查询与分析
                </a-descriptions-item>
            </a-descriptions>

                <a-divider dashed />

            <a-descriptions title="" bordered>
                <a-descriptions-item label="姓名">
                    薛彦涵
                </a-descriptions-item>
                <a-descriptions-item label="职务">
                    组员
                </a-descriptions-item>
                <a-descriptions-item label="学号">
                    1811650426
                </a-descriptions-item>
                <a-descriptions-item label="项目心得" :span="3">
                    <a-badge status="processing" text="这是一次很好的针对网页开发的一次实战，获益良多" />
                </a-descriptions-item>
                <a-descriptions-item label="爬虫爬取数量">
                    42061
                </a-descriptions-item>
                <a-descriptions-item label="数据表字段数">
                    15
                </a-descriptions-item>
                <a-descriptions-item label="爬取信息网站">
                    DangDang
                </a-descriptions-item>
                <a-descriptions-item label="任务">
                    爬虫：爬取当当网图书基本信息
                    <br />
                    后端：当当网图书信息数据查询与分析、登录
                    <br />
                    前端：项目框架辅助搭建、登录、dangdang网数据查询与分析，模块化分析界面
                </a-descriptions-item>
            </a-descriptions>

                <a-divider dashed />
            </a-collapse-panel>

        </a-collapse>
    </div>
</template>

<script>
    export default {
        name: "Main",
        components:{

        },
        data() {
            return {
                text: `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`,
                activeKey: ['1'],
            };
        },
    }
</script>

<style scoped>

</style>