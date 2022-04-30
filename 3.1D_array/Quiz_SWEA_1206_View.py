T = 10
results = []
for t in range(1, T+1):
    n = int(input())
    buildings = list(map(int, input().split()))

    result = 0
    for i in range(2, n-2):
        value = min(buildings[i]-buildings[i-2], buildings[i]-buildings[i-1], buildings[i]-buildings[i+1], buildings[i]-buildings[i+2])
        value = 0 if value < 0 else value
        result += value

    results.append(result)

for i, result in enumerate(results):
    print("#{} {}".format(i+1, result))