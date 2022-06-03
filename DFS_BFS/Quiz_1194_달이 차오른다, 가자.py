"""
문제 출처 : https://www.acmicpc.net/problem/1194
'a'~'f' :  97~102
"A"~"F" : 65~70
"""

from collections import deque


d = [[0,1], [1,0], [0,-1], [-1,0]]
N, M = map(int, input().split())
miro = [list(map(str, input())) for _ in range(N)]
start = None
flag = False
for i in range(N):
    if flag: break    
    for j in range(M):
        if miro[i][j] == '0':
            start = (i, j)
            miro[i][j] = '.'
            flag = True
            break
            

def BFS(now, keys):
    """
    now  : (x, y) = loc
    keys : 0 => 미로판 지나며 키 획득할때마다 update
    
    1. 'a'~'f' => 방문 & keys update
    2. '.' => 방문
    3. 'A'~'F' => keys & doors != 0 이라면 문 통과 가능. 이때만 방문한다.
    4. '1'  => 탈출 성공
    
    visited 검사를 n x m x keys 를 통해 계산한다.
    """
    queue = deque()
    queue.append((now, keys, 0))
    visited = [[[False]*(1<<6) for _ in range(M)] for _ in range(N)]
    visited[now[0]][now[1]][0] = True
    while queue:
        loc, now_key, cnt =  queue.popleft()
        x, y = loc
        if miro[x][y] == '1':
            return cnt
        for i in range(4):
            dx, dy = d[i]
            nx, ny = x+dx, y+dy
            if (nx<0 or nx>=N) or (ny<0 or ny>=M) or visited[nx][ny][now_key] or miro[nx][ny]=="#": continue
            if 97<= ord(miro[nx][ny]) <= 102: ## key 발견
                nxt_key = now_key | (1<<(ord(miro[nx][ny]) - 97))
                queue.append(((nx,ny),nxt_key, cnt+1))
                visited[nx][ny][nxt_key] = True
            elif miro[nx][ny] == '.' or miro[nx][ny] == '1':
                visited[nx][ny][now_key] = True
                queue.append(((nx, ny), now_key, cnt+1))
            elif 65<= ord(miro[nx][ny]) <= 70:
                if now_key & 1<<(ord(miro[nx][ny])-65) != 0:
                    visited[nx][ny][now_key] = True
                    queue.append(((nx, ny), now_key, cnt+1))
                
    
    return -1

print(BFS(start, 0))