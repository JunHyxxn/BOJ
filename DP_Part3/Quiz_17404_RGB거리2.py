"""
문제
RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 
각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.
입력
첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 
둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.

======================================================================================================================================================
i번째 집은 i-1, i+1 번째 집과 겹쳐서는 안되며
1번집은 N번 집과 겹쳐서도 안된다.

이 조건을 만족하며 최솟값을 구하기 위해서
위에서 아래 방향으로 최소 비용을 구하는 DP를 하나 만들고
역으로 아래에서 위로 올라가는 방향으로 최소 비용을 구하는 DP를 만든다.

이렇게 진행하면 red로 시작하는 경우 red로 끝나는 케이스를 계산하지 못하는 점을 해결할 수 있다.
즉 red로 시작한 경우 따로, red로 끝나는 경우 따로 계산하기 때문에 최소 비용을 구할 수 있다.
"""

from copy import deepcopy
## 마지막번째 집은 이전 집과도 겹쳐서는 안되고, 첫번째 집과도 겹쳐선 안된다.
def find(now_color, prev1, prev2):
    cand = []
    if now_color != prev1[0]:
        cand.append(prev1)
    
    if now_color != prev2[0]:
        cand.append(prev2)
    
    ## 가능한 경우가 없다면 Infinite를 return한다.
    if not cand:
        return (-1, float('inf'))
    
    ## 가능한 경우 중 최소 비용인 경우를 넘겨준다.    
    cand = list(sorted(cand, key = lambda x : x[1]))
    return cand[0]
        

n = int(input())
cost = []
for _ in range(n):
    cost.append(list(map(int, input().split())))

temp_cost = deepcopy(cost)
## 정방향 탐색
for i in range(1,n):
    if i == 1:
        red_flag = 1
        green_flag = 2
        blue_flag = 3
        
        temp_cost[i][0] = (green_flag if temp_cost[i-1][1] < temp_cost[i-1][2] else blue_flag, min(temp_cost[i-1][1], temp_cost[i-1][2]) + temp_cost[i][0])
        temp_cost[i][1] = (red_flag if temp_cost[i-1][0] < temp_cost[i-1][2] else blue_flag, min(temp_cost[i-1][0], temp_cost[i-1][2]) + temp_cost[i][1])
        temp_cost[i][2] = (green_flag if temp_cost[i-1][1] < temp_cost[i-1][0] else red_flag, min(temp_cost[i-1][1], temp_cost[i-1][0]) + temp_cost[i][2])
        continue
    if i == n-1:
        temp = find(red_flag, temp_cost[i-1][1], temp_cost[i-1][2])
        temp = (temp[0], temp[1] + temp_cost[i][0])
        temp_cost[i][0] = temp
        
        temp = find(green_flag, temp_cost[i-1][0], temp_cost[i-1][2])
        temp = (temp[0], temp[1] + temp_cost[i][1])
        temp_cost[i][1] = temp
        
        temp = find(blue_flag, temp_cost[i-1][0], temp_cost[i-1][1])
        temp = (temp[0], temp[1] + temp_cost[i][2])
        temp_cost[i][2] = temp
        break    
        
    temp_cost[i][0] = (temp_cost[i-1][1][0] if temp_cost[i-1][1][1] < temp_cost[i-1][2][1] else temp_cost[i-1][2][0], min(temp_cost[i-1][1][1], temp_cost[i-1][2][1]) + temp_cost[i][0])
    temp_cost[i][1] = (temp_cost[i-1][0][0] if temp_cost[i-1][0][1] < temp_cost[i-1][2][1] else temp_cost[i-1][2][0], min(temp_cost[i-1][0][1], temp_cost[i-1][2][1]) + temp_cost[i][1])
    temp_cost[i][2] = (temp_cost[i-1][1][0] if temp_cost[i-1][1][1] < temp_cost[i-1][0][1] else temp_cost[i-1][0][0], min(temp_cost[i-1][1][1], temp_cost[i-1][0][1]) + temp_cost[i][2])

## 정방향의 최소 비용
min_value = min(temp_cost[n-1][0][1], temp_cost[n-1][1][1], temp_cost[n-1][2][1])

## 역방향 탐색
temp_cost = deepcopy(cost)
for i in range(n-2, -1, -1):
    if i == n-2:
        red_flag = 1
        green_flag = 2
        blue_flag = 3
        
        temp_cost[i][0] = (green_flag if temp_cost[i+1][1] < temp_cost[i+1][2] else blue_flag, min(temp_cost[i+1][1], temp_cost[i+1][2]) + temp_cost[i][0])
        temp_cost[i][1] = (red_flag if temp_cost[i+1][0] < temp_cost[i+1][2] else blue_flag, min(temp_cost[i+1][0], temp_cost[i+1][2]) + temp_cost[i][1])
        temp_cost[i][2] = (green_flag if temp_cost[i+1][1] < temp_cost[i+1][0] else red_flag, min(temp_cost[i+1][1], temp_cost[i+1][0]) + temp_cost[i][2])
        continue
    if i == 0:
        temp = find(red_flag, temp_cost[i+1][1], temp_cost[i+1][2])
        temp = (temp[0], temp[1] + temp_cost[i][0])
        temp_cost[i][0] = temp
        
        temp = find(green_flag, temp_cost[i+1][0], temp_cost[i+1][2])
        temp = (temp[0], temp[1] + temp_cost[i][1])
        temp_cost[i][1] = temp
        
        temp = find(blue_flag, temp_cost[i+1][0], temp_cost[i+1][1])
        temp = (temp[0], temp[1] + temp_cost[i][2])
        temp_cost[i][2] = temp
        break    
        
    temp_cost[i][0] = (temp_cost[i+1][1][0] if temp_cost[i+1][1][1] < temp_cost[i+1][2][1] else temp_cost[i+1][2][0], min(temp_cost[i+1][1][1], temp_cost[i+1][2][1]) + temp_cost[i][0])
    temp_cost[i][1] = (temp_cost[i+1][0][0] if temp_cost[i+1][0][1] < temp_cost[i+1][2][1] else temp_cost[i+1][2][0], min(temp_cost[i+1][0][1], temp_cost[i+1][2][1]) + temp_cost[i][1])
    temp_cost[i][2] = (temp_cost[i+1][1][0] if temp_cost[i+1][1][1] < temp_cost[i+1][0][1] else temp_cost[i+1][0][0], min(temp_cost[i+1][1][1], temp_cost[i+1][0][1]) + temp_cost[i][2])

## 역방향 최소 비용
reverse_min_value = min(temp_cost[0][0][1], temp_cost[0][1][1], temp_cost[0][2][1])

## 최종 최소 비용
print(min(reverse_min_value, min_value))