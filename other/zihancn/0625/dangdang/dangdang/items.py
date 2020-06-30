# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class DangdangItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    author_tag = scrapy.Field()
    type_tag = scrapy.Field()
    name = scrapy.Field()
    # image_url = scrapy.Field()
    publish_company = scrapy.Field()
    time = scrapy.Field()
    star_level = scrapy.Field()
    price = scrapy.Field()
    isbn = scrapy.Field()
    brief = scrapy.Field()
    info = scrapy.Field()
    book_tag = scrapy.Field()
    link = scrapy.Field()
    pass

