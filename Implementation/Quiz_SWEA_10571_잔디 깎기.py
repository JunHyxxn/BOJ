"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AXO8Cd0aQI0DFAXS&categoryId=AXO8Cd0aQI0DFAXS&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=5
"""
T = int(input())
results = []
for t in range(1, T+1):
    N, M = map(int, input().split())

    H = [list(map(int, input().split())) for _ in range(N)]
    pos_height = set()
    for i in range(N):
        for j in range(M):
            pos_height.add(H[i][j])
    pos_height = list(sorted(pos_height, reverse=True))
    lawn = [[pos_height[0]]*M for _ in range(N)]
    for p in pos_height[1:]:
        for col in range(M):
            if H[0][col] <= p:
                flag = True
                for i in range(N):
                    if H[i][col] > p:
                        flag = False
                        break
                if flag:
                    for i in range(N):
                        lawn[i][col] = p
        for row in range(N):
            if H[row][0] <= p:
                flag = True
                for i in range(M):
                    if H[row][i] > p:
                        flag = False
                        break
                if flag:
                    for i in range(M):
                        lawn[row][i] = p
    if H == lawn:
        results.append(("#{} {}".format(t, "YES")))
    else:
        results.append(("#{} {}".format(t, "NO")))
for res in results:
    print(res)
