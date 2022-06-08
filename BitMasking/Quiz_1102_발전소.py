"""
문제 출처 : https://www.acmicpc.net/problem/1102
"""
from collections import deque

N = int(input())
cost = [list(map(int, input().split())) for _ in range(N)]
state = input()
P = int((input())) ## 필요 정상 발전소 수

now = 0 ## 현재 Bit
Y = 0 ## 초기 정상 발전소 수 
for i in range(len(state)):
    if state[len(state)-i -1] == 'Y':
        Y += 1
        now |= (1<<(len(state)-i -1))

if Y == 0 and P >0: ## 불가능한 경우
    print(-1)
elif Y >= P: ## 탐색할 필요 없는 경우
    print(0)
else: ## 그 외
    result = [float('inf')] * (1<<N) ## 524328 Bytes 

    result[now] = 0
    ## 4256ms
    def BFS(P, Y, start):
        queue = deque()
        queue.append((start, Y))
        res = float("inf")
        while queue:
            now_bit, now_cnt = queue.popleft()
            if now_cnt >= P:
                res = min(res, result[now_bit])
                continue
            for i in range(N):
                ## i : 정상 비트, j : 고장난 비트
                if now_bit & 1<<i == 0: continue ## i : 1인 Bit
                for j in range(N):
                    if now_bit & 1<<j == 1<<j: continue ## j : 0인 Bit
                    nxt_bit = now_bit | 1<<j
                    if result[nxt_bit] > result[now_bit] + cost[i][j]:
                        result[nxt_bit] = min(result[nxt_bit], result[now_bit] + cost[i][j])
                        queue.append((nxt_bit, now_cnt+1))
        return res

    res = BFS(P, Y, now)
    
    ## 1956ms
    # def DFS(bit, cnt):
    #     if cnt >= P:
    #         return 0
    #     if result[bit] != float("inf"):
    #         return result[bit]

    #     for i in range(N):
    #         if bit & (1<<i) == 0: continue
    #         for j in range(N):
    #             if bit & 1<<j == 1<<j: continue
    #             nxt_bit = bit | 1<<j
    #             result[bit] = min(result[bit], cost[i][j]+DFS(nxt_bit, cnt+1))
    #     return result[bit]
    # res = DFS(now, Y)
    
    
    print(res)
        
"""
16
0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
2 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
1 2 0 1 1 1 1 1 1 1 1 1 1 1 1 1 
1 2 1 0 1 1 1 1 1 1 1 1 1 1 1 1  
1 2 1 1 0 1 1 1 1 1 1 1 1 1 1 1 
1 2 1 1 2 0 1 1 1 1 1 1 1 1 1 1 
1 2 1 1 2 2 0 1 1 1 1 1 1 1 1 1 
1 2 1 1 2 2 1 0 1 1 1 1 1 1 1 1 
1 2 1 1 2 2 1 3 0 1 1 1 1 1 1 1 
1 2 1 1 2 2 1 3 1 0 1 1 1 1 1 1 
1 2 1 1 2 2 1 3 1 1 0 1 1 1 1 1 
1 2 1 1 2 2 1 3 1 1 4 0 1 1 1 1 
1 2 1 1 2 2 1 3 1 1 4 1 0 1 1 1 
1 2 1 1 2 2 1 3 1 1 4 1 1 0 1 1 
1 2 1 1 2 2 1 3 1 1 4 1 1 5 0 1 
1 2 1 1 2 2 1 3 1 1 4 1 1 5 3 0 
YNNNNNNNNNNNNNNN
16

ans = 15
"""