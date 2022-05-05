T = int(input())
SIZE = int(1000**0.5) + 1
dp = [False] * (SIZE)
for i in range(1, SIZE):
    if str(i) == str(i)[::-1]:
        dp[i] = True
results = []        
for t in range(1, T+1):
    A, B = map(int, input().split())
    cnt = 0
    for i in range(A, B+1):
        if i**0.5 != int(i**0.5): continue
        if str(i) == str(i)[::-1] and dp[int(i**0.5)]:
            cnt += 1
            

    results.append("#{} {}".format(t, cnt))
    
for result in results:
    print(result)