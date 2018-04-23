#!/usr/bin/python
#encoding:utf-8
import time,datetime,sys,os,re,MySQLdb
import httplib, urllib,urllib2,json,time,datetime,commands
from operator import itemgetter, attrgetter

def getCurPath() :
    path = sys.path[0]
    if os.path.isfile(path) :
        return os.path.dirname(path)
    elif os.path.isdir(path):
        return path
    else :
        print 'ERROR:can not get current path!'
        return ''
curpath = getCurPath()
print 'curpath in py:'+curpath
sys.path.append(curpath+'/tools')
import sendMail
reload(sys)
sys.setdefaultencoding('utf-8')

signature = '【大街网】'
mobileMap = dict()
MNOCache = dict()
tplToSendTime = dict()

class Monitor:
    def __init__(self,channel,operator,sendSum,successSum,unresultSum,signature):
        self.channel = channel
        self.sendSum = sendSum
        self.successSum = successSum
        self.operator = operator
        self.unresultSum = unresultSum
        self.signature = signature
    def __str__(self):
        signaturename=''
        operator =''
        if cmp("TUSI",self.signature)==0:
            signaturename = '【吐司】'
        elif cmp("DAJIE",self.signature)==0:
            signaturename =  '【大街网】'
        elif cmp("DAJIEYUNWEI",self.signature)==0:
            signaturename= '【大街运维】'
        elif cmp("XIAOTUIJI",self.signature)==0:
            signaturename = '【小推鸡】'
        elif cmp("GOUDA",self.signature)==0:
            signaturename = '【勾搭】'
        else:
            signaturename = self.signature
        if cmp("CMCC",self.operator)==0 or "CMCC"==self.operator:
            operator = "移动"
        if cmp("CU",self.operator)==0:
            operator = "联通"
        if cmp("CT",self.operator)==0:
            operator = "电信"
        else :
            operator = self.operator
        #      return '%s通道发送%s出问题' % (self.channel,self.operator)
        return '通道:%s 运营商:%s 签名:%s 发送:%s 成功:%s 无状态:%s' % (self.channel,operator,signaturename,self.sendSum,self.successSum,self.unresultSum)

class TimestampDiff:
    def __init__(self,tpl,diff,count):
        self.tpl = tpl
        self.diff = diff
        self.count = count
    def __str__(self):
        return '<td align="center">%s</td><td align="center">%s(s)</td><td align="center">%s</td>' % (self.tpl,self.diff,self.count)

class SendSlow:
    def __init__(self,tpl,status,count,createDate):
        self.tpl = tpl
        self.status = status
        self.count = count
        self.createDate = createDate
    def __str__(self):
        return 'tpl:%s status:%s create_date:%s count:%s' % (self.tpl,self.status,self.createDate,self.count)

class SendAlive:
    def __init__(self,tpl,count):
        self.tpl = tpl
        self.count = count
    def __str__(self):
        return 'tpl:%s noSendCount:%s' % (self.tpl,self.count)

def operatorReturn(opera):
    CMCC = ['中国移动','移动']
    CU = ['联通','中国联通']
    CT = ['中国电信','电信']
    if  opera in CMCC or '移动' in opera:
        return 'CMCC'
    elif opera in CU or '联通' in opera:
        return 'CU'
    elif opera in CT or '电信' in opera:
        return 'CT'
    else:
        return 'null'

