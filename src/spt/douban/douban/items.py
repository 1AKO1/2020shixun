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


class MusicItem(scrapy.Item):
    music_name = scrapy.Field()#名字
    music_alias = scrapy.Field()#又名
    music_singer = scrapy.Field()#表演者
    music_time = scrapy.Field()#时间
    music_rating = scrapy.Field()#评分
    music_votes = scrapy.Field()#评分人数
    music_tags = scrapy.Field()#标签
    music_url = scrapy.Field()#网址
    music_medium = scrapy.Field()#介质
    music_kind = scrapy.Field()#流派
    music_type = scrapy.Field()#专辑类型
    music_publisher = scrapy.Field()#出版者
    music_recommend = scrapy.Field()#推荐
    music_song = scrapy.Field()#曲目
    music_introduction = scrapy.Field()


class MusicReviewItem(scrapy.Item):
    review_content = scrapy.Field()
    review_author = scrapy.Field()
    review_music = scrapy.Field()
    review_time = scrapy.Field()
    review_votes = scrapy.Field()





