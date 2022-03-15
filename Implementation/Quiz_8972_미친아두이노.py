from collections import defaultdict
def Move_user(user, action):
    global board
    if actions == 5: return user
    user_x, user_y = user
    dx, dy = directions[action]
    nx, ny = user_x +dx, user_y+dy
    if nx <0 or nx >= R: return -9
    if ny <0 or ny >= C: return -9
    if board[nx][ny] == "R": return -1
    board[nx][ny] = "I"
    board[user_x][user_y] = "."    
    return (nx,ny)

def Move_ardu(mads, user):
    global board
    user_x, user_y = user
    new_ardu = defaultdict(int)
    for ardu in mads:
        dx = False
        dy = False
        ardu_x, ardu_y = ardu
        board[ardu_x][ardu_y] = '.'
        if ardu_x > user_x:
            dx = -1
        elif ardu_x == user_x:
            dx = 0
        elif ardu_x < user_x:
            dx = 1
        
        if ardu_y > user_y:
            dy = -1
        elif ardu_y == user_y:
            dy = 0
        elif ardu_y < user_y:
            dy = 1
        
        nx, ny = ardu_x+dx, ardu_y+dy
        if (nx, ny) == user: return -1
        
        new_ardu[(nx, ny)] += 1
    
    result = []
    for k, v in new_ardu.items():
        if v >1: continue
        result.append(k)
        board[k[0]][k[1]] = "R"
    
    
    return result

R, C = map(int, input().split())
mads = []
user = None
board = [['.']*C for _ in range(R)]
for r in range(R):
    line = input()
    for c in range(C):
        board[r][c] = line[c]
        if line[c] == 'I':
            user = (r,c)
        elif line[c] == 'R':
            mads.append((r,c))
        
actions = list(map(int, input()))

directions = {i : None for i in range(1,10)}
dx = 2
for i in range(3):
    dx -= 1
    dy = -1
    for j in range(3):
        directions[(i*3)+j +1] = (dx, dy)
        dy += 1


for cnt in range(len(actions)):
    user = Move_user(user, actions[cnt])
    if user == -1:
        print('kraj' , cnt+1)
        exit(0)
    mads = Move_ardu(mads, user)
    if mads == -1:
        print('kraj', cnt+1)
        exit(0)

for r in range(R):
    print(''.join(map(str, board[r])))