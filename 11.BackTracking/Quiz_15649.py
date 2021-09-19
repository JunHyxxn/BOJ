# # import itertools
# # import sys

# # n,m = map(int, sys.stdin.readline().split())
# # nPm = list(itertools.permutations([i for i in range(1,n+1)],m))


# # for li in nPm:
# #     for i in li:
# #         print(i, end=" ")
# #     print()




# import sys
# import itertools

# N, M = map(int, sys.stdin.readline().strip().split())
# l = [i for i in range(1,N+1)]
# nPm = itertools.permutations(l, M)
# for data in nPm:
#     for d in data:
#         sys.stdout.write('{} '.format(d))
#     sys.stdout.write('\n')



## Ver. BackTracking
import sys
N, M = map(int, sys.stdin.readline().strip().split())
arr = [0 for _ in range(M)]
isused = [False for _ in range(N+1)]

def permutation(k):
    if k==M:
        for i in range(M):
            sys.stdout.write('{} '.format(arr[i]))
        sys.stdout.write('\n')
        return

    for j in range(1, N+1):
        if isused[j] is False:
            arr[k] = j
            isused[j] = True
            permutation(k+1)
            isused[j] = False

permutation(0)