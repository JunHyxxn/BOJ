"""
SWEA 1226 - 미로1

출발점에서 목적지까지 도달가능한가 탐색하는 전형적인 미로 문제입니다.

BFS와 DFS를 이용해 쉽게 해결할 수 있습니다.

주로 경로, 최단 경로 등에서는 BFS가 유리하고 
도달 가능한가에 대한 것은 DFS가 유리하다고 알고 있습니다.
따라서 DFS로 해결했습니다.

SWEA에서의 DFS는 recursionlimit의 한계로 인해 재귀를 이용한 풀이는 어렵습니다.
따라서 stack을 이용한 DFS를 이용해 해결합니다.
"""
dir = [[0,1],[1,0],[0,-1],[-1,0]]
for _ in range(1, 10+1):
    t = int(input())
    miro = [list(map(int, input())) for _ in range(16)] 
    s_flag = False
    for i in range(16):
        if s_flag: break
        for j in range(16):
            if miro[i][j] == 2:
                source = (i,j)
                s_flag = True
                break
        
    stack = []
    visited = [[False]*16 for _ in range(16)]
    stack.append(source)
    visited[source[0]][source[1]] = True
    res = 0
    while stack:
        now = stack.pop()
        x, y = now
        if miro[x][y] == 3:
            res = 1
            break
        for d in dir:
            nx, ny = x+d[0] , y+d[1]
            if 0<= nx < 16 and 0<= ny < 16:
                if miro[nx][ny] != 1 and not visited[nx][ny]:
                    stack.append((nx, ny))
                    visited[nx][ny] = True
    
    print("#{} {}".format(t, res))