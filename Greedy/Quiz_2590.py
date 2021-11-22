import sys
input = sys.stdin.readline

cnt = 0
papers = [0] * 7
for i in range(1,7):
    papers[i] = int(input().strip())

cnt += papers[6]
papers[6] = 0

while papers[5]:
    papers[5] -= 1
    cnt += 1
    one = min(papers[1], 11)
    papers[1] -= one

while papers[4]:
    papers[4] -= 1
    cnt += 1
    two = min(papers[2], 5) * 4
    one = min(papers[1], 36 - 16 - two)
    papers[2] -= two//4
    papers[1] -= one

while papers[3]:
    if papers[3] >= 4:
        papers[3] -= 4
        cnt += 1
    elif papers[3] == 3:
        papers[3] = 0 
        cnt += 1
        two = min(papers[2], 1) * 4
        one = min(papers[1], 36 - 27 - two)
        papers[2] -= two//4
        papers[1] -= one
    elif papers[3] == 2:
        papers[3] = 0 
        cnt += 1
        two = min(papers[2], 3) * 4
        one = min(papers[1],36 - 18 - two)
        papers[2] -= two//4
        papers[1] -= one
    elif papers[3] == 1:
        papers[3] = 0 
        cnt += 1
        two = min(papers[2], 5) * 4
        one = min(papers[1], 36 - 9 - two)
        papers[2] -= two//4
        papers[1] -= one

while papers[2]:
    two = min(papers[2], 9) * 4
    one = min(papers[1], 36 - two)
    papers[2] -= two//4
    papers[1] -= one
    cnt += 1
    
while papers[1]:
    one = min(papers[1], 36)
    papers[1] -= one
    cnt += 1
    
print(cnt)