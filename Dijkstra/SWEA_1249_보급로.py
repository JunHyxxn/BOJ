"""
SWEA 1249 - 보급로

일종의 최단 경로를 구하는 문제와 같다.

Dijkstra 알고리즘을 활용해 해결합니다.

Source = 0, 0 에서 시작해 Goal = n-1, n-1 로 향하는 최단 경로를 탐색합니다.

이동하면서 cost를 기록하고 더 적은 cost로 이동가능한 경로가 나온다면
해당 위치에 대한 cost를 갱신하고 다시 거기에서부터 탐색하도록 합니다.
"""

from collections import deque
T = int(input())
dir = [[0,1],[1,0],[0,-1],[-1,0]]
for t in range(1, T+1):
    n = int(input())
    board = [list(map(int, input())) for _ in range(n)]
    cost = [[float("inf")]*n for _ in range(n)]
    cost[0][0] = 0
    queue = deque()
    queue.append((0, 0))
    while queue:
        now = queue.popleft()
        x, y = now
        if x==n-1 and y==n-1: continue ## 도착
        for d in dir:
            nx, ny = x+d[0], y + d[1]
            if 0<= nx < n and 0<= ny < n:
                if cost[nx][ny] <= cost[x][y] + board[nx][ny]:
                    ## 이미 기록된 cost가 더 적다면 탐색하지 않는다.
                    continue
                cost[nx][ny] = cost[x][y] + board[nx][ny]
                queue.append((nx, ny))    
    print("#{} {}".format(t, cost[n-1][n-1]))