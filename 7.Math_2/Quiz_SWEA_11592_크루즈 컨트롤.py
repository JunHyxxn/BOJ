"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AXfRhBl6KTMDFAS5&categoryId=AXfRhBl6KTMDFAS5&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=3
※ SW expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.

흥부네는 장영실이 개발한 자율주행차를 구매한 이후, 차를 타고 한양 시내를 드라이브하고는 한다. 
자율주행차의 성능은 최고지만, 아직도 말을 타고 다니는 사람들 때문에 최고 속도를 즐기지 못하는 게 문제이다.
종로거리는 Dkm의 길이를 가지며, 흥부네는 0km 지점에서 동쪽으로 드라이브를 시작한다. 
종로거리에는 현재 N마리의 다른 말들이 있으며 모두 동쪽으로 움직인다. 
이 중 i번째 말은 현재 Ki 킬로미터 지점에 있으며 맨 처음 Si km/시간 의 속도로 움직인다. 이 때, N은 1 혹은 2이다.

조선의 사람들은 정말 예의 바르기 때문에, 절대 다른 사람을 추월하지 않는다. 
각 사람을 종로거리 위의 크기가 없는 점이라고 생각하자. 만약 현재 앞에 있는 사람의 속도가 자신보다 느리다면, 
늦게 온 사람은 앞에 있는 사람의 속도에 맞춰서 느리게 주행한다.

흥부네는 크루즈 컨트롤 기능을 이용하여 종로거리 Dkm을 주행하려고 한다. 
크루즈 컨트롤 기능을 사용하면, 흥부네는 고정된 속도를 자율주행차에 입력할 수 있고, 자
율주행차는 이 속도에 맞춰서 주행을 시작한다. 
흥부는 다른 말을 추월하거나 속도를 줄이고 싶지 않아서, 이러한 일이 없는 한 최고 속도로 주행하려고 한다.
가능한 속도는 얼마인가?

[입력]

첫 번째 줄에 테스트 케이스의 수 TC가 주어진다. 이후 TC개의 테스트 케이스가 새 줄로 구분되어 주어진다. 각 테스트 케이스는 다음과 같이 구성되었다.

∙ 첫 번째 줄에 두 정수 D, N이 주어진다. (1≤D≤109, 1≤N≤2)

∙ 이후 N개의 줄에 두 정수 Ki, Si가 주어진다(0 < Ki < D, 1 ≤ Si ≤ 10000). 모든 Ki는 서로 다르다.

 

[출력]

각 테스트 케이스 마다 결과를 출력하라. 절대 오차 혹은 상대 오차가 10-5이하일 경우 통과된다.
========================================================================================================================================================================
- 말이 한마리인 경우
- 말이 두 마리인 경우
    - 말이 주어진 거리 안에 만나는 경우
    - 말이 서로 만나지 않는 경우

위의 경우의 수를 고려해 방정식을 해결하면 된다.
"""
from fractions import Fraction
    
T = int(input())

for t in range(1, T+1):
    D, N = map(int, input().split())
    if N == 1:
        K, S = map(int, input().split())
        time = Fraction((D-K),S)
        
    elif N == 2:
        rk, rs = map(int, input().split())
        lk, ls = map(int, input().split())
        if lk > rk:
            rk, lk = lk, rk
            rs, ls = ls, rs
        
        if rs < ls: ## 만난다
            contact_time = Fraction((rk-lk), (ls-rs))
            contact_dist = contact_time * ls + lk
            if contact_dist > D:
                time = Fraction((D-lk), ls)
            else:
                remain_time = Fraction((D-contact_dist), rs)
                time = contact_time + remain_time
        elif rs >= ls: ## 만나지 않는다.
            time = Fraction((D-lk), ls)
            
            

    speed = Fraction(D, time)
    print("#{} {:.7f}".format(t, speed.__float__()))
    
