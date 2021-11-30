"""
문제
백준이는 방 청소를 하면서 필요 없는 전공 서적을 사람들에게 나눠주려고 한다. 나눠줄 책을 모아보니 총 N권이었다. 
책이 너무 많기 때문에 백준이는 책을 구분하기 위해 각각 1부터 N까지의 정수 번호를 중복되지 않게 매겨 두었다.

조사를 해 보니 책을 원하는 서강대학교 학부생이 총 M명이었다. 
백준이는 이 M명에게 신청서에 두 정수 a, b (1 ≤ a ≤ b ≤ N)를 적어 내라고 했다. 
그러면 백준이는 책 번호가 a 이상 b 이하인 책 중 남아있는 책 한 권을 골라 그 학생에게 준다. 
만약 a번부터 b번까지의 모든 책을 이미 다른 학생에게 주고 없다면 그 학생에게는 책을 주지 않는다.

백준이가 책을 줄 수 있는 최대 학생 수를 구하시오.

입력
첫째 줄에 테스트 케이스의 수가 주어진다.

각 케이스의 첫 줄에 정수 N(1 ≤ N ≤ 1,000)과 M(1 ≤ M ≤ 1,000)이 주어진다. 다음 줄부터 M개의 줄에는 각각 정수 ai, bi가 주어진다. (1 ≤ ai ≤ bi ≤ N)

출력
각 테스트 케이스마다 백준이가 책을 줄 수 있는 최대 학생 수를 한 줄에 하나씩 출력한다.

======================================================================================================================================================

Bipartitle Matching - 이분 매칭
이분 그래프 : 모든 정점을 두가지 색으로만 칠할 수 있는 그래프. ( 두 개의 그룹으로 나눌 수 있는 그래프를 의미한다. )
- 이분 그래프에서 Maximum Bipartitle Matching 하는 알고리즘을 이분 매칭이라고 한다.
- Main Idea : 굴러들어온 돌이 박힌 돌을 빼내면서 매칭하면 된다.
"""

import sys

def dfs(start):
    global books, students
    if start == -1 or visited[start]: return False
    visited[start] = True
    
    for b in adj[start]:
        if books[b] == -1 or dfs(books[b]):
            students[start] = b
            books[b] = start
            return True
    return False

input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n, m  = map(int, input().split())
    
    adj = {}
    for i in range(1,m+1):
        a,b = map(int, input().split())
        adj[i] = [k for k in range(a, b+1)]
        
    
    books = [-1]*(n+1)
    students = [-1]*(m+1)
    size = 0
    for s in range(1,m+1):
        visited = [0] * (m+1)
        if(dfs(s)): size += 1
    
    print(size)