# 通道，运营商，签名 3个维度获取短信的发送量，成功量以及未返回结果数量
def getMonitorData(before,end):
    reports=dict()
    sql = "select channel,status, mobile,signature from tb_sms_data where status not in(4,5) and create_date >'"+before+"' and create_date<'"+end+"' and channel!='';"
    print sql
    db = MySQLdb.connect('10.10.67.62','platform_sel','dajie.platform)-','DB_MAIL',3308)
    cursor = db.cursor()
    cursor.execute(sql)
    resultss = cursor.fetchall()
    for line in resultss:
        if line[2][:3] in MNOCache:
            operator = MNOCache[line[2][:3]]#缓存中的运营商
            if operator is None or operator.strip() == 'None':
                print str(operator),line
                continue
        else:
            operator =getOperator(line[2])
            print str(line[2])+ str(operator)
            if operator.strip()=='' or operator is None or operator=='1':
                continue
            operator = operatorReturn(operator)
            if operator=='':
                continue
            MNOCache[line[2][:3]] = operator
            w.write(str(line[2][:3])+'\t'+str(operator)+'\n')
        key = str(line[0])+str(operator)+str(line[3])#通道 + 运营商 + 签名
        if key not in reports:
            success = 0
            if line[1]==3:
                success = 1
            monitor = Monitor(line[0],operator,1,success,1-success,line[3])
            reports[key] = monitor
        else:
            reports[key].sendSum = reports[key].sendSum + 1
            if line[1] == 3:
                reports[key].successSum = reports[key].successSum + 1
            else:
                reports[key].unresultSum = reports[key].unresultSum + 1
    return reports

#查询各个模板短信发送时间与入库时间差值 的数量
def getTimestampDiff():
    records = []
    sql = "select tpl_id,timestampdiff(second,create_date,send_time) diff,count(1) from tb_sms_data where create_date > left(now(),13) and status in (2,3,4) group by tpl_id,timestampdiff(second,create_date,send_time)"
    db = MySQLdb.connect('10.10.67.62','platform_sel','dajie.platform)-','DB_MAIL',3308)
    cursor = db.cursor()
    cursor.execute(sql)
    result = cursor.fetchall()
    for line in result:
        timestampDiff = TimestampDiff(line[0],line[1],line[2])
        records.append(timestampDiff)
    return records

#查询短信是否长时间未加载；或者加载后未发送的。
def getIsAlive(sendDate):#sendDate取10min之前的数据
    tplToSendTime = dict()
    records = list()
    db = MySQLdb.connect('10.10.67.62','platform_sel','dajie.platform)-','DB_MAIL',3308)
    cursor = db.cursor()
    #查询前20min
    sql1 = "select distinct tpl_id from tb_sms_data where  create_date>'%s' and status in(0,1);" %(str(sendDate))
    print sql1
    cursor.execute(sql1)
    result = cursor.fetchall()
    tpls=''
    for line in result:
        print line
        if line not in ['425','428','429','430','10001','10012','10013','10016','10017','10031']:#这些是营销模板号大批量，不用考虑为发送
            tpls = str(tpls) + str(line[0])+','
    print "tpls:"+tpls +"\nsleep before："+str(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())))
    '''
    sql2 = "select tpl_id ,MAX(send_time) from tb_sms_data where create_date >'%s' and tpl_id in(%s)  group by tpl_id;" %s(str(sendDate),tpls[:-1])
    cursor.execute(sql)
    result_2 = cursor.fetchall()
    for lin2 in result_2 : 
        tplToSendTime[line2[0]] = line2[1]
    '''
    if len(tpls) > 1 :
        currentNow =datetime.datetime.now()- datetime.timedelta(minutes=1)
        currentTimeAfter = datetime.datetime.strftime(currentNow,'%Y-%m-%d %H:%M:%S')#取当前时间前一分钟
        sql = "select tpl_id ,count(1) from tb_sms_data where create_date >'%s' and create_date<'%s' and tpl_id in(%s) and status in(0,1) group by tpl_id;" %(str(sendDate),currentTimeAfter,tpls[:-1])
        print sql+"\n sleep 1min after："+currentTimeAfter
        cursor.execute(sql)
        result = cursor.fetchall()
        if result!=None and result!='' :
            for line in result:
                print "tpl_id:"+str(line[0])+"\t count(1):"+str(line[1])
                sendAlive= SendAlive(line[0],line[1])
                records.append(sendAlive)
    return records

