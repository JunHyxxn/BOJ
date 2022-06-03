T = int(input())

for t in range(1, T+1):
    N = int(input())
    lines = []
    for _ in range(N):
        lines.append(list(map(int, input().split())))

    lines = sorted(lines, key=lambda x: x[0])
    cnt = 0
    for i in range(N):
        _, prev = lines[i]
        for j in range(i+1, N):
            _, post = lines[j]
            if prev > post:
                cnt += 1
    print("#{} {}".format(t, cnt))


