def DFS(now, cnt, path, order):
    x, y = now   

    for i in range(4):
        if castle[x][y] & 1<<i != 0: continue
        dx, dy = d[i]
        nx, ny = x+dx, y+dy
        if visited[nx][ny]: continue
        visited[nx][ny] = True
        path.append((nx, ny))
        cnt = max(cnt, DFS((nx, ny), cnt+1, path, order))
    for p in path:
        px, py = p
        record[px][py] = (cnt, order)
    return cnt

d = [[0,-1], [-1,0],  [0,1],  [1,0]]
N,M = map(int, input().split())
castle = [list(map(int, input().split())) for _ in range(M)]

visited = [[False]*N for _ in range(M)]
record = [[0]*N for _ in range(M)]
rooms = []
order = 1
for i in range(M):
    for j in range(N):
        if visited[i][j]: continue
        visited[i][j] = True
        rooms.append(DFS((i,j), 1, [(i,j)], order))
        order += 1


third_res = float("-inf")
for i in range(M):
    for j in range(N):
        now = record[i][j]
        for k in range(4):
            dx, dy = d[k]
            nx, ny = i+dx, j+dy
            if (nx<0 or nx>=M) or (ny<0 or ny>=N): continue
            nxt = record[nx][ny]
            if now[1] != nxt[1]:
                third_res = max(third_res, now[0] + nxt[0])
print(len(rooms))
print(max(rooms))
print(third_res)