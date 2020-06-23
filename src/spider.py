# -*- coding: utf-8 -*-
import scrapy


class SpiderSpider(scrapy.Spider):
    name = 'spider'
    allowed_domains = ['https://movie.douban.com']
    start_urls = ['http://https://movie.douban.com/']

    def parse(self, response):
        pass
