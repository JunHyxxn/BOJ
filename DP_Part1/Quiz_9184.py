"""
if a <= 0 or b <= 0 or c <= 0, then w(a, b, c) returns:
    1

if a > 20 or b > 20 or c > 20, then w(a, b, c) returns:
    w(20, 20, 20)

if a < b and b < c, then w(a, b, c) returns:
    w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)

otherwise it returns:
    w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)


위의 함수를 구현하는 것은 매우 쉽다. 하지만, 그대로 구현하면 값을 구하는데 매우 오랜 시간이 걸린다. (예를 들면, a=15, b=15, c=15)

a, b, c가 주어졌을 때, w(a, b, c)를 출력하는 프로그램을 작성하시오


=====================================================================================================================
dict형태를 이용해서 계산하면 어떨까?
"""
import sys
sol = dict()


for i in range(20+1):
    for j in range(20+1):
        for k in range(20+1):
            if i <= 0 or j<=0 or k<=0:
                sol[(i,j,k)] = 1
            elif i < j and j < k:
                sol[(i,j,k)] = sol[(i,j,k-1)] + sol[(i,j-1,k-1)] - sol[(i,j-1,k)]
            else:
                sol[(i,j,k)]  =sol[(i-1,j,k)] +  sol[(i-1,j-1,k)] + sol[(i-1,j,k-1)] - sol[(i-1,j-1,k-1)]

while(True):
    a,b,c = map(int, sys.stdin.readline().strip().split())
    if a == -1 and b == -1 and c == -1:
        break
    if a <= 0 or b<=0 or c<=0:
        print('w({0}, {1}, {2}) = 1'.format(a,b,c))
    elif a>20 or b>20 or c>20:
        print('w({0}, {1}, {2}) = {3}'.format(a,b,c, sol[(20,20,20)]))
    else:
        print('w({0}, {1}, {2}) = {3}'.format(a,b,c, sol[(a,b,c)]))
