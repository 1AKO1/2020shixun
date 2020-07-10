# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
from douban import settings
from douban.items import MusicItem,MusicReviewItem


class DoubanPipeline:

    def __init__(self):
        self.connect = pymysql.connect(
            host=settings.MYSQL_HOST,
            port=settings.MYSQL_PORT,
            db=settings.MYSQL_DBNAME,
            user=settings.MYSQL_USER,
            passwd=settings.MYSQL_PASSWD,
            charset='utf8',
            use_unicode=True,
            cursorclass=pymysql.cursors.DictCursor
        )
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        if isinstance(item, MusicItem):
            self.process_music(item)
        return item

    def process_music(self, item):
        try:
            self.cursor.execute('''
                select * from spt_music where music_name=%s         
            ''',(item['music_name'],))
            music = self.cursor.fetchone()
            if music == None:
                self.cursor.execute('''
                    insert into spt_music(music_name, music_alias, music_singer, music_time, 
                    music_rating,music_votes, music_tags, music_url, music_medium, music_kind,
                    music_type, music_publisher, music_recommend, music_song, music_introduction)
                    value (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
                ''',
                (item['music_name'],
                item['music_alias'],
                item['music_singer'],
                item['music_time'],
                item['music_rating'],
                item['music_votes'],
                item['music_tags'],
                item['music_url'],
                item['music_medium'],
                item['music_kind'],
                item['music_type'],
                item['music_publisher'],
                item['music_recommend'],
                item['music_song'],
                item['music_introduction']))
            else:
                self.cursor.execute('''
                    update spt_music
                    set music_alias = %s, 
                        music_singer = %s, 
                        music_time = %s, 
                        music_rating = %s,
                        music_votes = %s, 
                        music_tags = %s, 
                        music_url = %s,
                        music_medium = %s,
                        music_kind = %s,
                        music_type = %s,
                        music_publisher = %s,
                        music_recommend = %s,
                        music_song = %s,
                        music_introduction = %s
                    where music_name=%s
                ''',
                (item['music_alias'],
                item['music_singer'],
                item['music_time'],
                item['music_rating'],
                item['music_votes'],
                item['music_tags'],
                item['music_url'],
                item['music_medium'],
                item['music_kind'],
                item['music_type'],
                item['music_publisher'],
                item['music_recommend'],
                item['music_song'],
                item['music_introduction'],
                item['music_name']))
            self.connect.commit()
        except Exception as err:
            print(err)

    # def process_music_review(self, item):
    #     try:
    #         self.cursor.execute('''
    #             select * from spt_music_review where music_name=%s
    #         ''',(item['music_name'],))
    #         music = self.cursor.fetchone()
    #         if music == None:
    #             self.cursor.execute('''
    #                 insert into spt_music(music_name, music_alias, music_singer, music_time, music_rating,music_votes, music_tags, music_url)
    #                 value (%s,%s,%s,%s,%s,%s,%s,%s)
    #             ''',
    #             (item['music_name'],
    #             item['music_alias'],
    #             item['music_singer'],
    #             item['music_time'],
    #             item['music_rating'],
    #             item['music_votes'],
    #             item['music_tags'],
    #             item['music_url']))
    #         else:
    #             self.cursor.execute('''
    #                 update spt_music
    #                 set music_alias = %s,
    #                     music_singer = %s,
    #                     music_time = %s,
    #                     music_rating = %s,
    #                     music_votes = %s,
    #                     music_tags = %s,
    #                     music_url = %s
    #                 where music_name=%s
    #             ''',
    #             (item['music_alias'],
    #             item['music_singer'],
    #             item['music_time'],
    #             item['music_rating'],
    #             item['music_votes'],
    #             item['music_tags'],
    #             item['music_url'],
    #             item['music_name']))
    #         self.connect.commit()
    #     except Exception as err:
    #         print(err)