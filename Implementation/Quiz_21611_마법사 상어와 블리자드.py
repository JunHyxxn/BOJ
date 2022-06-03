from collections import deque
def blizard(d, s):
    global board
    dx, dy = direction[d]
    cx, cy = N//2, N//2
    for _ in range(s):
        nx, ny = cx+dx, cy+dy
        board[nx][ny] = 0
        cx, cy = nx, ny
    

def bomb(arr):
    """
    동시에 터트린다.
    즉 배열을 전부 돌면서 터트릴 수 있는 범위 모두 터트리고 난 후,
    구슬을 당긴다.
    이 과정을 터진 구슬이 없을때까지 진행한다.
    """
    out = 0
    while True:
        isBomb = False
        stack = deque()
        prev = arr.popleft()
        stack.append(prev)
        count = 1
        while arr:
            now = arr.popleft()
            if prev == now:
                count += 1
                stack.append(now)
            else:
                if count < 4:
                    count = 1
                    stack.append(now)
                else:
                    out += (stack[-1] * count)
                    isBomb = True
                    while count:
                        stack.pop()
                        count -= 1
                    count = 1
                    stack.append(now)
            prev = now
        if not isBomb:
            break
        arr = stack
    ## 마지막에 나온 stack에는 처음부터 끝까지 같은 구슬일 경우가 존재한다.
    ## count > 3 이라면 터트릴 수 있기에 그만큼 터트려준다.
    if count > 3:
        out += (stack[-1] * count)
        while count:
            stack.popleft()
            count -= 1
    return out, stack

def refactor(stack):
    """
    stack에 들어있는 구슬을 같은 구슬끼리 list로 묶은 다음
    각 묶음에 들어있는 정보를 통해
    구슬 개수, 구슬 번호 를 차례로 return할 stack에 추가한다.
    이 때, N**2 - 1 개 까지만 담는다.
    
    부족하다면 0으로 채운다.
    """
    temp = deque()
    temp.append([stack.popleft()])
    
    for s in stack:
        if temp[-1][-1] == s:
            temp[-1].append(s)
        else:
            temp.append([s])
            
    stack = deque()
    for t in temp:
        if len(stack) >= N**2 -1:
            break
        stack.append(len(t))
        stack.append(t[0])

    if len(stack) < N**2-1:
        stack.extend([0]*(N**2 - len(stack)-1))
    return stack
    
## 1 : Up, 2 : Down, 3 : Left, 4 : Right
direction = {1:[-1,0],2:[1,0], 3:[0,-1], 4:[0,1]}
N,M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
result = 0
for _ in range(M):
    d, s = map(int, input().split())
    
    ## 블리자드 공격
    blizard(d, s)
    
    if (sum(map(sum, board))) == 0:
        continue
    
    ## 구슬 배열
    arr = deque()
    
    def check(x, y):
        if board[x][y]:
            arr.append(board[x][y])
    
    ## 보드판 순회 O(N*N)
    prev_x, prev_y = N//2, N//2
    for i in range(1, N//2+1):
        ## left 1회
        nx, ny = prev_x + direction[3][0], prev_y + direction[3][1]
        check(nx, ny)
        prev_x, prev_y = nx, ny
        ## down (2*i - 1) 회 진행
        for j in range(2*i-1):
            nx, ny = prev_x + direction[2][0], prev_y + direction[2][1]
            check(nx, ny)
            prev_x, prev_y = nx, ny

        ## right, up, left 각 2*i 회 진행
        for dir in [4,1,3]:
            for _ in range(2*i):
                nx, ny = prev_x + direction[dir][0], prev_y + direction[dir][1]
                check(nx, ny)
                prev_x, prev_y = nx, ny
    
    res, stack = bomb(arr)
    result += res ## score 갱신
    
    ## 배열 재정의
    if len(stack) == 0: ## 남은 구슬이 없다면 0으로 채운다.
        stack = deque([0]*(N**2-1))
    else: ## refactoring 과정을 거친다.
        stack = refactor(stack)
        
    ## board update
    prev_x, prev_y = N//2, N//2
    for i in range(1, N//2+1):
        ## left 1회
        nx, ny = prev_x + direction[3][0], prev_y + direction[3][1]
        board[nx][ny] = stack.popleft()
        prev_x, prev_y = nx, ny
        ## down (2*i - 1) 회 진행
        for j in range(2*i-1):
            nx, ny = prev_x + direction[2][0], prev_y + direction[2][1]
            board[nx][ny] = stack.popleft()
            prev_x, prev_y = nx, ny

        ## right, up, left 각 2*i 회 진행
        for dir in [4,1,3]:
            for _ in range(2*i):
                nx, ny = prev_x + direction[dir][0], prev_y + direction[dir][1]
                board[nx][ny] = stack.popleft()
                prev_x, prev_y = nx, ny
print(result)


