"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do

※ SW expert 아카데미의 문제를 무단 복제하는 것을 금지합니다. 

틱택톰은 틱택토를 변형한 게임으로, 4 x 4 게임판에서 진행한다. 
처음에 판에는 최대 하나의 ‘T’ 가 16개의 격자 중 하나에 있고, 나머지 격자 칸은 비어 있다. 두 플레이어 X, O 가 게임을 한다.
처음에는 X가 진행하며, 이후 두 사람은 번갈아서 턴을 바꾸며 게임을 진행한다. 
각 턴에 플레이어는 빈 칸에 하나의 말을 놓는다. 
X가 놓은 말에는 ‘X’, O가 놓은 말에는 ‘O’ 가 적혀 있다.  

턴이 끝난 이후, 어떠한 행, 열, 그리고 두 대각선 중 하나가 4개의 같은 말을 포함하거나, 3개의 같은 말과 ‘T’ 를 포함하면, 해당 말의 주인이 이기고 게임이 끝난다. 
그렇지 않은 경우 게임은 계속된다. 모든 칸이 다 찼는데 게임이 끝나지 않았다면, 동점으로 게임이 끝난다.

'X', 'O', 'T' 와 '.' 를 포함하는 4 x 4 판이 주어진다 ('.' 는 빈 칸을 뜻함).
이 판은 게임의 현재 상태를 표현한다. 
이를 토대로 현재 틱택톰 게임이 어떻게 진행되어 있는지 판단하라. 결과는 다음 4가지 중 하나이다.

∙ “X won” (게임이 끝났고 X가 이김)

∙ "O won" (게임이 끝났고 O가 이김)

∙ "Draw” (게임이 끝났고 동점)

∙ "Game has not completed" (게임이 안 끝남)

[입력]

첫 번째 줄에 테스트 케이스의 수 TC가 주어진다. 이후 TC개의 테스트 케이스가 새 줄로 구분되어 주어진다. 각 테스트 케이스는 다음과 같이 구성되었다.

∙ 4개의 줄에 'X', 'O', 'T' 와 '.' 를 포함하는 4 x 4 판이 주어진다.

∙ 정상적인 게임의 진행 도중에 나온 입력임이 보장된다. 고로 위 4개의 결과가 모호하지 않게 판단될 수 있다.

[출력]

각 테스트 케이스마다 결과를 출력하라.
============================================================================================================================================================

X와 O의 개수를 통해 누구의 turn인지 판단하고 
게임 결과를 판단해 결과로 출력한다.
"""
T = int(input())

results = []
for t in range(1, T+1):
    tictactom = []
    for i in range(5):
        if t == T and i ==4: break
        if i == 4: 
            input()
            continue
        tictactom.append( ['.']+list(map(str, input())) + ['.'])
    tictactom = [['.']*6] + tictactom

    X, O, pos_continue = 0, 0, 0
    for i in range(1, 5):
        for j in range(1, 5):
            if tictactom[i][j] == "X":
                X += 1
            if tictactom[i][j] == "O":
                O += 1
            if tictactom[i][j] == '.':
                pos_continue += 1
    turn = "X" if X > O else "O"
    
    score = [[[0]*6 for _ in range(5)] for _ in range(4)]
    isFinish = False
    for i in range(1, 5):
        if isFinish: break
        for j in range(1, 5):
            if tictactom[i][j] == turn or tictactom[i][j] == "T":
                ## 가로
                score[0][i][j] = score[0][i][j-1] + 1
                ## 세로
                score[1][i][j] = score[1][i-1][j] + 1
                ## 우하향 대각선
                score[2][i][j] = score[2][i-1][j-1] + 1
                ## 우상향 대각선
                score[3][i][j] = score[3][i-1][j+1] + 1

            if score[0][i][j] == 4 or score[1][i][j] == 4 or score[2][i][j] == 4 or score[3][i][j] == 4:
                isFinish = True
                break
    
    if isFinish:
        results.append("#{} {} won".format(t, turn))
    else:
        if pos_continue:
            results.append("#{} Game has not completed".format(t))
        else:
            results.append("#{} Draw".format(t))

for res in results:
    print(res)
    
