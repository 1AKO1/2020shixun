# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql


class DangdangPipeline:

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

    def process_item(self, book, spider):
        self.cursor.execute(
            """INSERT INTO `zhihandangdang` (`book_tag`, `a_tag`, `t_type`, `b_name`, `publishing_company`, `time`, `star_level`, `price`, `isbn`, `brief`,`image_url`, `link`) 
        VALUES (MD5(%s), MD5(%s), MD5(%s), %s, %s, %s, %s, %s, %s, %s, %s, %s)""",
            (
                book['book_tag'], book['author_tag'], book['type_tag'], book['name'], book['publish_company'],
                book['time'],
                book['star_level'], book['price'], book['isbn'], book['brief'], book['image_url'], book['link'])

        )
        self.connect.commit()
        # print(item['cont'])
        return book
