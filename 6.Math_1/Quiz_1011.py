T = int(input())
for i in range(T):
    x, y = map(int, input().split())
    distance = y - x
    N = 1
    while True:
        if N**2 <= distance and distance < (N+1)**2:
            break
        else:
            N = N + 1

    if (distance - N**2) == 0:
        print(N+ (N-1))
    elif (distance - N**2) <= N:
        print(N+(N-1) + 1)
    elif (distance - N**2) > N:
        print(N+(N-1) + 2)
    else:
        print("Error")
