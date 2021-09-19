#  ★★★★ 체스판 다시 칠하기


# Problem : M x N 체스판      맨 왼쪽 위칸이 흰색 or 검은색으로 시작

# KeyPoint : 왼쪽 위가 흰색, 검은색 둘 중 무엇으로 시작해도 상관없다.
#             그렇다면 어떠한 경우에 어떤 색으로 시작해야할지 판단할 필요가 있다.

#            M x N의 보드판 중 어디부터 8 x 8 만큼 떼어올지 결정하는 것도 필요.


# 보드판이 M x N인데 이 중 8 x 8 만큼 추출할 것이기 때문에
# 우린 M x N 의 0,0 부터 M-7 x N-7 까지 


N, M = map(int, input().split())
matrix = []

for i in range(N):
    matrix.append(input())

cnt_list = []
for i in range(N-7):
    for j in range(M-7):
        W_cnt = 0
        B_cnt = 0
        for k in range(i, i+8):
            for o in range(j, j+8):
                if (k+o)%2 ==0:
                    if matrix[k][o] == "W":
                        W_cnt +=1
                    if matrix[k][o] == "B":
                        B_cnt += 1
                else:
                    if matrix[k][o] == "B":
                        W_cnt +=1
                    if matrix[k][o] == "W":
                        B_cnt += 1
        cnt_list.append(W_cnt)
        cnt_list.append(B_cnt)                    


cnt_list.sort()
print(cnt_list[0])