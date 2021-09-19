# 직사각형에서 탈출
#                     (w,h)


# (x,y)
# 위와 같은 직사각형에서 경계선으로 가는 최소 거리는? 
# 즉 변으로 가는 최소 거리 -> 가로 세로 대각선 등의 이동을 고려?

# 직사각형은 (0,0) (w,0) (0,h) (w,h) 로 이루어져 있고
# 내가 (x,y)에 위치해 있다.
# 그렇다면 총 4가지 경우를 비교하면 될 것 같다.
# x, y, w-x, h-y 중 가장 큰 값을 찾는다



x, y, w, h = map(int, input().split())

x2 = w-x
y2 = h-y

distance = [x,y,x2,y2]
distance.sort()
print(distance[0])