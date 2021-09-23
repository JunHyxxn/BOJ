"""
문제

오늘은 스타트링크에 다니는 사람들이 모여서 축구를 해보려고 한다. 축구는 평일 오후에 하고 의무 참석도 아니다. 
축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다. 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.

BOJ를 운영하는 회사 답게 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다. 
능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다. 
팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다. 
Sij는 Sji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.

N=4이고, S가 아래와 같은 경우를 살펴보자.

i\j	1	2	3	4
1	 	1	2	3
2	4	 	5	6
3	7	1	 	2
4	3	4	5	 
예를 들어, 1, 2번이 스타트 팀, 3, 4번이 링크 팀에 속한 경우에 두 팀의 능력치는 아래와 같다.

스타트 팀: S12 + S21 = 1 + 4 = 5
링크 팀: S34 + S43 = 2 + 5 = 7
1, 3번이 스타트 팀, 2, 4번이 링크 팀에 속하면, 두 팀의 능력치는 아래와 같다.

스타트 팀: S13 + S31 = 2 + 7 = 9
링크 팀: S24 + S42 = 6 + 4 = 10
축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다. 

위의 예제와 같은 경우에는 1, 4번이 스타트 팀, 2, 3번 팀이 링크 팀에 속하면 스타트 팀의 능력치는 6, 링크 팀의 능력치는 6이 되어서 차이가 0이 되고 이 값이 최소이다.
========================================================================================================================================================================================================
입력
첫째 줄에 N(4 ≤ N ≤ 20, N은 짝수)이 주어진다. 둘째 줄부터 N개의 줄에 S가 주어진다. 
각 줄은 N개의 수로 이루어져 있고, i번 줄의 j번째 수는 Sij 이다. Sii는 항상 0이고, 나머지 Sij는 1보다 크거나 같고, 100보다 작거나 같은 정수이다.

출력
첫째 줄에 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력한다.

========================================================================================================================================================================================================
"""

# ## Ver 1.0
# """
# version1.0의 경우 
# N개 중 N//2 개만큼 뽑아내는데 양 팀이 쌍으로 이루어지기 때문에 모든 경우를 순회하지 않고 딱 절반만 뽑아낸다.
# => N Combination N//2 

# 뽑은 start팀과 link팀의 팀 전력을 총합하기 위해 이중 for문을 사용. 
# ㄴ=> (N//2) ^2 = O(N^2)

# 상당히 시간복잡도가 큰 방식이라 시간초과된다.
# 개선이 필요함.
# """
# import sys
# N= int(sys.stdin.readline())
# S = []
# for i in range(N):
#     l = list(map(int, sys.stdin.readline().split()))
#     S.append(l)

# ## start 팀은 value가 1 //  link 팀은 value를 0으로 설정하도록 한다.
# count = {i :0 for i in range(N)}
# isused = [False] * N ## 중복 제거
# first_flag = True ## 첫 경우인 0 이후는 쌍으로 이루어지면 반복되기 때문에 첫 0이 오는 경우까지만 탐색하기 위한 flag
# diff = 1e9 ## different value

# def solve(depth):
#     start_sum = 0
#     link_sum = 0
#     start = []
#     link = []
#     global first_flag, diff, count, isused
#     if depth == N//2:
#         for k, v in count.items():
#             if v ==0:
#                 link.append(k)
#             else:
#                 start.append(k)

#         for i, x in zip(start, link):
#             for j , y in zip(start, link):
#                 start_sum += S[i][j]
#                 link_sum += S[x][y]
            
#         diff = min(diff, abs(start_sum - link_sum))
#         return 

#     for i in range(N):
#         if depth ==0 and count[0] == 0 and first_flag is False:
#             break
#         first_flag = False
#         if isused[i] is False:
#             isused[i] = True
#             count[i] += 1
#             solve(depth+1)
#             isused[i] = False
#             count[i] -= 1



# solve(0)
# sys.stdout.write(str(diff))





## Ver 2.0
"""
version1.0의 경우 
N개 중 N//2 개만큼 뽑아내는데 양 팀이 쌍으로 이루어지기 때문에 모든 경우를 순회하지 않고 딱 절반만 뽑아낸다.
=> N Combination N//2 

뽑은 start팀과 link팀의 팀 전력을 총합하기 위해 이중 for문을 사용. 
ㄴ=> (N//2) ^2 = O(N^2)

상당히 시간복잡도가 큰 방식이라 시간초과된다.
개선이 필요함.

=======================================================================================================

변수로 저장하면 계산이 줄어드는 경우 변수로 저장
불필요한 for문 방문을 최대한 줄이기.

dictionary를 이용해 두개의 list를 만드는 것이 아닌
하나의 리스트로 선택되면 start팀
선택되지 않으면 link팀 

But, 여전히 시간초과

어떻게 개선해야할지 모르겠음. 추후 개선 필요
"""
import sys
import time

s_t = time.time()
N= int(sys.stdin.readline().strip())
S = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(N)]
diff = 1e9 ## different value

## start 팀은 value가 1 //  link 팀은 value를 0으로 설정하도록 한다.
members = [0 for _ in range( N)]
n = N//2 ## 계산 줄이기 위해 변수로 저장
def solve(depth):
    global diff
    
    if depth == n:
        start_sum = 0
        link_sum = 0
        for i in range(N):
            for j in range(i+1, N): ## 계산 줄이기 위해 i+1 ~ N-1 까지 중복 순회 방지
                if members[i] and members[j]: ## i, j 가 모두 선택된 경우 start-team
                    start_sum = start_sum + S[i][j] + S[j][i]   ## i,j j,i 둘 다 더해준다.
                elif not members[i] and not members[j]: ## i, j 모두 선택되지 않으면 link_team
                    link_sum = link_sum + S[i][j] + S[j][i]
        diff = min(diff, abs(start_sum - link_sum))
        return 

    for i in range(depth, N): ## 중복검사 방지 위해 depth부터 순회
        if members[i]: continue
        members[i] = 1
        solve(depth+1)
        members[i] = 0 



solve(0)
sys.stdout.write(str(diff))
sys.stdout.write(str(time.time() - s_t))