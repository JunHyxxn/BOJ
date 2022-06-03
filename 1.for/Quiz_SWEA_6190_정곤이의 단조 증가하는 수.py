def check(number):
    digit = 10
    prev = number%digit
    while number//digit:
        number //= digit
        now = number%digit
        if now > prev: return False
        prev = now
    return True

T = int(input())
for t in range(1, T+1):
    N = int(input())
    nums = list(sorted(list(map(int, input().split())), reverse=True))
    res = 0
    for i in range(N):
        for j in range(i+1, N):
            number = nums[i] * nums[j]
            if res >= number: continue
            if check(number):
                res = max(number, res)


    print("#{} {}".format(t, res if res else -1))

