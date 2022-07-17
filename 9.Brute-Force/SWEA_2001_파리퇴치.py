"""
SWEA 2001 - 파리 퇴치

NxN , MxM 
N <= 15
M <= N

N^2 * M^2 => N^4 = 15^4 = 50625
TC = 10

따라서 506250 => 30초 안에 충분히 해결 가능하다.
Brute-Force로 해결해도 무방하다.

"""

T = int(input())

for t in range(1, T+1):
    N, M = map(int, input().split())
    fly = [list(map(int, input().split())) for _ in range(N)]
    
    res = 0
    for i in range(N-M+1):
        for j in range(N-M+1):
            temp = 0
            for k in range(M):
                for l in range(M):
                    temp += fly[i+k][j+l]
            res = temp if temp > res else res

    print("#{} {}".format(t, res))