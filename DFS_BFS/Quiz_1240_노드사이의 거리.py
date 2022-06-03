"""
문제 출처 : https://www.acmicpc.net/problem/1240
문제
N(2≤N≤1,000)개의 노드로 이루어진 트리가 주어지고 M(M≤1,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

입력
첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리(10,000 이하의 정수)를 입력받는다. 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.

출력
M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.
======================================================================
N번의 DFS를 통해서 모든 노드간의 거리를 기록해두면 해결가능하다.
"""
from collections import defaultdict
N, M = map(int, input().split())
graph = defaultdict(list)
for _ in range(N-1):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))
weight = [[0]*(N+1) for _ in range(N+1)]

def DFS(root, now, total):

    for info in graph[now]:
        nxt, cost = info
        if visited[nxt]: continue
        visited[nxt] = True
        weight[root][nxt] = total + cost
        DFS(root, nxt, total + cost)

for node in range(1, N+1):
    visited = [False]*(N+1)
    visited[node] = True
    DFS(node, node, 0)

for _ in range(M):
    a, b = map(int, input().split())
    print(weight[a][b])