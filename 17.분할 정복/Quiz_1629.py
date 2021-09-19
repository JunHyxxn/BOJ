import sys

def solve(A, B, C):
    if B == 1: return A % C
    mod = solve(A, B//2, C)
    mod = mod * mod % C
    if B %2 == 1:
        mod = mod * A % C
    return mod
    


A, B, C  = map(int, sys.stdin.readline().strip().split())
sys.stdout.write('{}'.format(solve(A,B,C)))