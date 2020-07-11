# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql.cursors


class ScrapydangdangPipeline(object):

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
            """insert into zhihan_dang(`name`,commentNum,tuijian,author,ptimes,phouse,pn,pr,ps,pe,timehot,biglei,samlllei)
                    value (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",
            (item['bookname'], item['commentNum'], item['tuijian'], item['author'], item['Publishing_time'],
             item['Publishing_house'], item['price_n'], item['price_r'], item['price_s'], item['price_e'],
             item['timehot'], item['biglei'], item['samlllei'])

        )
        self.connect.commit()
        # print(item['cont'])
        return item
