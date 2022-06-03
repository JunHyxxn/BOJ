
N, K = map(int, input().split())
dp = [[0]*(K+1) for _ in range(N+1)]
temp = []
for _ in range(N):
    temp.append(list(map(int, input().split())))
temp = sorted(temp, key=lambda x : x[0])
info = {}
for idx, data in enumerate(temp):
    info[idx+1] = data

for i in range(1, N+1):
    weight, cost = info[i]
    for j in range(1, K+1):
        if j < weight:
            dp[i][j] = max(dp[i][j-1], dp[i-1][j])
            continue
        dp[i][j] = max(
            dp[i][j-1], dp[i-1][j],
            dp[i-1][j-weight] + cost
        )
print(dp[-1][-1])