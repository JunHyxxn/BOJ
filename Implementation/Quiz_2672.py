"""
문제
밑변이 모두 x축에 평행한 N개의 직사각형이 주어질 때, 이 N개의 직사각형들이 차지하는 면적을 구하는 프로그램을 작성하시오. 
여기서 주어진 직사각형들은 서로 겹칠 수도 있으며, 변이나 꼭짓점을 공유할 수도 있다.



입력
첫째 줄에 직사각형의 개수 N(1 ≤ N ≤ 30)이 주어지고 그 다음 N줄에는 각각의 직사각형에 대한 자료가 주어진다. 
이 자료는 4개의 숫자로 표시되는데 첫째, 둘째 숫자는 직사각형의 왼쪽 아래 모서리의 x좌표, y좌표이고 셋째 숫자는 폭, 넷째 숫자는 높이를 나타낸다. 
각 수는 최대 소수점 이하 한 자리까지 주어지며, 1000.0보다 작거나 같은 양의 실수이다.

출력
첫째 줄에 N개의 직사각형이 차지하는 면적을 소수점 이하 2자리까지 출력한다. 단, 값이 소수 부분이 없이 정수로 맞아떨어지는 경우는 정수 부분만 출력한다.

==========================================================================================================================================================
x1, y1, w, h
4
52.7 22.9 27.3 13.3
68.8 57.6 23.2 8.0
20.0 48.0 37.0 23.5
41.5 36.2 27.3 21.4

Plane Sweeping Problem - x 축을 기준으로 sweeping하고 현재 x좌표들을 기준으로 y가 포함되어 있는 구간을 체크해준다.
N이 작기 때문에 매번 y 리스트에 체크된 개수를 세면서 진행이 가능하다.
만약 N이 커진다면 Segment Tree를 활용해야한다.
"""
## y_line에 체크된 개수 count함수
def count(y_line):
    cnt = 0
    for y_value in y_line:
        if y_value: cnt += 1
    return cnt

n = int(input())
points = []
for _ in range(n):
    x,y,w,h = map(float,input().split())
    ## 좌표에 쉽게 체크하기 위해서 *10들을 해준다.
    x,y,w,h = int(x*10), int(y*10), int(w*10), int(h*10)
    ## 세로선을 기준으로 points에 입력한다.
    ## 시작선은 flag가 1이고, 끝 선은 flag = -1
    points.append([x,y,y+h,1])
    points.append([x+w,y,y+h,-1])
## x좌표의 크기 순으로 정렬한다.
points = sorted(points, key = lambda x : x[0])

area = 0.0
## y_list
y_line = [0]*  (2*(10000+1)-1)

## Initialize
last,s_y, e_y, flag = points[0]
for j in range(s_y, e_y):
    y_line[j] += 1
## Area calculate
for point in points[1:]:
    x,y,y2,flag = point
    ## (x - last) * height
    area += ((x-last)/10) * (count(y_line)/10)
    ## 시작선이라면 높이를 update하며 겹치지 않은 부분을 더 높일 수 있도록 해준다.
    if flag == 1:
        for j in range(y, y2):
            y_line[j] += 1
    ## 끝선이라면 높이를 update하며 높이를 낮출 수 있도록 한다.       
    elif flag == -1:
        for j in range(y, y2):
            y_line[j] -= 1
    ## last update
    last = x
## 출력구문
if area - int(area) > 0:
    print(f'{area:0.2f}')
else:
    print(int(area))