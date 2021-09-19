for _ in range(int(input())):
    i = float(0)
    scores = list(map(float, input().split()))[1:]
    mean = float(sum(scores))/len(scores)
    for s in scores:
        if s > mean:
            i+=1
    print('{:.3f}'.format(i/len(scores)*100), end='%\n')
    