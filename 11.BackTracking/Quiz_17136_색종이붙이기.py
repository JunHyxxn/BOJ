# # def attach(dp, x,y, length):
# #     global papers, counts
# #     flag = True
# #     for i in range(x, x+length):
# #         for j in range(y, y+length):
# #             if dp[i][j] == 0:
# #                 flag = False
# #                 break
    
# #     if flag:
# #         for i in range(x,x+length):
# #             papers[i][y:y+length] = [0]*length
# #             dp[i][y:y+length] = [0]*length
# #         counts[length] -=1
# #     else:
# #         dp[x+length-1][y+length-1] -= 1
# #     return dp

# # def solve(dp, length):
# #     global papers
    
# #     for i in range(10, 0 ,-1):
# #         for j in range(10,0,-1):
# #             if dp[i][j] == length:
# #                 dp = attach(dp, i-length+1, j-length+1, length)
                
# #     return dp            
                
# papers = [[0]* 11 for _ in range(11)]
# for i in range(1, 11):
#     papers[i][1:] = list(map(int, input().split()))
    
# dp = [[0]*11 for _ in range(11)]
# for i in range(1, 11):
#     for j in range(1, 11):
#         if papers[i][j]:
#             dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
#             if dp[i][j] >5: dp[i][j] = 5
            
# counts = {i:5 for i in range(1,6)}
# for l in range(5,0,-1):
#     dp = solve(dp, l)
    
# total = 0
# flag = False
# for _, cnt in counts.items():
#     if cnt <0: 
#         flag =True
#         break
#     else:
#         total += (5-cnt)
# if flag:
#     print(-1)
# else:
#     print(total)

import sys
sys.setrecursionlimit(10**8)

def summarize():
    global papers
    total = 0
    for i in range(10):
        total += sum(papers[i])
        
    return total

def dfs(x, y):
    global papers, counts, answer
    if x >=10:
        if summarize() == 0:
            total = 0
            for _, v in counts.items():
                total += (5-v)
            answer = min(answer, total)
        return
    if y >= 10:
        dfs(x+1, 0)
        return
    
    if papers[x][y]:
        for k in range(5):
            if x+k >= 10 or y+k>=10: continue
            if counts[k] <= 0: continue
            
            flag=  True
            for i in range(x, x+k+1):
                for j in range(y, y+k+1):
                    if not papers[i][j]:
                        flag = False
                        break
                if not flag: break
            
            if flag:
                for i in range(x, x+k+1):
                    for j in range(y, y+k+1):
                        papers[i][j] = 0
                counts[k] -= 1
                dfs(x,y+k+1)
                
                counts[k] += 1
                for i in range(x, x+k+1):
                    for j in range(y, y+k+1):
                        papers[i][j] = 1
    else:
        dfs(x,y+1)                        
    return 

papers = [list(map(int, input().split())) for _ in range(10)]
counts = {i:5 for i in range(5)}
answer = float('inf')
dfs(0,0)
print(answer if answer != float('inf') else -1)