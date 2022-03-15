"""
문제
원장선생님께서는 1부터 N까지 번호가 붙은 N(K ≤ N ≤ 900)명의 학생들 중에서 K(1 ≤ K ≤ 62)명의 학생들을 소풍에 보내려고 한다. 
그런데 원장선생님께서는 중간에 싸움이 일어나면 안되므로 소풍을 갈 학생들이 모두 서로 친구 사이이기를 원한다. 
원장선생님께서는 이러한 일을 이번에 조교로 참가한 고은이에게 친구 관계에 대한 정보를 F(1 ≤ F ≤ 5,600)개를 주시며 K명을 선발하라고 부탁하였다.

고은 조교를 도와 소풍을 가게 될 K명의 학생들을 결정하시오.

입력
첫째 줄에 공백으로 분리된 세 정수 K, N, F가 주어진다. 다음 F개의 줄에는 서로 친구 관계인 두 사람의 번호가 주어진다. 
친구 관계는 상호적인 관계이므로 2번 학생이 4번 학생을 좋아하면 4번 학생도 2번 학생을 좋아한다. 
같은 친구 관계가 여러 번 주어지는 경우는 없다.

출력
만약 K명의 친구 관계인 학생들이 존재하지 않는다면 -1을 출력한다. 
그 외의 경우에는, K개의 줄에 학생들의 번호를 증가하는 순서로 한 줄에 한 개씩 출력한다. 
여러 경우가 존재한다면 첫 번째 학생의 번호가 제일 작은 것을 출력한다. 
첫 번째 학생의 번호가 같은 경우라면, 두 번째 학생의 번호가 작은 경우를 출력하고, 이와 같은 식으로 출력한다.
"""
def allFriend(group, newOne):
    flag = True
    for g in group:
        if not friends[newOne][g]:
            flag = False
    return flag
def find_group(student):
    global group
    if len(group) == K:
        group = list(sorted(group))
        print('\n'.join(map(str, group)))
        return 

    
    for friend in range(1, N+1):
        if friends[student][friend] and not visited[friend]:
            if allFriend(group, friend):
                group.append(friend)
                visited[friend] = 1
                find_group(friend)
                visited[friend] = 0

    return

K, N, F = map(int, input().split())
friends = [[0]*(N+1) for _ in range(N+1)]
for _ in range(F):
    f1, f2 = map(int, input().split())
    friends[f1][f2] = 1
    friends[f2][f1] = 1
    

flag = False
for i in range(1, N+1):
    if flag: break
    group = [i]
    visited = [0]*(N+1)
    visited[i] = 1
    find_group(i)
    if len(group) >= K:
        flag = True
if not flag:
    print(-1)
    
