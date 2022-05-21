"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AWNcJ2sapZMDFAV8&categoryId=AWNcJ2sapZMDFAV8&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=10

해당 코드는 좌표 압축을 통해 N = 200 까지 단축할 수 있기에 Line Sweeping 으로 해결했지만,
시간을 좀 더 단축하고자 한다면 Segment Tree를 활용해 해결할 수 있다.

복도를 하나의 노드로 두고 leaf node가 True인 개수를 세면 된다.
"""
T = int(input())
def compression(num):
    num = int(num)
    if num%2 == 1:
        return (num+1)//2
    elif num%2 ==0:
        return num//2

for t in range(1, T+1):
    N = int(input())
    line = [0] * 201
    for _ in range(N):
        a, b = map(compression, input().split())
        a, b = min(a, b), max(a, b)
        for i in range(a, b+1):
            line[i] += 1
    print("#{} {}".format(t, max(line)))


