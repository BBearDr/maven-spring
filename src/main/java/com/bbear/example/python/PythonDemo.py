# -*- coding: utf-8 -*-
# 这是注释，并且python还是大小写敏感
a = 100
if a > 100:
    print('大于100')
else:
    print('输出中文')
A = 'ABC'
B = '中文'
# str转byte
print(A.encode('ascii'))
print(B.encode('utf-8'))
# byte转str
print(b'ABC'.decode('ascii'))
print(b'\xe4\xb8\xad\xe6\x96'.decode('utf-8', errors='ignore'))
print('I have money %d %s' % (10000000, 'chen'))
print('Hello, {0},成绩提升{1:.1f}%'.format('Ming', 50.235))
# list是可变的集合
nameList = ['Ming', 'Jack', 'Tom']
nameList.append('AJ')
nameList.insert(2, 'Joke')
# 删除list元素
nameList.pop()
print(nameList)
# tuple 是不可变的集合，无法改变集合的元素
classTuple = (1, 2, 'Cjx')
print(classTuple[1])
# age = input("birth:")
# s = int(age)
# if s > 1900:
#     print(True)
# else:
#     print(False)
for x in range(1):
    print(x)
# dict字典，相当于是map,无关乎key多少查询时间是相同的，占用空间大所以是空间换时间
# dict的key必须是不可变对象
str = "name"
d = {str: "1", "age": "18"}
# 增加元素
d['sex'] = "man"
# 当没有key时，返回none，也可以自定义值
# 删除使用pop,value也会对应的删除
print(d)
print(d['age'])
print(d.get('age'))
print(d.get('address', -1))
print('Tom' in d)
# set 无序无重复集合
s = set([1, 2, 3])
# 增加元素
s.add(4)
# 删除的是元素，不是坐标
s.remove(1)
print(s)
