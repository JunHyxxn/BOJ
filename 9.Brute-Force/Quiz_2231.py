import sys
N = int(sys.stdin.readline().strip())

def product_number(n):
    total = n
    while(True):
        if n //10 !=0:
            f = n % 10
            total += f
            n = n//10
        else:
            total += n
            break
    
    return total

for i in range(1, N+1):
    if product_number(i) == N:
        print(i)
        break
    if i == N:
        print(0)
        break