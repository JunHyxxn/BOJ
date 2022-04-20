"""
개인 학습용 코드!!

문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AX8BAN1qTwoDFARO&categoryId=AX8BAN1qTwoDFARO&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

※ SW expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.

N×N 크기의 격자판이 있다. 각각의 격자는 비어 있거나(‘.’), 막혀 있다(‘#’). 
이때, 막혀 있는 칸들이 하나의 정사각형을 이루는지를 판단하는 프로그램을 작성하라.

[입력]
첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 격자판의 크기 N (1≤N≤20 이 주어진다. 
다음 N개의 줄은 격자판의 배치를 나타내며, 각 줄에는 ‘.’ 또는 ‘#’로만 이루어진 길이가 N인 문자열이 주어진다. 
모든 격자판에는 최소 1개 이상의 ‘#’ 칸이 있음이 보장된다.

[출력]
각 테스트 케이스마다 격자판의 막혀 있는 칸들이 하나의 정사각형을 이루면 ‘yes’를, 그렇지 않다면 ‘no’를 출력한다.
"""
t = int(input())
for test_case in range(1, t+1):
    n = int(input())
    board = [['.'] + list(map(str, input())) for _ in range(n)]
    board = [['.'] * (n+1)] + board
    dp = [[0] * (n+1) for _ in range(n+1)]
    
    isFirst = True
    last_col = -1
    last_row = -1
    for i in range(1,n+1):
        for j in range(1,n+1):
            if board[i][j] == '#':
                last_col = max(last_col,j)
                last_row = max(last_row, i)
                if isFirst:
                    location = [i,j]
                    isFirst = False
                dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
    
    x, y = location
    square_len = max((last_col - y + 1), (last_row - x + 1))    
    
    value = max(map(max, dp))
    if value != square_len:
        print('#{} {}'.format(test_case, "no"))
    else:
        print('#{} {}'.format(test_case, "yes"))