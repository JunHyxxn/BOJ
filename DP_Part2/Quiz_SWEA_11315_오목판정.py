T = int(input())
results = []
for t in range(1, T+1):
    N = int(input())
    board = [['.']+list(map(str, input()))+['.'] for _ in range(N)]
    board = [['.']*(N+2)] + board
    dp = [[[0]*(N+2) for _ in range(N+1)] for _ in range(4)] ## mode x row x col
    
    flag = False
    for i in range(1, N+1):
        if flag: break
        for j in range(1, N+1):
            if board[i][j] =="o":
                dp[0][i][j] = dp[0][i][j-1] + 1
                dp[1][i][j] = dp[1][i-1][j] + 1
                dp[2][i][j] = dp[2][i-1][j-1] + 1
                dp[3][i][j] = dp[3][i-1][j+1] + 1
                
                if dp[0][i][j] >= 5 or dp[1][i][j] >= 5 or dp[2][i][j] >= 5 or dp[3][i][j] >= 5:
                    flag = True
                    break
    
    
    results.append("#{} {}".format(t, "YES" if flag else "NO"))

for result in results:
    print(result)
    
    

