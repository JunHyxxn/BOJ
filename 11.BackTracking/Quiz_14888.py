
"""
N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.

예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 
예를 들어, 아래와 같은 식을 만들 수 있다.

1+2+3-4×5÷6
1÷2+3+4-5×6
1+2÷3×4-5+6
1÷2×3-4+5+6

식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 
음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

1+2+3-4×5÷6 = 1
1÷2+3+4-5×6 = 12
1+2÷3×4-5+6 = 5
1÷2×3-4+5+6 = 7
N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

==================================================================================================================================================================================================================
Input 
첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 
둘째 줄에는 A1, A2, ..., AN이 주어진다.(1 ≤ Ai ≤ 100) 
셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다. 

ex) 
2
5 6
0 0 1 0

Output
첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. 
연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 
또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
ex) 
30
30


==================================================================================================================================================================================================================
Key Point
N은 11 이하로 Worst Case n=11이라도
연산자 10개를 선택하는 문제가 되고 이는 10! == 약 360만 
Brute-Force(DFS)로 해결해도 시간내에 해결이 된다.
따라서 DFS로 접근한다.
"""

## Ver1. -> 시간초과로 실패
# import sys

# N = int(sys.stdin.readline())

# nums = list(map(int, sys.stdin.readline().split()))
# ## 각 연산자의 개수
# plus, minus, multiple, divide = map(int, sys.stdin.readline().split())
# ## 연산자를 리스트에 담아둔다.
# operators = ['+']*plus + ['-']*minus + ['*'] * multiple + ['/'] * divide
# flag = [False] * (plus+minus+multiple+divide)
# ## 수행할 연산자 순서를 담은 리스트
# simul = []

# ## 10억 
# _max = -999999999
# _min = 999999999

# def calc(num1, oper, num2):
#     if oper == '+':
#         return num1 + num2
#     elif oper == '-':
#         return num1 - num2
#     elif oper == '*':
#         return num1 * num2
#     elif oper == '/':
#         if num1 < 0 and num2 >0:
#             return ((-1*num1)//num2) * (-1)
#         else:
#             return num1 // num2

# def solution(depth, simul):
#     global _max, _min
#     if depth == N-1:
#         _in = nums[0]
#         for i in range(N-1):
#             _in = calc(_in, simul[i], nums[i+1])
#         _min = min(_min, _in)
#         _max = max(_max, _in)
#         return

#     for i in range(len(operators)):
#         if flag[i] == False:
#             flag[i] = True
#             simul.append(operators[i])
#             solution(depth+1, simul)
#             flag[i]= False
#             simul.pop()

# solution(0, simul)

# sys.stdout.write("{0}\n{1}".format(_max,_min))



## Ver2.0  
## operator를 리스트로 담아 접근하지 않고 개수를 각각 정수형태로 저장하고 이를 depth가 진행됨에 따라 -1 하는 방식으로 수정
## 한번에 계산하지 않고 depth가 진행됨에 따라 바로바로 계산하여 조금 더 간편화 수정.
import sys
N = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))
plus, minus, multiple, divide = map(int, sys.stdin.readline().split())
_max = -1e9
_min = 1e9
def solution(depth, ans, plus, minus, multiple, divide):
    global _max, _min
    if depth == N:
        _max = max(_max, ans)
        _min = min(_min, ans)
        return

    if plus:
        solution(depth+1, ans + nums[depth], plus -1, minus, multiple, divide)
    if minus:
        solution(depth+1, ans - nums[depth], plus, minus-1, multiple, divide)
    if multiple:
        solution(depth+1, ans * nums[depth], plus, minus, multiple-1, divide)
    if divide:
        solution(depth+1, -1*(-1*ans//nums[depth]) if (ans < 0 and nums[depth]>0) else (ans // nums[depth]), plus, minus, multiple, divide-1)


solution(1, nums[0], plus, minus, multiple, divide)
sys.stdout.write("{}\n{}".format(_max, _min))