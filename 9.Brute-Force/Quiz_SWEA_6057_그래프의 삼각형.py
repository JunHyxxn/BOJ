from itertools import combinations


def minus(number):
    return int(number) - 1
# def combination(c, k, cnt = 0):
#     res = 0
#     if cnt >= k:
#         x, y, z = c
#         if adjacent[x][y] and adjacent[y][z] and adjacent[z][x]:
#             res += 1
#         return res
#     for i in range(N):
#         if visited[i] or c[cnt-1] > i: continue
#         visited[i] = True
#         c[cnt] = i
#         res += combination(c, k, cnt +1)
#         visited[i] = False
#         c[cnt] = 0
#
#     return res

T = int(input())
for t in range(1, T+1):
    N, M = map(int, input().split())
    adjacent = [[False] * N for _ in range(N)]
    for _ in range(M):
        x, y = map(minus, input().split())
        adjacent[x][y] = True
        adjacent[y][x] = True

    # visited = [False]*N
    # res = combinations([0]*N, 3)
    # print("#{} {}".format(t, res))

    comb = combinations([i for i in range(N)], 3)
    res = 0
    for c in comb:
        x,y,z  = c
        if adjacent[x][y] and adjacent[y][z] and adjacent[z][x]:
            res += 1
    print("#{} {}".format(t, res))