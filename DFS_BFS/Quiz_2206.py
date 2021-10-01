"""
문제
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 
당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 
최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.

한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

출력
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

============================================================================================================================================
- 2s
- 최단 거리는 보통 BFS를 활용하는 편이다.
"""
# from collections import deque

# N, M = map(int, input().split())
# maze = [[int(i)for i in input()] for _ in range(N)]

# ## 이동 가능한 위치 찾는 함수
# def neighbors(location): 
#     x, y = location
#     candidate = [(x,y-1),(x,y+1),(x-1,y),(x+1,y)]
#     loc = []
#     for cand in candidate:
#         if (cand[0]>= 0 and cand[0]<N) and (cand[1]>= 0 and cand[1]<M):
#             loc.append(cand)

#     return loc

# ## 탐색
# def Search(maze, start, destination):
#     visited = [[(0, False)]*M for _ in range(N)]
#     ## 방문 기록은 벽을 지나왔으면 True, 벽을 지나지 않으면 False
#     visited[start[0]][start[1]] = (1, False) 
#     queue = deque()
#     queue.append(start)

#     while queue:
#         cur_loc = queue.popleft()
#         if cur_loc == destination:
#             print(visited[destination[0]][destination[1]][0])
#             return 
#         next_loc = neighbors(cur_loc)
#         for nxt in next_loc:
#             new_x, new_y = nxt

#             ## 이동 가능하고, 현재까지 아직 벽을 지난적이 없는 경우
#             if maze[new_x][new_y] == 0 and visited[cur_loc[0]][cur_loc[1]][1] == False:
#                 ## next위치가 이미 벽을 지난경우로 저장된 경우 벽 지나지 않는 경우로 재정의해준다.
#                 if visited[new_x][new_y][0] != 0 and visited[new_x][new_y][1] == True: ## 재정의이기 때문에 위치를 queue에 추가하지 않는다.
#                     level, flag = visited[new_x][new_y]
#                     visited[new_x][new_y] = (level, False)
#                 else: ## queue에 위치를 추가하고 방문을 기록한다.
#                     level, flag = visited[cur_loc[0]][cur_loc[1]]
#                     visited[new_x][new_y] = (level + 1, flag)
#                     queue.append(nxt)
#             ## 이동가능하고 이미 벽을 지난적이 있다면
#             if maze[new_x][new_y] == 0 and visited[cur_loc[0]][cur_loc[1]][1] == True:
#                 ## True flag를 그대로 가져온다.
#                 level, flag = visited[cur_loc[0]][cur_loc[1]]
#                 visited[new_x][new_y] = (level + 1, flag)
#                 queue.append(nxt)
#             ## 이동이 불가능한 벽이고 벽을 지난적이 없다면 
#             if maze[new_x][new_y] == 1 and visited[cur_loc[0]][cur_loc[1]][1] == False:
#                 ## 이동하고 flag를 True로 변경해준다.
#                 level, _ = visited[cur_loc[0]][cur_loc[1]]
#                 visited[new_x][new_y] = (level + 1, True)
#                 queue.append(nxt)

#     ## destination 도착하지 못하면 -1을 출력한다.
#     print(-1)

# source = (0,0)
# destination = (N-1, M-1)
# Search(maze, source, destination)




from collections import deque

N, M = map(int, input().split())
maze = [[int(i) for i in input()] for _ in range(N)]
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

#### 외부 함수 호출 시 시간 소요가 크게 작용하는 듯 하다. 되도록 함수 내에서 구현한다.
# ## 이동 가능한 위치 찾는 함수
# def neighbors(location): 
#     x, y = location
#     candidate = [(x,y-1),(x,y+1),(x-1,y),(x+1,y)]
#     loc = []
#     for cand in candidate:
#         if (cand[0]>= 0 and cand[0]<N) and (cand[1]>= 0 and cand[1]<M):
#             loc.append(cand)

#     return loc

def Search(maze, source, destination, mode): 
    x, y = source
    source = (x,y,mode)
    visited = [[[0]*2 for _ in range(M)] for _ in range(N)]
    visited[x][y][mode] = 1
    queue = deque()
    queue.append(source)

    while queue:
        cur = queue.popleft()
        cur_x, cur_y, cur_mode = cur
        if (cur_x, cur_y) == destination:
            return visited[cur_x][cur_y][cur_mode]
        ## neighbors 찾기
        for i in range(4):
            new_x = cur_x + dx[i]
            new_y = cur_y + dy[i]
            if new_x <= -1 or new_x >= N or new_y <= -1 or new_y >= M:
                continue

            ## 벽 부수지 않고 이동
            if maze[new_x][new_y] == 0 and visited[new_x][new_y][cur_mode] == 0:
                visited[new_x][new_y][cur_mode] = visited[cur_x][cur_y][cur_mode] + 1
                queue.append((new_x, new_y, cur_mode))

            ## 벽 부수고 이동
            if maze[new_x][new_y] == 1 and cur_mode == 0:
                visited[new_x][new_y][cur_mode+1] = visited[cur_x][cur_y][cur_mode] +1 
                queue.append((new_x, new_y, cur_mode+1))
    return -1
            
source = (0,0)
destination = (N-1, M-1)
mode = 0
print(Search(maze, source, destination, mode), end = '')