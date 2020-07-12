# -*- coding: utf-8 -*-
import scrapy
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor
from douban.items import BookItem


class BookSpider(CrawlSpider):
    name = 'book2_spider'
    allowed_domains = ['book.douban.com']
    # start_urls = ['https://book.douban.com/tag/']
    # 耽美
    # start_urls = ['https://book.douban.com/tag/%E8%80%BD%E7%BE%8E']
    # 言情
    start_urls = ['https://book.douban.com/tag/%E8%A8%80%E6%83%85']
    rules = {
        # div[2] 流行标签
        # Rule(LinkExtractor(allow='/tag/', restrict_xpaths="//div[@class='article']/div[2]/div[2]"), follow=True),
        Rule(LinkExtractor(allow="\?start=\d+\&type=", restrict_xpaths="//div[@class='paginator']"), follow=True),
        Rule(LinkExtractor(allow="/subject/\d+/$", restrict_xpaths="//ul[@class='subject-list']"), callback='parse_book')
    }

    def parse_book(self, response):
        print("=" * 80)
        print(response.url)
        print("=" * 80)
        item = BookItem()
        # comm_item = BookHotCommItem()
        if response.status == 200:
            try:
                item['book_url'] = response.url
                item['book_picUrl'] = response.xpath('//div[@id="mainpic"]/a/@href').get()

                item['book_title'] = response.xpath('//div[@id="wrapper"]/h1/span/text()').get()
                item['book_author'] = get_author(response)
                item['book_type'] = '流行'
                item['book_press'] = response.xpath(u'//span[contains(./text(), "出版社:")]/following::text()[1]').get()
                item['book_pubDate'] = get_pubdate(response)
                item['book_tags'] = get_tags(response)
                item['book_price'] = fix_field(response.xpath(u'//span[contains(./text(), "定价:")]/following::text()[1]').get())
                item['book_isbn'] = response.xpath(u'//span[contains(./text(), "ISBN:")]/following::text()[1]').get()
                item['book_report'] = get_report(response)
                item['book_dbid'] = item['book_url'].split('/')[-2]
                item['book_versions'] = get_versions(response)

                item['book_score'] = get_score(response)
                item['book_votes'] = convert_field(response.xpath(
                    '//a[@class="rating_people"]/span/text()').get()) if response.xpath(
                    '//a[@class="rating_people"]') else 0
                item['book_shortComms'] = convert_field(response.xpath(
                    '//div[@class="mod-hd"]/h2/span[2]/a/text()').get()).split(' ')[1] if response.xpath(
                    '//div[@class="mod-hd"]/h2/span[2]') else 0
                item['book_reviews'] = convert_field(response.xpath(
                    '//section[@id="reviews-wrapper"]/header/h2/span/a/text()').get()).split(' ')[1]
                item['book_fiveStar'] = float(convert_field(response.css(
                    'span.rating_per')[0].css('::text').get()).split('%')[0]) / 100 if response.css(
                    'span.rating_per') else 0
                item['book_fourStar'] = float(convert_field(response.css(
                    'span.rating_per')[1].css('::text').get()).split('%')[0]) / 100 if response.css(
                    'span.rating_per') else 0
                item['book_threeStar'] = float(convert_field(response.css(
                    'span.rating_per')[2].css('::text').get()).split('%')[0]) / 100 if response.css(
                    'span.rating_per') else 0
                item['book_twoStar'] = float(convert_field(response.css(
                    'span.rating_per')[3].css('::text').get()).split('%')[0]) / 100 if response.css(
                    'span.rating_per') else 0
                item['book_oneStar'] = float(convert_field(response.css(
                    'span.rating_per')[4].css('::text').get()).split('%')[0]) / 100 if response.css(
                    'span.rating_per') else 0

                if response.xpath('//div[@id="collector"]/p[3]'):
                    item['book_readings'] = int(convert_field(response.xpath(
                        '//div[@id="collector"]/p[1]/a/text()').get()).split('人在读')[0])
                    item['book_reads'] = int(convert_field(response.xpath(
                        '//div[@id="collector"]/p[2]/a/text()').get()).split('人读过')[0])
                    item['book_toReads'] = int(convert_field(response.xpath(
                        '//div[@id="collector"]/p[3]/a/text()').get()).split('人想读')[0])

                elif response.xpath('//div[@id="collector"]/p[3]') is None and response.xpath(
                        '//div[@id="collector"]/p[2]'):
                    item['book_readings'] = 0
                    item['book_reads'] = int(convert_field(response.xpath(
                        '//div[@id="collector"]/p[1]/a/text()').get()).split('人想读')[0])
                    item['book_toReads'] = int(convert_field(response.xpath(
                        '//div[@id="collector"]/p[2]/a/text()').get()).split('人想读')[0])

                elif response.xpath('//div[@id="collector"]/p[2]') is None and response.xpath(
                        '//div[@id="collector"]/p[1]'):
                    item['book_readings'] = 0
                    item['book_reads'] = 0
                    item['book_toReads'] = int(convert_field(response.xpath(
                        '//div[@id="collector"]/p[1]/a/text()').get()).split('人想读')[0])

                else:
                    item['book_readings'] = 0
                    item['book_reads'] = 0
                    item['book_toReads'] = 0

                # 问题代码 不知道为什么会得到None
                # hotComm = response.xpath('//div[@class="comment-list hot show"]/ul/li[1]/div')
                # print('=' * 30 + '!' * 15 + '=' * 30)
                # print(hotComm.xpath('/h3/span[1]'))
                # print('=' * 30 + '!' * 15 + '=' * 30)
                # comm_item['hotComm_people'] = hotComm.xpath(
                #     '/h3/span[2]/a/text()').get()
                # comm_item['hotComm_content'] = hotComm.xpath(
                #     '/h3/p/span/text()').get()
                # comm_item['hotComm_score'] = hotComm.xpath(
                #     '/h3/span[2]/span[@class="user-stars"]/@title').get()
                # comm_item['hotComm_votes'] = int(hotComm.xpath(
                #     '/h3/span[1]/span/text()').get())

                # item['hotComm_dbid'] = item['book_dbid']
                item['hotComm_content'] = get_hotcommcontent(response)
                item['hotComm_people'] = fix_field(response.xpath(
                    '//div[@class="comment-list hot show"]/ul/li[1]/div/h3/span[2]/a/text()').get())
                item['hotComm_score'] = fix_field(response.xpath(
                    '//div[@class="comment-list hot show"]/ul/li[1]/div/h3/span[2]/span[@class="user-stars allstar50 rating"]/@title').get())
                item['hotComm_votes'] = convert_field(response.xpath(
                    '//div[@class="comment-list hot show"]/ul/li[1]/div/h3/span[1]/span/text()').get())

                print('=' * 30 + '!' * 15 + '=' * 30)
                yield item
                print('=' * 30 + '!' * 15 + '=' * 30)
                # yield comm_item
                # print('=' * 30 + '!' * 15 + '=' * 30)

            except Exception as error:
                print('!' * 80)
                print('【流行】分类爬虫爬取错误')
                print('==> 错误书籍为：' + str(response.url))
                print('==> 错误信息为：' + str(error))
                print('!' * 80)

        pass


