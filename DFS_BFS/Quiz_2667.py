"""
문제
<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 
철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 
여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 
지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.



입력
첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

출력
첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
"""
from collections import deque
N = int(input())
adj_mat = [[int(i) for i in input()] for _ in range(N)]
## visited : 방문한 적 있는 칸은 체크
visited = [[0]*(N) for _ in range(N)]

## 현재 위치에서 탐색 가능한 칸 찾기.
def get_neighbor(location):
    x, y = location
    candidate = [(x,y-1), (x,y+1), (x-1,y), (x+1,y)]
    locations = []
    for cand in candidate:
        if (cand[0] >= 0 and cand[0] < N) and (cand[1] >= 0 and cand[1] < N):
            locations.append(cand)
    return locations  

## BFS 사용한다.
def bfs(start):
    if N==1 and adj_mat[0][0] == 1:
        return 1
    global visited
    discovered = [start]
    queue = deque()
    visited[start[0]][start[1]] = 1
    queue.append(start)
    while queue:
        v = queue.popleft()
        candidate = get_neighbor(v)
        for c in candidate:
            if adj_mat[c[0]][c[1]] == 1 and visited[c[0]][c[1]] == 0:
                visited[c[0]][c[1]] = 1
                discovered.append(c)
                queue.append(c)
    return len(discovered)


total = []
for i in range(N):
    for j in range(N):
        ## 이미 방문했거나 움직일 수 없는 위치라면 탐색 진행하지 않는다.
        if visited[i][j] or adj_mat[i][j] == 0:
            continue
        nums = bfs((i,j))
        total.append(nums)

total = sorted(total)
print(len(total))
print('\n'.join(map(str, total)))