"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AX_N7oHac6QDFARi&categoryId=AX_N7oHac6QDFARi&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=1
"""
T = int(input())

for t in range(1, T+1):
    N, K = map(int, input().split())
    mod = 10**9 + 7
    nums = list(map(int, input().split()))
    right, left = [0] *N, [0] *N
    for i in range(1, N):
        for j in range(i):
            if nums[j] > nums[i]:
                right[i] += 1

    for i in range(N-2, -1, -1):
        for j in range(i+1, N):
            if nums[i] < nums[j]:
                left[i] += 1
    if K ==1:
        print("#{} {}".format(t, sum(right)%mod))
    else:
        sum_r = sum(right)
        sum_l = sum(left)
        total = ((K*(K+1)//2)*sum_r) + ((K*(K-1)//2)*sum_l)
        print("#{} {}".format(t, total%mod))
