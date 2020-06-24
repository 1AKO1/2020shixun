# -*- coding: utf-8 -*-
import json
import scrapy

from aira_kaguya_sama.items import AiraKaguyaSamaItem


class Kaguya(scrapy.Spider):
    BASE_URL = "https://api.bilibili.com/pgc/review/short/list?media_id=28228367&ps=20&sort=0&cursor={0}"
    name = "Kaguya"
    allowed_domains = {"api.bilibili.com"}

    start_urls = [BASE_URL.format("79173446879952")]

    def parse(self, response):
        print(response.url)
        resdata = json.loads(response.body_as_unicode())

        if resdata["code"] == 0:
            nextt = 0
            if len(resdata["data"]["list"]) > 0:
                data = resdata["data"]["list"]
                nextt = resdata["data"]["next"]
                for one in data:
                    item = AiraKaguyaSamaItem()

                    item["avatar"] = one["author"]["avatar"]
                    item["mid"] = one["author"]["mid"]
                    item["uname"] = one["author"]["uname"]

                    item["themeType"] = one["author"]["vip"]["themeType"]
                    item["vipStatus"] = one["author"]["vip"]["vipStatus"]
                    item["vipType"] = one["author"]["vip"]["vipStatus"]

                    item["content"] = one["content"]
                    item["media_id"] = one["media_id"]
                    item["mid"] = one["mid"]
                    item["score"] = one["score"]
                    item["progress"] = one["progress"] if "progress" in one else "没看"
                    item["likes"] = one["stat"]["likes"]

                    item["ctime"] = one["ctime"]
                    item["mtime"] = one["mtime"]
                    yield item
            yield scrapy.Request(url=self.BASE_URL.format(nextt), callback=self.parse)
            print(nextt)
        else:
            print(resdata)
