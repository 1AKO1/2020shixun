# -*- coding: utf-8 -*-
import logging
import time

import scrapy

from dangdang.items import DangdangItem


class BookSpider(scrapy.Spider):
    name = 'book'
    allowed_domains = ['dangdang.com']
    start_urls = ['http://category.dangdang.com/cp01.00.00.00.00.00.html']
    dom = 'http://category.dangdang.com'

    def start_requests(self):
        return [scrapy.Request(url=self.start_urls[0], callback=self.parse)]

    def parse(self, response):
        try:
            typestr = response.meta['type']
        except(KeyError):
            typestr = ""
        types = response.xpath('//*[@id="navigation"]/ul/li[1]/div[2]/div[  1]/div/span/a')
        tyname = response.xpath('//*[@id="navigation"]/ul/li[1]').xpath('@dd_name').pop().extract()
        if types and tyname == '分类':  # 到分类终止递归
            for type in types:
                url = self.dom + type.xpath('@href').pop().extract()
                typestr_new = typestr + "{0}>>".format(type.xpath('text()').pop().extract())  # 多级分类

                scrapy.Spider.log(self, "Find url:{0},type{1}".format(url, typestr_new), logging.INFO)
                yield scrapy.Request(url=url, callback=self.parse, meta={'type': typestr_new})
        else:
            n_y = int(response.xpath('//*[@id="go_sort"]/div/div[2]/span[1]/text()').pop().extract())  # 当前页
            c_y = int(response.xpath('//*[@id="go_sort"]/div/div[2]/span[2]/text()').pop().extract().lstrip('/'))  # 总页数
            for x in range(n_y, c_y):  # 处理分页
                yield scrapy.Request(url=self.dom + '/pg{0}-'.format(x) + response.url.split('/')[-1],
                                     callback=self.parse_page,
                                     meta={'type': typestr})

    def parse_page(self, response):
        for item in response.xpath('//*[@id="search_nature_rg"]/ul[@class="bigimg"]/li'):
            # 所有图书
            book = DangdangItem()
            # try:
            book['price'] = float(item.xpath('./p[@class="price"]/span[1]/text()').pop().extract().lstrip('¥'))
            book['type_tag'] = response.meta['type']
            book['name'] = item.xpath('./p[@class="name"]/a/text()').pop().extract().strip()
            book['book_tag'] = str(time.time()) + book.get('name', None)
            # book['image_url'] = item.xpath('./a/img/@src').pop().extract()
            book['link'] = item.xpath('./p[1]/a/@href').pop().extract()

            book['star_level'] = \
                int(item.xpath('./p[@class="search_star_line"]/span/span/@style').pop().extract().split(' ')[-1].rstrip(
                    '%;'))
            try:
                book['time'] = item.xpath('./p[@class="search_book_author"]/span[2]/text()').pop().extract().split('/')[
                    -1]
                book['author_tag'] = ','.join(
                    item.xpath('./p[@class="search_book_author"]/span[1]/a/text()').extract()).strip()
                book['publish_company'] = item.xpath(
                    './p[@class="search_book_author"]/span[3]/a/text()').pop().extract().strip()
                book['brief'] = item.xpath('./p[2]/text()').pop().extract().strip()
            except:
                scrapy.Spider.log(self, "Error:{} , url {}:".format(book['name'], response.url))
            finally:
                yield book
            # yield scrapy.Request(callback=self.parse_book, cookies=self.cookir, headers=DEFAULT_ERQUEST_HEADER,
            #                          meta={'item':book},url=book['link'])

    def parse_book(self, response):
        book = response.meta['item']
        try:
            book['isbn'] = int(
                response.xpath('//ul[@class="key clearfix"]/li')[-2].xpath('text()').pop().extract().split('：')[-1])
        except:
            scrapy.Spider.log(self, "Book {0} Can't open.and url is:{1}".format(book['name'], book['link']),
                              logging.ERROR)
            return
        return book