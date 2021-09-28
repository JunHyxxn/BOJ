import sys
from collections import deque

N, M, V = map(int, sys.stdin.readline().split())

adj = [[0] * (N+1) for _ in range(N+1)]
for _ in range(M):
    v1, v2 = map(int, sys.stdin.readline().split())
    adj[v1][v2] = adj[v2][v1] = 1

## Recursion 으로 해결
def dfs(start, visited=[]):
  visited.append(start)
  sys.stdout.write(str(start) + ' ')

  for i in range(len(adj[start])):
    if adj[start][i] == 1 and (i not in visited):
      dfs(i, visited)
## queue를 이용해 해결 // list의 경우 pop은 O(N) 이라 시간초과
def bfs(start):
    visited = []
    queue = deque()
    queue.append(start)
    visited.append(start)
    while(queue):
        v = queue.popleft()
        for i in range(len(adj[v])):
            if adj[v][i] ==1 and i not in visited:
                queue.append(i)
                visited.append(i)
        
        
    sys.stdout.write(' '.join(map(str, visited)))

dfs(V)
print()
bfs(V)
