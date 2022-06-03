
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

d = [[1,0], [0,1], [-1,0],[0,-1]]
M, N = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(M)]
cnt = [[-1]*N for _ in range(M)]
def DFS(x,y):
    if x == M-1 and y == N-1:
        return 1
    if cnt[x][y] != -1:
        return cnt[x][y]
    cnt[x][y] = 0
    for i in range(4):
        dx, dy = d[i]
        nx, ny = x+dx, y+dy
        if 0<=nx <M and 0<=ny<N and board[x][y] > board[nx][ny]:
            cnt[x][y] += DFS(nx, ny)
    return cnt[x][y]

DFS(0,0)
print(cnt[0][0] if cnt[0][0] != -1 else 0)




"""
BFS는 시간초과 발생한다.
"""
# from collections import deque
#
# d = [[-1,0], [0,-1], [1,0],[0,1]]
# M, N = map(int, input().split())
# board = [list(map(int, input().split())) for _ in range(M)]
#
#
# def BFS(start):
#     result = 0
#     queue = deque()
#     queue.append(start)
#     impos = [[False]*N for _ in range(M)]
#
#     while queue:
#         x, y= queue.popleft()
#         if x == 0 and y == 0:
#             result += 1
#             continue
#         isImpos = True
#         for i in range(4):
#             dx, dy = d[i]
#             nx, ny = x+dx, y+dy
#             if (0<=nx<M and 0<=ny<N) and not impos[nx][ny] and board[nx][ny] > board[x][y]:
#                 queue.append((nx, ny))
#                 isImpos = False
#         if isImpos:
#             impos[x][y] = True
#
#     return result
#
# result = BFS((M-1, N-1))
# print(result)

