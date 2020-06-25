# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
from aira_kaguya_sama import settings
from aira_kaguya_sama.items import AiraKaguyaSamaItem


class AiraKaguyaSamaPipeline(object):
    def __init__(self):
        self.connect = pymysql.connect(
            host=settings.MYSQL_HOST,
            port=settings.MYSQL_PORT,
            db=settings.MYSQL_DBNAME,
            user=settings.MYSQL_USER,
            passwd=settings.MYSQL_PASSWD,
            charset='utf8',
            use_unicode=True,
            cursorclass=pymysql.cursors.DictCursor)
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        try:
            self.cursor.execute("""
                select * from aira_kaguya_sama where mid = %s
            """, (item["mid"]))
            film = self.cursor.fetchone()

            if film is None:
                self.cursor.execute("""
                    insert into aira_kaguya_sama(avatar,
                                                mid,
                                                content,
                                                ctime,
                                                likes,
                                                media_id,
                                                mtime,
                                                progress,
                                                score,
                                                uname,
                                                themeType,
                                                vipStatus,
                                                vipType)
                                value (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                """, (item["avatar"],
                      item["mid"],
                      item["content"],
                      item["ctime"],
                      item["likes"],
                      item["media_id"],
                      item["mtime"],
                      item["progress"],
                      item["score"],
                      item["uname"],
                      item["themeType"],
                      item["vipStatus"],
                      item["vipType"]))
            else:
                self.cursor.execute("""
                    update aira_kaguya_sama
                    set avatar = %s,
                        mid = %s,
                        content = %s,
                        ctime = %s,
                        likes = %s,
                        media_id = %s,
                        mtime = %s,
                        progress = %s,
                        score = %s,
                        uname = %s,
                        themeType = %s,
                        vipStatus = %s,
                        vipType = %s
                    where id = %s
                """, (item["avatar"],
                      item["mid"],
                      item["content"],
                      item["ctime"],
                      item["likes"],
                      item["media_id"],
                      item["mtime"],
                      item["progress"],
                      item["score"],
                      item["uname"],
                      item["themeType"],
                      item["vipStatus"],
                      item["vipType"],
                      film["id"]))
            self.connect.commit()
        except Exception as err:
            print("数据库报错 ===》 错误信息为：" + str(err))
        return item
