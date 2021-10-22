while True:
    n, w = map(int, input().split())
    if n==0 and w==0: break
    hist = [0] * (100//w+1)
    for _ in range(n): 
        data = int(input())
        hist[data//w] += 1
    i = len(hist)-1
    while True:
        if hist[i] != 0:
            hist = hist[:i+1]
            break
        i -=1
    contract_hist = dict()
    for i in range(len(hist)):
        contract_hist[i*w] = hist[i]
    contract_hist = {(d[0], 1-i/(len(contract_hist)-1)) : d[1] for i, d in enumerate(contract_hist.items())}

    Ink = 0
    standard = max(contract_hist.values())
    for k, v in contract_hist.items():
        Ink += (v/standard * k[1])
    
    print(round((Ink+0.01), 16))