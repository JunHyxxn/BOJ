"""
문제
<그림 1>과 같이 정사각형 모양을 한 여섯 종류의 색종이가 있다. 
1번 색종이는 한 변의 길이가 1cm이고, 차례대로 그 길이가 1cm씩 커져, 6번 색종이의 한 변의 길이는 6cm가 된다.



<그림 1>

주어진 색종이를 <그림 2>와 같이 가로, 세로의 길이가 각각 6cm인 판 위에 붙이려고 한다. 
색종이를 붙일 때는 색종이가 판의 경계 밖으로 삐져 나가서는 안되며, 색종이가 서로 겹쳐서도 안 된다. 
또한 하나의 색종이는 하나의 판에만 붙여야 한다.



<그림 2>

각 종류별로 색종이의 장수가 주어질 때, 그 색종이를 모두 붙이기 위해서 위와 같은 판이 최소 몇 개가 필요한지 구하는 프로그램을 작성하시오.

입력
첫째 줄부터 여섯째 줄까지 각 종류의 색종이의 장수가 1번부터 6번까지 차례로 주어진다. 각 종류의 색종이의 장수는 최대 100이다.

출력
첫째 줄에 필요한 판의 최소 개수를 출력한다.
==========================================================================================================================================================
각 색종이는 여러 판에 붙일수 없다 -> 한 판에만 붙여야함. 이를 지켜야한다.
"""

import sys
input = sys.stdin.readline

cnt = 0
papers = [0] * 7
for i in range(1,7):
    papers[i] = int(input().strip())

## 6은 그냥 한 판 온전히 사용한다.
cnt += papers[6]
papers[6] = 0
## 5는 한 판 온전히 사용하고 나머지는 1로 채운다
while papers[5]:
    papers[5] -= 1
    cnt += 1
    one = min(papers[1], 11)
    papers[1] -= one
## 4는 한판 사용하고 남은 칸을 2를 이용해 채울 수 있다 이를 계산하여 적용한다.
while papers[4]:
    papers[4] -= 1
    cnt += 1
    two = min(papers[2], 5) * 4
    one = min(papers[1], 36 - 16 - two)
    papers[2] -= two//4
    papers[1] -= one

## 3은 총 4가지 경우로 나뉜다.
while papers[3]:
    if papers[3] >= 4: ## 4개 이상일 경우는 4개로 한 판 완전히 채울수 있으니 3만 4개 사용한다.
        papers[3] -= 4
        cnt += 1
    elif papers[3] == 3: ## 3이 3개일 경우는 2를 최대 1개 사용 가능하다.
        papers[3] = 0 
        cnt += 1
        two = min(papers[2], 1) * 4
        one = min(papers[1], 36 - 27 - two)
        papers[2] -= two//4
        papers[1] -= one
    elif papers[3] == 2: ## 3이 2개일 경우 2를 최대 3개 사용 가능하다.
        papers[3] = 0 
        cnt += 1
        two = min(papers[2], 3) * 4
        one = min(papers[1],36 - 18 - two)
        papers[2] -= two//4
        papers[1] -= one
    elif papers[3] == 1: ## 3이 1개일 경우 2를 최대 5개 사용가능하다.
        papers[3] = 0 
        cnt += 1
        two = min(papers[2], 5) * 4
        one = min(papers[1], 36 - 9 - two)
        papers[2] -= two//4
        papers[1] -= one

## 2는 9개 이상이면 완전히 채울 수 있다.
while papers[2]:
    two = min(papers[2], 9) * 4
    one = min(papers[1], 36 - two)
    papers[2] -= two//4
    papers[1] -= one
    cnt += 1
## 1은 그냥 채운다.
while papers[1]:
    one = min(papers[1], 36)
    papers[1] -= one
    cnt += 1
    
print(cnt)