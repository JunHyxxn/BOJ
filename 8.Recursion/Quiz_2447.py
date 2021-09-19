# # 별찍기 - 10

# # ㅉㅉㅉㅉㅉㅉㅉㅉㅉ      
# # ㅉ  ㅉㅉ  ㅉㅉ  ㅉ      1,1          1,4         1,7
# # ㅉㅉㅉㅉㅉㅉㅉㅉㅉ      
# # ㅉㅉㅉ      ㅉㅉㅉ              3,3  3,4   3,5
# # ㅉ  ㅉ      ㅉ  ㅉ      4,1    4,3   4,4   4,5   4,7
# # ㅉㅉㅉ      ㅉㅉㅉ              5,3  5,4   5,5
# # ㅉㅉㅉㅉㅉㅉㅉㅉㅉ      
# # ㅉ  ㅉㅉ  ㅉㅉ  ㅉ      7,1          7,4         7,7
# # ㅉㅉㅉㅉㅉㅉㅉㅉㅉ      

# # ex)
# # 27이면 27/3 X 27/3 행렬이 만들어진다.
# # 이 때, 9X9 행렬
# # 1,4,7       %3 == 1
# # 3,4,5      //3 == 1



# 1,1      1,4     1,7     1,10 ...                                          1,25

#      3,3 3,4 3,5             3,12 3,13 3,14                3,21 3,22 3,23
# 4,1  4,3 4,4 4,5 4,7   4,9   4,12 4,13 4,14  4,16   4,19   4,21 4,22 4,23  4,25
#      5,3 5,4 5,5             5,12 5,13 5,14                5,21 5,22 5,23

# 7,1      7,4     7,7     7,10 ...                                          7,25

#                        9,9.....                 9,17


# 3으로 나눴을때 나머지가 1이 재귀적으로 1이 되는 부분을 공백으로 한다.



# ## Functional Version!!
# def Star(N):
#     star_list = []
#     for x in range(N):
#         for y in range(N):
#             flag = True
#             dx = x
#             dy = y
#             while dy != 0:
#                 if dx % 3 ==1 and dy % 3 == 1:
#                     flag = False
#                     star_list.append(" ")
#                     break
#                 dx = dx//3
#                 dy = dy//3
#             if flag is True:
#                 star_list.append("*")
#         star_list.append("\n")
#     return star_list

# N = int(input())
# result = Star(N)
# print("".join(result))






## Recursion Version!!

def Star(N, row, col):
    if row // N %3 == 1 and col // N % 3 ==1:
        print(" ", end="")
    elif N//3 == 0:
        print("*", end="")
    else:
        Star(N//3, row, col)


N = int(input())
for i in range(N):
    for j in range(N):
        Star(N, j, i)
    print()