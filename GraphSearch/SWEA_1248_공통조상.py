"""
SWEA - 1248 [S/W 문제해결 응용] 3일차 - 공통조상 - D5


1️⃣ find_root - 공통 조상 찾기
우선 두 노드가 조상, 후손 관계가 존재하지 않는다는 가정이 존재한다.
하나의 노드에서 부모 노드들은 전부 체크해둔다.
ㄴ> Worst Case : O(V) 

다른 노드에서 마찬가지로 부모 노드로 올라가면서 해당 노드가 위에서 부모 노드로 체크되었다면 공통 조상이 된다.
따라서 O(V) 안에 무조건 공통 조상을 찾을 수 있다.
종합적으로는 O(2*V) => 최악의 경우라도 약 20000번 안에 공통 조상을 찾을 수 있다.

2️⃣ tree 순회
위에서 찾은 공통 조상에서 트리 순회를 진행한다.
이 때, Tree를 배열로 표현하게 되면 2^V + 1 만큼의 배열이 필요한데, 최악의 경우 2^10000 개의 배열이 필요하니 불가능하다.
따라서 graph 형태로 표현한다.
이 후 BFS로 순회하면 최악의 경우 V개의 노드를 순회하여 해결 가능하다.

1개의 테스트 케이스에 200000000(2억) 번에 해결가능하고, 총 10개의 TC => 20억번 안에 해결가능하다. 
따라서  30s 안에 가능하다
"""

from collections import defaultdict, deque

def find_root(V, x, y, parent):
    is_parent = [False]*(V+1)
    while parent[x] != -1:
        temp = parent[x]
        is_parent[temp] = True
        x = temp
    
    while True:
        if is_parent[y]:
            return y
        if parent[y] == -1:
            break
        y = parent[y]
        
    return -1    

T = int(input())
for t in range(1, T+1):
    V, E, x, y = map(int, input().split())
    cmd = list(map(int, input().split()))
    parent = [-1]*(V+1)
    tree = defaultdict(list)
    for i in range(E):
        m, n = i*2, (i*2+1)
        
        parent[cmd[n]] = cmd[m]
        tree[cmd[m]].append(cmd[n])
    
    root = find_root(V,x,y,parent)
    cnt = 0
    node = deque()
    node.append(root)
    while node:
        now = node.popleft()
        cnt += 1
        for nxt in tree[now]:
            node.append(nxt)
    
    
    print("#{} {} {}".format(t, root, cnt))