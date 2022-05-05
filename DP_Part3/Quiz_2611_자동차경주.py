
"""
Topological Sort - 위상 정렬을 진행하면서 해당 노드를 방문했을 때, 경로와 cost를 기록해둔다.

유향 그래프이면서 1번 노드를 거치지 않고는 어느 노드도 순회할 수 없는 조건이 걸려있기 때문에 visited 검사는 생략하도록 한다.

우선 이 문제는 최장 경로로 visited 검사를 수행해서는 안된다. 
그렇다면 BFS를 진행하게 된다면 순환이 없다고 하더라도 반복된 노드들이 queue에 입력된다.
이 문제를 보완하기 위해서 Topological Sort를 사용한다고 생각하면 된다.

Topological Sort(위상 정렬)은 쉽게 설명하자면 현재 노드에서 다음 노드를 방문할 떄, 해당 간선을 제거해 나가는 방법이라고 생각하면 쉽다.
간선을 제거해 나가며 in degree(진입 차수) 가 0이 된다면 queue에 append 해주면 된다. BFS에서는 동일한 노드를 여러번 방문했던 작업이 1번으로 줄어들게 된다.

queue에 추가는 하지 않더라도 방문은 3번 되기 때문에 모든 경로를 고려할 수 있다.
각 노드를 방문할 때마다 원래 담겨있던 cost와 new_cost를 비교해 경로와 cost를 갱신해주면 된다.

🔥 하지만 이 방법 또한 Python3 에서는 TLE발생한다. 

Pypy3 으로 통과

"""

from collections import defaultdict, deque


N = int(input())
M = int(input())

graph= defaultdict(list)
in_degree = [0] *  (1+N)

for _ in range(M):
    p, q, r = map(int, input().split())
    graph[p].append([q, r])
    in_degree[q] += 1
    
path_dp = [""] * (1+N)
cost_dp = [0] * (1+N)

def topological(start ):
    queue = deque()
    queue.append(start)
    path_dp[start] += " {}".format(start)
    while queue:
        now = queue.popleft()
        if now == 1 and cost_dp[now] >0:
            return
        
        for info in graph[now]:
            nxt, cost = info
            new_path = path_dp[now] + " {}".format(nxt)
            new_cost = cost_dp[now] + cost
            path_dp[nxt] = new_path if cost_dp[nxt] < new_cost else path_dp[nxt]
            cost_dp[nxt] = new_cost if cost_dp[nxt] < new_cost else cost_dp[nxt]
            in_degree[nxt] -= 1
            
            if in_degree[nxt] == 0:
                queue.append(nxt)

topological(1)
print(cost_dp[1])
print(path_dp[1].strip())

# """
# BFS - TLE 발생한다.
# """
# from collections import defaultdict, deque

# N = int(input())
# M = int(input())

# graph= defaultdict(list)
# for _ in range(M):
#     p, q, r = map(int, input().split())
#     graph[p].append([q, r])


# def bfs(start):
#     queue = deque()
#     queue.append([start, [start], 0])
    
#     result = [[], float('-inf')]
    
#     while queue:
#         now, path, total = queue.popleft()
#         if now == 1 and total > 0: 
#             result[0] = path if result[1] < total else result[0]
#             result[1] = total if result[1] < total else result[1]
#             continue
            
#         for info in graph[now]:
#             nxt, cost = info
#             queue.append([nxt, path+[nxt], total+cost])

#     return result 
    
# result = bfs(1)
# print(result[1])
# print(' '.join(map(str, result[0])))




