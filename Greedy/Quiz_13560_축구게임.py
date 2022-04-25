"""0 2 1 3
0 1 0 2
1 0 2    => 승 패 승 -> O => 0 0 1 => 0 1 => 승 패 / 패 승 -> 0 0 0 0 완성 가능 
         => 승 승 패 -> X


1,2   1,3 1,4 1이 전부 승
2,3 2,4 => 2,3 에서 2가 승 2,4에서 4가 승
3,4 => 4 승

3 1 0 2
ㄴ> i번째 팀을 0으로 만들기 위해서 ( 최대 승점 - i번째 팀의 승점 ) => 즉 i번째 팀이 진 만큼 이긴 팀의 승점을 제거 -> 탐색 작업 필요. Brute-Force
ㄴ> i번째 팀이 최대 승점을 얻었다면 i번째 팀의 승점만 제거하면 된다.
0 1 0 2

0 1 0 2 
ㄴ> 2번째 팀은 1번 승리와 1번의 패배가 있다. => 1번의 승리는 자기 자신에서 점수를 빼면 되고 1번의 패배는 다른 팀에서 빼면 된다. 
ㄴ> 다른 팀에서 뺄때는 이미 0점이라면 뺄 승점이 없으니 생략한다.

0 0 0 1
ㄴ> 3번째 팀은 0승 1패 임을 알 수 있다. 1패를 마지막 남은 팀에 승점 착감하면 된다.

결과적으로 0 0 0 0 으로 초기상태가 완성 된다. 이렇게 되면 가능한 것이다.

2 2 2 0 이라고  한다면?
1. 승점의 총 합이 n*(n-1) / 2와 같은지부터 확인한다.

2 2 2 0
2승 1패
0 1 2 0                         0 2 1 0
1승 1패                         2승 0패
0 0 1 0                         0 0 1 0
1승 0패
0 0 0 0 완성
"""

# from copy import deepcopy
# from itertools import combinations
# n = int(input())
# scores = list(map(int,input().split()))
# total = sum(scores)
# pos_total = n*(n-1) //2
# result = False
# def solve(i, score, max_score):
#     global result
#     # print('----------New State---------------')
#     # print("i, score, max_score")
#     # print(i, score, max_score)
#     if result: return
    
#     if i == n-1:
#         # print(score)
#         if sum(score) == 0:
#             result = True
#         return
#     if score[i] > max_score:
#         return
#     win = score[i]
#     lose = max_score - score[i]
#     score[i] -= win

#     temp = deepcopy(score)
 
#     # print("Win : {}\tLose : {}".format(win, lose))
#     candidate = list(combinations([j for j in range(i+1, i+max_score+1)], lose))
#     # print(candidate)
#     for cand in candidate:
#         flag = True
#         for c in cand:
#             if temp[c] == 0: 
#                 flag = False
#                 continue
#             temp[c] -= 1
#         if flag:
#             solve(i+1, temp, max_score-1)
#             temp = deepcopy(score)            
            
# if pos_total != total: ## 가능한 승점이 아니라면 바로 불가능 출력
#     print(-1)
# else:
#     solve(0, scores, n-1)
#     if result:
#         print(1)
#     else:
#         print(-1)




from math import comb


n = int(input())
scores = list(map(int, input().split()))

scores.sort()
dp = [0] * n
dp[0] = scores[0]
flag = True

for i in range(1, n):
    dp[i] = dp[i-1] + scores[i]
    if i == n-1:
       if dp[i] != n*(n-1)//2:
           flag = False
    elif i < n-1 and dp[i] < (i*(i+1)//2): ## dp[i]>=kC2
        flag= False
        break

if flag:
    print(1)
else:
    print(-1)