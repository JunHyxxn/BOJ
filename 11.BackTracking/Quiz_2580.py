## Ver 1.0
""" 
Fail
"""
# import sys

# ## 접근은 나쁘지 않은듯 한데 아무래도 한바퀴 먼저 다 돌고 row, col, mat을 채운 후 해야할듯.
# board = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(9)]

# ## 편의를 위해 row, col 를 i,j 로 설명한다.
# ## 각 row에서 포함된 숫자를 체크한다.
# row = [[0 for _ in range(9)] for _ in range(9)] ## board판의 i가 이 matrix의 row index가 된다. 
# ## 각 col에서 포함된 숫자를 체크한다.
# col = [[0 for _ in range(9)] for _ in range(9)] ## board판의 j가 인 경우 이 matrix의 row index가 된다.
# ## 3x3 판에 포함된 숫자를 체크한다.
# mat = [[0 for _ in range(9)] for _ in range(9)] ## board판의 3*(i//3) + j//3 가 이 matrix의 row index가 된다. => 3x3 부분 체크


# for i in range(9):
#     for j in range(9):
#         if board[i][j]: ## 이미 채워져있는 경우 row, col, 3x3 matrix에 체크해준다.
#             row[i][board[i][j]-1] = 1
#             col[j][board[i][j]-1] = 1
#             mat[3*(i//3)+j//3][board[i][j]-1] = 1

# def solve(n):
#     global board
#     ## Base Condition
#     if n == 9:
#         return
#     ## 현재 row에서 먼저 가능한 후보를 탐색한다.
#     cur_row = [i for i in range(1, 9+1)]
#     for i in range(9):
#         if board[n][i]:
#             cur_row.remove(board[n][i])

#     for i in range(9):
#         if board[n][i]:
#             continue
#         for candidate in cur_row: ## 비어있는 칸은 채워야하는데 뽑은 후보군을 이용한다.
#             if row[n][candidate-1] | col[i][candidate-1] | mat[3*(n//3)+i//3][candidate-1]: ## 이미 들어간 숫자면 넘어간다
#                 continue
#             ## DFS 진행
#             board[n][i] = candidate
#             row[n][board[n][i]-1] = 1
#             col[i][board[n][i]-1] = 1
#             mat[3*(n//3)+i//3][board[n][i]-1] = 1
#             solve(n+1)
#             row[n][board[n][i]-1] = 0
#             col[i][board[n][i]-1] = 0
#             mat[3*(n//3)+i//3][board[n][i]-1] = 0


# solve(0)

# for i in range(9):
#     sys.stdout.write(' '.join(map(str, board[i]))+'\n')


## - Ver2.0 
""" 
ver1에서는 solve 함수를 재귀적으로 처리할때 solve(n+1)이라면 바로 다음 행으로 가게되는데 
이렇게 되면 행 안에 여러 빈칸이 있는 경우 제대로 처리 하지 못한다.
따라서 재귀를 비어있는 칸들을 진행하도록 수정해주었다.
"""
import sys

## 접근은 나쁘지 않은듯 한데 아무래도 한바퀴 먼저 다 돌고 row, col, mat을 채운 후 해야할듯.
board = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(9)]

## 편의를 위해 row, col 를 i,j 로 설명한다.
## 각 row에서 포함된 숫자를 체크한다.
row = [[0 for _ in range(9)] for _ in range(9)] ## board판의 i가 이 matrix의 row index가 된다. 
## 각 col에서 포함된 숫자를 체크한다.
col = [[0 for _ in range(9)] for _ in range(9)] ## board판의 j가 인 경우 이 matrix의 row index가 된다.
## 3x3 판에 포함된 숫자를 체크한다.
mat = [[0 for _ in range(9)] for _ in range(9)] ## board판의 3*(i//3) + j//3 가 이 matrix의 row index가 된다. => 3x3 부분 체크


for i in range(9):
    for j in range(9):
        if board[i][j]: ## 이미 채워져있는 경우 row, col, 3x3 matrix에 체크해준다.
            row[i][board[i][j]-1] = 1
            col[j][board[i][j]-1] = 1
            mat[3*(i//3)+j//3][board[i][j]-1] = 1

empty = [(x, y) for x in range(9) for y in range(9) if board[x][y] == 0]
flag = False
def solve(n):
    global board, flag
    ## Base Condition
    if flag:
        return
    if n == len(empty):
        flag = True
        for i in range(9):
            sys.stdout.write(' '.join(map(str, board[i]))+'\n')
        return

    x, y = empty[n]
    for i in range(1, 9+1):
        if row[x][i-1] | col[y][i-1] | mat[3*(x//3)+y//3][i-1]: ## 가지치기 
            continue
        ## DFS
        board[x][y] = i
        row[x][i-1] = 1
        col[y][i-1] = 1
        mat[3*(x//3)+y//3][i-1] = 1
        solve(n+1)
        board[x][y] = 0
        row[x][i-1] = 0
        col[y][i-1] = 0
        mat[3*(x//3)+y//3][i-1] = 0

solve(0)
