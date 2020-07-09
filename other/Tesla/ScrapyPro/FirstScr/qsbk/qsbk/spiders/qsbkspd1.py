# -*- coding: utf-8 -*-
import scrapy
from scrapy.http.response.html import HtmlResponse
from scrapy.selector.unified import SelectorList

class Qsbkspd1Spider(scrapy.Spider):
    name = 'qsbkspd1'
    allowed_domains = ['qiushibaike.com']
    start_urls = ['https://www.qiushibaike.com/text/page/1/']

    def parse(self, response):
        # SelectorList
        content_main = response.xpath("//div[@id = 'content']/div/div[2]/div")
        # @class = 'co1 old-style-col1'

        for text in content_main:
            # Selector
            author = text.xpath(".//h2/text()").get().strip()
            content = text.xpath(".//div[@class = 'content']//text()").getall()
            content = "".join(content)
            print(author)
            print(content)