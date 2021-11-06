"""
문제
캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다. 
격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 
격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.

성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 
각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다. 
궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면, 적이 이동한다. 
적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 
모든 적이 격자판에서 제외되면 게임이 끝난다. 

게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다. 따라서, 이 게임은 궁수의 위치가 중요하다. 
격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.

격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

입력
첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 
둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.

출력
첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.

============================================================================================================================================================
Implementation Problem - 조건들 자세히 살펴볼 것.
"""


from itertools import combinations
from collections import deque
import copy

## 적 찾기. - BFS로 탐색하며 거리 이내에 적 발견시 조기 종료하도록 한다.
def find_enemy(t_board, start_x, start_y, d):
    level = 0
    ## archer마다 공격할 적을 탐색해야하기 때문에 visited initialize
    visited = [[0]*m for _ in range(n)]
    queue = deque()
    queue.append([start_x, start_y, level])
    
    while queue:
        x,y,l = queue.popleft() ## x,y, level=dist
        
        ## 탐색하고 있는 거리가 이미 공격 가능 범위 넘어가면 -1 return
        if l > d: return [-1,-1]
        ## 공격 가능 거리 이내 탐색중일 때 
        elif l <= d:
            if x != n: ## (n, archer) 에서 시작하니까 x==n 이라면  index error 발생한다.
                if t_board[x][y]: return [x,y] ## 해당 칸에 적이 있다면 해당 위치 retrun

        ## 거리가 같을 경우 우선순위가 왼쪽이므로 // left, up, right 순서로 탐색 진행한다.        
        for i in range(3):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0<=nx<n and 0<= ny < m:
                if not visited[nx][ny]: ## 일반적인 BFS
                    visited[nx][ny] = 1
                    queue.append([nx,ny,l+1])

    ## 끝까지 탐색했을 때 공격 가능한 적이 없다면 -1 return 
    return [-1,-1]


## 공격 수행하는 함수
def shoot(t_board, archers, cnt=0):
    ## archer들이 같은 적을 공격가능하기 때문에 set으로 중복 제거하며 저장한다.
    enemy = set()
    for archer in archers: 
        ## enemy 찾기
        e = find_enemy(t_board, n,archer,d)
        if -1 == e[0]: continue ## 공격가능한 적이 없다면 -1 => 저장하지 않는다.
        enemy.add((e[0],e[1]))
    ## 공격 횟수 저장
    cnt += len(enemy)

    ## 공격진행
    for e in enemy:
        t_board[e[0]][e[1]] = 0
    return cnt

## enemy 이동
def move(t_board, archers):
    """
    아래로 한 칸씩 이동한다면 마지막 행은 삭제되고 맨위에 [0]으로 이루어진 행을 추가해준다.
    살아남는 archer를 조사한다.
    """
    remove_row = t_board[-1]
    save_archers = [] ## 살아남는 archer
    for archer in archers:
        if not remove_row[archer]: ## archer위치에 적이 남아있다면 해당 archer는 삭제된다.
            save_archers.append(archer)
    
    ## board 판 update
    t_board = t_board[:-1]
    t_board = [[0]*m] + t_board
    
    return t_board, save_archers

## 공격가능한 적이 있는지 살핀다.
def check_enemy(t_board):
    return sum(map(sum,t_board))

n,m,d  = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
dx = [0,-1,0]
dy = [-1,0,1]

## 공격한 횟수를 담는 리스트
counts = []
for archers in combinations(range(m),3): ## archer 위치 combination으로 모두 탐색
    temp_board = copy.deepcopy(board) ## board 판 자체가 update되면 안되기 때문에 deepcopy 진행한다.
    cnt = 0
    while True:
        ## 더이상 공격할 수 있는 적이 없다면 종료 or 남은 archer가 없다면 종료
        if not check_enemy(temp_board) or len(archers)==0: break
        c = shoot(temp_board, archers, 0) ## 공격
        cnt += c
        temp_board, archers= move(temp_board, archers) ## 이동
    ## 게임 끝나면 공격한 횟수 저장
    counts.append(cnt)
counts = sorted(counts, reverse=True)
print(counts[0])
