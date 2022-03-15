"""
문제
이진트리를 다음의 규칙에 따라 행과 열에 번호가 붙어있는 격자 모양의 틀 속에 그리려고 한다. 이때 다음의 규칙에 따라 그리려고 한다.

이진트리에서 같은 레벨(level)에 있는 노드는 같은 행에 위치한다.
한 열에는 한 노드만 존재한다.
임의의 노드의 왼쪽 부트리(left subtree)에 있는 노드들은 해당 노드보다 왼쪽의 열에 위치하고, 오른쪽 부트리(right subtree)에 있는 노드들은 해당 노드보다 오른쪽의 열에 위치한다.
노드가 배치된 가장 왼쪽 열과 오른쪽 열 사이엔 아무 노드도 없이 비어있는 열은 없다.
이와 같은 규칙에 따라 이진트리를 그릴 때 각 레벨의 너비는 그 레벨에 할당된 노드 중 가장 오른쪽에 위치한 노드의 열 번호에서 가장 왼쪽에 위치한 노드의 열 번호를 뺀 값 더하기 1로 정의한다. 
트리의 레벨은 가장 위쪽에 있는 루트 노드가 1이고 아래로 1씩 증가한다.

아래 그림은 어떤 이진트리를 위의 규칙에 따라 그려 본 것이다. 
첫 번째 레벨의 너비는 1, 두 번째 레벨의 너비는 13, 3번째, 4번째 레벨의 너비는 각각 18이고, 5번째 레벨의 너비는 13이며, 그리고 6번째 레벨의 너비는 12이다.



우리는 주어진 이진트리를 위의 규칙에 따라 그릴 때에 너비가 가장 넓은 레벨과 그 레벨의 너비를 계산하려고 한다.
위의 그림의 예에서 너비가 가장 넓은 레벨은 3번째와 4번째로 그 너비는 18이다. 
너비가 가장 넓은 레벨이 두 개 이상 있을 때는 번호가 작은 레벨을 답으로 한다. 
그러므로 이 예에 대한 답은 레벨은 3이고, 너비는 18이다.

임의의 이진트리가 입력으로 주어질 때 너비가 가장 넓은 레벨과 그 레벨의 너비를 출력하는 프로그램을 작성하시오

입력
첫째 줄에 노드의 개수를 나타내는 정수 N(1 ≤ N ≤ 10,000)이 주어진다. 
다음 N개의 줄에는 각 줄마다 노드 번호와 해당 노드의 왼쪽 자식 노드와 오른쪽 자식 노드의 번호가 순서대로 주어진다. 
노드들의 번호는 1부터 N까지이며, 자식이 없는 경우에는 자식 노드의 번호에 -1이 주어진다.

출력
첫째 줄에 너비가 가장 넓은 레벨과 그 레벨의 너비를 순서대로 출력한다. 
너비가 가장 넓은 레벨이 두 개 이상 있을 때에는 번호가 작은 레벨을 출력한다.

====================================================================================================================================================================================

중위 순회하면서 문제를 해결한다.

root에서 시작해 중위 순회 순서를 column으로 설정한다. 
각 level의 first order, last order를 저장한다.

level : [first order, last order] 형태로 저장한다.

중위 순회를 통해 모든 노드를 순회하면 각 level 별로 last_order - first_order로 너비를 구할 수 있다.

단, 문제에서 root가 무조건 1이 아니고 데이터가 root부터 순서대로 주어지는 것이 아니기 때문에
root를 찾아주는 과정이 필요하다.

이는 모든 노드들의 부모 노드를 가리키는 {child : parent} 형태의 변수를 생성하고 value가 None인 값을 찾으면 그 값이 root가 된다.

root 찾는 과정은 O(N) Time
In-order traversal 과정 또한, O(N)

생성한 depth_width = {level : [first order, last order]} 를 정렬하는 과정이 필요하다.

이 과정에서 이진 트리가 사향 트리로 주어질 경우 길이 N만큼의 dictionary가 생성된다.

따라서 총 Time Complexity는 O(N log N) Time이 된다.
"""

from collections import defaultdict

n = int(input())
## Tree로 만들지 않고 Graph로 생성해 DFS 방식으로 In-Order Traversal 를 수행한다. 
graph = {i : {"left" : None, "right" : None} for i in range(1,n+1)}
## 각 노드의 부모 노드가 무엇인지 체크한다. => 모든 노드를 체크하면 root 노드만 parent가 None이 된다.
parents = {i : None for i in range(1, n+1)}
## Input
for i in range(1, n+1):
    par, left, right = map(int, input().split())
    
    if left != -1:  ## left child 존재
        graph[par]["left"] = left
        parents[left] = par
    if right != -1:  ## right child 존재
        graph[par]["right"] = right
        parents[right] = par
        
## root node를 찾아야 한다.
def find_root(parents):
    for k, v in parents.items():
        if v is None: return k

## depth_width = {level : [first order, last order]} 형태 
depth_width = defaultdict(list)
order = 1 ## 방문 순서
def dfs(now, depth):
    global order
    ## left visit
    if graph[now]["left"]:
        dfs(graph[now]["left"], depth +1)

    ## No left child
    if depth_width[depth] == []: ## 해당 Level의 첫 방문이라면
        depth_width[depth] = [order, order] ## 최초 방문이다. => first, last = order
    else:
        ## level을 이미 방문한 적이 있다면 마지막 방문만 update해주면 된다.
        depth_width[depth][1] = max(depth_width[depth][1], order)
    order += 1

    ## Right visit    
    if graph[now]["right"]:
        dfs(graph[now]["right"], depth + 1)
        
        
root = find_root(parents)
dfs(root, 1)

depth_width = sorted(depth_width.items(), key= lambda x: [x[1][0]-x[1][1], x[0]])
print(depth_width[0][0], depth_width[0][1][1]-depth_width[0][1][0]+1)