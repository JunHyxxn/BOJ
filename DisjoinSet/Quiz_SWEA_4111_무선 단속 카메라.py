"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AWJHjcFqdyoDFAUH&categoryId=AWJHjcFqdyoDFAUH&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=10

UnionFind 을 이용해 해결할 수 있다.
"""
from collections import defaultdict

T = int(input())

def find(disjoint_set, num):
    if disjoint_set[num] == num:
        return num
    num = disjoint_set[num]
    return num

def union(disjoint_set, a, b):
    a = find(disjoint_set, a)
    b = find(disjoint_set, b)
    if a == b:
        return disjoint_set
    disjoint_set[max(a, b)] = min(a, b)
    return disjoint_set

for t in range(1, T+1):
    N = int(input())
    K = int(input())
    ## 중복된 카메라 위치는 고려하지 않아도 된다. set으로 줄여준다.
    camera = set(map(int, input().split()))
    ## 정렬 해줘야 카메라간 거리 알 수 있다.
    camera = list(sorted(camera))
    ## 중복 제거했으니 N을 다시 정의해줘야한다.
    N = len(camera)
    ## 인접 거리를 두 카메라의 위치를 key로 잡는다.
    adjacent_dist = defaultdict(int)
    for i in range(len(camera)-1):
        adjacent_dist[(camera[i], camera[i+1])] = camera[i+1] - camera[i]

    disjoint_set = {c: c for c in camera}

    adjacent_dist = list(sorted(adjacent_dist.items(), key=lambda x: x[1]))

    total = 0
    for info in adjacent_dist:
        if N <= K:
            break
        N -= 1
        nodes, dist = info
        a, b = nodes
        disjoint_set = union(disjoint_set, a, b)
        total += dist
    print("#{} {}".format(t, total))
