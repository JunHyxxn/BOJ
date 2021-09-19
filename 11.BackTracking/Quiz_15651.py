import sys

N, M = map(int, sys.stdin.readline().strip().split())

arr = [i for i in range(1, N+1)]
out = [0 for _ in range(M)]

def overlap(k):
    if k == M:
        for i in range(M):
            sys.stdout.write('{} '.format(out[i]))
        sys.stdout.write('\n')
        return
    for i in range(N):
        out[k] = arr[i]
        overlap(k+1)


overlap(0)