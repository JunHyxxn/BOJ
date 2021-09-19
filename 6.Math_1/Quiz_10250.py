T = int(input())
for i in range(T):
    H, W, N = map(int, input().split())
    floor = N%H
    number = N//H
    if floor == 0:
        floor = H
    else:
        number += 1
    
    if number >= 10:
        print('{0}{1}'.format(floor, number))
    else:    
        print('{0}0{1}'.format(floor , number))