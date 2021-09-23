"""
BOJ - 2167

Quiz.
2차원 배열의 합.


Input
첫째 줄에 배열의 크기 N, M(1 ≤ N, M ≤ 300)이 주어진다. 다음 N개의 줄에는 M개의 정수로 배열이 주어진다. 
배열에 포함되어 있는 수는 절댓값이 10,000보다 작거나 같은 정수이다. 
그 다음 줄에는 합을 구할 부분의 개수 K(1 ≤ K ≤ 10,000)가 주어진다. 
다음 K개의 줄에는 네 개의 정수로 i, j, x, y가 주어진다(i ≤ x, j ≤ y).

Output
K개의 줄에 순서대로 배열의 합을 출력한다. 배열의 합은 231-1보다 작거나 같다.

EX)
2 3
1 2 4
8 16 32
3
1 1 2 3
1 2 1 2
1 3 2 3

63
2
36


==============================================================================================================================
Keyword. 2d-array cumsum 
S[x,y] = S[x-1, y] + S[x,y-1] - S[x-1,y-1] + A[x,y]
"""

import sys

N, M = map(int, sys.stdin.readline().split())


def cumsum(i,j,x,y, S):
    return S[x][y] - S[x][j-1] - S[i-1][y] + S[i-1][j-1]

A = []
S = [[0]*(M+1) for _ in range(N+1)]

for i in range(N):
    l = list(map(int, sys.stdin.readline().split()))
    A.append(l)


for i in range(1,N+1):
    for j in range(1,M+1):
        S[i][j] = S[i-1][j] + S[i][j-1] - S[i-1][j-1] + A[i-1][j-1]

k = int(sys.stdin.readline())

for i in range(k):
    i,j,x,y = map(int, sys.stdin.readline().split())
    print(cumsum(i,j,x,y, S))

