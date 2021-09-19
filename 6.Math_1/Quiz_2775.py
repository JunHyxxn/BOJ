import math

T = int(input())
for i in range(T):
    k = int(input())
    n = int(input())
    parent = n
    for i in range(k):
        parent *= (n+i+1)
    print(parent//math.factorial(k+1))
