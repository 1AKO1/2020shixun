# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class AiraKaguyaSamaItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    # author
    avatar = scrapy.Field()  # 头像
    mid = scrapy.Field()  # 用户id
    uname = scrapy.Field()  # 用户姓名

    # vip
    themeType = scrapy.Field()
    vipStatus = scrapy.Field()
    vipType = scrapy.Field()

    content = scrapy.Field()    # 内容
    media_id = scrapy.Field()   # 动漫id
    progress = scrapy.Field()   # 看番进度
    review_id = scrapy.Field()  # 评论id
    likes = scrapy.Field()      # 点赞数
    score = scrapy.Field()      # 评分
    # 我都不知道这字段啥意思， 啥时间啊
    mtime = scrapy.Field()
    ctime = scrapy.Field()

    pass
