"""
문제
러시아 가스를 크로아티아로 운반하기 위해 자그레브와 모스코바는 파이프라인을 디자인하고 있다. 두 사람은 실제 디자인을 하기 전에 파이프 매니아 게임을 이용해서 설계를 해보려고 한다.

이 게임에서 유럽은 R행 C열로 나누어져 있다. 각 칸은 비어있거나, 아래 그림과 같은 일곱가지 기본 블록으로 이루어져 있다.

						
블록 '|'	블록 '-'	블록 '+'	블록 '1'	블록 '2'	블록 '3'	블록 '4'
가스는 모스크바에서 자그레브로 흐른다. 가스는 블록을 통해 양방향으로 흐를 수 있다. '+'는 특별한 블록으로, 아래 예시처럼 두 방향 (수직, 수평)으로 흘러야 한다.



파이프 라인의 설계를 마친 후 두 사람은 잠시 저녁을 먹으러 갔다. 그 사이 해커가 침임해 블록 하나를 지웠다. 지운 블록은 빈 칸이 되어있다.

해커가 어떤 칸을 지웠고, 그 칸에는 원래 어떤 블록이 있었는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 유럽의 크기 R과 C가 주어진다. (1 ≤ R, C ≤ 25)

다음 R개 줄에는 C개 글자가 주어지며, 다음과 같은 글자로 이루어져 있다.

빈칸을 나타내는 '.'
블록을 나타내는 '|'(아스키 124), '-','+','1','2','3','4'
모스크바의 위치를 나타내는 'M'과 자그레브를 나타내는 'Z'. 두 글자는 한 번만 주어진다.
항상 답이 존재하고, 가스의 흐름이 유일한 경우만 입력으로 주어진다, 또, 모스크바와 자그레브가 하나의 블록과 인접해 있는 입력만 주어진다.
또, 불필요한 블록이 존재하지 않는다. 즉, 없어진 블록을 추가하면, 모든 블록에 가스가 흐르게 된다.

출력
지워진 블록의 행과 열 위치를 출력하고, 어떤 블록이었는지를 출력한다.
"""

import sys
sys.setrecursionlimit(10**6)

## 시작 위치에서 이동가능한 방향과 위치 찾는다.
def howToStart(location, blocks):
    global moveDir, posPipe, visited
    x, y = location
    for dir, dx_dy in moveDir.items():
        dx, dy = dx_dy
        nx, ny = x+dx, y+dy
        if 0< nx<=R and 0<ny<=C:
            if blocks[nx][ny] in posPipe[dir]:
                visited[x][y] = 1
                return dir, (nx, ny)
    return -1, -1

## 이전 이동 방향과 현재의 pipe를 보고 현재 이동방향을 결정한다.
## 이렇게 하면 매번 모든 방향을 고려하지 않아도 된다. => 빠르게 Miss block 찾을 수 있다.
def findDir(prev_dir, pipe):
    global dir2gear, gear
    if pipe == '|' or pipe== '-' or pipe == '+': ## - | +  의 경우 이전 방향 그대로 유지
        return prev_dir
    else:
        """
            U
        L       R
            D
        형태의 기어를 만들고
        
        이전 진행 방향과 파이프 모양을 통해 방향 유추하도록 한다.
        
        그림으로 설명
        """
        dir_x, dir_y = dir2gear[prev_dir]
        if pipe == '1':
            dir_x += 1
            dir_y += 1
        elif pipe == '2':
            dir_x -= 1
            dir_y += 1
        elif pipe == '3':
            dir_x -= 1
            dir_y -= 1
        elif pipe == '4':
            dir_x += 1
            dir_y -= 1             
        return gear[dir_x][dir_y]
    
