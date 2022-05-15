
"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWRurBkKkKADFAXt
"""

## DP
T = int(input())

for t in range(1, T+1):
    L, K = map(int, input().split())
    word = input()
    cost = {chr(i) : min(list(map(int, input().split()))) for i in range(97, 97+K)}
    ## DP Initialize
    dp = [[0]*L for _ in range(L)]
    for i in range(L):
        for j in range(i+1, L):
            dp[i][j] = float("INF")
    
    for diff in range(1, L):
        for j in range(diff, L):
            i = j - diff
            if word[i] == word[j]:
                dp[i][j] = min(dp[i][j], dp[i+1][j-1])
                continue
            dp[i][j] = min(dp[i][j], dp[i+1][j]+cost[word[i]], dp[i][j-1]+cost[word[j]])
    print("#{} {}".format(t, dp[0][-1]))

## Back Tracking - Stack memory 1MB => 재귀 사실상 불가능.
# import sys
# sys.setrecursionlimit(10**6)
# T = int(input())
# def BT(cost, word, value, path):
#     global min_value
#     print(word, value, min_value)
#     if word == ''.join(reversed(word)):
#         min_value = min(min_value, value)
#         return 
#     if value > min_value: 
#         return 
    
#     for alpha, weight in cost.items():
#         add, rmv = weight
#         ## 제거
#         temp = word
#         while temp.find(alpha) >= 0:
#             temp = temp.replace(alpha, '')
#         if not path.__contains__(temp):
#             path.add(temp)
#             BT(cost, temp, value+rmv, path)
        
#         ## 추가
#         for i in range(len(word)):
#             temp = word[:i+1] + alpha + word[i+1:]
#             if not path.__contains__(temp):
#                 path.add(temp)
#                 BT(cost, temp, value+add, path)
    
        
# for t in range(1, T+1):
#     L, K = map(int, input().split())
#     word = input()
#     cost = {chr(i) : list(map(int, input().split())) for i in range(97, 97+K)}
#     min_value = float("INF")
#     path = set()

#     BT(cost, word, 0, path)
 
#     print("#{} {}".format(t, min_value))
    
    



