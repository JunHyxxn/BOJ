from collections import deque
from copy import deepcopy
import sys

inupt = sys.stdin.readline
d = [[1,0],[0,1],[-1,0],[0,-1]]

def rotate(ice, L):
    for i in range(0, N, L):
        for j in range(0, N, L):
            temp = [[0]*L for _ in range(L)]
            for k in range(L):
                for l in range(L):
                    temp[l][L-1-k] = ice[k+i][l+j]
            for k in range(L):
                for l in range(L):
                    ice[i+k][j+l] = temp[k][l]
    return ice
def melt(ice):
    def isMelt(x, y):
        cnt = 0
        for dd in d:
            dx, dy =dd
            nx, ny = x+dx, y+dy
            if 0<= nx <N and 0 <= ny <N and ice[nx][ny]>0:
                cnt += 1
        if cnt >=3:
            return False
        else:
            return True

    temp = deepcopy(ice)
    for i in range(N):
        for j in range(N):
            if isMelt(i,j):
                temp[i][j] = temp[i][j]-1 if temp[i][j]>0 else 0

    return temp

def group(start):
    cnt = 1
    queue = deque()
    queue.append(start)
    visited[start[0]][start[1]] = True
    while queue:
        x, y = queue.popleft()
        for dd in d:
            dx, dy = dd
            nx, ny = x+dx, y+dy
            if 0<=nx<N and 0<=ny<N and not visited[nx][ny] and ice[nx][ny] >0:
                visited[nx][ny] = True
                queue.append((nx, ny))
                cnt += 1
    return cnt

N, Q = map(int, input().split())
N = 1<<N
ice = [list(map(int, input().split())) for _ in range(N)]

command = list(map(int, input().split()))
for c in command:
    ice = rotate(ice, 1<<c)
    ice = melt(ice)



visited = [[False]*N for _ in range(N)]

res = 0
for i in range(N):
    for j in range(N):
        if ice[i][j] == 0: continue
        res = max(res, group((i,j)))

print(sum(map(sum, ice)))
print(res)