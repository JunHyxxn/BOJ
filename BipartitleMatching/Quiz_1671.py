"""
문제
어떤 상어는 저녁식사로 서로를 먹는다. 모든 상어는 자신과 다른 상어의 크기, 속도, 지능을 수치로 나타낸 것을 알고 있다. 
만약, 상어 A의 크기, 속도, 지능이 상어 B의 크기, 속도, 지능보다 크거나 같다면 상어 A는 상어 B를 먹을 수 있다. 
그러나, 상어들의 왕 김재홍은 상어들이 많이 없어지는 것을 방지하기 위해서 한 상어가 최대 두 개의 상어만 먹을 수 있게 했다. 
상어들은 김재홍의 말을 모두 듣는다.

한 상어가 다른 상어를 잡아먹는 동안 나머지 상어들은 상어를 잡아먹을 수 없으며, 이미 잡아먹힌 상어는 다른 상어들을 잡아먹을 수 없다.

N마리 상어의 크기, 속도, 지능이 주어졌을 때, 살아남을 수 있는 상어 수의 최솟값을 구하시오.

입력
첫째 줄에 상어의 마리 수 N이 주어진다. 이 값은 50보다 작거나 같은 자연수이다. 
둘째 줄부터 각 상어의 크기, 속도, 지능의 정보가 주어진다. 
이 값은 2,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 살아남을 수 있는 상어 수의 최솟값을 출력한다.

==========================================================================================================================================================

우선 각 상어들이 향할 수 있는 관계를 찾아야한다. 
크기 속도 지능 모두 크거나 같을때 잡아먹을 수 있다.
이는 brute-force 필요. => n*n
"""
from collections import defaultdict

def compare(s1, s2):
    # print('s1, s2', s1, s2)
    s11,s12,s13 = s1
    s21,s22,s23 = s2
    if s11 == s21 and s12 == s22 and s13 == s23: return 1
    if s11 >= s21 and s12 >= s22 and s13 >= s23: return 2
    return 0

def dfs(start):
    if start == -1 or visited[start]: return False
    visited[start] = True
    
    for f in adj[start]:
        if eaten[f] == -1 or dfs(eaten[f]):
            eaten[f] = start
            eater[start] = f
            return True
    return False

n = int(input())
shark = defaultdict(int)
for i in range(1, n+1):
    shark[i] = list(map(int, input().split()))
adj = defaultdict(list)
for i in range(1,n+1):
    for j in range(1,n+1):
        if i ==j: continue
        flag = compare(shark[i], shark[j])
        if flag == 1 and i < j:
            adj[i] += [j]
        elif flag == 2:
            adj[i] += [j]
eater = [-1] * (n+1)
eaten = [-1] * (n+1)
cnt = 0
for i in range(1, n+1):
    visited = [0] * (n+1)
    if dfs(i): cnt += 1
    visited = [0] * (n+1)
    if dfs(i): cnt += 1

print(n-cnt)