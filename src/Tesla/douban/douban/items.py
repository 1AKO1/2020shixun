# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class DoubanItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass


class BookItem(scrapy.Item):
    book_title = scrapy.Field()     # 书名        varchar(256)
    book_author = scrapy.Field()    # 作者        varchar(256)
    book_type = scrapy.Field()      # 类别        varchar(32)
    book_press = scrapy.Field()     # 出版社      varchar(128)
    book_pubDate = scrapy.Field()   # 出版时间    date
    book_tags = scrapy.Field()      # 所属标签    varchar(128)
    book_price = scrapy.Field()     # 参考定价    varchar(64)
    book_isbn = scrapy.Field()      # ISBN号      varchar(16)
    book_report = scrapy.Field()    # 内容简介    text
    book_dbid = scrapy.Field()      # 豆瓣ID      varchar(16)
    book_versions = scrapy.Field()  # 书版本数    int

    book_score = scrapy.Field()         # 豆瓣评分        decimal(2,1)
    book_votes = scrapy.Field()         # 评价数量        int
    book_shortComms = scrapy.Field()    # 短评条数        int
    book_reviews = scrapy.Field()       # 书评条数        int
    book_fiveStar = scrapy.Field()      # 五星评分占比    decimal(4,3)
    book_fourStar = scrapy.Field()      # 四星评分占比    decimal(4,3)
    book_threeStar = scrapy.Field()     # 三星评分占比    decimal(4,3)
    book_twoStar = scrapy.Field()       # 二星评分占比    decimal(4,3)
    book_oneStar = scrapy.Field()       # 一星评分占比    decimal(4,3)
    book_readings = scrapy.Field()      # 在读人数        int
    book_reads = scrapy.Field()         # 读过人数        int
    book_toReads = scrapy.Field()       # 想读人数        int

    book_url = scrapy.Field()           # 图书URL         varchar(256)
    book_picUrl = scrapy.Field()        # 图书图片URL     varchar(256)

    hotComm_content = scrapy.Field()    # 热门短评内容    text
    hotComm_people = scrapy.Field()     # 热门评论用户名  varchar(256)
    hotComm_score = scrapy.Field()      # 热门短评评分    varchar(32)
    hotComm_votes = scrapy.Field()      # 热门短评有用数  int

    # hotComm_dbid = scrapy.Field()       # 豆瓣ID         仅用于标记识别
