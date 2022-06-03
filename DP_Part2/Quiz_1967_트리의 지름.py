import sys
from collections import defaultdict

sys.setrecursionlimit(10**6)
n = int(input())

graph = defaultdict(list)
for _ in range(n-1):
    a, b, c  = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))


def isLeafNode(visited, now):
    isLeafNode = True
    for info in graph[now]:
        nxt, _ = info
        if not visited[nxt]:
            isLeafNode = False
            break
    return isLeafNode

visited = [False] * (n+1)
visited[1] = True

def findStart(now, total):
    start= (1,0)
    if isLeafNode(visited, now):
        if start[1] < total:
            start = (now, total)
        return start

    for info in graph[now]:
        nxt, cost = info
        if visited[nxt]: continue
        visited[nxt] = True
        temp = findStart(nxt, total+cost)
        if start[1] < temp[1]:
            start = temp


    return start

start = findStart(1, 0)

visited = [False] * (n+1)
visited[start[0]] = True
def DFS(now, total):
    result = 0
    if isLeafNode(visited, now):
        result = max(result, total)
        return result

    for info in graph[now]:
        nxt, cost = info
        if visited[nxt]: continue
        visited[nxt] = True
        result = max(result, DFS(nxt, total+cost))

    return result

print(DFS(start[0], 0))

