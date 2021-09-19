import sys

N, M = map(int, sys.stdin.readline().strip().split())

arr = sorted(set(list(map(int, sys.stdin.readline().strip().split()))))

out = [0 for _ in range(M+1)]


def solve(k):
    if k == M:
        print(' '.join(map(str, out[1:])), end='\n')
        return
    
    for i in range(len(arr)):
        if out[k] <= arr[i]:
            out[k+1] = arr[i]
            solve(k+1)

solve(0)