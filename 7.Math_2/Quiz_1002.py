# # 터렛 - 거리 계산
# # 조규현의 좌표 (x1, y1)
# # 백승환의 좌표 (x2, y2)
# # 조규현이 계산한 류재명 거리 r1
# # 백승환이 계산한 류재명 거리 r2
# # 류재명이 있을 수 있는 좌표의 수는?

# 원의 중심간 거리 : distance 
# 두 원의 반지름의 합 : r1 + r2
# 이 두 거리의 관계를 살펴보면 접점의 개수 파악 가능

# 1. 접하지 않는 외접원
# ㄴ distance > r1 + r2

# 2. 한 점에서 접하는 외접원
# ㄴ distance == r1 + r2

# 3. 두 접에서 접하는 외접원
# ㄴ |r1 - r2 | < distance < r1 + r2

# 4. 두 점에서 접하는 내접원
# ㄴ distance < long_r (r1 != r2)

# 5. 한 점에서 접하는 내접원
# ㄴ dstance == |r1-r2|

# 6. 접하지 않는 내접원
# ㄴ distance < |r1- r2| || distance = 0 and r1 != r2

# 7. 무한대 
# ㄴ distance == 0 and r1 == r2

import math
def Position(x1,y1, x2, y2):
    distance  = math.sqrt((x1-x2)**2 + (y1-y2)**2)
    return distance


T = int(input())

for i in range(T):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    distance = Position(x1, y1, x2, y2)
    # math.fabs
    if distance == 0:
        if r1 != r2:
            # print("distance : {0}   r1+r2 : {1}   |r1-r2| : {2}".format(distance, r1+r2, math.fabs(r1-r2)))
            print(0)
        else:
            # print("distance : {0}   r1+r2 : {1}   |r1-r2| : {2}".format(distance, r1+r2, math.fabs(r1-r2)))
            print(-1)
    else:
        if (distance < math.fabs(r1-r2)) or  (distance > r1 + r2):
            # print("distance : {0}   r1+r2 : {1}   |r1-r2| : {2}".format(distance, r1+r2, math.fabs(r1-r2)))
            print(0)
        elif (distance == r1 + r2) or (distance ==  math.fabs(r1 - r2)):
            # print("distance : {0}   r1+r2 : {1}   |r1-r2| : {2}".format(distance, r1+r2, math.fabs(r1-r2)))
            print(1)
        elif (distance < r1 + r2) or (distance > math.fabs(r1 - r2)):
            # print("distance : {0}   r1+r2 : {1}   |r1-r2| : {2}".format(distance, r1+r2, math.fabs(r1-r2)))
            print(2)
        
        