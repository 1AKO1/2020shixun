# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class ScrapydangdangItemTop500(scrapy.Item):
    # define the fields for your item here like:
    bookname = scrapy.Field()
    commentNum = scrapy.Field()
    tuijian = scrapy.Field()
    author = scrapy.Field()
    Publishing_house = scrapy.Field()
    Publishing_time = scrapy.Field()
    price_n = scrapy.Field()
    price_r = scrapy.Field()
    price_s = scrapy.Field()
    price_e = scrapy.Field()
    timehot = scrapy.Field()
    biglei = scrapy.Field()
    samlllei = scrapy.Field()
    pass

class Items_detail(scrapy.Item):
    # define the fields for your item here like:
    editor_choose = scrapy.Field()
    brief_introduction = scrapy.Field()
    # author_introduction = scrapy.Field()
    # catalog = scrapy.Field()
    # media_comment = scrapy.Field()
    # part_free_read = scrapy.Field()
    pass

# class Comment_drtail(scrapy.Item):



