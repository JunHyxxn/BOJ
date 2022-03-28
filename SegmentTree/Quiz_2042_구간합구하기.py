"""
문제
어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 
만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 
그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 
그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 
그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, 
a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.

출력
첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.
========================================================================================================================================================

"""
import math
import sys

input = sys.stdin.readline

def init(nums, tree, node, start, end):
    if start == end:
        tree[node] = nums[start]
        return tree[node]
    
    mid = (start+end)//2
    l_sum = init(nums, tree, node*2, start, mid)
    r_sum = init(nums, tree, 2*node + 1, mid+1, end)
    
    tree[node] = l_sum + r_sum
    return tree[node]

def query(tree, node, start, end, left, right):
    if left > end or right < start:
        return 0
    elif left <= start and end <= right:
        return tree[node]
    
    mid = (start + end) // 2
    l_sum = query(tree, node*2, start, mid, left, right)
    r_sum = query(tree, node*2 + 1 , mid + 1 , end, left, right)
    return l_sum + r_sum
def update(tree, node, index, diff, start, end):
    if index < start or index > end:
        return
    tree[node] += diff
    if start != end:
        mid = (start+end)//2
        update(tree, node*2, index, diff, start, mid)
        update(tree, node*2+1, index, diff, mid+1, end)
    
n,m,k = map(int, input().split())
nums = []
for _ in range(n):
    nums.append(int(input()))

tree = [0] * pow(2, math.ceil(math.log(n, 2) + 1)) ##  2^(|log2(n)|+1)
init(nums, tree, 1, 0, n-1)
for _ in range(m+k):
    command, x,y = map(int, input().split())
    if command == 1: ## update
        diff = y - nums[x-1]
        nums[x-1] = y
        update(tree, 1, x-1, diff, 0, n-1)
    elif command == 2: ## query
        result = query(tree, 1, 0, n-1, x-1, y-1)
        print(result)

