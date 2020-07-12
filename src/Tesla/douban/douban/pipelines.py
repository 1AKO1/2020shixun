# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
from douban import settings
from douban.items import BookItem


class DoubanPipeline:

    def __init__(self):
        self.connect = pymysql.connect(
            host=settings.MYSQL_HOST,
            port=settings.MYSQL_PORT,
            db=settings.MYSQL_DBNAME,
            user=settings.MYSQL_USER,
            passwd=settings.MYSQL_PWD,
            charset='utf8mb4',
            use_unicode=True,
            cursorclass=pymysql.cursors.DictCursor
        )
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        if isinstance(item, BookItem):
            print('BookItem piepeline starts')
            self.process_book(item)
            print('BookItem piepeline end')
            print('*' * 20 + 'END' + '*' * 20 + 'END' + '*' * 20 + 'END' + '*' * 20)

        return item

    def process_book(self, item):
        try:
            self.cursor.execute('''
                SELECT * FROM tesla_book where book_dbid=%s
            ''', (item['book_dbid']))
            book = self.cursor.fetchone()

            if book is None:
                self.cursor.execute('''
                    INSERT INTO tesla_book (
                        book_title, book_author, book_type, book_press, book_pubDate, 
                        book_tags, book_price, book_isbn, book_report, book_dbid, book_versions, 
                        book_score, book_votes, book_shortComms, book_reviews, 
                        book_fiveStar, book_fourStar, book_threeStar, book_twoStar, book_oneStar, 
                        book_readings, book_reads, book_toReads, 
                        book_url, book_picUrl, 
                        hotComm_content, hotComm_people, hotComm_score, hotComm_votes
                        )
                    VALUE (
                        %s, %s, %s, %s, %s, 
                        %s, %s, %s, %s, %s, %s, 
                        %s, %s, %s, %s, 
                        %s, %s, %s, %s, %s, 
                        %s, %s, %s, 
                        %s, %s,
                        %s, %s, %s, %s)''',
                                    (item['book_title'], item['book_author'], item['book_type'], item['book_press'], item['book_pubDate'],
                                     item['book_tags'], item['book_price'], item['book_isbn'], item['book_report'], item['book_dbid'], item['book_versions'],
                                     item['book_score'], item['book_votes'], item['book_shortComms'], item['book_reviews'],
                                     item['book_fiveStar'], item['book_fourStar'], item['book_threeStar'], item['book_twoStar'], item['book_oneStar'],
                                     item['book_readings'], item['book_reads'], item['book_toReads'],
                                     item['book_url'], item['book_picUrl'],
                                     item['hotComm_content'], item['hotComm_people'], item['hotComm_score'], item['hotComm_votes'])
                                    )
            else:
                self.cursor.execute('''
                    UPDATE tesla.book
                        SET book_title = %s, book_author = %s, book_type = %s, book_press = %s, book_pubDate = %s, 
                            book_tags = %s, book_price = %s, book_isbn = %s, book_report = %s, book_dbid = %s, book_versions = %s, 
                            book_score = %s, book_votes = %s, book_shortComms = %s, book_reviews = %s, 
                            book_fiveStar = %s, book_fourStar = %s, book_threeStar = %s, book_twoStar = %s, book_oneStar = %s, 
                            book_readings = %s, book_reads = %s, book_toReads = %s, 
                            book_url = %s, book_picUrl = %s, 
                            hotComm_content = %s, hotComm_people = %s, hotComm_score = %s, hotComm_votes = %s
                    WHERE ID = %s
                    ''',
                                    (item['book_title'], item['book_author'], item['book_type'], item['book_press'], item['book_pubDate'],
                                     item['book_tags'], item['book_price'], item['book_isbn'], item['book_report'], item['book_dbid'], item['book_versions'],
                                     item['book_score'], item['book_votes'], item['book_shortComms'], item['book_reviews'],
                                     item['book_fiveStar'], item['book_fourStar'], item['book_threeStar'], item['book_twoStar'], item['book_oneStar'],
                                     item['book_readings'], item['book_reads'], item['book_toReads'],
                                     item['book_url'], item['book_picUrl'],
                                     item['hotComm_content'], item['hotComm_people'], item['hotComm_score'], item['hotComm_votes'],
                                     book['ID'])
                                    )

            self.connect.commit()

        except Exception as error:
            print('!' * 80)
            print('【BookItem】插入数据库错误')
            print('==> 错误书籍为：' + str(item['book_url']))
            print('==> 错误信息为：' + str(error))
            print('!' * 80)

    # def process_comm(self, item):
    #     try:
    #         # self.cursor.execute('''
    #         #                 SELECT * FROM tesla_book where book_dbid=%s
    #         #             ''', (item['hotComm_dbid']))
    #         # book = self.cursor.fetchone()
    #
    #         self.cursor.execute('''
    #                             UPDATE tesla.book
    #                                 SET hotComm_content = %s,
    #                                     hotComm_people = %s,
    #                                     hotComm_score = %s,
    #                                     hotComm_votes = %s
    #                             WHERE hotComm_dbid = %s
    #                             ''',
    #                             (item['hotComm_content'],
    #                              item['hotComm_people'],
    #                              item['hotComm_score'],
    #                              item['hotComm_votes'],
    #                              item['hotComm_dbid'])
    #                             )
    #
    #         self.connect.commit()
    #
    #     except Exception as error:
    #         print('!' * 80)
    #         print('【BookHotCommentItem】插入数据库错误')
    #         print('==> 错误书籍的豆瓣ID为：' + 'https://book.douban.com/subject/' + str(item.dbid)) + '/'
    #         print('==> 错误信息为：' + str(error))
    #         print('!' * 80)
