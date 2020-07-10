# -*- coding: utf-8 -*-
import scrapy
from scrapy import Request
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
import re
from scrapydangdang.items import ScrapydangdangItemTop500


class DangdangsSpider(CrawlSpider):
    name = 'dangdangs'
    allowed_domains = ['bang.dangdang.com']
    start_urls = []

    # rules = (
    #     Rule(LinkExtractor(allow=r'01.00.00.00.00.00-recent7-0-0-1-\d+'), callback='parse_item', follow=True),
    # )

    for x in range(1, 25):
        for y in range(2019, 2016,-1):
            for w in range(1, 70):
                    for z in range(1, 59):
                        url = 'http://bang.dangdang.com/books/bestsellers/01.{0:0>2d}.{1:0>2d}.00.00.00-year-{2}-0-1-{3}'.format(z,w,y,x)
                        start_urls.append(url)

    def parse(self, response):

        booklist = response.xpath('//ul[@class="bang_list clearfix bang_list_mode"]/li')

        item = ScrapydangdangItemTop500()

        for i in booklist:
            item['bookname'] = i.css(".name a::text").extract_first()
            commentNum1 = i.css(".star a::text").extract_first()
            index = commentNum1.index("条")
            commentNum = int(commentNum1[0:index])
            tuijian1 = i.css(".star .tuijian::text").extract_first()
            index = tuijian1.index("%")
            tuijian = float(tuijian1[0:index])
            item['author'] = i.xpath('div[@class="publisher_info"][1]/a/text()').extract_first()
            Publishing_time = i.xpath('div[@class="publisher_info"][2]/span/text()').extract_first()
            Publishing_house = i.xpath('div[@class="publisher_info"][2]/a/text()').extract_first()
            price_n1 = i.xpath("div[@class='price']/p/span[@class='price_n'][1]/text()").extract_first()
            price_n = float(price_n1[1:])
            price_r1 = i.xpath("div[@class='price']/p/span[@class='price_r']/text()").extract_first()
            price_r = float(price_r1[1:])
            price_s1 = i.xpath("div[@class='price']/p/span[@class='price_s']/text()").extract_first()
            index = price_s1.index("折")
            price_s = float(price_s1[0:index])
            price_e1 = i.xpath("div[@class='price']/p[@class='price_e']/span[@class='price_n']/text()").extract_first()
            try:
                price_e = float(price_e1[1:])
            except :
                price_e = 0
            samlllei = i.xpath('//*[@id="sortRanking"]/ul[@class="side_nav_detail"]/li[@class="now"]/a/text()').extract_first()

            href = i.xpath('//*[@id="sortRanking"]/ul[@class="side_nav_detail"]/li[@class="now"]/a/@href').extract_first()

            s = re.sub(r'/(.\d+)(.\d+).\d+',lambda m: '/'+m.group(1)+m.group(2)+'.00', href)

            l="01.01.00.00.00.00"
            biglei = i.xpath('//*[@id="sortRanking"]/div/a[@href="%s"]/text()' % s).extract_first()

            if href.find('2016') != -1:
                timehot = 2016
            elif href.find('2017') != -1:
                timehot = 2017
            elif href.find('2018') != -1:
                timehot = 2018
            else:
                timehot = 2019

            item['commentNum'] = commentNum
            item['tuijian'] = tuijian
            item['Publishing_time'] = Publishing_time
            item['Publishing_house'] = Publishing_house
            item['price_n'] = price_n
            item['price_r'] = price_r
            item['price_s'] = price_s
            item['price_e'] = price_e
            item['biglei'] = biglei
            item['timehot'] = timehot
            item['samlllei'] = samlllei
            yield item
