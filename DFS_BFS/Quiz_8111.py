"""
문제
폴란드 왕자 구사과는 다음과 같은 수를 좋아한다.

0과 1로만 이루어져 있어야 한다.
1이 적어도 하나 있어야 한다.
수의 길이가 100 이하이다.
수가 0으로 시작하지 않는다.
예를 들어, 101은 구사과가 좋아하는 수이다.

자연수 N이 주어졌을 때, N의 배수 중에서 구사과가 좋아하는 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T(T < 1,000)가 주어진다.

둘째 줄부터 T개의 줄에는 자연수 N이 한 줄에 하나씩 주어진다. N은 20,000보다 작거나 같은 자연수이다.

출력
각각의 테스트 케이스마다 N의 배수이면서, 구사과가 좋아하는 수를 아무거나 출력한다. 만약, 그러한 수가 없다면 BRAK을 출력한다.
"""
import sys
from collections import deque

T = int(sys.stdin.readline())


## OverFlow로 인해 오답 예상된다.
# def BFS(num):
#     possible = [{'0'}, {'1'}, {'0','1'}]
#     elem = '1'
#     queue = deque()
#     queue.append(elem)

#     while queue:
#         cur = queue.popleft()
#         if len(cur) == 101:
#             return 'BRAK'
        
#         queue.append(cur + '1')
#         result = (int(cur) - int(cur)%num)
#         if set(str(result)) in possible and result != 0:
#             return str(result)


def BFS(num):
    visited = [''] * (num)
    visited[1] = '1'
    queue = deque()
    queue.append(1)

    while queue:
        cur = queue.popleft()
        
        if len(visited[cur]) == 101: ## 100글자 넘어가면 못찾은 경우
            print('BRAK')
            return
        for add in [0,1]: ## 가지치기는 0과 1로 진행
            next_num = (cur*10 + add) % num ## mod특징 이용
            if next_num == 0: ## 나누어 떨어지면 출력
                print(visited[cur] + str(add))
                return
            if visited[next_num] =='': ## 방문하지 않았을 때만 추가한다.
                visited[next_num] = visited[cur] + str(add)
                queue.append(next_num)

for _ in range(T):
    N = int(sys.stdin.readline())

    BFS(N)