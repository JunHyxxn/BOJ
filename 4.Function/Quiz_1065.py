def Han(n):
    if n == 1000:
        return 0
    digit = n % 10
    n = n //10
    ten = n %10
    n = n//10
    hund  = n %10

    if hund - ten == ten - digit:
        return 1
    else:
        return 0


num = int(input())

if num < 100:
    print(num)
else:
    cnt = 0
    for i in range(100, num+1):
        cnt += Han(i)

    print(99 + cnt)


