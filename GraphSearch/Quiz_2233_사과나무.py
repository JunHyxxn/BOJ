n = int(input())
appletree = input()
x, y = map(int, input().split())


"""
0001011011
1233442551


각 노드의 부모를 가리키는 배열 parents
parents =>
1   2 3 4 5
-1  1 2 2 1
"""
represents = [-1] * (2*n+1)
parents = [0] * (n+1)
stack = []
node = 1
for i in range(len(appletree)):
    if appletree[i] == '0':
        stack.append(node)
        represents[i+1] = node
        node += 1
    else:
        child = stack.pop()
        represents[i+1] = child
        if len(stack) == 0:
            parents[child] = -1
        else:
            parents[child] = stack[-1]
        

apple1, apple2 = represents[x], represents[y]

"""
parents =>
1   2 3 4 5
-1  1 2 2 1

썩은 사과들의 부모 집합 생성
apple1 = 3
ㄴ> { 2, 1 }

apple2 = 4
ㄴ> {2, 1}

"""

def make_candidate(now):
    parent_set = []
    parent_set.append(now)
    while True:
        if parents[now] == -1: 
            return set(parent_set)
        
        parent_set.append(parents[now])
        now = parents[now]
    
    
set1 = make_candidate(apple1)
set2 = make_candidate(apple2)
"""
                1
        2              5
    3       4



apple1 = 3
ㄴ> { 3, 2, 1 }

apple2 = 4
ㄴ> {4, 2, 1}

set의 intersection => 공통 부모 집합들 표현
그 중 max 가져오면 이게 가장 하위 노드됨.

노드 값을 알아냄.
노드에 해당하는 인덱스 뽑아내면 됨,




000010110101100100101111

                        1
                2               8
        3       !6   7       9       10
    4     5   7                   11    !12

"""
result = max(set1.intersection(set2))
answer = []
for i in range(1, len(represents)):
    if len(answer) ==2: break
    if result == represents[i]:
        answer.append(i)
        
print(' '.join(map(str, answer)))