MOD  = 1000000007

N, L, R = map(int, input().split())

dp = [[[0]*(N+1) for _ in range(N+1)] for _ in range(N+1)]
## Initialize DP
dp[1][1][1] = 1

## Make DP
## Recurrence Relation : dp[N][L][R] = dp[N-1][L-1][R] + dp[N-1][L][R-1] + dp[N-1][L][R] * (N-2)
for n in range(2, N+1):
    for l in range(1, L+1):
        for r in range(1, R+1):
            dp[n][l][r] = (dp[n-1][l-1][r] + dp[n-1][l][r-1] + (dp[n-1][l][r] * (n-2))) %MOD

print(dp[N][L][R]%MOD)