## Miss Pipe 위치를 찾았다면 해당 block에 어떤 파이프가 들어갈지 찾아야 한다.
def findMissPipe(location):
    global blocks, posPipe, moveDir, visited
    x, y = location
    posDir = [] ## Miss pipe block에서 진행 가능한 방향을 담을 리스트
    for dir, dx_dy in moveDir.items(): ## U L R D 순서로 탐색
        dx, dy = dx_dy
        nx, ny = x+dx, y+dy
        if 0< nx<=R and 0<ny<=C:
            if blocks[nx][ny] == '.': continue ## '.' 는 이동 불가
            if blocks[nx][ny] in posPipe[dir]: ## 다음 블럭의 파이프 모양이 진입 방향에서 알맞은 pipe 모양이라면 direction 추가한다.
                posDir.append(dir)
            elif (blocks[nx][ny] == 'M' or blocks[nx][ny] == 'Z') and not visited[nx][ny]:
                ## 다음 블럭이 M이나 Z 일 수 있다. 이 M, Z가 마지막일 경우에만 추가되도록 해야한다.
                posDir.append([dir,blocks[nx][ny]])
                
    ## 우선 추가시키고 이후 처리해준다.
    for i in range(len(posDir)):
        if len(posDir[i]) >1 and len(posDir) == 3: 
            ## positive Direction에 direction이 3개 담겨있다면 해당 파이프는 존재하지 않기에 M, Z 중 뭔가 잘 못들어간 것을 알 수 있다.
            ## 따라서 해당 direction을 제거한다.
            posDir.remove(posDir[i])
            break
        elif len(posDir[i]) >1:
            ## M, Z가 올바르게 들어가는 경우라면 direction만 남도록 재정의해준다.
            posDir[i] = posDir[i][0]
            break
        
    ## positive direction을 보고 pipe를 결정한다.
    if len(posDir) == 4: return '+'
    elif posDir == ['U','D']: return '|'
    elif posDir == ['L','R']: return '-'
    elif posDir == ['R','D']: return '1'
    elif posDir == ['U','R']: return '2'
    elif posDir == ['U','L']: return '3'
    elif posDir == ['L','D']: return '4'

## dfs로 Miss Pipe block을 찾는다.
def dfs(dir, location):
    global blocks, moveDir, visited
    now_x, now_y = location
    pipe = blocks[now_x][now_y]
    ## 새로운 direction을 찾아 모든 블럭마다 4방향을 탐색하지 않도록 해준다.
    new_dir = findDir(dir, pipe)
    dx, dy = moveDir[new_dir]
    nx, ny = now_x+dx, now_y+dy
    if blocks[nx][ny] == '.': ## 진행 도중 끊기는 구간이 있다면 해당 구간이 Miss Pipe block이다.
        miss = findMissPipe((nx, ny)) ## Miss Pipe에 올바른 파이프 모양을 찾아준다.
        print(nx, ny, miss)
        return
    else:
        ## 진행방향에 맞게 진행하면서 visited 체크해준다.
        visited[now_x][now_y] += 1
        dfs(new_dir, (nx, ny))
    
    return
    

R, C = map(int, input().split())
blocks = [['.']*(C+1) for _ in range(R+1)]
for r in range(1, R+1):
    blocks[r][1:] = list(input())

## 모스크바와 자그레브 위치를 찾는다.
moskva = (0,0)
zagreb = (0,0)
for r in range(1, R+1):
    for c in range(1, C+1):
        if blocks[r][c] == 'M':
            moskva = (r,c)
        if blocks[r][c] == 'Z':
            zagreb = (r,c)

## 기어
gear = [['.']*3 for _ in range(3)]
gear[0][1] = 'U'
gear[1][0] = 'L'
gear[1][2] = 'R'
gear[2][1] = 'D'

## direction이 gear 어느 위치에 담겨있는지 파악하기 위한 변수
dir2gear = {'U' : (0,1), 'L' : (1,0), 'R' : (1,2), 'D' : (2,1)}
## moveDir : 방향과 그 방향일 떄의 dx, dy 값을 담는다.
moveDir = {'U' : (-1,0), 'L' : (0,-1), 'R' : (0,1), 'D' : (1,0)}
## posPipe : 진입 방향에 따라 가능한 파이프 형태
posPipe = {'U' : ['|','+','1','4'], 'L' : ['-','+','1','2'], 'R' : ['-','+','3','4'], 'D' : ['|','+','2','3']}

visited = [[0]*(C+1) for _ in range(R+1)]

dir, loc = howToStart(moskva, blocks)
if dir == -1: ## moskva에서 이동 가능한 위치가 없다면 자그레브에서 다시 시작
    dir, loc = howToStart(zagreb, blocks)
    if dir == -1: ## 자그레브에서도 없다면
        """
        M.Z 와같은 경우 모스크바와 자그레브 모두 찾지 못한다.
        이런 경우는 모스크바와 자그레브의 중간 위치를 찾아 출력하면 된다.
        """
        m_x, m_y = moskva
        z_x, z_y = zagreb
        x = (m_x+z_x)//2
        y = (m_y+z_y)//2
        if m_x == z_x:
            print(x, y, '-')
        elif m_y == z_y:
            print(x, y, '|')
    else:
        ## 자그레브에서 시작한 경우
        dfs(dir, loc)
else:
    ## 모스크바에서 시작한 경우
    dfs(dir, loc)