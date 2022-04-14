def add_fish(fishbowl):
    min_value = float("INF")
    index = []
    for i in range(len(fishbowl)):
        if min_value > fishbowl[i]:
            min_value = fishbowl[i]
            index = [i]
        elif min_value == fishbowl[i]:
            index.append(i)
    
    for idx in index:
        fishbowl[idx] += 1
    
    return fishbowl

def stack_bowl(fishbowl):
    vertical = [] ## 세로 어항
    horizontal = fishbowl ## 가로 어항 
    
    ## Init
    vertical.append([horizontal.pop(0)])
    
    ## 어항 쌓기 시작
    while len(vertical[0]) <= len(horizontal):
        temp = [] ## 임시 vertical 배열
        for i in range(len(vertical[0])-1, -1, -1):
            vert = [] ## 임시 한 줄
            for j in range(len(vertical)): 
                vert.append(vertical[j][i])
            vert.append(horizontal.pop(0))

            temp.append(vert)
        vertical = temp
        
    new_bowl = [[-1] * (len(vertical)+len(horizontal)) for _ in range(len(vertical[0]))]
    
    for i in range(len(vertical)):
        for j in range(len(vertical[0])):
            new_bowl[j][i] = vertical[i][j]
    if len(horizontal) > 0:
        for i in range(len(vertical), len(vertical) + len(horizontal)):
            new_bowl[len(vertical[0]) - 1][i] = horizontal[i-len(vertical)]
            
    return new_bowl

def balance(bowl):
    global d
    
    temp = [[0] * len(bowl[0]) for _ in range(len(bowl))]
    
    for x in range(len(bowl)):
        for y in range(len(bowl[0])):
            if bowl[x][y] == -1: continue ## 탐색 불필요 지역은 건너뛴다.
            
            ## 4개의 방향
            for dd in d:
                dx, dy = dd
                nx, ny = x+dx, y+dy
                if 0<= nx < len(bowl) and 0<= ny < len(bowl[0]) and bowl[nx][ny] != -1:
                    diff = abs(bowl[x][y] - bowl[nx][ny]) // 5
                    if diff >0:
                        if bowl[x][y] > bowl[nx][ny]: ## x,y -> nx,ny 로 이동 
                            temp[x][y] -= diff
                            temp[nx][ny] += diff
                            
    for x in range(len(bowl)):
        for y in range(len(bowl[0])):
            bowl[x][y] += temp[x][y]
    
    return bowl

def one_row(bowl):
    temp = []
    
    for y in range(len(bowl[0])):
        for x in range(len(bowl)-1, -1, -1):
            if bowl[x][y] != -1:
                temp.append(bowl[x][y])
                
    return temp

def rotate_half(bowl):
    temp = []
    st, nd, rd, th = len(bowl)//4, len(bowl)//4*2, len(bowl)//4 * 3, len(bowl)
    a, b, c, d = bowl[:st], bowl[st:nd], bowl[nd:rd], bowl[rd:th]
    temp.append(c[::-1])
    temp.append(b)
    temp.append(a[::-1])
    temp.append(d)
    
    return temp
    
N, K = map(int, input().split())
d = [[0,1],[1,0],[0,-1],[-1,0]]
fishbowl = list(map(int, input().split()))
cnt = 0
diff = float("INF")
while True:
    if diff <= K: 
        print(cnt)
        break
    fishbowl = add_fish(fishbowl)
    temp_bowl = stack_bowl(fishbowl)
    temp_bowl = balance(temp_bowl)
    temp_bowl = one_row(temp_bowl)
    temp_bowl = rotate_half(temp_bowl)
    temp_bowl = balance(temp_bowl)
    temp_bowl = one_row(temp_bowl)
    diff = max(temp_bowl) - min(temp_bowl)
    fishbowl = temp_bowl
    cnt += 1
