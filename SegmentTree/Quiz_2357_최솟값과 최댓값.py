"""
문제
N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 또는 제일 큰 정수를 찾는 것은 어려운 일이 아니다. 
하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.

여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다. 
예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최소, 최댓값을 찾아야 한다. 
각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.

입력
첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다. 다음 M개의 줄에는 a, b의 쌍이 주어진다.

출력
M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 최솟값, 최댓값 순서로 출력한다.
============================================================================================================================================
Segment Tree를 이용한다면 구간에 대한 최솟값과 최댓값을 쉽게 저장해놓을 수 있다.
이를 이용해 해결하면 된다.
"""


import math
import sys
input = sys.stdin.readline
n,m = map(int, input().split())
nums = []
for _ in range(n):
    nums.append(int(input()))
    
tree = [0] * (2**( math.ceil( math.log(n, 2) ) + 1 ) - 1)

def init(nums, tree, node, start, end):
    if start == end:
        tree[node] = (nums[start], nums[start])
        return (tree[node])
    mid = (start + end) //2
    l_node = init(nums, tree, node*2, start, mid)
    r_node = init(nums, tree, node*2 + 1, mid+1, end)
    tree[node] = (min(l_node[0], r_node[0]), max(l_node[1], r_node[1]))
    return tree[node]
init(nums, tree, 1, 0, n-1)

def query(tree, node, start, end, left, right):
    """
    start : nums start index
    end : nums finish index
    left : range start index
    right : range end index
    """
    if left > end or right < start: ## 완전 벗어나는 경우
        return (float("INF"), float("-INF"))
    elif left <= start and right >= end: ## 포함되는 경우
        return tree[node]
    
        
    mid = (start + end) //2
    l_res = query(tree, node*2, start, mid, left, right)
    r_res = query(tree, node*2+1, mid+1, end, left, right)
    return (min(l_res[0], r_res[0]), max(l_res[1], r_res[1]))

for _ in range(m):
    a, b = map(int, input().split())
    result = query(tree, 1, 0, n-1, a-1, b-1)
    print(' '.join(map(str, result)))