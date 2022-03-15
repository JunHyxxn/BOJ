"""
문제
두 마리의 백조가 호수에서 살고 있었다. 그렇지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.

호수는 행이 R개, 열이 C개인 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.

호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다. 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.

아래에는 세 가지 예가 있다.

...XXXXXX..XX.XXX ....XXXX.......XX .....XX.......... 
....XXXXXXXXX.XXX .....XXXX..X..... ......X.......... 
...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X..... 
..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX.... 
.XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX.... 
XXXXXXX...XXXX... ..XXXX.....XX.... ....X............ 
..XXXXX...XXX.... ....XX.....X..... ................. 
....XXXXX.XXX.... .....XX....X..... ................. 
      처음               첫째 날             둘째 날
백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.

며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성하시오.

입력
입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.

다음 R개의 줄에는 각각 길이 C의 문자열이 하나씩 주어진다. '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.

출력
첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.

============================================================================================================================================================

중요한 점은 어떻게 BFS 탐색하는 과정을 줄일 것인가 이다.
1. 한 백조에서 출발해 다른 백조에 도달할 수 있는지 => 영역을 모두 체크해 그 안에 다른 백조가 있는지 체크. <BFS>
2. 물과 맞닿은 얼음을 모두 녹인다. <BFS>

Key Point
1. 백조의 영역을 체크할 때, 매번 백조에서부터 시작하지 않고 기존 영역은 탐색하지 않도록 하고
    ㄴ> 다음 queue에는 기존 영역과 맞닿아 있고 녹을 예정인 얼음들만 추가해주면 된다.

2. 물의 영역을 전부 queue에 추가해두고, 큐에 저장된 물 주변에 얼음이 있는지만 탐색한다.
    이 떄, 주변에 얼음이 있다면 그 얼음을 녹이고 녹은 얼음의 위치를 큐에 추가한다. 
    ㄴ> 탐색을 시작할 때, queue의 길이만큼 popleft한다. => 기존의 물의 영역만 pop
    ㄴ> 이제 녹은 물은 다음번 탐색에 기존의 물로 사용된다.

"""

from collections import deque

## 백조 탐색
## visited 는 초기화 하지 않는다. 기존의 탐색된 영역을 유지하기 위해서.
def BFS(swan, another_swan, queue):
    """
    visited 
    0 : not visited
    1 : next area checked
    2 : visited
    """
    global R, C, d, arr, visited
    ## 기준 백조 value
    root = swan[0]*C + swan[1]
    ## 목표 백조 value
    another_root = another_swan[0] *C + another_swan[1]
    ## next queue => 다음번에 녹을 예정인 물이자 백조가 영역 확장가능한 부분.
    nxt = deque()
    
    ## 영역 탐색
    while queue:
        cx, cy = queue.popleft()
        for i in range(4):
            dx, dy = d[i]
            nx, ny = cx+dx, cy+dy
            if 0<=nx<R and 0<=ny<C:
                if lake[nx][ny] == '.' and visited[nx][ny] != 2: ## 다음 위치가 물이고 아직 방문 전
                    if arr[nx*C + ny] == another_root: ## 다음번 움직일 공간에 물이고 다른 백조가 있다면 탐색 완료
                        return True, -1
                    ## 물이고 다른 백조 없다면 방문하고 queue 에 추가한다.
                    visited[nx][ny] = 2
                    queue.append((nx,ny))
                    arr[nx*C+ny] = root ## 기준 백조 영역 체크한다.
                elif lake[nx][ny] == 'X' and not visited[nx][ny]: ## 영역과 맞닿아 있는 얼음 -> 녹을 예정인 얼음 = 영역 확장될 예정.
                    ## 얼음이면서 전혀 방문되지 않아야한다. ( visited[nx][ny] => 1 라면 이미 영역 체크했기 때문에 중복으로 넣지 않는다.)
                    nxt.append((nx,ny))
                    visited[nx][ny] = 1 ## next area checked
                    
    ## 아직 백조 못만났다면 next area queue를 반환한다. => 다음번 탐색을 여기서부터 시작하겠다.
    return False, nxt

## 얼음 녹는 함수
def melt(lake):
    global d, water
    
    ## 현재 물의 영역만큼 탐색한다.
    n = len(water)
    while n>0:
        x,y = water.popleft()
        for k in range(4):
            dx, dy = d[k]
            nx, ny = x+dx, y+dy
            if 0<= nx < R and 0<= ny < C:
                if lake[nx][ny] == 'X': ## 물과 맞닿은 얼음이라면
                    lake[nx][ny] = '.' ## 물로 바꾼다.
                    water.append((nx,ny)) ## 녹은 물은 새로 추가한다. => 다음번에는 기존 물의 영역이 될 것이다.
                    
        n -= 1 
    return lake
    

d = [[-1,0],[0,1],[1,0],[0,-1]]
R, C = map(int , input().split())
## 호수
lake = [list(map(str, input())) for _ in range(R)]
## 백조
swan = []
## 물 영역
water = deque()

for i in range(R):
    for j in range(C):
        if lake[i][j] == 'L':  ## 백조가 있다는 것은 물이라는 것을 의미한다.
            swan.append((i,j))
            lake[i][j] = '.' ## 백조 위치 저장했으니 물로 변경해준다.
            water.append((i,j))
        elif lake[i][j] == '.': ## 물의 영역 추가
            water.append((i,j))

## 어느 백조의 영역인지 체크할 배열
arr = [i for i in range(R*C)]

swan_queue = deque()
swan_queue.append(swan[0])

visited = [[False]*C for _ in range(R)]
visited[swan[0][0]][swan[0][1]] = True

days = 0
flag = False

while True:
    flag, swan_queue = BFS(swan[0], swan[1], swan_queue) ## BFS 탐색을 통해 다른 백조를 만났는지 여부와 다음 탐색 영역을 반환한다.
    if flag:
        print(days)
        break
    ## 아직 안만났다면 하루 지난다.
    lake = melt(lake)
    days += 1
    
    