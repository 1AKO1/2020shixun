# -*- coding: utf-8 -*-
import json

from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor
from scrapydangdang.items import Items_detail


class DangdangsSpider0(CrawlSpider):
    name = 'dangdangs0'
    allowed_domains = ['product.dangdang.com']
    start_urls = ['http://product.dangdang.com/index.php?r=callback%2Fdetail&productId=23761145&templateType=publish&describeMap=01000031416%3A1&shopId=0&categoryPath=01.03.45.00.00.00']

    # start_urls = ["http://bang.dangdang.com/books/bestsellers"]
    # rules = (
    #     Rule(LinkExtractor(allow=r'http://product\.dangdang\.com/\d+\.html'), callback='parse', follow=True),
    # )

    def parse(self, response):
        booklist0 = response.xpath('*')

        item0 = Items_detail()

        for i in booklist0:
            item0['editor_choose'] = i.xpath('div[@id="abstract"]/div[@class="descrip"]/textarea[@id="abstract-textarea"]/p/text()').extract()
            # editor_choose = i.xpath('// *[ @ id = "abstract" ] / div[1] / span/text()').extract()
            #
            item0['brief_introduction'] = i.xpath('div[@id="content"]/div[@class="descrip"]/p/text()').extract()
            # author_introduction = i.xpath('div[@id="authorIntroduction"]/div[@class="descrip"]/p/text()').extract()
            # catalog = i.xpath(
            #     'div[@id="catalog"]/div[@class="descrip"]/span[@id="catalog-show-all"]/p/text()').extract()
            # media_comment = i.xpath('div[@id="mediaFeedback"]/div[@class="descrip"]/p/text()').extract()
            # part_free_read = i.xpath(
            #     'div[@id="extract"]/div[@class="descrip"]/span[@id="extract-show-all"]/p/text()').extract()
            #
            # item0['editor_choose'] = editor_choose
            # item0['brief_introduction'] = brief_introduction
            # item0['author_introduction'] = author_introduction
            # item0['catalog'] = catalog
            # item0['media_comment'] = media_comment
            # item0['part_free_read'] = part_free_read

            yield item0

            # next_page = response.xpath("//div[@class='paginating']/ul/li[@class=next]/a/@href").extract_first()
            # if next_page is not None:
            #     next_page = response.urljoin(next_page)
            #     yield scrapy.Request(next_page, callback=self.parse_item)
        # yield Request('http://bang.dangdang.com/books/bestsellers', callback=self.parse_item, dont_filter=True)
