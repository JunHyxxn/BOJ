# from collections import deque
# n = int(input())
# maze = [list(map(int, input().split())) for _ in range(n)]
# dn ={0:[(0,1,0),(1,1,2)],
#      1:[(1,0,1),(1,1,2)],
#      2:[(0,1,0), (1,0,1), (1,1,2)]}
# ## state = [0,1,2] // 0 : 가로      1 : 세로    2 : 대각선
# def make_range(x,y,state):
#     check_range = []
#     if state == 0:
#         check_range.append((x,y-1))
#         check_range.append((x,y))
#     elif state==1:
#         check_range.append((x-1,y))
#         check_range.append((x,y))
#     else:
#         check_range.append((x-1,y-1))
#         check_range.append((x-1,y))
#         check_range.append((x,y-1))
#         check_range.append((x,y))
#     return check_range

# def check(x,y, state):
#     pipe = make_range(x,y,state)
#     flag = True
#     for cx,cy in pipe:
#         if maze[cx][cy]:
#             flag = False
#     return flag

# def bfs(start): ## start = [0,1]
#     DP = [[0]*n for _ in range(n)]
#     queue = deque()
#     queue.append((start,0))

#     while queue:
#         cur, state = queue.popleft()
#         cur_x, cur_y = cur
#         next_action = dn[state]
#         for action in next_action:
#             i, j, next_state = action
#             nx = cur_x + i
#             ny = cur_y + j
#             if 0<=nx<n and 0<=ny<n and check(nx,ny,next_state):
#                 queue.append([(nx,ny), next_state])
#                 DP[nx][ny] += 1

#     print(DP[n-1][n-1])

# bfs((0,1))


import sys
input = sys.stdin.readline
n = int(input())
maze = [list(map(int, input().split())) for _ in range(n)]
result = [[[0]*n for _ in range(n)] for _ in range(3)]
result[0][0][1] = 1
for i in range(2,n):
    if maze[0][i] ==0: result[0][0][i] = result[0][0][i-1]

for i in range(1,n):
    for j in range(2,n):
        if maze[i-1][j] == 0 and maze[i][j-1] ==0 and maze[i][j] == 0:
            result[2][i][j] = result[0][i-1][j-1] + result[1][i-1][j-1] + result[2][i-1][j-1]
        if maze[i][j] ==0:
            result[0][i][j] = result[0][i][j-1] + result[2][i][j-1]
            result[1][i][j] = result[1][i-1][j] + result[2][i-1][j]
sys.stdout.write(str(result[0][n-1][n-1]+result[1][n-1][n-1]+result[2][n-1][n-1]))