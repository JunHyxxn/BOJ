def isFinish(board, turn):
    turn = 2 if turn == 1 else 1
    for i in range(3):
        if (board[i][0] == turn and board[i][1] == turn and board[i][2] == turn) or (board[0][i] == turn and board[1][i] == turn and board[2][i] == turn):
            return True
    
    
    if board[0][0] == turn and board[1][1] == turn and board[2][2] == turn:
        return True

    if board[0][2] == turn and board[1][1] == turn and board[2][0] == turn:
        return True
    
    return False
def Play(board, turn):
    res = float("INF")

    if isFinish(board, turn):
        return -1
    else:
        if sum(map(sum, board)) == 13:
            return 0

    
    for i in range(3):
        for j in range(3):
            if board[i][j] == 0:
                board[i][j] = turn
                res = min(res, Play(board, 2 if turn == 1 else 1))
                board[i][j] = 0
    
    if res == 2 or res == 0: 
        return 0
    else:
        return -1 * res
                

board = [list(map(int, input().split())) for _ in range(3)]
order = 0
for i in range(3):
    for j in range(3):
        if board[i][j]:
            order +=1
if order %2 == 0: order = 1
else: order = 2

result = Play(board, order)

if result == 1:
    print("W")
elif result == 0:
    print("D")
elif result == -1:
    print("L")
