"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AWczm7QaACgDFAWn&categoryId=AWczm7QaACgDFAWn&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=7
"""
T = int(input())
for t in range(1, T+1):
    N = int(input())
    bus = [0] * 5001
    for _ in range(N):
        a, b = map(int, input().split())
        for i in range(a, b+1):
            bus[i] += 1

    P = int(input())
    result = ""
    for _ in range(P):
        i = int(input())
        result += (" " +str(bus[i]))
    print("#{}{}".format(t, result))