def convert_field(field):
    return field.strip() if field else 0


def fix_field(field):
    return field.strip() if field else ''


def get_author(response):
    # 有些不同页面的author html会有所不同
    author = response.css('#info > a:nth-child(2)::text').get()
    if not author:
        author = response.css('#info > span > a::text').get()
    # 部分书籍如一千零一夜，没有作者，使用get，若无则直接返回None
    return author.replace('\n            ', '').strip() if author else '无'


def get_pubdate(response):
    date = response.xpath(u'//span[contains(./text(), "出版年:")]/following::text()[1]').get()
    if date:
        return date.strip() if len(date.split('-')) > 2 else date.strip() + '-15'
    else:
        return None


def get_tags(response):
    # 有问题，不知道怎么用xpath爬取tags
    # tags_list = response.xpath('//*[@id="db-tags-section"]/div')
    # tags = ''
    # for tag in tags_list:
    #     tags += '#'
    #     tags += tag.xpath('/a/text()').get().strip()
    #     tags += ' '
    # return tags
    tags_list = response.css('a.tag')
    tags = ''
    for tag in tags_list:
        if tag:
            tags += '#'
            tags += tag.css('::text').get().strip()
            tags += ' '
    return tags if tags != '' else '无'


def get_report(response):
    report = ''
    if response.css('#link-report > div:nth-child(1) > div'):
        report_list = response.css('#link-report > div:nth-child(1) > div > p::text').extract()
        for s in report_list:
            report += s
    else:
        report_list = response.css('div.intro')[1].css('p::text').extract()
        for s in report_list:
            report += s
    return report


def get_versions(response):
    versions = response.css('div.version_works > h2 > span:nth-child(2) > a::text').get()
    return int(versions.split('全部')[1]) if versions else 0


def get_score(response):
    score = response.xpath('//div[@class="rating_self clearfix"]/strong/text()').get().strip()
    return float(score) if len(score) > 1 else 0.0


def get_hotcommcontent(response):
    content_list = response.xpath(
        '//div[@class="comment-list hot show"]/ul/li[1]/div/p/span/text()').get()
    content = ''
    for s in content_list:
        content += s
    return content
