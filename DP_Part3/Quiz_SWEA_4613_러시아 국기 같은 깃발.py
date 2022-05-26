"""
color_info : R, B, W 순서
"""

color2idx ={"R" : 0, "B" : 1, "W" : 2}
T = int(input())
for t in range(1, T+1):
    N, M = map(int, input().split())
    flag = [list(map(str, input())) for _ in range(N)]
    colors = []
    for i in range(N):
        color_info = [0,0,0] ## R B W
        for j in range(M):
            color_info[color2idx[flag[i][j]]] += 1
        colors.append(color_info)

    dp = [[float("inf")]*3 for _ in range(N)]
    dp[0][2] = M-colors[0][2]
    for i in range(1, N-1):
        R, B, W = colors[i]
        ## Red -> red 추가 필요
        dp[i][0] = min(dp[i-1][0]+M-R, dp[i-1][1] + M-R)
        dp[i][1] = min(dp[i-1][1] + M-B, dp[i-1][2]+M-B, dp[i][1])
        dp[i][2] = dp[i-1][2] + M-W
    print("#{} {}".format(t, min(dp[-2][0], dp[-2][1])+M-colors[-1][0] ) )


