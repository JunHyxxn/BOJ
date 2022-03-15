"""
미해결 - IndexError
"""

green = [[0]*4 for _ in range(6)]
blue = [[0]*4 for _ in range(6)]

def t1_down(t, r, c, num):
    i = 0
    while True:
        if i>=6 or green[i][c]:
            green[i-1][c] = (t, num)
            break
        i+=1
    
    i = 0
    while True:
        if i>=6 or blue[i][r]:
            blue[i-1][r] = (t, num)
            break
        i += 1
    
def t2_down(t, r, c, num):
    c1, c2 = c, c+1
    i = 0
    while True:
        if i>=6 or green[i][c1] or green[i][c2]:
            green[i-1][c1] = (t, num)
            green[i-1][c2] = (t, num)
            break
        i+=1
    
    
    c = r
    i = 1
    while True:
        if i>= 6 or blue[i][c] or blue[i-1][c]:
            blue[i-1][c] = (3, num, 0) ## bottom block
            blue[i-2][c] = (3, num, 1) ## top block
            break
        i += 1
        
def t3_down(t, r, c, num):
    c1, c2 = r, r+1
    i = 0
    while True:
        if i>=6 or blue[i][c1] or blue[i][c2]:
            blue[i-1][c1] = (2, num)
            blue[i-1][c2] = (2, num)
            break
        i+=1
    
    
    
    i = 1
    while True:
        if i>= 6 or green[i][c] or green[i-1][c]:
            green[i-1][c] = (t, num, 0) ## bottom block
            green[i-2][c] = (t, num, 1) ## top block
            break
        i += 1
        
## 연한 부분에 블럭이 있다면 그 행 만큼 아래 행을 제거.
def over_row():
    global green, blue
    ## green
    green_cnt = 0
    for i in range(2):
        for c in range(4):
            if green[i][c] != 0:
                green_cnt += 1
                break
    green = [[0]*4 for _ in range(green_cnt)] + green[:6-green_cnt]
    if green_cnt == 1:
        for c in range(4):
            if green[5][c] != 0 and (green[5][c][0] == 3 and green[5][c][2] == 1):
                green[5][c] = (1, green[5][c][1])
    
    
    ## blue
    blue_cnt = 0
    for i in range(2):
        for c in range(4):
            if blue[i][c] != 0:
                blue_cnt += 1
                break
    blue = [[0]*4 for _ in range(blue_cnt)] + blue[:6-blue_cnt]
    if blue_cnt == 1:
        for c in range(4):
            if blue[5][c] != 0 and (blue[5][c][0] == 3 and blue[5][c][2] ==1):
                blue[5][c] = (1, blue[5][c][1])
    
def isFull(board, row):
    cnt = 0
    for c in range(4):
        if board[row][c] != 0: cnt += 1
    
    return True if cnt == 4 else False
def shift(board, row):
    visited = [[False]*4 for _ in range(6)]
    
    for r in range(row-1, -1, -1):
        for c in range(4):
            if not visited[r][c] and board[r][c] != 0:
                if board[r][c][0] == 1:
                    visited[r][c] = True
                    nxt_r = row
                    while True:
                        if  nxt_r >= 6 or board[nxt_r][c] != 0:
                            board[nxt_r-1][c] = board[r][c]
                            board[r][c] = 0
                            break
                        nxt_r += 1
                elif board[r][c][0] == 2:
                    visited[r][c] = True
                    visited[r][c+1] = True 
                    nxt_r = row
                    while True:
                        if nxt_r >= 6 or (board[nxt_r][c] != 0 or board[nxt_r][c+1] != 0):
                            board[nxt_r-1][c] = board[r][c]
                            board[nxt_r-1][c+1] = board[r][c+1]
                            board[r][c], board[r][c+1] = 0, 0
                            break
                        nxt_r += 1
                elif board[r][c][0] == 3:
                    visited[r][c] = True 
                    visited[r-1][c] = True
                    nxt_r = row
                    while True:
                        if nxt_r >= 6 or board[nxt_r][c] != 0:
                            board[nxt_r-1][c] = board[r][c]
                            board[nxt_r-2][c] = board[r-1][c]
                            if nxt_r - r - 1 >= 2:
                                board[r][c] = 0
                                board[r-1][c] = 0
                            else:
                                board[r-1][c] = 0
                            break
                        nxt_r += 1
    return board

def row_full_check():
    global green, blue, cnt
    ## green
    i = 5
    while True:
        if i < 0:
            break
        if isFull(green, i):
            for c in range(4):
                if green[i][c][0] == 1 or green[i][c][0] == 2:
                    green[i][c] = 0
                elif green[i][c][0] == 3:
                    if green[i][c][2] == 0: ## bottom block
                        green[i-1][c] = (1, green[i-1][c][1])
                    elif green[i][c][2] ==1: ## top block
                        green[i+1][c] = (1, green[i+1][c][1])
                    green[i][c] = 0
            green = shift(green, i)
            cnt += 1
            i = 5
        else:
            i -= 1
            
    ##blue  
    i = 5
    while True:
        if i < 0:
            break
        if isFull(blue, i):
            for c in range(4):
                if blue[i][c][0] == 1 or blue[i][c][0] == 2:
                    blue[i][c] = 0
                elif blue[i][c][0] == 3:
                    if blue[i][c][2] == 0: ## bottom block
                        blue[i-1][c] = (1, blue[i-1][c][1])
                    elif blue[i][c][2] ==1: ## top block
                        blue[i+1][c] = (1, blue[i+1][c][1])
                    blue[i][c] = 0
            blue = shift(blue, i)
            cnt += 1
            i = 5
        else:
            i -= 1
            
        

n = int(input())
cnt = 0
for i in range(1,n+1):
    t, x, y = map(int, input().split())
    if t == 1:
        t1_down(t, x, y, i)
    elif t ==2:
        t2_down(t, x, y, i)
    elif t == 3:
        t3_down(t, x, y, i)
    
    row_full_check()
    over_row()
    
    
    
total = 0
for r in range(6):
    for c in range(4):
        if green[r][c] != 0: total +=1
        if blue[r][c] != 0: total +=1

# print("\n\nFinish Green")
# pprint(green)
# print("Finish Blue")
# pprint(blue)
print(cnt)
print(total)

"""
18
1 2 2
1 2 3
2 0 0
1 2 0
1 1 2
1 1 0
2 3 0
3 0 1
3 1 3
2 1 0
1 2 0
2 3 0
2 2 1
1 2 2
3 0 3
1 2 0
2 2 0
3 2 3


6
1 1 0
2 1 0
3 1 2
3 0 1
3 0 2
3 0 3


6
1 0 0
1 0 2
2 0 0
3 0 2
2 0 1
3 0 3


9
1 1 1
2 3 0
3 2 2
3 2 3
3 1 3
2 0 0
1 0 0
2 0 0
3 1 2


7
1 0 1
3 0 1
1 0 2
2 2 0
2 2 1
3 0 3
2 3 0


10
2 2 1
2 1 1
1 2 3
3 2 3
1 0 0
3 0 3
3 1 3
1 2 3
1 3 3
2 1 2
"""