# ## Ver 1 - Permutation application
# import sys
# import itertools

# N, M = map(int, sys.stdin.readline().strip().split())

# l = [i for i in range(1,N+1)]
# sol = []
# for it in itertools.permutations(l, M):
#     it = sorted(it)
#     if it not in sol:
#         for d in it:
#             sys.stdout.write('{} '.format(d))
#         sol.append(it)
#         sys.stdout.write('\n')


# ## Ver2 - itertools's Combination
# import sys
# import itertools

# N,M = map(int, sys.stdin.readline().strip().split())
# comb = itertools.combinations(range(1, N+1), M)
# for data in comb:
#     sys.stdout.write('{}\n'.format(' '.join(map(str, data))))




## Ver 3 - Back Tracking Method
import sys

N, M = map(int, sys.stdin.readline().strip().split())
arr = [0 for _ in range(M+1)]
isused = [False for _ in range(N+1)]
def combination(k):
    if k > M:
        for i in range(1, M+1):
            sys.stdout.write('{} '.format(arr[i]))
        sys.stdout.write('\n')
        return

    for j in range(1, N+1):
        if isused[j] is False and arr[k-1] < j:
            arr[k] = j
            isused[j] = True
            combination(k+1)
            isused[j] = False

combination(1)