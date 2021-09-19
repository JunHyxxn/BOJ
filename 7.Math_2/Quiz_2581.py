def prime(number):
    flag = False
    if number == 1:
        return flag
    else:
        for i in range(2, (number//2)+1):
            if (number % i) == 0:
                return flag
                break
        flag = True
        return flag  

M, N = map(int, input().split())

prime_list = []

for i in range(M, N+1):
    if prime(i) is True:
        prime_list.append(i)

for num in prime_list:
    print(num)