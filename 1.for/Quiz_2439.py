"""
별 찍기
1 1
1 2
1 3
1 4
1 5
"""
n = int(input())
for i in range(1,n+1):
    print(' '*(n-i), '*'*i,sep = '')
