"""
문제
정수 N과 K가 주어졌을 때, 다음 두 조건을 만족하는 문자열 S를 찾는 프로그램을 작성하시오.

문자열 S의 길이는 N이고, 'A', 'B'로 이루어져 있다.
문자열 S에는 0 ≤ i < j < N 이면서 s[i] == 'A' && s[j] == 'B'를 만족하는 (i, j) 쌍이 K개가 있다.
입력
첫째 줄에 N과 K가 주어진다. (2 ≤ N ≤ 50, 0 ≤ K ≤ N(N-1)/2)

출력
첫째 줄에 문제의 조건을 만족하는 문자열 S를 출력한다. 가능한 S가 여러 가지라면, 아무거나 출력한다. 만약, 그러한 S가 존재하지 않는 경우에는 -1을 출력한다.
============================================================================================================================================
첫 시도는 n이 50이하라 크지 않아 bfs를 수행하여 해당 조건에 맞는 값을 발견하면 멈추는 방법으로 진행했다. 
하지만 2^50 이라는 최악의 경우가 발생할 수 있어 시간초과가 발생.

주어진 데이터의 n과 k에 주목해야한다.
특히 n에 주목하여 초기 값을 길이가 n인 문자열로 지정해준다면 많은 계산을 줄일 수 있다.
그렇다면 문제에서는 A의 위치보다 큰 값이 B인 횟수들을 찾는것으로 보이기 때문에 초기값을 'B'*n으로 두고 A의 위치를 찾아가면 될 듯 하다.
"""

# ## BFS - Fail
# from collections import deque
# n,k = map(int, input().split())
# pos = ['A','B']

# def bfs():
#     global cnt
#     queue = deque()
#     queue.append(('A', 0))
#     queue.append(('B', 0))

#     while queue:
#         word, count = queue.popleft()

#         if len(word) == n and count == k:
#             return word
#         if len(word) >n: return -1

#         for p in pos:
#             if p =='A': 
#                 queue.append((word+p,count))
#             else:
#                 cnt = 0
#                 for w in word:
#                     if w=='A': 
#                         cnt +=1
#                 queue.append((word+p, count+cnt))

# out = bfs()
# print(out)



## Version 2

n, k = map(int, input().split())
def check(s):
    cnt = 0
    for i in range(len(s)):
        if s[i] =='A':
            for j in range(i+1, len(s)):
                if s[j] == 'B': cnt +=1
    return cnt == k

s = 'B'*n
s = list(s)
flag = False
for i in range(len(s)):
    for j in range(len(s)-1, i-1, -1):
        s[j] = 'A'
        if check(s): 
            flag = True
            break
        elif j != i:
            s[j] = 'B'
    if flag: break

s=  ''.join(s)
if s == 'A'*n:
    if k==0: print(s)
    else: print(-1)
else:
    print(s)