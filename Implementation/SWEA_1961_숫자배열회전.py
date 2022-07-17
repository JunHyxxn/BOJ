def rotate_90(matrix):
    temp = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N-1, -1, -1):
            temp[i][N-j-1] = matrix[j][i]
    return temp
        

T = int(input())
for t in range(1, T+1):
    N = int(input())
    matrix = [list(map(str, input().split())) for _ in range(N)]
    res = [[""] * 3 for _ in range(N)]
    for i in range(3):
        matrix=rotate_90(matrix)
        for j in range(N):
            res[j][i] = "".join(matrix[j])
    
    print("#{}".format(t))
    for i in range(N):
        for j in range(3):
            print(res[i][j], end=" ")
        print()