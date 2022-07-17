"""
SWEA 1979 - 어디에 단어가 들어갈 수 있을까

NxN 보드판이 있다. 
NxN 보드판을 탐색하며 가로 빈 칸의 개수와 세로 빈 칸 개수를 각각 저장해둔다.

ㄴ> N^2 두 번 수행하게 되면 가로, 세로 빈 칸의 개수를 모두 파악할 수 있다.

즉, O(2 * N^2) Time 으로 해결가능하다.
"""

T = int(input())

for t in range(1, T+1):
    N, K = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]
    res = 0
    vertical = {i: 0 for i in range(N+1)}
    horizontal = {i: 0 for i in range(N+1)}
    for i in range(N):
        hor = 0
        for j in range(N):
            if board[i][j] == 0:
                horizontal[hor] += 1
                hor = 0
            else:
                hor+=1
        horizontal[hor] += 1
        
    for j in range(N):
        ver = 0
        for i in range(N):
            if board[i][j] == 0:
                vertical[ver] += 1
                ver = 0
            else:
                ver+=1
        vertical[ver] += 1
    
    print("#{} {}".format(t, horizontal[K] + vertical[K]))