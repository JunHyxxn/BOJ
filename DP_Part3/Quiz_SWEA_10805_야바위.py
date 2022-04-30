
# T = int(input())

# results = []
# for t in range(1, T+1):
#     N, Q = map(int, input().split())
#     board = [[[0]*(N+1) for _ in range(Q+1)] for _ in range(2)] ## 속임수 사용 여부 x row(shake 횟수) x col(컵 개수)
#     def shake(mode, time, A, B):
#         # global board, N
        
#         for cup in range(1, N+1):
#             board[mode][time][cup] = board[mode][time-1][cup]
            
#         temp = board[mode][time][A]
#         board[mode][time][A] = board[mode][time][B]
#         board[mode][time][B] = temp
        
        
#         return
    
    
#     ## Initialize - 미사용, 0, 1  => 공  & 시작하자마자 속임수 사용 -> 사용, 0, 2 => 공
#     board[0][0][1] = 1
#     board[1][0][2] = 1
#     ## 속임수 미사용한 상태 - 공의 위치
#     ball_loc = 1
#     for time in range(1, Q+1):
#         A, B = map(int, input().split())
#         ## 1. 속임수 미사용한 상태 shake
#         shake(0, time, A, B)
#         if ball_loc == A or ball_loc ==B:
#             ball_loc = B if ball_loc == A else A

#         ## 2. 속임수 사용한 상태 shake
#         shake(1, time, A, B)
        
#         ## 3. 속임수 사용
#         if 0 < ball_loc-1 <= N:
#             board[1][time][ball_loc-1] = 1
        
#         if 0< ball_loc+1 <= N:
#             board[1][time][ball_loc+1] = 1

#     results.append("#{} {}".format(t, sum(board[1][Q])))


# for result in results:
#     print(result)



T = int(input())
for t in range(1, T+1):
    N, Q = map(int, input().split())
    board = [0]*(N+2) ## 속임수 사용 여부 x col(컵 개수)
    
    ## 공의 위치
    ball_loc = 1
    query = [list(map(int, input().split())) for _ in range(Q)]
    for q in query:
        A, B = q
        ## 속임수 사용
        board[ball_loc-1] = 1
        board[ball_loc+1] = 1        
        
        if ball_loc == A:
            ball_loc = B
        elif ball_loc ==B:
            ball_loc = A
        
        board[A], board[B] = board[B], board[A]


	
    board[ball_loc-1] = 1
    board[ball_loc+1] = 1    
    print("#{} {}".format(t, sum(board[1:-1])))
