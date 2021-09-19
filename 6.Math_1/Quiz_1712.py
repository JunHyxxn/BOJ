A, B, C = map(int, input().split())

if (C-B) <= 0:
    print(-1)
else:
    num = A // (C-B)
    num += 1
    print(num)