import math 
A, B, V = map(int, input().split())
cnt = math.ceil(float((V-A)/(A-B))) + 1

print(cnt)