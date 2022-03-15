"""
문제
다음과 같은 네 가지 조건을 만족하는 자연수 수열 <a0, a1, ..., am>을 n에 대한 덧셈 체인이라고 한다.

1. a0 = 1
2. am = n
3. a0 < a1 < a2 < ... < am-1 < am
4. 각각의 k(1 ≤ k ≤ m)에 대해서, ak = ai + aj를 만족하는 두 자연수(같아도 됨) i와 j가 존재 (0 ≤ i, j ≤ k-1)

자연수 n이 주어졌을 때, 가장 길이가 짧은 n에 대한 덧셈 체인을 찾는 프로그램을 작성하시오.

입력
입력은 하나 또는 그 이상의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있으며, 자연수 n이 주어진다. 입력의 마지막 줄에는 0이 하나 주어진다. (1 ≤ n ≤100)

출력
각각의 테스트 케이스에 대해서, 해당하는 덧셈 체인을 공백으로 구분하여 한 줄에 출력한다. 가능한 덧셈 체인이 여러 가지인 경우에는 아무거나 출력한다.
"""

from copy import deepcopy

def solve(lead, chain):
    global answer, nums
    if chain[lead] == n: ## ai + aj = n이 된다면 조건 만족
        answer = deepcopy(chain[:-1]) if len(answer) >= len(chain) else answer ## 가장 짧은 덧셈 체인을 찾기 위해서 기존의 answer보다 짧거나 같은 값을 찾는다.
        nums[chain[-2]] = True ## 중복 계산을 줄이기 위해서 n의 직전 숫자가 나온다면 이미 완성시킬 수 있다는 것을 확인했으니 배열을 True로 만들어준다.
        return
    
    if len(answer) <= len(chain): ## 기존의 answer보다 길이가 길거나 같다면 굳이 탐색할 필요가 없다. [ 중요함!! ]
        return
    
    for i in range(len(chain)-1, -1,-1): ## 역으로 살펴본다
        number = chain[lead] + chain[i] ## ai + aj
        if number > n: ## n보다 크면 생략
            continue
        if nums[number]: ## ai + aj 를 통해 이미 n은 만든 적이 있다면 생략한다.
            continue    
        
    
        chain.append(number)
        solve(lead+1, chain)
        chain.pop()
        

    return 

while True:
    n = int(input())
    answer = [-1]*100
    chain = [1,2] ## 1, 2 는 고정
    nums = [False] * 101
    
    if n == 0: ## 0은 종료
        break
    elif n <=2: ## 1, 2 는 고정으로 정해져있다.
        answer = [i for i in range(1, n+1)]
        print(' '.join(map(str, answer)))
    else: ## n >= 3 인 경우
        solve(len(chain)-1, chain)
        answer += [n]
        print(' '.join(map(str, answer)))