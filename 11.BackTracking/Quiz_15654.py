import sys

N, M = map(int, sys.stdin.readline().strip().split())

isused = [False for _ in range(N+1)]
arr = list(map(int, sys.stdin.readline().strip().split()))
assert N == len(arr)
arr = sorted(arr)
out = [0 for _ in range(M)]
def permutation(k):
    if k == M:
        for i in range(M):
            sys.stdout.write('{} '.format(out[i]))
        sys.stdout.write('\n')
        return

    for i in range(0, N):
        if isused[i] is False:
            out[k] = arr[i]
            isused[i] = True
            permutation(k+1)
            isused[i] = False

permutation(0)