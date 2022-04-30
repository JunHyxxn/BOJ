import math
T = int(input())
results = []
for t in range(1, T+1):
    N, D = map(int, input().split())
    cover = 2*D+1
    result = math.ceil(N/cover)
    results.append(result)
    
for i, result in enumerate(results):
    print("#{} {}".format(i+1, result))