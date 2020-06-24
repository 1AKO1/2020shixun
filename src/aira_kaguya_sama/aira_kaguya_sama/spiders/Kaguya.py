# -*- coding: utf-8 -*-
import json
import scrapy


class Kaguya(scrapy.Spider):
    BASE_URL = "https://api.bilibili.com/pgc/review/short/list?media_id=28228367&ps=20&sort={}"
    name = "Kaguya"
    allowed_domains = {"bangumi.bilibili.com"}

    start_urls = [BASE_URL.format("0")]

    def parse(self, response):
        print(response.url)
        resdata = json.loads(response.body_as_unicode())

        if resdata["code"] == 0:
            pass
