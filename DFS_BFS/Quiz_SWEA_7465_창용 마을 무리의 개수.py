"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.


창용 마을에는 N명의 사람이 살고 있다.

사람은 편의상 1번부터 N번 사람까지 번호가 붙어져 있다고 가정한다.

두 사람은 서로를 알고 있는 관계일 수 있고, 아닐 수 있다.

두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면,

이러한 사람들을 모두 다 묶어서 하나의 무리라고 한다.

창용 마을에 몇 개의 무리가 존재하는지 계산하는 프로그램을 작성하라.


[입력]

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 각각 창용 마을에 사는 사람의 수와 서로를 알고 있는 사람의 관계 수를 나타내는

두 정수 N, M(1 ≤ N ≤ 100, 0 ≤ M ≤ N(N-1)/2) 이 공백 하나로 구분되어 주어진다.

이후 M개의 줄에 걸쳐서 서로를 알고 있는 두 사람의 번호가 주어진다.

같은 관계는 반복해서 주어지지 않는다.


[출력]

각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,

무리의 개수를 출력한다.
"""

from collections import defaultdict, deque
T = int(input())

def bfs( visited, start):
    global graph
    queue = deque()
    queue.append(start)
    while queue:
        now = queue.popleft()
        visited[now] = start
        for nxt in graph[now]:
            if not visited[nxt]:
                queue.append(nxt)

    return visited

for t in range(1, T+1):
    N, M = map(int, input().split())
    graph = defaultdict(list)
    for _ in range(M):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    
    visited = [False] * (N+1)
    for i in range(1, N+1):
        if not visited[i]:
            visited = bfs(visited, i)

    cnt = len(set(visited[1:]))
    
    print("#{} {}".format(t, cnt))
    



