"""
============================================================================================================================================
Key Point : BP인데 대각 부호는 자기 자신의 부호를 나타내기 때문에 이를 이용하면 수를 좀 줄일 수 있다.
+a) check문과 재귀를 if문 의 조건으로 하는 색다른 방식.
"""
def check(idx):
    for i in range(idx, -1, -1):
        sum_num = sum(result[i:idx+1])
        if sum_num == 0 and S[i][idx] != 0:
            return False
        elif sum_num >0 and S[i][idx] <= 0:
            return False
        elif sum_num <0 and S[i][idx] >= 0:
            return False
    return True

def solve(idx):
    if idx == N:
        return True
    if S[idx][idx] == 0:
        result[idx] =0
        return solve(idx+1)
    else:
        for i in range(1,11):
            result[idx] = S[idx][idx] * i
            if check(idx) and solve(idx+1):
                return True
    # return True

N = int(input())
string = input()
S = [[float('INF')]*N for _ in range(N)]
cnt = 0
for i in range(N):
    for j in range(i, N):
        temp = string[cnt]
        if temp =='+':
            S[i][j] = 1
        elif temp =='-':
            S[i][j] = -1
        else:
            S[i][j] = 0
        cnt += 1

result = [float('INF')]*N


solve(0)

print(' '.join(map(str, result)))