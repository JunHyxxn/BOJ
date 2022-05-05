"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWQmA4uK8ygDFAXj&categoryId=AWQmA4uK8ygDFAXj&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=3&pageSize=10&pageIndex=6

-전형적인 Implementation 문제
"""
def outOfRange(point):
    global N
    if point < 0 or point >= N:
        return True

def othello(board, x, y):
    global N

    pattern = board[x][y]
    enemy = "W" if pattern == "B" else "B"
    ## 왼쪽
    i = 1
    isFinish = False
    left = []
    while not outOfRange(y-i) and board[x][y-i] == enemy:
        left.append([x,y-i])
        i += 1
        if outOfRange(y - i): break
        if board[x][y-i] == pattern:
            isFinish = True
            break
    if not isFinish: left = []
    
    
    ## 오른쪽
    i = 1
    isFinish = False
    right = []
    while not outOfRange(y+i) and board[x][y+i] == enemy:
        right.append([x,y+i])
        i += 1
        if outOfRange(y + i): break
        if board[x][y+i] == pattern:
            isFinish = True
            break
    if not isFinish: right = []
    
    
    ## 위쪽
    i = 1
    isFinish = False
    up = []
    while not outOfRange(x-i) and board[x-i][y] == enemy:
        up.append([x-i,y])
        i += 1
        if outOfRange(x - i): break
        if board[x-i][y] == pattern:
            isFinish = True
            break
    if not isFinish: up = []
    
    
    ## 아래
    i = 1
    isFinish = False
    down = []
    
    while not outOfRange(x+i) and board[x+i][y] == enemy:
        down.append([x+i,y])
        i += 1
        if outOfRange(x + i): break
        if board[x+i][y] == pattern:
            isFinish = True
            break
    if not isFinish: down = []
    
    ## left top
    i = 1
    isFinish = False
    lt = []
    while not outOfRange(x-i) and not outOfRange(y-i) and board[x-i][y-i] == enemy:
        lt.append([x-i,y-i])
        i += 1
        if outOfRange(x-i) or outOfRange(y-i): break
        if board[x-i][y-i] == pattern:
            isFinish = True
            break
    if not isFinish: lt = []
    
    ## right donw
    i = 1
    isFinish = False
    rd = []
    while not outOfRange(x+i) and not outOfRange(y+i) and board[x+i][y+i] == enemy:
        rd.append([x+i,y+i])
        i += 1
        if outOfRange(x+i) or outOfRange(y + i): break
        if board[x+i][y+i] == pattern:
            isFinish = True
            break
    if not isFinish: rd = []
    
    ## left down
    i = 1
    isFinish = False
    ld = []
    while not outOfRange(x+i) and not outOfRange(y-i) and board[x+i][y-i] == enemy:
        ld.append([x+i,y-i])
        i += 1
        if outOfRange(x+i) or outOfRange(y - i): break
        if board[x+i][y-i] == pattern:
            isFinish = True
            break
    if not isFinish: ld = []
    
    ## right top
    i = 1
    isFinish = False
    rt = []
    while not outOfRange(x-i) and not outOfRange(y+i) and board[x-i][y+i] == enemy:
        rt.append([x-i,y+i])
        i += 1
        if outOfRange(x - i) or outOfRange(y + i): break
        if board[x-i][y+i] == pattern:
            isFinish = True
            break
    if not isFinish: rt = []
    
    return left + right + up + down + lt+ rd+ ld+ rt

def catch(board, locations, turn):
    for loc in locations:
        x, y = loc
        board[x][y] = turn
        
    return board
    
T = int(input())
results = []
for t in range(1, T+1):
    N, M = map(int, input().split())
    board = [[''] * N for _ in range(N)]
    
    init_idx = N//2
    board[init_idx-1][init_idx-1] = "W"
    board[init_idx-1][init_idx] = "B"
    board[init_idx][init_idx-1] = "B"
    board[init_idx][init_idx] = "W"
    
    for _ in range(M):
        a, b, mode = map(int, input().split())
        turn = "B" if mode ==1 else "W"
        board[a-1][b-1] = turn
        change_stone = othello(board, a-1, b-1)
        
        if len(change_stone) !=0:
            catch(board, change_stone, turn)
            
    b_cnt = 0
    w_cnt = 0
    for i in range(N):
        for j in range(N):
            if board[i][j] == "B":
                b_cnt +=1
            elif board[i][j] == "W":
                w_cnt += 1

    results.append("#{} {} {}".format(t, b_cnt, w_cnt))
    
for res in results:
    print(res)


