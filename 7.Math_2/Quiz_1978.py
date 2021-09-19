N = int(input())

numbers = list(map(int, input().split()))
flag = False
cnt = 0

for number in numbers:
    if number == 1: continue
    for i in range(2, (number//2)+1):
        if (number % i) == 0:
            flag = True
            break
    if not flag: cnt += 1
    flag = False

print(cnt)
