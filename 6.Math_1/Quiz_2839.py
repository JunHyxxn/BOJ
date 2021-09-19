import sys
N = int(input())
cnt = 0
while True:
    if N < 0:
        print(-1)
        sys.exit()

    if N % 5 == 0:
        cnt += N //5
        print(cnt)
        sys.exit()
    
    N -= 3
    cnt += 1