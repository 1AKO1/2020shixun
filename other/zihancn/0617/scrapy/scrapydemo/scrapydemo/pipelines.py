# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

import pymysql.cursors


# 数据库操作类

# class DbConnection(object):


class ScrapydemoPipeline(object):

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
            """insert into zhihancn(content,tags,author)
                    value (%s , %s,%s)""",
            (item["cont"], item["tag"],item["author"])

        )
        self.connect.commit()
        # print(item['cont'])
        return item
