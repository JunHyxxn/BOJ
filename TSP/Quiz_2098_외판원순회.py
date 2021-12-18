def tsp(start, visited):
    ## Base condition
    if visited == (1<<n)-1:
        if W[start][0]: return W[start][0]
        else: return INF

    ## Memoization
    if dp[visited][start]!= INF: return dp[visited][start]

    
    ## 탐색
    for i in range(n):
        ## 아직 방문하지 않았고, 경로 존재할 경우 탐색 진행.
        if (visited & (1<<i))==0 and W[start][i] != 0:
            dp[visited][start] = min(dp[visited][start], tsp(i ,visited | (1<<i))+ W[start][i])
    return dp[visited][start]

n = int(input())
W = [list(map(int, input().split())) for _ in range(n)]
INF = float('INF')
dp = [[INF]*n for _ in range(2**n)]

print(tsp(0,1))