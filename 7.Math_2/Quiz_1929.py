import sys

M, N = map(int, sys.stdin.readline().split())


prime_list =  []
prime_list.append(False)
prime_list.append(False)

for i in range(1, N+1):
    prime_list.append(True)

for i in range(2, int(N **0.5+1)):
    if prime_list[i] == True:
        for j in range(i+i, N+1, i):
            prime_list[j] = False

for k in range(M, N+1):
    if prime_list[k] == True:
        print(k)