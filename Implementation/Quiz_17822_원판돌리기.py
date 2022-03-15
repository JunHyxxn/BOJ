def check():
    re_candidate = set()
    for i in range(1, n+1):
        for j in range(1, m+1):
            if j == 1:
                search_cand = [[i-1, j], [i+1,j], [i,j+1], [i,m]]
                for s in search_cand:
                    x, y = s
                    if board[i][j] == board[x][y] and board[i][j] != -1: re_candidate.add((i,j))
            elif j == m:
                search_cand = [[i-1, j], [i+1,j], [i,j-1], [i,1]]
                for s in search_cand:
                    x, y = s
                    if board[i][j] == board[x][y] and board[i][j] != -1: re_candidate.add((i,j))
            else:
                search_cand = [[i-1, j], [i+1,j], [i,j+1], [i,j-1]]
                for s in search_cand:
                    x, y = s
                    if board[i][j] == board[x][y] and board[i][j] != -1: re_candidate.add((i,j))
    
    return re_candidate
def calc():
    total = 0
    cnt = 0
    for i in range(1,n+1):
        for j in range(1, m+1):
            if board[i][j] != -1:
                cnt += 1
                total += board[i][j]
    if cnt: return float(total/cnt)
    return -1

def replace(average):
    global board
    for i in range(1,n+1):
        for j in range(1, m+1):
            if board[i][j] > average and board[i][j] != -1:
                board[i][j] -= 1
            elif board[i][j] < average and board[i][j] != -1:
                board[i][j] += 1

def Rotate(multiple, mode, nums):
    global board
    pans = [i for i in range(multiple, (n+1), multiple)]
    if mode == 0: ## 시계 방향
        for p in pans:
            temp = board[p][(-1*nums)-1:-1]
            board[p] = [board[p][0]] + temp + board[p][1:m-nums+1] + [board[p][-1]]        
            
    elif mode == 1: ## 반시계 방향
        for p in pans:
            temp = board[p][1:1+nums]
            board[p] = [board[p][0]] + board[p][nums+1:-1] + temp + [board[p][-1]]
            
    
    remove_candidate = check()
    if remove_candidate:
        for rm in remove_candidate:
            x,y = rm
            board[x][y] = -1
        del remove_candidate
    else:
        average = calc()
        if average != -1:
            replace(average)
 
n,m,t = map(int, input().split())
board = [[-1]*(m+2) for _ in range(n+2)]
rotates = []
for i in range(1, n+1):
    board[i][1:-1] = list(map(int, input().split()))
for _ in range(t):
    rotates.append(list(map(int, input().split())))
    
for r in rotates:
    mul, md, k = r
    Rotate(mul, md, k)

result = 0
for i in range(1,n+1):
    for j in range(1,m+1):
        if board[i][j] != -1: 
            result += board[i][j]
print(int(result))