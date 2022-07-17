"""
SWEA 1959 - 두 개의 숫자열

더 짧은 문자열을 A라 기준잡는다.

긴 문자열을 기준 점으로 짧은 문자열과 대응시켜 값을 전부 계산한다.

Brute-Force 로 해결한다.

N <= 15, M <= N
O(N*M) Time 에 해결가능하다.
"""
T = int(input())

result = []
for t in range(1,T+1):
    N, M = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    if N > M:
        A, B = B, A
        N, M = M, N
    res = float("-inf")
    for i in range(0, M-N+1):
        temp = 0
        for j in range(N):
            temp += (A[j] * B[j+i])
        res = temp if temp > res else res

    result.append("#{} {}".format(t, res))

for r in result:
    print(r)