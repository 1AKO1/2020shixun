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
    book_isbn = scrapy.Field()      #ISBN号
    book_name = scrapy.Field()      #书名
    book_author = scrapy.Field()    #作者
    book_press = scrapy.Field()     #出版社
    book_time = scrapy.Field()      #出版时间
    book_tags = scrapy.Field()      #所属标签
    book_price = scrapy.Field()     #参考定价
    book_report = scrapy.Field()    #内容简介
    book_versions = scrapy.Field()  #书版本数
    book_url = scrapy.Field()       #图书URL

    book_score = scrapy.Field()         #豆瓣评分
    book_votes = scrapy.Field()         #评价数量
    book_shortComms = scrapy.Field()    #短评条数
    book_review = scrapy.Field()        #书评条数
    book_fiveStar = scrapy.Field()      #五星评分占比
    book_fourStar = scrapy.Field()      #四星评分占比
    book_threeStar = scrapy.Field()     #三星评分占比
    book_twoStar = scrapy.Field()       #二星评分占比
    book_oneStar = scrapy.Field()       #一星评分占比
    book_readings = scrapy.Field()      #在读人数
    book_reads = scrapy.Field()         #读过人数
    book_roReads = scrapy.Field()       #想读人数


class BookHotCommItem(scrapy.Item):
    hotComm_content = scrapy.Field()    #热门短评内容
    hotComm_score = scrapy.Field()      #热门短评评分
    hotComm_people = scrapy.Field()     #热门评论用户名
    hotComm_votes = scrapy.Field()      #热门短评有用数
