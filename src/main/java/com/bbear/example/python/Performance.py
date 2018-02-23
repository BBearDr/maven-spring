# 高级特性
# 切片(Slice),含头不含尾,如果是0可以省略不写
L = [1, 2, 3, 4, 5, 6]
print(L[0:3])
print(L[-2:])
# 每n个取一个
print(L[::2])
print(L)
# 迭代
M = {'a': 1, 'b': 2, 'c': 3}
for k in M:
    print(k)
for v in M.values():
    print(v)
for k, v in M.items():
    print(k, v)
# 类似list针对下标进行迭代
for i, value in enumerate(L):
    print(i, value)
for x, y in ([1, 2], [3, 4]):
    print(x, y)

# 查找list中的最大值和最小值
N = [3, 5, 7, 9, 12, 2]
def findMaxMin(N):
    max = N[0]
    minn = N[0]
    for x in N:
        if max > x:
            pass
        else:
            max = x
        if minn < x:
           pass
        else:
         minn = x
    print(max, minn)
findMaxMin(N)


