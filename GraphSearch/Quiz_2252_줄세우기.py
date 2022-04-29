from collections import defaultdict, deque
N, M = map(int, input().split())

graph = defaultdict(list)
in_degree = [0] *(N+1)
for _ in range(M):
    A, B = map(int, input().split())
    in_degree[B] += 1
    
    graph[A].append(B)
    
start_node = []
for i in range(1, N+1):
    if not in_degree[i]:
        start_node.append(i)
    

def solve(start):
    queue = deque()
    queue.append(start)
    result = []
    
    while queue:
        now = queue.popleft()
        result.append(now)
        for nxt in graph[now]:
            in_degree[nxt] -= 1
        
            if not in_degree[nxt]:
                queue.append(nxt)
                
    return result

result = []
for start in start_node:
    result += solve(start)
print(" ".join(map(str, result)))