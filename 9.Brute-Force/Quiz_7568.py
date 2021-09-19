# # # 덩치

# # {() : 0 , } 이런 형태라면?

# ## dictionary 형태는 같은 키 몸무게를 갖는 학생이 불가능하다 
# ## 따라서 dictionary 형태는 올바른 접근이 아니다.
# N = int(input())
# peoples = {}
# for i in range(N):
#     person = tuple(map(int, input().split()))
#     peoples[person] = 0

# for key in peoples.keys():
#     for k in peoples.keys():
#         if key == k:
#             continue
#         if key[0] > k[0] and key[1] > k[1]:
#             peoples[k] = peoples.get(k) + 1
#         else:
#             continue
        
        


## 위의 dict형태를 list로 해결해본다.

N = int(input())
peoples = [] 
for i in range(N):
    peoples.append(tuple(map(int, input().split())))

for i in peoples:
    rank = 1
    for j in peoples:
        if i == j:
            continue
        if i[0] < j[0] and i[1] < j[1]:
            rank += 1
    print(rank , end=" ")