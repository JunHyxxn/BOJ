from copy import deepcopy


def combination(arr, k, cnt=0):
    if cnt >= k:
        comb.append(deepcopy(arr))
        return

    for i in range(n):
        if visited[i] or arr[cnt-1] > i: continue
        visited[i] = True
        arr[cnt] = i
        combination(arr, k, cnt+1)
        arr[cnt] = 0
        visited[i] = False
    return


n, k = 5,3
visited = [False] * n
comb = []
combination([0]*k, k)
print(comb)