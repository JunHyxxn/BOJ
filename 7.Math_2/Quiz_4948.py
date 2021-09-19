# def prime(n):
#     cnt = 0
#     prime_list =[]
#     prime_list.append(False)
#     prime_list.append(False)
#     for k in range(2, n*2+1):
#         prime_list.append(True)

#     i = 2
#     while i**2 <= n*2:
#         if prime_list[i] is True:
#             j = i*i
#             while j <= n*2:
#                 prime_list[j] = False
#                 j += i
#         i +=1
    
#     for o in range(n+1, n*2+1):
#         if prime_list[o] is True:
#             cnt += 1
    
#     return cnt

# while True:
#     n = int(input())
#     cnt = 0
#     if n == 0:
#         break
#     else:
#         cnt = prime(n)
#         print(cnt)



import sys
import math

while(True):
    N = int(sys.stdin.readline().strip())

    if N ==0:
        break
    prime_list = []
    prime_list.append(False)
    prime_list.append(False)

    for i in range(1, 2*N+1):
        prime_list.append(True)

    for i in range(2, int(math.sqrt(2*N) + 1)):
        if prime_list[i] == True:
            for j in range( i+i, 2*N+1, i):
                prime_list[j] = False


    cnt = 0
    for k in range(N+1, 2*N+1):
        if prime_list[k] == True:
            cnt += 1


    print(cnt)