# -*- coding: utf-8 -*-
import scrapy


class BookSpider(scrapy.Spider):
    name = 'book_spider'
    allowed_domains = ['book.douban.com']
    start_urls = ['https://book.douban.com/tag/#/']

    def parse(self, response):
        
        pass
