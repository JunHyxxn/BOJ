"""

문제
가장 빠른 시간 내에 몬스터를 처치하려고 한다. 사용할 수 있는 스킬은 N개 있으며, 각 스킬은 사용하는 데 1초가 들고, 사용을 시작한 지 1초 후 몬스터에게 일정 대미지를 입힌다. 
여러 개의 스킬을 동시에 사용할 수는 없다.

각 스킬에는 저마다의 대기 시간과 대미지가 있다. 대기 시간은 스킬을 사용하기 시작한 순간부터 차기 시작한다.

예를 들어, 현재 시각이 t = 0이고, 대기 시간이 10초이고 대미지가 10인 스킬을 체력이 100인 몬스터에게 사용했다고 하자. 
그러면 t = 1일 때 몬스터의 체력이 90으로 줄어들고, 같은 스킬은 t = 10일 때부터 다시 사용할 수 있다.

입력
첫 번째 줄에는 스킬 개수 N(1 ≤ N ≤ 5)과 몬스터의 체력(HP)을 나타내는 정수(1 ≤ HP ≤ 100000)가 주어진다.

두 번째 줄부터는 줄마다 스킬의 대기 시간을 초 단위로 나타내는 정수 C(1 ≤ C ≤ 10)와 스킬의 대미지를 나타내는 정수 D(HP/10 ≤ D ≤ HP)가 공백을 두고 주어진다. 
모든 스킬의 대기 시간은 다르며, 모든 D의 합은 HP보다 작다.

출력
몬스터를 처치하는 데 걸리는 최소 시간을 출력한다.

====================================================================================================================================
스킬의 최소 데미지가 1/10 이므로 최대 10번 스킬을 사용하게 된다.
스킬의 종류는 5가지이다.
따라서  5^10 Time 이 소요된다.

Brute-Force 로 해야하고 어떤 선택을 했는지에 따라 상태가 변하고
최단 시간을 찾는 문제이기 때문에 BFS로 해결가능하다.
"""
from collections import defaultdict, deque
from copy import deepcopy
N, HP = map(int, input().split())
## Skill Information
skills = []
## Cool Time Information
skill_cool = defaultdict(int)

for _ in range(N):
    cool, damage = map(int, input().split())
    skills.append([cool, damage])
    skill_cool[cool] = 0
    
## Attack이 가능한지 판단하는 함수
def canAttack(coolTime):
    for skill in skills:
        cool, damage = skill
        if coolTime[cool] == 0: return True
    return False

## BFS로 탐색한다.
def BFS(t):
    ## Start State = (0, Initial HP, Initial Skill Cool Time)
    start = (t, HP, skill_cool)
    queue = deque()
    queue.append(start)
    
    while queue:
        time, remain_HP, coolTime = queue.popleft()
        if remain_HP <= 0: ## Monster Die
            ## 최단 시간 탐색이니 출력하고 종료시킨다.
            print(time)
            return

        if canAttack(coolTime): ## Attack Possible
            for skill in skills:
                cool, damage  = skill
                now_HP = remain_HP
                ## coolTime Deepcopy 
                now_cool = deepcopy(coolTime)
                if coolTime[cool] == 0: ## Possible Skill to use 
                    ## Cool Time Setting
                    now_cool[cool] = cool 
                    for ss in skills: ## minus 1 second 
                        cc, _ = ss
                        if now_cool[cc]: now_cool[cc] -= 1
                    now_HP -= damage
                    queue.append((time +1, now_HP, now_cool))
        else: ## Attack Impossible
            ## Minus 1 second cool time
            for s in skills:
                cc, _ = s
                if coolTime[cc]: coolTime[cc] -= 1
            queue.append((time+1, remain_HP, coolTime))
        
    return 
BFS(0)