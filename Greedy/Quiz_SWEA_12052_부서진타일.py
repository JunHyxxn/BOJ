"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AXmwOSJaSNIDFARX&categoryId=AXmwOSJaSNIDFARX&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=3
※ SW expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.

N X M 격자에 일부 타일이 부서졌다.
당신은 이 부서진 타일을 새로운 2 X 2 격자 크기의 타일로 대체하려고 한다. 
이 때 대체하는 타일은 원래 부서진 타일이 있던 위치에 올바른 방향으로 (기울여서는 안된다) 배치할 것이며, 부서지지 않은 타일을 덮으면 안된다. 
이러한 대체가 가능할까?

[입력]
첫 번째 줄에 테스트 케이스의 수 TC가 주어진다. 이후 TC개의 테스트 케이스가 새 줄로 구분되어 주어진다. 
각 테스트 케이스는 다음과 같이 구성되었다.
    ∙ 첫 번째 줄에 정수 N, M이 주어진다. (1 ≤ N, M ≤ 50)
    ∙ 이후 N개의 줄에 타일의 상태를 나타내는 길이 M의 문자열이 주어진다. 부서진 타일이면 ‘#’, 정상 타일이면 ‘.’ 로 표시된다.

[출력]
각 테스트 케이스 마다 한 줄씩, 가능하다면 “YES”, 불가능하다면 “NO” 를 출력하라.

============================================================================================================================================================
2x2 정사각형을 몇 개 만들 수 있는지 count하면 된다.
"""

T = int(input())

results = []

for t in range(1, T+1):
    N, M = map(int, input().split())
    board = [['.'] + list(map(str, input())) for _ in range(N)]
    board = [['.']*(M+1)] + board
    square = [[0]*(M+1) for _ in range(N+1)]
    total = 0
    square_tile = 0
    for i in range(1, N+1):
        for j in range(1, M+1):
            if board[i][j] == "#":
                total += 1
                square[i][j] = min(0 if square[i-1][j-1] == 2 else square[i-1][j-1], 
                                   0 if square[i][j-1] ==2 else square[i][j-1], 
                                   0 if square[i-1][j] ==2 else square[i-1][j]) + 1
                if square[i][j] == 2:
                    square_tile += 1

    if total == square_tile*4:
        results.append("#{} {}".format(t, "YES"))
    else:
        results.append("#{} {}".format(t, "NO"))
        
for res in results:
    print(res)