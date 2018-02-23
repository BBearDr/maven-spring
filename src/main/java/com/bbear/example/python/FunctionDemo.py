# python中的函数
print(abs(-100))
print(max(1, 4, 5, 6, 7))
# int()可以把其他类型转化成数字
# 相应的还有float，str，bool,
print(int('12'))
print(bool(1))
# 可以将函数起个别名
a = abs
print(a(-2))


# 自定义函数
def my_function(x):
    # 如果没有return，那么返回的将是none
    # return 返回值是多个的时候可以用','分割
    # pass相当与continue
    if x > 10:
        return x
    else:
        return x - 1


print(my_function(9))


# 自定义绝对值函数
def my_abs(x):
    if not isinstance(x, (float, int)):
        raise TypeError('bad type')
    else:
        return abs(x)


print(my_abs(10))


# 定义函数的参数设置
# 参数中可以使用默认值，相当于重载，不传则使用默认值
def power(x, n=2):
    s = 1
    while n > 0:
        n = n - 1
        s = s * x
    return s


print(power(5))


# 自定义函数，注意参数的不可变性
def add_end(L=None):
    if L is None:
        L = []
    L.append('end')
    return L


print(add_end())
print(add_end())


# 自定义函数传入可变参数 不带* 传参数时只能使用list,tuple
def cal(*number):
    s = 0
    for n in number:
        s = s + n
    return s


print(cal(1, 2, 3))


# 必选参数，默认参数，可变参数（*arg），命名关键字参数(在没有可变参数情况下，需要使用*分隔符)，关键字参数(**arg)
def cal1(a, b=1, *arg, **num):
    print(a, b, arg, num)


def cal2(a, b=1, *, d, **num):
    print(a, b, d, num)


cal1(1, 2)
cal2(1, 2, d=3)
# 前2个参数可以忽略，会从命名关键字中取前2个补充上
arg = (1, 2, 3, 4, 5)
num = {'n': 1, 'm': 2}
cal1(*arg, **num)
# 汉诺塔
def move(n, a, b, c):
    if n==1:
        print(a, '-->', c)
    else:
        move(n-1, a, c, b)
        move(1, a, b, c)
        move(n-1, b, a, c)
print(move(3, 'a', 'b', 'c'))
