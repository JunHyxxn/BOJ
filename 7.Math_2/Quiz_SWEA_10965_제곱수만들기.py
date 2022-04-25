"""
SWEA 문제 출처 : https://www.acmicpc.net/status?user_id=tlaxh000&problem_id=13560&from_mine=1
※ SW expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.

어떤 자연수 A가 주어진다. 여기에 자연수 B를 곱한 결과가 거듭제곱수가 되는 최소의 B를 구하는 프로그램을 작성하라. 여기서 자연수는 1이상인 정수를 뜻한다.

[입력]

첫 번째 줄에 테스트 케이스의 수 T 가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 하나의 자연수 A(1≤A≤107) 가 주어진다.

[출력]
각 테스트 케이스마다 A에 곱한 결과가 거듭제곱수가 되는 최소의 자연수를 출력한다.
================================================================================================================================================
개인적인 의견으로는 별로 좋은 문제가 아니다고 생각한다.

주어진 수의 절반까지 나눠가며 소인수를 기록하는 방식은 시간초과 / Worst Case - 약 하나의 케이스에 900만의 연산이 필요. 이는 이해한다.

하지만 소수를 구해 나누는 과정을 최소화하려 했다.
에라토스테네스의 체가 소수를 구하는 최적의 방식으로 알려져 있지만, 이는 메모리 초과발생.
소수를 구하는 다른 방법을 구한 후 위의 방식을 적용해야한다.
별로 좋은 문제는 아닌것 같다.
"""

p = 10**7
primes=[2]
for i in range(3,int(p**0.5),2):
    for prime in primes:
        if i%prime == 0: break
    else:
        primes.append(i)

T = int(input())
result = []
for t in range(1, T+1):
    A = int(input())
    res = 1
    if A**0.5 != int(A**0.5):        
        for num in primes:
            cnt = 0
            while A%num == 0:
                A//=num
                cnt += 1
            if cnt%2 ==1:
                res *= num
            if A < num or A == 1:
                break
        if A > 1:
            res *= A
    result.append(res)
        
    

for i, res in enumerate(result):
    print("#{} {}".format(i+1, res))