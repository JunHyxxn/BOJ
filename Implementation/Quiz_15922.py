"""
문제
아우으 우아으이야!! 으어아아아아아아아ㅏㅏㅏ아아앙ㅇ아아ㅏ

수직선 위에 선분을 여러 개 그릴 거 야아아앙ㅇ아아아ㅏㅏ아아ㅏㅏ!!

선분을 겹치게 그리는 것도 가능하다아어으우어우으아아아아아아아아아이야!!!!1

선분을 모두 그렸을 때, 수직선 위에 그려진 선분 길이의 총합은 얼마아아으으우어으이으야이야!!!!

입력
첫째 줄에 수직선 위에 그릴 선분의 개수 N이 주어진다아우으 우아으이야!!. (1 ≤ N ≤ 100,000)

둘째 줄 부터 N개의 줄에 좌표를 나타내는 정수쌍 (x, y)가 주어진다으어아아아아아아아ㅏㅏㅏ아아앙ㅇ아아.

이는 [x, y] 구간 (x와 y를 포함하는 구간)에 선분을 그린다는 의미이다유아아우응아이양. 

좌표는 x가 증가하는 순으로, x가 같다면 y가 증가하는 순으로 주어진다으우오아앙아ㅓㅇ아ㅡㅇ. (-1,000,000,000 ≤ x < y ≤ 1,000,000,000)

출력
N개의 선분을 모두 그렸을 때, 수직선 위에 그어진 선분 길이의 총합을 출력한다아아어으잉에애야우아으아이아야아아아아아아이야!!!
====================================================================================================================================
Algorithm : Sweeping
            이 문제는 조건 자체가 x의 순서대로 같은경우 y 증가하는 순으로 주어지기 때문에 정렬을 따로 하지 않아도 된다.
"""

n = int(input())

x,y = map(int, input().split())
result = 0

for _ in range(n-1):
    xx, yy = map(int, input().split())

    if x == xx:
        y = yy
    elif y >= xx and y < yy:
        y = yy
    elif y < xx:
        result += (y-x)
        x = xx
        y = yy

print(result + y-x)