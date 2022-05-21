"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYCne646vKQDFARx&categoryId=AYCne646vKQDFARx&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
0 : right
1 : down
2 : left
3 : up
dir = [0,0,0,0]  : 각 방향에 대한 움직임 횟수 저장
index+1 => Right
index-1 => Left
"""
T = int(input())
dxdy = {0:[1,0], 1: [0, -1], 2: [-1,0], 3:[0,1]}
for t in range(1, T+1):
    move = input()
    direction = 0
    x, y = 0, 0
    max_value = 0
    path = []
    for m in move:
        if m == 'S':
            path.append(direction)
            dx, dy = dxdy[direction]
            x, y = x + dx, y + dy
            max_value = max_value if max_value > x**2+y**2 else x**2+y**2
        elif m == 'L':
            direction = (direction + 3) % 4
        elif m == 'R':
            direction = (direction+1)%4

    ## 1. 움직임이 없는 경우
    if len(path) == 0:
        print("#{} {}".format(t, max_value))
        continue
    
    last_dir = direction
    ## 2-1 초기 방향과 마지막 진행 방향이 같고, 원점 - 움직임 반복
    if x == 0 and y == 0:
        print("#{} {}".format(t, max_value))
        continue
    ## 2-2 초기 방향과 마지막 진행 방향 같고 원점 아닌 경우 - 끝없이 뻗어나간다.
    if last_dir == 0:
        print("#{} {}".format(t, "oo"))
        continue
    ## 3. 마지막 진행 방향이 Down인 경우 - 움직임 시계 방향으로 3번 반복 진행
    elif last_dir == 1:
        for i in range(1,4):
            for p in path:
                p = (p + i) % 4
                dx, dy = dxdy[p]
                x, y = x + dx, y + dy
                max_value = max_value if max_value > x ** 2 + y ** 2 else x ** 2 + y ** 2
    ## 4. 마지막 진행 방향이 Left인 경우 - 움직임 반대로 한 번 반복
    elif last_dir == 2:
        for p in path:
            p = (p+2)%4
            dx, dy = dxdy[p]
            x, y = x+dx, y+dy
            max_value = max_value if max_value > x**2+y**2 else x**2+y**2
    ## 5. 마지막 진행방향이 Right인 경우 - 움직임 반 시계 방향으로 3번 반복 진행
    elif last_dir == 3:
        for i in range(1,4):
            for p in path:
                p = (p + (4-i)) % 4
                dx, dy = dxdy[p]
                x, y = x + dx, y + dy
                max_value = max_value if max_value > x ** 2 + y ** 2 else x ** 2 + y ** 2
    print("#{} {}".format(t, max_value))