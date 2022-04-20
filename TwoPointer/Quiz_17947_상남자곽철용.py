N, M, K = map(int, input().split())
cards = [(4*N)//K] * K
for i in range(1, (4*N)%K +1):
    cards[i] += 1

for _ in range(M):
    a, b = map(int, input().split())
    cards[a%K] -= 1
    cards[b%K] -= 1

a, b = map(int, input().split())
score = abs((a%K) - (b%K))
cards[a%K] -= 1
cards[b%K] -= 1

deck = []
for i in range(K):
    if cards[i]:
        deck += [i] * (cards[i])
cnt = 0
start = 0
end = len(deck)//2

while end < len(deck) and cnt < M-1:
    if deck[end] - deck[start] > score:
        start += 1
        end += 1
        cnt += 1
    else:
        end += 1
print(cnt)



"""
최소 score씩 묶게 되면 예외 발생
ex)
1 2 3 4 5 6 카드가 남아있고 score = 1이라 하면
최소 score인 2를 기준으로 먼저 묶게 되는데
1-3, 2-4 가 묶여 정답은 2가 된다.
하지만 실제로는 1-4 2-5 3-6 으로 묶인다면 
모두 score가 3이 되면서 곽철용보다 높은 score를 가지게 된다.
따라서 이 방법은 옳지 않다.

해결 방법은 Greedy & Two Pointer
플레이어가 사용하고 남은 카드 덱을 카드 크기 순서로 정렬해둔다.
남은 카드 덱을 최대의 경우 전부 사용한다 이 경우는 M-1 명이 사용하게 되는데,
결국 둘씩 짝짓게 되면 반반 나눠서 생각할 수 있다.

만약 앞의 절반 중 서로 짝짓거나 뒤의 절반 중 서로 짝 짓는다면 
이는 차이가 적을 수 밖에 없다.

따라서 앞의 절반과 뒤의 절반을 짝지어주도록 한다면
score들을 최대로 짝지을수 있을 것이다.
"""
# # print("--------------- Start -----------------")
# # print("       Start View Cards State\n\t\t\t", cards)
# cnt = 0
# # for i in range(K):
# #     if cards[i] == 0: continue
# #     for j in range(i+score+1, K):
# #         if cards[j] == 0: continue
# #         print(i, j)
# #         pos_cnt = min(cards[i], cards[j])
# #         cards[i] -= pos_cnt
# #         cards[j] -= pos_cnt
# #         print("After Set People\t", cards)
# #         cnt += pos_cnt
# #         if cards[i] == 0: break
# for diff in range(score+1, K): ## diff
#     # i-score, score
#     if cnt == M-1:
#         break
#     start = 0
#     while start < K:
#         if cards[start]:
#             break
#         start += 1
#     # print("Start : ",start)
#     end = start + diff
#     while end < K:
#         if cards[end]:
#             break
#         end += 1
#     # print("End : " , end)
    
#     # print("--------- start two pointer -----------")
#     while end < K:
#         # print("Start : {}\tEnd:{}".format(start, end))
        
#         if end - start == diff:
#             cards[start] -= 1
#             cards[end] -= 1
#             # print(start, end)
#             cnt += 1
#             while end < K:
#                 if cards[end]: break
#                 end +=1
#         if end - start > diff:
#             start += 1
#             while start < K:
#                 if cards[start]: break
#                 start +=1
#         elif end - start < diff:
#             end += 1
#             while end < K:
#                 if cards[end]: break
#                 end += 1
#     # print("---------- end two pointer -------------")

    
# # print("--------------- End -----------------")
# print(cnt)



