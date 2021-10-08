# """
# 문제
# 도미노는 재밌다. 
# 도미노 블록을 일렬로 길게 늘어세운 뒤 블록 하나를 넘어뜨리면 그 블록이 넘어지며 다음 블록을 넘어뜨리는 일이 반복되어
#  일렬로 늘어선 블록들을 연쇄적으로 모두 쓰러뜨릴 수 있다. 그러나, 가끔씩 도미노가 다른 블록을 넘어뜨리지 못하게 배치되어 있다면, 
#  우리는 다음 블록을 수동으로 넘어뜨려야 한다.

# 이제 각 도미노 블록의 배치가 주어졌을 때, 모든 블록을 넘어뜨리기 위해 손으로 넘어뜨려야 하는 블록 개수의 최솟값을 구하자.

# 입력
# 첫 번째 줄에 테스트 케이스의 개수가 주어진다.

# 각 테스트 케이스의 첫 번째 줄에는 두 정수 N, M이 주어지며 두 수는 100,000을 넘지 않는다. 
# N은 도미노의 개수를, M은 관계의 개수를 나타낸다. 
# 도미노 블록의 번호는 1과 N 사이의 정수다. 
# 다음 M개의 줄에는 각각 두 정수 x, y가 주어지는데, 
# 이는 x번 블록이 넘어지면 y번 블록도 넘어짐을 뜻한다.

# 출력
# 각 테스트 케이스마다 한 줄에 정수 하나를 출력한다. 정답은 손으로 넘어뜨려야 하는 최소의 도미노 블록 개수이다.

# ==============================================================================================================================
# SCC만들고 SCC간 연결되는지 확인하면 될 듯.
# leader가 연결되어 있으면 연결 가능...?
# => SCC 그룹간 in_degree가 0인 SCC의 개수가 쓰러뜨려야하는 도미노가 된다.
# Ver. Tarjan's Algorithms
# """
# import sys
# sys.setrecursionlimit(10**9)

# def Tarjan(now):
#     visited[now] = now
#     stack.append(now)
#     par = visited[now]

#     for next_node in graph[now]:
#         if visited[next_node] == 0: ## 아직 미방문
#             par = min(par, Tarjan(next_node)) ## 재귀
#         elif finished[next_node]==0: ## 방문했지만 아직 SCC 검사 끝나지 않은 경우
#             par = min(par, visited[next_node]) ## 더 작은 par값으로 return 해준다.
    
#     ## 더 이상 DFS 진행할 노드 없으면 SCC완성 시킨다.
#     if par == visited[now]: 
#         while True:
#             temp = stack.pop()
#             SCC[temp] = par ## SCC 배열에 leader를 Check
#             finished[temp] = 1 ## SCC 검사 끝났으면 finished
#             if temp == now: ## now와 같아질때까지 stack에서 pop한다.
#                 break
#     return par 

# T = int(sys.stdin.readline())

# for _ in range(T):
#     N,M = map(int, sys.stdin.readline().split())
#     graph = {i:[] for i in range(1,N+1)}
#     for _ in range(M):
#         s, d = map(int,sys.stdin.readline().split())
#         graph[s].append(d)

#     ## initialize
#     visited = [0] * (N+1)
#     finished = [0] * (N+1)
#     SCC =[0] *(1+N)
#     stack = []
#     ## 모든 노드를 DFS진행
#     for i in graph.keys():
#         if visited[i] == 0: Tarjan(i)

#     ## SCC간 연결 여부 검사
#     scc_adj = {i : [] for i in set(SCC[1:])}
#     for i in range(1, N+1):
#         for j in graph[i]:
#             if SCC[i] != SCC[j]:
#                 scc_adj[SCC[j]].append(SCC[i])

#     cnt = 0
#     for k, v in scc_adj.items():
        
#         if len(v) == 0:
#             cnt += 1

#     sys.stdout.write(str(cnt))


import sys
sys.setrecursionlimit(10**8)

def dfs(start):
    stack.append(start)
    visited[start] = start
    par = start

    for nxt in graph[start]:
        if not visited[nxt]:
            par = min(par, dfs(nxt))
        elif not finished[nxt]:
            par = min(par, visited[nxt])
    
    if par == visited[start]:
        while True:
            temp = stack.pop()
            finished[start] = temp
            SCC[temp] = par
            if temp == par:
                break
    return par

T = int(sys.stdin.readline())
for _ in range(T):
    N,M = map(int, sys.stdin.readline().split())
    graph = {i:[] for i in range(1, N+1)}
    for _ in range(M):
        x,y = map(int, sys.stdin.readline().split())
        graph[x].append(y)
    visited= [0]*(1+N)
    finished = [0] * (1+N)
    SCC = [0]*(1+N)
    stack = []

    for i in range(1, 1+N):
        if not visited[i]: dfs(i)
    scc_graph = {i : [] for i in set(SCC[1:])}
    for i in range(1, 1+N): ## start
        for j in graph[i]: ## destination
            if SCC[i] != SCC[j]: ## 서로 다른 SCC라면 in,out degree존재
                scc_graph[SCC[j]].append(SCC[i])
    
    cnt = 0
    for _,v in scc_graph.items():
        if len(v) == 0:
            cnt +=1

    if cnt: sys.stdout.write(str(cnt)+'\n')
    else: sys.stdout.write(str(1)+'\n')