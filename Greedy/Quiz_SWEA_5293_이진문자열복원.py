# """
# Quiz_ SWEA 5293 이진문자열 복원

# 처음에는 BackTracking으로 해결했지만 TLE 발생한다. 
# 그리디를 이용해 빠르게 해결할 수 있다.
# """

# T = int(input())
# results = []
# for t in range(1, T+1):
#     zz, zo, oz, oo = map(int, input().split())
#     result = ""
#     if zo == oz+1:
#         result = "0"*zz + "01" + "1" * oo + "01"*(zo-1)
#     elif zo == oz != 0:
#         result = "0"*zz + "01" + "1" * oo + "01"*(zo-1) + "0"
#     elif zo == oz == zz ==0:
#         result = "1" * (oo+1)
#     elif zo == oz == oo == 0:
#         result = "0"*(zz+1)
#     elif zo == oz -1:
#         result = "1"*oo + "10" + "0" * zz + "10" *(oz-1)
        
#     else:
#         result = "impossible"
    
#     results.append(result)
    
# for i, result in enumerate(results):
#     print("#{} {}".format(i+1, result))
    
    


