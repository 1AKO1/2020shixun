# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql.cursors


class ScralabPipeline(object):

    def __init__(self):
        self.connect = pymysql.connect(
            host="www.91iedu.com",
            port=3391,
            db="team04",
            user="team04",
            passwd="team04",
            charset="utf8mb4",
            use_unicode=True
        )
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        self.cursor.execute(
            """insert into tesla_labtable1(tag, content)
                    value (%s, %s)""",
            (item["tag"], item["cont"])
        )
        self.connect.commit()

        return item
