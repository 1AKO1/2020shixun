# -*- coding: utf-8 -*-
import scrapy

from scrapydemo.items import ScrapydemoItem


class ScrapydSpider(scrapy.Spider):
    name = 'scrapyd'
    allowed_domains = ['lab.scrapyd.cn']
    start_urls = ['http://lab.scrapyd.cn/']

    def parse(self, response):
        mingyan = response.css("div.quote")

        item = ScrapydemoItem()

        for i in mingyan:
            item['cont'] = i.css(".text::text").extract_first()  # 伪操作符 标签里的内容
            tags = i.css(".tags .tag::text").extract()
            authors = i.css(".author::text").extract_first()

            item['tag'] = ",".join(tags)
            item['author'] = authors

            yield item

        next_page = response.css("li.next a::attr(href)").extract_first()
        if next_page is not None:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)
