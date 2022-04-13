"""
문제
비밀 요원 상근이는 시리아의 화학 무기에 대한 정보를 보관하고 있는 금고를 열려고 한다. 
금고를 열려면 금고에 암호를 입력해야 한다. 암호는 숫자 네 개로 이루어져 있다.

상근이는 시도해야 하는 암호의 목록을 가지고 있다. 
목록에는 매우 많은 암호가 적혀있기 때문에, 암호가 될 수 없는 것을 미리 지우려고 한다.

올바른 암호는 24 조건을 만족한다. 
암호를 이루는 수 네 개 사이에 덧셈, 뺄셈, 곱셈, 나눗셈, 괄호를 적절히 삽입해서 24를 만들 수 있을 때, 그 암호를 24 조건을 만족한다고 한다.

예를 들어, (4, 7, 8, 8)은 (7-8/8)*4 = 24이기 때문에, 24 조건을 만족한다. 
하지만, (1, 1, 2, 4)나 (1, 1, 1, 1)과 같은 암호는 24 조건을 만족하지 않는다. 따라서, 이러한 암호는 시도해볼 필요가 없다.

가능한 암호가 모두 주어졌을 때, 24 조건을 만족하는지 안 하는지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 개수는 100개를 넘지 않는다. 
각 테스트 케이스는 한 줄로 이루어져 있고, 가능한 암호를 나타내는 네 정수 a, b, c, d (1 ≤ a, b, c, d ≤ 9)가 주어진다.

출력
각 테스트 케이스 마다, 입력으로 주어진 암호가 24 조건을 만족하면 "YES"를, 만족하지 않으면 "NO"를 출력한다.
========================================================================================================================
4 * 4 * 3 * 4 * 2 * 4 * 1 => 식을 만드는 경우의 수
식은 총 4^4 * 6 = 1536 개가 만들어진다.

식이 만들어지면 bracket 을 만들어주는데 각 식마다 총 6가지 경우의 수를 만들 수 있다.
즉 1536 * 6 = 9216 개의 식을 생성한다.

이 과정이 최대 100번 이루어진다. 즉 921600 번 이루어지기 때문에 Brute-Force로 충분히 탐색가능하다.

탐색이 완료되면 추가 탐색을 방지한다.
"""
from fractions import Fraction
from collections import deque

def calc(expression):
    expression = deque(expression)
    nums = []
    opers = []
    while expression:
        elem = expression.popleft()
        if type(elem) == int or type(elem) == Fraction:
            nums.append(elem)
        elif type(elem) == str:
            if elem == '*' or elem == '/':
                x = nums.pop()
                y = expression.popleft()
                if elem == '*':
                    nums.append(x*y)
                elif elem == '/':
                    if y == 0: return -1
                    nums.append(Fraction(x,y))
            else:
                opers.append(elem)
    
    x = nums[0]
    for i in range(len(opers)):
        if opers[i] == '+':
            x = x + nums[i+1]
        elif opers[i] == '-':
            x = x - nums[i+1]
            
    return x
        
def bracket(exp):
    """
    O : operand
    _ : operator
    """
    flag = False
    
    ## 1. (O _ O) _ O _ O
    res = calc(exp[:3])
    res = calc([res]+exp[3:])
    if res == 24.0:
        flag = True
        return flag
    
    ## 2. (O _ O _ O) _ O
    res = calc(exp[:5])
    res = calc([res] + exp[5:])
    if res == 24.0:
        flag = True
        return flag
    ## 3. O _ (O _ O) _ O
    res = calc(exp[2:5])
    res = calc(exp[:2] + [res] + exp[5:])
    if res == 24.0:
        flag = True
        return flag
    
    ## 4. O _ (O _ O _ O)
    res = calc(exp[2:])
    res = calc(exp[:2] + [res])
    if res == 24.0:
        flag = True
        return flag
    
    ## 5. O _ O _ (O _ O)
    res = calc(exp[4:])
    res = calc(exp[:4] + [res])
    if res == 24.0:
        flag = True
        return flag
    
    ## 6. (O _ O) _ (O _ O)
    res1 = calc(exp[:3])
    res2 = calc(exp[4:])
    res = calc([res1, exp[3], res2])
    if res == 24.0:
        flag = True
        return flag
    
    return flag    
    
def Solve(exp, visited, operator, operand):
    global result
    if result: return
    if len(exp) == 7:
        result = bracket(exp)
        return
        
    if len(exp)%2 ==0: ## operand
        for i in range(4):
            if visited[i]: continue
            exp.append(operand[i])
            visited[i] = True
            Solve(exp, visited, operator, operand)
            exp.pop()
            visited[i] = False
            
    elif len(exp)%2 ==1: ## operator
        for i in range(4):
            exp.append(operator[i])
            Solve(exp, visited, operator, operand)
            exp.pop()
            
t = int(input())
for _ in range(t):
    operator = ['+','-','*','/']
    operand = list(map(int, input().split()))
    visited = [False]* 4
    result = False
    exp = []
    Solve(exp, visited, operator, operand)
    print('YES' if result else 'NO')
