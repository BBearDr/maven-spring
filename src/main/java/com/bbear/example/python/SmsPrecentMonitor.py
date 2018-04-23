#!/usr/bin/python
# -*- coding:utf-8 -*-
import time, datetime, sys, os, re
import MySQLdb
import httplib, urllib, urllib2, json, time, datetime, commands, redis
from operator import itemgetter, attrgetter
from rediscluster import RedisCluster
import sendMail

mydir = sys.path[0]
reload(sys)
sys.setdefaultencoding("utf-8")
sys.path.append(mydir + "/../tools/")


def getRedisClusterHash(key):
    redis_nodes = [{'host': 'rc_pt_send_center1', 'port': 8031},
                   {'host': 'rc_pt_send_center2', 'port': 8032},
                   {'host': 'rc_pt_send_center3', 'port': 8033},
                   {'host': 'rc_pt_send_center4', 'port': 8034}]
    try:
        redisconn = RedisCluster(startup_nodes=redis_nodes)
        result = redisconn.hgetall(str(key))
        return result
    except Exception, e:
        errMsg = str(e)
        print "ERROR:" + errMsg


def getRedisClusterString(key):
    redis_nodes = [{'host': 'rc_pt_send_center1', 'port': 8031},
                   {'host': 'rc_pt_send_center2', 'port': 8032},
                   {'host': 'rc_pt_send_center3', 'port': 8033},
                   {'host': 'rc_pt_send_center4', 'port': 8034}]
    try:
        redisconn = RedisCluster(startup_nodes=redis_nodes)
        result = redisconn.get(str(key))
        return result
    except Exception, e:
        errMsg = str(e)
        print "ERROR:" + errMsg


def sendSms(mobile, content, type):
    url = 'http://120.196.116.126:7902/MWGate/wmgw.asmx/MongateSendSubmit'
    values = {'userId': 'js0619', 'password': '890972', 'pszMobis': mobile, 'pszMsg': content, 'iMobiCount': '1',
              'pszSubPort': '00123', 'MsgId': '0'}
    data = urllib.urlencode(values)
    req = urllib2.Request(url, data)
    response = urllib2.urlopen(req)
    respon = response.read()
    return respon

class BizCodeMail:
    def __init__(self, bizCode, mail, mobile):
        self.bizCode = bizCode
        self.mail = mail
        self.mobile = mobile

class BizCodeCountPrice:
    def __init__(self, bizCode, strKey):
        self.bizCode = bizCode
        self.strKey = strKey


if __name__ == "__main__":
    biz_has_day_count = "sms_AbstractCondition_bizcode_has_send_"
    biz_has_month_count = "sms_AbstractCondition_bizcode_month_has_send_"
    # 获取业务对应的管理人邮箱
    bizCodeMailList = []
    f = file(mydir + '/sms_biz_manageinfo.txt', 'r')
    # 联系人信息
    bizMailList = []
    # 目前为止今天的已发送量
    bizCodeDayCountList = []
    # 目前为止当月的已发送量
    bizCodeMonthCountList = []
    while True:
        line = f.readline()
        if len(line) == 0:
            break
        biz_mail_arr = line.split("\t")
        if len(biz_mail_arr) != 3:
            continue
        bizCode = biz_mail_arr[0]
        mail = biz_mail_arr[1]
        mobile = biz_mail_arr[2]
        bizCodeMailList.append(BizCodeMail(bizCode, mail, mobile))
        bizCodeDayCountList.append(BizCodeCountPrice(bizCode, biz_has_day_count + time.strftime('%Y-%m-%d',time.localtime(time.time())) + bizCode))
        bizCodeMonthCountList.append(BizCodeCountPrice(bizCode, biz_has_month_count + time.strftime('%Y-%m-%d', time.localtime(time.time())) + bizCode))
    f.close()

    # 查询每天的发送总量
    dayBizCodeMaxBillCountDict = getRedisClusterHash("sms_AbstractCondition_bizcode_max_send_")
    # 查询每个月的发送总量
    monthBizCodeMaxBillCountDict = getRedisClusterHash("sms_AbstractCondition_bizcode_month_max_send_")

    # 统计当月的发送量
    for index, bizCodeMonthCountPrice in enumerate(bizCodeMonthCountList):
        monthLimit = "70000"
        if monthBizCodeMaxBillCountDict.has_key(bizCodeMonthCountPrice.bizCode):
            monthLimit = str(monthBizCodeMaxBillCountDict[bizCodeMonthCountPrice.bizCode])
        monthHasSend = str(getRedisClusterString(bizCodeMonthCountPrice.strKey))
        monthSendRate = round(float(monthHasSend) / float(monthLimit) * 100, 2)
        if monthSendRate >= 80 and monthSendRate <= 95:
            # 邮箱，标题，内容
            for i, bizCodeMail in enumerate(bizCodeMailList):
                if bizCodeMonthCountPrice.bizCode == bizCodeMail.bizCode:
                    print 'monthCountendMail:' + sendMail.send_mail(bizCodeMail.mail, '邮件发送总量提醒',
                                       '业务:' + bizCodeMonthCountPrice.bizCode + '截止目前已发送总量为:' + str(
                                           monthSendRate) + '%')
        elif monthSendRate > 95:
            for i, bizCodeMail in enumerate(bizCodeMailList):
                if bizCodeMonthCountPrice.bizCode == bizCodeMail.bizCode:
                    print 'monthCountendSMS:' + sendSms(bizCodeMail.mobile,
                            '业务:' + bizCodeMonthCountPrice.bizCode + '截止目前已发送总量为:' + str(monthSendRate) + '%', '')
    # 统计当天的发送量
    for index, bizCodeDayCountPrice in enumerate(bizCodeDayCountList):
        dayLimit = "10000"
        if dayBizCodeMaxBillCountDict.has_key(bizCodeDayCountPrice.bizCode):
            dayLimit = str(dayBizCodeMaxBillCountDict[bizCodeDayCountPrice.bizCode])
        dayHasSend = str(getRedisClusterString(bizCodeDayCountPrice.strKey))
        daySendRate = round(float(dayHasSend) / float(dayLimit) * 100, 2)
        if daySendRate >= 80 and daySendRate <= 95:
            for i, bizCodeMail in enumerate(bizCodeMailList):
                if bizCodeDayCountPrice.bizCode == bizCodeMail.bizCode:
                    print 'dayCountSendMail:'+ sendMail.send_mail(bizCodeMail.mail, '邮件发送总量提醒',
                                       '业务:' + bizCodeDayCountPrice.bizCode + '截止目前已发送总量为:' + str(monthSendRate) + '%')
        elif daySendRate > 95:
            for i, bizCodeMail in enumerate(bizCodeMailList):
                if bizCodeDayCountPrice.bizCode == bizCodeMail.bizCode:
                    mobileList = bizCodeMail.mobile.split(',')
                    print 'dayCountSendSMS:'+ sendSms(bizCodeMail.mobile,
                            '业务:' + bizCodeDayCountPrice.bizCode + '截止目前已发送总量为:' + str(monthSendRate) + '%', '')