#查询短信是否入库后长时间没有发送
def getSlowbusiness(before,end):
    records = list()
    sql = "select tpl_id , status , sum(case when timestampdiff(minute,create_date,now())>30 then 1 else 0 end) as 'nums' , create_date from tb_sms_data where create_date >'%s' and create_date<'%s' and  status in(0,1)  group by tpl_id,status;" %(str(before),str(end))
    print sql
    db = MySQLdb.connect('10.10.67.62','platform_sel','dajie.platform)-','DB_MAIL',3308)
    cursor = db.cursor()
    cursor.execute(sql)
    result = cursor.fetchall()
    for line in result:
        if int(line[2])>1000:
            sendSlow = SendSlow(line[0],line[1],line[2],line[3])
            print str(sendSlow)
            records.append(sendSlow)
    return records

def sendSms(mobile,content,type):
    #默认走国都的发送
    url='http://qxtsms.guodulink.net:8000/QxtSms/QxtFirewall?Content='+urllib.quote((content+'【大街运维】').encode('gbk'))
    values ={'OperID':'dajyw','OperPass':'1224yunwei','SendTime':'','ValidTime':'','DesMobile':mobile,'ContentType':'8'}
    if cmp(type,"GUODU")==0:#zzwx的发送通道
        url='http://sms.pica.com/zqhdServer/sendSMS.jsp?regcode=ZXHD-CRM-0100-TZVERR&pwd=f3c0f24b4fcad2bdef9571ba7e4d6495&phone='+mobile+'&CONTENT='+urllib.quote((content+'【大街网】').encode('gbk'))+'&extnum=&level=1&schtime=&reportflag=1&url=&smstype=4&hardKEY=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'
    elif cmp(type,"MWPT")==0:
        url='http://120.196.116.126:7902/MWGate/wmgw.asmx/MongateSendSubmit'
        values ={'userId':'js0619','password':'890972','pszMobis':mobile,'pszMsg':content,'iMobiCount':'1','pszSubPort':'*','MsgId':'0'}
    data = urllib.urlencode(values)
    req = ''
    if cmp(type,"ZZWX")==0:
        req = urllib2.Request(url)
    else:
        req = urllib2.Request(url, data)
    response = urllib2.urlopen(req)
    respon = response.read()
    return  respon

def renderOld(objs):
    print str(objs)
    content =  '<table width="100%" align="center" border="1" cellpadding="2" cellspacing="0" bordercolor="#00BFFF"><tbody><tr align="center">'
    for index,obj in enumerate(objs):
        if index%2==0:
            content =content+'<tr align="center">'+ str(obj) + '</tr>'
        else:
            content =content+'<tr align="center" style="background:#EAF2D3">' + str(obj) + '</tr>'
    content = content + '</tbody></table><hr style="height:3px">'
    print content
    return content

def render(header,objs):
    print str(objs)
    content =  '<table width="100%" align="center" border="1" cellpadding="2" cellspacing="0" bordercolor="#00BFFF" style="word-break:break-all; word-wrap:break-all;"><tbody><tr>'
    for i in range(len(header)):
        content = content + '<td style="white-space: nowrap; text-align:center;border: 1px solid #ccc;" >'+header[i]+'</td>'
    content = content + '</tr>'
    for index,obj in enumerate(objs):
        if index%2==0:
            content =content+'<tr align="center">'+ str(obj) + '</tr>'
        else:
            content =content+'<tr align="center" style="background:#EAF2D3">' + str(obj) + '</tr>'
    content = content + '</tbody></table><hr style="height:3px">'
    return content

#调用http请求 根据手机号获取运营商
def getOperator(mobile):
    try:
        mobileHeader = mobile[0:3]
        if mobileHeader in mobileMap:
            return mobileMap[mobileHeader]
        else:
            url = 'http://apis.baidu.com/apistore/mobilephoneservice/mobilephone?tel='+mobile
            #url = 'http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone='+mobile
            req = urllib2.Request(url)
            req.add_header("apikey", "e58672d49deb5777ea65b7f3cd488902")
            resp = urllib2.urlopen(req)
            content = resp.read()
            supplier = json.loads(content)
            #operator =  supplier["retData"]["supplier"]
            #mobileHead = supplier["retData"]["suit"][0:3]
            operator =  supplier["retData"]["carrier"]
            mobileHead = supplier["retData"]["telString"][0:3]
            mobileMap[mobileHead] = operator
            return operator
    except Exception,e:
        print 'error:'+mobile,e
        return '1'

