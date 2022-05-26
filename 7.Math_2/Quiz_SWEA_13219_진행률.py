import math


T = int(input())

results = []
for t in range(1, T+1):
    P, X, Y = map(int, input().split())
    
    progress = P *3.6
    
    X -= 50
    Y -= 50
    radian = 0
    if X == 0 and Y == 0 and progress > 0.0:
        results.append("#{} {}".format(t, 1))
        continue
    elif X == 0 and Y == 0 and progress == 0.0:
        results.append("#{} {}".format(t, 0))
        continue
    
    if X  == 0:
        if (Y > 0 and progress > 0.0) or (Y < 0 and progress >= 180.0):
            results.append("#{} {}".format(t, 1))
            continue
        else:
            results.append("#{} {}".format(t, 0))
            continue
    if Y == 0:
        if (X > 0 and progress >= 90.0) or (X < 0 and progress >= 270.0):
            results.append("#{} {}".format(t, 1))
            continue
        else:
            results.append("#{} {}".format(t, 0))
            continue
    
    radian = round(math.degrees(math.atan(X/Y)),1)
    if (X>0 and Y<0) or (X<0 and Y <0):
        radian = 180.0 + radian
    elif X<0 and Y>0:
        radian = 360.0 + radian
        
    
    if radian <= progress:
        if (X**2+Y**2)**0.5 <= 50.0:
            results.append("#{} {}".format(t, 1))
        else:
            results.append("#{} {}".format(t, 0))
    else:
        results.append("#{} {}".format(t, 0))

for res in results:
    print(res)
    