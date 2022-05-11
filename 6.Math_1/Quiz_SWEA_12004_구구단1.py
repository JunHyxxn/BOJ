"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AXkcWgFa8sADFAS8&categoryId=AXkcWgFa8sADFAS8&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=3
※ SW expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.


정수 N이 주어졌을 때, N 이 1 이상 9 이하의 두 수 a, b의 곱으로 표현될 수 있는지 판단하라.
 

[입력]

첫 번째 줄에 테스트 케이스의 수 TC가 주어진다. 이후 TC개의 테스트 케이스가 새 줄로 구분되어 주어진다. 각 테스트 케이스는 다음과 같이 구성되었다.

    ∙ 하나의 정수 N이 주어진다. (1≤N≤100)
 

[출력]

각 테스트 케이스마다
    ∙ N 이 1 이상 9 이하의 두 수 a, b의 곱으로 표현될 수 있으면 “Yes”, 아니면 “No” 를 출력하라.
"""

T = int(input())
for t in range(1, T+1):
    N = int(input())
    i = 1
    res = False
    while i < 10:
        temp = N / i
        if temp < 10 and temp == N//i:
            res = True
            break
        i += 1
    print("#{} {}".format(t, "Yes" if res else "No"))
    

