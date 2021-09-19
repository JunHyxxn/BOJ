inNum = int(input())
floor = 1
while True:
    if inNum == 1:
        print(1)
        break
    elif (inNum-1)/3 <= floor*(floor+1):
        print(floor+1)
        break
    else:
        floor +=1