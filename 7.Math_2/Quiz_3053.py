# 택시 기하학
# 두 점 T1(x1,y1)  T2(x2,y2) 사이의 거리는 
# D(T1, T2) = |x1 - x2| + |y1-y2|

# 택시 기하학에서의 원의 넓이 : 반지름 * 반지름 * 2
# 유클리드 원의 넓이 : Pi * r**2


import math
R = int(input())
print(math.pi*R**2)
print(R**2*2)