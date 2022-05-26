T = int(input())
for t in range(1, T+1):
    case = " "+input()
    total = 0
    res = 0
    for i in range(1, len(case)):
        if case[i] == "0": continue

        if i-1 > total:
            res += (i-1 - total)
            total = i-1
        total += int(case[i])
    print("#{} {}".format(t, res))