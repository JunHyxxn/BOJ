"""
문제
백준중학교에서는 학기가 끝날 무렵에 출결사항을 보고 개근상을 줄 것인지 말 것인지 결정한다. 
이 학교는 이상해서 학생들이 학교를 너무 자주 빠지기 때문에, 개근상을 주는 조건이 조금 독특하다.

출결사항이 기록되는 출결은 출석, 지각, 결석이다.

개근상을 받을 수 없는 사람은 지각을 두 번 이상 했거나, 결석을 세 번 연속으로 한 사람이다.

한 학기가 4일이고, O를 출석, L을 지각, A를 결석이라고 했을 때, 개근상을 받을 수 있는 출결정보는

OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA 
OAOL OAAO OAAL OALO OALA OLOO OLOA OLAO OLAA AOOO 
AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL
AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA 
LAOO LAOA LAAO
총 43가지이다.

한 학기는 N일이다. N이 주어졌을 때, 개근상을 받을 수 있는 출결정보의 개수를 세는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. N은 1,000보다 작거나 같다.

출력
첫째 줄에 정답을 1,000,000으로 나눈 나머지를 출력한다.
================================================================================================================================================
BT => OLOA,LOOA,OOLA => 지각 1회 결석 1회인 경우인데 이를 모두 계산하게 된다. 시간초과가 발생한다.
따라서 좀 더 단축시킬 필요가 있다.
보통 단축이 필요하면 DP를 이용하면 단축을 시킬 수 있다.

어떻게 DP를 이용할 것인가?
ㄴ=> dp[day][late][absent]
"""
## BT
# import sys
# sys.setrecursionlimit(10**8)

# N = int(input())
# pos_state = ['O','L','A']
# d_state = {s : 0 for s in pos_state}
# cnt = 0
# def dfs(depth, state = ''):
#     global cnt
#     if d_state['L'] >= 2 or 'AAA' == state[-3:]:
#         return
#     if depth == N:
#         cnt+=1
#         return

#     for ps in pos_state:
#         state += ps
#         d_state[ps] +=18
#         dfs(depth+1, state)
#         state = state[0:-1]
#         d_state[ps] -=1


# dfs(0,'')
# print(cnt)





## DFS + DP
import sys
sys.setrecursionlimit(10**6)
n = int(input())
dp = [[[-1 for _ in range(3)] for _ in range(2)] for _ in range(n)]

def dfs(day, late, absent):
    if late >=2 or absent >=3:
        return 0
    if day ==n:
        return 1
    
    if dp[day][late][absent]==-1:
        number = dfs(day+1,late, 0) + dfs(day+1, late+1,0) + dfs(day+1, late, absent+1)
        dp[day][late][absent] = number

        return number
    else:
        return dp[day][late][absent]

out = dfs(0,0,0)
print(out%1000000)