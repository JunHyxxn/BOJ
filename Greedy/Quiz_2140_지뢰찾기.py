"""
문제
지뢰찾기는 N×N에서 이뤄지는 게임이다. 보드의 곳곳에는 몇 개의 지뢰가 숨겨져 있고, 
지뢰가 없는 칸에는 그 칸과 인접(상하좌우 및 대각선)해 있는 8개의 칸들에 몇 개의 지뢰가 숨겨져 있는지에 대한 정보가 주어진다.
게이머는 게임을 진행하면서 보드의 칸을 하나씩 열게 된다. 
만약 그 칸에 지뢰가 있다면 게임이 끝나고, 없는 경우에는 그 칸에 적혀있는 숫자, 즉 그 칸과 인접해 있는 8개의 칸들 중 몇 개의 칸에 지뢰가 있는지를 알 수 있게 된다.

이 문제는 보드의 테두리가 모두 열려있고, 그 외는 모두 닫혀있는 상태에서 시작한다. 예를 들어 다음과 같은 경우를 보자.

1	1	1	0	0
2	#	#	#	1
3	#	#	#	1
2	#	#	#	1
1	2	2	1	0
#는 닫혀있는 칸을 나타낸다. 이러한 보드가 주어졌을 때, 닫혀있는 칸들 중 최대 몇 개의 칸에 지뢰가 묻혀있는지 알아내는 프로그램을 작성하시오. 
# 위의 예와 같은 경우에는 다음과 같이 6개의 지뢰가 묻혀있을 수 있다.

1	1	1	0	0
2	*	 	 	1
3	*	*	*	1
2	*	*	 	1
1	2	2	1	0
입력
첫째 줄에 N(1≤N≤100)이 주어진다. 다음 N개의 줄에는 N개의 문자가 공백 없이 주어지는데, 이는 게임 보드를 의미한다.

출력
첫째 줄에 묻혀있을 수 있는 지뢰의 최대 개수를 출력한다.

========================================================================================================================================

우선 크기가 100 x 100 으로 크지 않다. 정석적인 방법으로 접근해도 무방할듯 하다.

실제로 지뢰찾기를 할 때 어떻게 플레이하는지 생각해본다면 확실한 위치부터 체크하고 넘어간다. 
하지만 컴퓨터의 경우는 0인 경우를 모두 체크하고 1인 경우를 모두 체크하면서 진행하게 되면 경우의 수가 나눠져 오히려 처리하기 더 복잡해지는 경우가 발생한다.
따라서 한 줄씩 순서대로 판단할 수 있도록 구현해준다.

예를 들어 맨 윗줄을 판단한다면, 0,0 을 통해 1,1의 위치는 반드시 파악할 수 있게 되고,
0,1 을 통해서 1,1의 정보는 알고 있기 때문에 1,2 만 추론하면 되기에 반드시 파악할 수 있게 되고,
0,2의 정보를 통해서 1,1 과 1,2 까지 알고 있기 때문에 1,3 만 추론하면 된다. 
이런 식으로 순서대로 추론할 경우 컴퓨터도 쉽게 파악할 수 있어진다.

4개의 변을 모두 판단하고 나면 안쪽의 #칸은 모두 지뢰라고 가정하고 출력하면 정답이다.
"""

N = int(input())
board = [list(map(str, input())) for i in range(N)]
cnt = 0

def top_row_check():
    global cnt    
    d = [[1,-1],[1,0],[1,1]]
    
    for i in range(N):
        num = int(board[0][i])
        for dd in d:
            
            dx, dy = dd
            nx, ny = 0+dx, i+dy
            if 0<nx<N-1 and 0< ny < N-1:
                if board[nx][ny] == "#" and num != 0:
                    board[nx][ny] = "*"
                    cnt += 1
                    num -= 1
                elif board[nx][ny] == "#" and num == 0:
                    board[nx][ny] = " "
                elif board[nx][ny] == "*":
                    num -= 1
    return board
                    
def left_col_check():
    global cnt
    d = [[-1,1],[0,1],[1,1]]
    
    for i in range(N):
        num = int(board[i][0])
        for dd in d:
            
            dx, dy = dd
            nx, ny = i+dx, 0+dy
            if 0<nx<N-1 and 0< ny < N-1:
                if board[nx][ny] == "#" and num != 0:
                    board[nx][ny] = "*"
                    cnt += 1
                    num -= 1
                elif board[nx][ny] == "#" and num == 0:
                    board[nx][ny] = " "
                elif board[nx][ny] == "*":
                    num -= 1
    return board
def bottom_row_check():
    global cnt
    d = [[-1,-1],[-1,0],[-1,1]]
    
    for i in range(N):
        num = int(board[N-1][i])
        for dd in d:
            
            dx, dy = dd
            nx, ny = N-1+dx, i+dy
            if 0<nx<N-1 and 0< ny < N-1:
                if board[nx][ny] == "#" and num != 0:
                    board[nx][ny] = "*"
                    cnt += 1
                    num -= 1
                elif board[nx][ny] == "#" and num == 0:
                    board[nx][ny] = " "
                elif board[nx][ny] == "*":
                    num -= 1
    return board
def right_col_check():
    global cnt
    d = [[-1,-1],[0,-1],[1,-1]]
    
    for i in range(N):
        num = int(board[i][N-1])
        for dd in d:
            
            dx, dy = dd
            nx, ny = i+dx, N-1+dy
            if 0<nx<N-1 and 0< ny < N-1:
                if board[nx][ny] == "#" and num != 0:
                    board[nx][ny] = "*"
                    cnt += 1
                    num -= 1
                elif board[nx][ny] == "#" and num == 0:
                    board[nx][ny] = " "
                elif board[nx][ny] == "*":
                    num -= 1
    return board

board = top_row_check()
board = left_col_check()
board = bottom_row_check()
board = right_col_check()

for i in range(2, N-2):
    for j in range(2, N-2):
        if board[i][j] == "#":
            cnt += 1
print(cnt)