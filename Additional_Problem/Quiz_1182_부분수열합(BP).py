"""
BOJ 1182 - 부분수열의 합
재귀 & BackTracking
"""

N, S = map(int, input().split())
nums = list(map(int, input().split()))

cnt = 0

def solve(depth, total):
    global cnt
    if depth == N:
        if total == S:
            cnt +=1
            return 
        return 
    
    solve(depth+1, total) ## no add
    solve(depth+1, total + nums[depth]) ## add

    return 0
solve(0,0)
print(cnt-1 if S == 0 else cnt)


