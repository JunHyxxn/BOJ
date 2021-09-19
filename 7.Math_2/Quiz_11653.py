N = int(input())
while N != 1:
    for i in range(2, N+1):
        if N%i == 0 :
            print(i)
            N = N // i
            break

            
# def prime(number):
#     flag = False
#     if number == 1:
#         return flag
#     else:
#         for i in range(2, (number//2)+1):
#             if (number % i) == 0:
#                 return flag
#                 break
#         flag = True
#         return flag

# N = int(input())
# prime_list = []

# for i in range(2, (N//2)+1):
#     if prime(i) is True:
#         prime_list.append(i)


# for i in range(len(prime_list)//2):
#     while True:
#         if (N % prime_list[i]) == 0:
#             print(prime_list[i])
#             N = N // prime_list[i]
#         else:
#             break

