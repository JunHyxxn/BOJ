from collections import defaultdict, deque
N, M = map(int, input().split())

graph = defaultdict(list)
in_degree = [0]*(N+1)
for _ in range(M):
    info = list(map(int, input().split()))
    
    for i in range(2, len(info)):
        a, b = info[i-1], info[i]
        graph[a].append(b)
        
        in_degree[b] += 1

start_node = deque()
for i in range(1, N+1):
    if not in_degree[i]:
        start_node.append(i)
    
def solve(queue):
    result = []
    
    while queue:
        now = queue.popleft()
        result.append(now)
        
        for nxt in graph[now]:
            in_degree[nxt] -= 1
        
            if not in_degree[nxt]:
                queue.append(nxt)
                
    if len(result) != N:
        return 0
    else:
        return result
    
    
result = solve(start_node)
if result != 0:
    for res in result:
        print(res)
        
else:
    print(result)