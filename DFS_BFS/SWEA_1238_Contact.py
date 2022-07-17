"""
SWEA 1238 - Contact

유향 그래프로 도달할 수 있는 마지막 노드를 탐색하는 문제이다.

같은 level에 도달가능한 노드가 여러개라면 노드의 크기가 큰 값을 출력하면 된다.

따라서 DFS 보단 BFS가 더 직관적이라고 생각해 BFS로 해결했습니다.
"""
from collections import defaultdict, deque

for t in range(1, 10+1):
    n, start = map(int, input().split())
    data = list(map(int, input().split()))
    graph = defaultdict(list)
    for i in range(1, n, 2):
        a, b = data[i-1], data[i]
        graph[a].append(b)

    res = (start, 0)
    visited = [False]*101
    queue = deque()
    queue.append((start, 0))
    visited[start] = True
    while queue:
        now, level = queue.popleft()
        if level > res[1]: 
            res = (now, level)
        elif level == res[1]:
            res = (now, level) if now > res[0] else res
        
        for nxt in graph[now]:
            if visited[nxt]: continue
            queue.append((nxt, level+1))
            visited[nxt] = True
    print("#{} {}".format(t, res[0]))