"""
문제
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 
이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 
한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
================================================================================================================================
Key Point - 이동 가능한 위치에서 목적지까지 가장 가까운 부분으로 이동한다. => Best First Search : 현재 노드부터 목표 노드까지의 거리 정보 활용 -> Heuristic
"""
from collections import deque
N, M = map(int, input().split())
maze = [[int(i) for i in input()] for _ in range(N)]

def get_neighbor(location):
    x, y = location
    candidate = [(x,y-1), (x,y+1), (x-1,y), (x+1,y)]
    locations = []
    for cand in candidate:
        if (cand[0] >= 0 and cand[0] < N) and (cand[1] >= 0 and cand[1] < M):
            locations.append(cand)
    return locations  

def BFS(location, destination):
    visited = [[0]*M for _ in range(N)]
    queue = deque()
    queue.append(location)
    visited[location[0]][location[1]] = 1
    while queue:
        cur_loc = queue.popleft()
        if visited[destination[0]][destination[1]]: ## visited 부분이 0이라면 아직 끝까지 탐색이 되지 않은 것이고, 0이 아닌 값이 들어갔다면 최단경로 탐색 완료.
            print(visited[destination[0]][destination[1]], end='')
            break
        candidate = get_neighbor(cur_loc)
        for cand in candidate:
            if maze[cand[0]][cand[1]] == 1 and visited[cand[0]][cand[1]] == 0:
                visited[cand[0]][cand[1]] = visited[cur_loc[0]][cur_loc[1]] + 1 ## Check Path
                queue.append(cand) ## append candidate

    # print(' '.join(map(str, visited)))
    
    
BFS((0,0), (N-1,M-1))