if __name__ == "__main__":
    print "========================"+time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())+"==============================="
    to_mobile1= '13153226293'
    to_mobile = '13264015025'
    to_email = ['junxiong.chen@dajie-inc.com']
    timestampList=list()

    #加载MNO数据
    f = open('MNO.map','r')
    for line in f:
        line = line[:-1]
        info = line.split('\t')
        MNOCache[info[0]] = info[1]
    f.close()
    w = open('MNO.map','a')
    print 'MNOCache size:'+str(len(MNOCache))
    before = time.strftime('%Y-%m-%d',time.localtime(time.time()-60*20))
    beforeHour = time.strftime('%Y-%m-%d %H:%M',time.localtime(time.time()-60*10))

    after = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
    currentminute=time.strftime('%M',time.localtime(time.time()))

    if int(currentminute)%20==0:
        #短信延时报警diff(send_time,create_date)
        timestampDiffList = getTimestampDiff()
        content = ''
        t_channelResult=''
        for line in timestampDiffList:
            print str(line)
            if(line.diff >= 3 and line.count >=5):
                timestampList.append(str(line))
                t_channelResult=t_channelResult+str(line)

        if len(timestampList) >0:
            content = render(["模板号","延时","数量"],timestampList)
            sendMail.send_mail(to_email,'短信延时'+before+'--'+after,content)
            if len(t_channelResult)>60:
                t_channelResult=t_channelResult[len(t_channelResult)-60:len(t_channelResult)-1]

        #通道异常报警（当前两小时内无数据入库）
        monitorDateStart = time.strftime('%Y-%m-%d %H',time.localtime(time.time()-60*60*2))
        results = getMonitorData(monitorDateStart,after) #查询开始时间（当前时间提前两小时）到结束时间（当前时间）数据
        if len(results)<=0:
            sendSms(to_mobile,'短信无数据入库','')
            sendSms(to_mobile1,'短信无数据入库','')
            exit
        channelResult=''
        for line in sorted(results.keys()):
            monitor = results[line]
            print str(monitor)
            if monitor.sendSum> 5 and  float(monitor.successSum)/monitor.sendSum < 0.3:
                if len(monitor.operator)<2 or monitor.operator is None or monitor.operator=='':
                    print "filter"+str(monitor)
                    continue
                if monitor.channel == 'YXLK' and monitor.operator == 'CT':
                    print 'YXLK CU:'+str(monitor)
                    continue
                print 'alert:'+str(monitor)
                channelResult= channelResult + str(monitor)+'<br>'
        dayWeek = time.localtime(time.time())[6]+1  #time tuple
        if len(channelResult)>5:
            if dayWeek >=1 and dayWeek <= 6:#周一到周六邮件报警　　周天邮件短信报警
                sendMail.send_mail(to_email,'通道报警'+before+'--'+after,channelResult)
            else:
                sendMail.send_mail(to_email,'通道报警'+before+'--'+after,channelResult)
                sendSms(to_mobile,channelResult,monitor.channel)
                sendSmS(to_mobile1,channelResult,monitor.channel)

                #短信入库长时间为发送监控
        beforeTime = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()-60*60*4))
        sendSlowList = getSlowbusiness(beforeTime,after)#当前时间提前4h到当前时间的数据
        if len(sendSlowList) >0:
            content = renderOld(sendSlowList)
            sendMail.send_mail(to_email,'短信超时未发送'+before+'--'+after,content)

    #系统存活报警, 取10min前到目前的数据
    isAlive = getIsAlive(beforeHour)
    isAliveContent = "未发送:"
    if len(isAlive) > 0:
        for line in isAlive:
            print line
            if line.count>5 :
                isAliveContent = isAliveContent + str(line.tpl)+'模板:'+str(line.count)+';'
        print str(len(isAliveContent))+'\t'+str(isAliveContent)
        if len(isAliveContent)>13:
            sendSms(to_mobile,str(isAliveContent),'')
            sendSms(to_mobile1,str(isAliveContent),'')
