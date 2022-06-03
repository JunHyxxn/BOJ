"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJAVpqrzQDFAWr
"""
from collections import defaultdict

T = int(input())
for t in range(1, T+1):
    N, K = map(int, input().split())
    temp = {i : list(map(int, input().split())) for i in range(1, N + 1)}
    temp = sorted(temp.items(), key= lambda x : x[1][0])
    info = defaultdict(list)
    for idx, data  in enumerate(temp):
        k, v = data
        info[idx+1] = v
    dp = [[0]*(K+1) for _ in range(N+1)]

    for i in range(1, N+1):
        weight, cost = info[i]
        for j in range(1, K+1):
            if j < weight:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
                continue
            dp[i][j] = max(
                dp[i-1][j-weight] + cost
                , dp[i-1][j], dp[i][j-1]
            )

    print("#{} {}".format(t,dp[-1][-1]))