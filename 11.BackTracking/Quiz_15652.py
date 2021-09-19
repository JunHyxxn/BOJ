import sys

N, M = map(int, sys.stdin.readline().strip().split())

arr = [i for i in range(1, N+1)]
out = [0 for _ in range(M+1)]

def over_com(k):
    if k > M:
        for i in range(M):
            sys.stdout.write('{} '.format(out[i+1]))
        sys.stdout.write('\n')
        return

    for i in range(N):
        if out[k-1] <= arr[i]:
            out[k] = arr[i]
            over_com(k+1)

over_com(1)