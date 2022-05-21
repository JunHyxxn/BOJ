"""
문제 출처 : https://www.acmicpc.net/problem/17471
"""
from collections import deque

N = int(input())

people = list(map(int, input().split()))

adjacent_section = {i: [] for i in range(1, N+1)}
for i in range(1, N+1):
    l = list(map(int, input().split()))
    adjacent_section[i] = list(sorted(l[1:]))



def extraction(num):
    a = str(bin(num))[2:]
    return a if len(a) == 10 else "0"*(N-len(a)) + a

visited = [False]*(1<<N)
node_path = {i : [] for i in range(1, N+1)}

def BFS(start, path):
    queue = deque()
    queue.append((start, path))
    node_path[start].append(path)
    visited[path] = True
    while queue:
        now, now_path = queue.popleft()
        for nxt in adjacent_section[now]:
            new_path = now_path | 1<<(nxt-1)
            if new_path in node_path[nxt]: continue
            queue.append((nxt, new_path))
            node_path[nxt].append(new_path)
            visited[new_path] = True

for i in range(1, N+1):
    BFS(i,1<<(i-1))



res = float("inf")
for i in range(1, (1<<N) //2):
    a, b = i, (1<<N)-1-i

    if visited[a] and visited[b]:
        a = extraction(a)
        red, blue = 0, 0
        for j in range(len(a)):
            if a[j] == '1':
                red += people[len(a)-j-1]
            else:
                blue += people[len(a)-j-1]
        res = min(res, max(red, blue)-min(red, blue))

if res == float("inf"):
    print(-1)
else:
    print(res)