"""
BOJ - 1074 Z문제
재귀 & 분할정복
"""

import sys

arr = [[0,1],[2,3]]

def solve(n, r, c):
    if n==1: return arr[r][c] * 4**(n-1)
    half = 2**n // 2
    if r < half and c < half:
        return 0*4**(n-1) + solve(n-1,r,c)
    elif r < half and c >= half:
        return 1*4**(n-1) + solve(n-1,r,c-half)
    elif r >= half and c < half:
        return 2*4**(n-1) + solve(n-1,r-half,c)
    elif r >= half and c >= half:
        return 3*4**(n-1) + solve(n-1,r-half,c-half)

n, r, c = map(int, sys.stdin.readline().strip().split())

print(solve(n,r,c))