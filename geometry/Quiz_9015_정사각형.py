"""
문제
평면 위에 N개의 점이 주어졌을 때, 가장 큰 정사각형의 넓이를 구하여라.

입력
첫째 줄에 테스트케이스의 개수 T가 주어진다.

각 테스트케이스의 첫째 줄에는 점의 개수 N(4 ≤ n ≤ 3,000)이 주어지고, 
이어서 N개의 줄에는 점의 x좌표와 y좌표가 주어진다. 모든 좌표는 -10000 이상 +10000이하의 정수이다. 
같은 위치의 점이 여러 번 주어지는 경우는 없다.

출력
각 테스트 케이스마다 가장 큰 정사각형의 넓이를 한 줄에 하나씩 출력한다. 단, 정사각형이 없는 경우 0을 출력한다.

==============================================================================================================================================
Geometry 문제 + 자료구조 문제
Pypy3 로 해결.

set 구조를 사용해 포함 여부를 빠르게 판단하도록 한다. -> Python에서의 Set은 Hash Table으로 구현되어 있어 Average Time Complexity가 O(1) Time이 된다.
선분 하나를 선택하는 과정이 O(N^2) 
그 선분으로부터 정사각형을 만들 수 있는지 확인하는 과정이 O(1) Time 

다만 Set의 경우 Hash Table에 따라 Worst case의 경우 O(n) 이 될 수도 있다.
Python3의 경우 느린 편이므로 Average Time Complexity O(N^2) 이어도 시간초과가 발생.
"""

import sys
input = sys.stdin.readline
T = int(input()) 
for _ in range(T):
    n = int(input())
    points = set()
    for _ in range(n):
        a, b = map(int, input().split())
        points.add((a,b))
    pos_line = list(points)
    pos_square = set()
    ## 선분 하나 선택 - O(N^2)
    for i in range(n):
        for j in range(i+1, n):
            x1, y1 = pos_line[i]
            x2, y2 = pos_line[j]
            a =  x2-x1
            b =  y2-y1

            
            x3, x4 = x1 + b, x2 + b
            y3, y4 = y1 - a, y2 - a
            
            if points.__contains__((x3,y3)) and points.__contains__((x4, y4)):
                pos_square.add(((x2-x1)**2 + (y2-y1)**2))
    if pos_square:
        print(max(pos_square))
    else:
        print(0)
            
