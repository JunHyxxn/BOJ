"""
문제
World Soccer Championship이 다가오고 있다! 
천재적인 전술을 창조하는 플랜 아티스트 감독 도현이는 자신의 팀이 승리하도록 만반의 준비를 가하고 있다.
도현이의 전략은 경기장을 여러 개의 구역으로 나누고, 어떤 선수가 A구역에서 B구역으로 이동하게 하는 움직임을 (A, B)로 표현한다. 
모든 도현이의 팀 선수들이 이 움직임만을 따라서 이동한다면 승리하리라고 도현이는 확신한다.

도현이는 선수들에게 자신의 전술을 말해주며, 
다른 모든 구역에 도달할 수 있는 시작 구역을 찾은 뒤 지시한 움직임만을 따라가라고 했다. 
그러나 도현이는 한 가지 간과한 것이 있었는데 그건 선수들이 자신만큼 똑똑하지 않다는 것이다. 선수들은 그러한 시작 구역을 찾는 것이 어려웠다. 
이제 당신이 적절한 시작 구역을 찾아줘야 한다.

 
입력
첫 번째 줄에 테스트 케이스의 개수가 주어지며, 이는 11보다 작거나 같은 정수이다.

그 다음 줄부터 여러 개의 테스트 케이스가 주어지는데, 
각 테스트 케이스마다 첫 번째 줄에 구역의 수 N, 지시한 움직임의 수 M이 주어지며 1 ≤ N, M ≤ 100,000 이다. 
그 다음 M개의 줄에 걸쳐서 움직임 (A, B)가 주어지며, 
A, B는 0 ≤ A, B < N인 정수이다.

각 테스트 케이스는 하나의 빈 줄로 구분된다.

출력
각 테스트 케이스마다 가능한 모든 시작 구역을 오름차순으로 한 줄에 하나씩 출력한다. 
만약 그러한 시작 구역이 없으면, "Confused"를 출력한다.

각 테스트 케이스의 끝에는 하나의 빈 줄을 출력한다.

===============================================================================================================================================
Key Point : 특정 정점에서 시작하면 모든 정점을 방문할 수 있는 점을 찾는다.
=> 모든 vertex DFS해서 할 수 있지만 이는 너무 오래 걸린다.
★ SCC를 생성하여 SCC간 연결하면 단축할 수 있다. => in_degree = 0인 SCC가 2개이상 있으면 Confused하다.
                                             => in_degree = 0 인 SCC는 무조건 하나이어야 함. 0이 없는 경우는 발생하지 않음. 발생한다면 또 다른 SCC형성
Kosaraju로 해결해본다.
"""

import sys
sys.setrecursionlimit(10**9)

def dfs(start):
    visited[start] = 1
    for nxt in graph[start]:
        if not visited[nxt]:
            dfs(nxt)
    finished.append(start)


def rev_dfs(start, root):
    visited[start] = 1
    stack.append(start)
    
    for nxt in reverse_g[start]:
        if not visited[nxt]:
            rev_dfs(nxt, root)
    while stack:
        node = stack.pop()
        SCC[node] = root


T = int(sys.stdin.readline())
while T:
    command = sys.stdin.readline()
    if command == '\n': continue
    N,M = map(int, command.split())
    graph = [[] for _ in range(N)]
    reverse_g = [[] for _ in range(N)]
    for _ in range(M):
        A,B = map(int, sys.stdin.readline().split())
        graph[A].append(B)
        reverse_g[B].append(A)

    visited = [0] * (N)
    finished = []
    for i in range(N):
        if not visited[i]: dfs(i)

    visited = [0] * (N)
    stack = []
    SCC = [0] * N
    while finished:
        f = finished.pop()
        if not visited[f]: rev_dfs(f, f)
    SCC_component = {i:[] for i in set(SCC)}
    SCC_graph = {i:[] for i in set(SCC)}
    for i in range(N):
        SCC_component[SCC[i]].append(i)
        for j in graph[i]:
            if SCC[i] != SCC[j]:
                SCC_graph[SCC[j]].append(SCC[i])

    cnt = 0
    leader = -1
    for l, v in SCC_graph.items():
        if len(v) == 0: 
            cnt += 1
            leader = l            
    if cnt == 1:
        sys.stdout.write('\n'.join(map(str,sorted(SCC_component[leader])))+'\n')
    else:
        sys.stdout.write('Confused\n')
    print()
    T -= 1