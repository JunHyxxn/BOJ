"""
정사각형을 찾는 방법으로는 naive한 방법이 있고 이를 2967번에 이용했다.
본 문제에서는 DP로 접근하기 어려워 naive한 방법을 적용했지만,

일반적인 문제에서는 DP가 훨씬 효율적이다.

DP Method

*  *
*  *        편의상 1,1  1,2  2,1  2,2 로 표현한다

2,2 를 구하기 위해선 1,1    1,2     2,1 세 칸의 최소값에 +1을 해주면 된다.
=> 이렇게 구할 수 있는 것은 세칸이 모두 정사각형을 만들 수 있어야 2,2도 정사각형이 되기 떄문이다.
"""

def solution(board):
    answer = 1234
    dp = [[0]*(len(board[0])+1) for _ in range(len(board))] 
    dp = [[0] * (len(board[0])+1)] + dp
    
    for i in range(1, len(board)+1):
        for j in range(1, len(board[0])+1):
            if board[i-1][j-1]:
                dp[i][j] = min([dp[i-1][j], dp[i][j-1], dp[i-1][j-1]]) + 1
                
    answer = max(map(max, dp))**2
    return answer