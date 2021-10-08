"""
문제
방향 그래프가 주어졌을 때, 그 그래프를 SCC들로 나누는 프로그램을 작성하시오.

방향 그래프의 SCC는 우선 정점의 최대 부분집합이며, 그 부분집합에 들어있는 서로 다른 임의의 두 정점 u, v에 대해서 u에서 v로 가는 경로와 v에서 u로 가는 경로가 모두 존재하는 경우를 말한다.



예를 들어 위와 같은 그림을 보자. 이 그래프에서 SCC들은 {a, b, e}, {c, d}, {f, g}, {h} 가 있다. 물론 h에서 h로 가는 간선이 없는 경우에도 {h}는 SCC를 이룬다.

입력
첫째 줄에 두 정수 V(1 ≤ V ≤ 10,000), E(1 ≤ E ≤ 100,000)가 주어진다. 
이는 그래프가 V개의 정점과 E개의 간선으로 이루어져 있다는 의미이다. 
다음 E개의 줄에는 간선에 대한 정보를 나타내는 두 정수 A, B가 주어진다.
 이는 A번 정점과 B번 정점이 연결되어 있다는 의미이다. 이때 방향은 A → B가 된다.

정점은 1부터 V까지 번호가 매겨져 있다.

출력
첫째 줄에 SCC의 개수 K를 출력한다. 다음 K개의 줄에는 각 줄에 하나의 SCC에 속한 정점의 번호를 출력한다. 
각 줄의 끝에는 -1을 출력하여 그 줄의 끝을 나타낸다. 
각각의 SCC를 출력할 때 그 안에 속한 정점들은 오름차순으로 출력한다. 
또한 여러 개의 SCC에 대해서는 그 안에 속해있는 가장 작은 정점의 정점 번호 순으로 출력한다.

========================================================================================================================================================================

SCC : Strongly Connected Component
2가지 방법이 있다. 
1. Kosaraju's algorithms
2. Tarjan's Algorithm
"""
## 1. Kosaraju's algorithms  
import sys
sys.setrecursionlimit(10**6)
#   
## 정방향 DFS
def DFS(start, stack):
    global visited
    if visited[start]: ## 이미 방문한 시작 노드면 종료
        return
    visited[start] = 1
    for vertex in adj_list[start]:
        if visited[vertex] == 0:
            DFS(vertex, stack)
    stack.append(start) ## finish stack

## 역방향 DFS
def reverse_DFS(start, par):
    global visited
    if visited[start]: ## 이미 방문한 시작 노드면 종료
        return
    if par not  in result.keys():
        result[par] = [] ## dict형태로 SCC 저장 ex) lead : [elem1, elem2, elem3 ...]
    visited[start] = 1
    for vertex in reverse_adj[start]:
        if visited[vertex] == 0:
            reverse_DFS(vertex, par)
    
    result[par].append(start)

## input
V, E = map(int, input().split())
adj_list = {i:[] for i in range(1, V+1)}
reverse_adj = {i:[] for i in range(1, V+1)}
for _ in range(E):
    S, D = map(int, input().split())

    adj_list[S].append(D) ## 정방향 그래프
    reverse_adj[D].append(S) ## 역방향 그래프

    
## 정방향 DFS수행 => finish stack 구한다.
finish = []
visited = [0] * (1+V)
for i in range(1,V+1):
    DFS(i, finish)


## visited Initialize
visited = [0] * (1+V)
result = {}

while finish: ## finish stack pop하며 탐색
    start = finish.pop()
    reverse_DFS(start, start)

result = sorted(result.items(), key=lambda x : min(x[1])) ## SCC내부 원소의 최솟값 순서로 정렬
print(len(result))
for k, v in result:
    print(' '.join(map(str, sorted(v))),'-1') ## 정렬하여 출력



## 2. Tarjan's Algorithm - 4196번으로 대체