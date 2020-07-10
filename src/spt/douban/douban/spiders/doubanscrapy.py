# -*- coding: utf-8 -*-
import scrapy
from scrapy.spiders import Rule, CrawlSpider
from scrapy.linkextractors import LinkExtractor
from douban.items import MusicItem,MusicReviewItem
import re


class DoubanscrapySpider(CrawlSpider):

    name = 'doubanscrapy'
    allowed_domains = ['music.douban.com']
    start_urls = ['https://music.douban.com/tag/']

    rules = (
        Rule(LinkExtractor(allow=r"/tag/((\d+)|([\u4e00-\u9fa5]+)|(\w+))$")),
        Rule(LinkExtractor(allow=r"/tag/((\d+)|([\u4e00-\u9fa5]+)|(\w+))\?start=\d+\&type=T$")),
        Rule(LinkExtractor(allow=r"/subject/\d+/reviews\?sort=time$")),
        Rule(LinkExtractor(allow=r"/subject/\d+/reviews\?sort=time\&start=\d+$")),
        Rule(LinkExtractor(allow=r"/subject/\d+/$"), callback='parse_music', follow=True),
        Rule(LinkExtractor(allow=r"/comments/$"),callback='parse_review', follow=True)
    )

    def parse_music(self, response):
        item = MusicItem()
        try:
            item['music_name'] = response.xpath('//*[@id="wrapper"]/h1/span/text()').extract()[0]
            content = "".join(response.xpath('//*[@id="info"]').extract())
            info = response.xpath('//*[@id="info"]/span').extract()
            item['music_alias'] = ""
            for i in range(0, len(info)):
                if "又名" in info[i]:
                    if i == 0:
                        item['music_alias'] = response.xpath('//*[@id="info"]/text()').extract()[1]\
                        .replace("\xa0", "").replace("\n", "").rstrip()
                    if i == 1:
                        item['music_alias'] = response.xpath('//*[@id="info"]/text()').extract()[2]\
                        .replace("\xa0", "").replace("\n", "").rstrip()
                    if i == 2:
                        item['music_alias'] = response.xpath('//*[@id="info"]/text()').extract()[3]\
                        .replace("\xa0", "").replace("\n", "").rstrip()

                if "表演者" in info[i]:
                    if i == 0:
                        item['music_singer'] = "|".join(
                            response.xpath('//*[@id="info"]/span[1]/span/a/text()').extract()
                        )
                    elif i == 1:
                        item['music_singer'] = "|".join(
                            response.xpath('//*[@id="info"]/span[2]/span/a/text()').extract()
                        )
                    elif i == 2:
                        item['music_singer'] = "|".join(
                            response.xpath('//*[@id="info"]/span[3]/span/a/text()').extract()
                        )
                    else:
                        item['music_singer'] = ""
                if "发行时间" in info[i]:
                    nbsp = re.findall(r"<span class=\"pl\">发行时间:</span>(.*?)<br>",content,re.S)
                    item['music_time'] = "".join(nbsp).replace("\xa0", "").replace("\n", "").replace(" ", "")
                if "流派" in info[i]:
                    nbsp = re.findall(r"<span class=\"pl\">流派:</span>(.*?)<br>", content, re.S)
                    item['music_kind'] = "".join(nbsp).replace("\xa0", "").replace("\n", "").replace(" ", "")
                if "专辑类型" in info[i]:
                    nbsp = re.findall(r"<span class=\"pl\">专辑类型:</span>(.*?)<br>", content, re.S)
                    item['music_type'] = "".join(nbsp).replace("\xa0", "").replace("\n", "").replace(" ", "")
                if "介质" in info[i]:
                    nbsp = re.findall(r"<span class=\"pl\">介质:</span>(.*?)<br>", content, re.S)
                    item['music_medium'] = "".join(nbsp).replace("\xa0", "").replace("\n", "").replace(" ", "")
                if "出版者" in info[i]:
                    nbsp = re.findall(r"<span class=\"pl\">出版者:</span>(.*?)<br>", content, re.S)
                    item['music_publisher'] = "".join(nbsp).replace("\xa0", "").replace("\n", "").replace(" ", "")

            try:
                item['music_rating'] = "".join(response.xpath(
                    '//*[@class="rating_self clearfix"]/strong/text()').extract())
                item['music_votes'] = "".join(response.xpath(
                     '//*[@class="rating_self clearfix"]/div/div[@class="rating_sum"]/a/span/text()').extract())
            except Exception as error:
                item['music_rating'] = '0'
                item['music_votes'] = '0'
                print(error)

            li_list = response.xpath('//*[@id="db-rec-section"]/div')
            for li in li_list:
                item['music_recommend'] = " ".join(li.xpath('dl/dt/dd/text()').extract())

            item['music_tags'] = "|".join(response.xpath('//*[@id="db-tags-section"]/div/a/text()').extract())
            item['music_url'] = response.url
            item['music_song'] = response.xpath('//*[class="track-list"]/div/div/text').extract()

            item['music_introduction'] = "".join(response.xpath('//*[@class="all hidden"]/text()').extract()).replace("\u3000","").rstrip()



            yield item
        except Exception as err:
            print((err))








