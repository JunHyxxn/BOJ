"""
문제
45656이란 수를 보자.

이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.

N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.

입력
첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.

출력
첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.

============================================================================================================

Key point.
각 숫자가 어떻게 뻗어나가는지를 파악하면 쉽게 해결할 수 있다.

0은 1로, 1은 0과 2로, ... 8은 7과 9로, 9는 8로 뻗어나가는 것을 알 수 있다.

따라서 

0의 개수 = 1의 개수
1의 개수 = 0의 개수 + 2의 개수
2의 개수 = 1의 개수 + 3의 개수
...
8의 개수 = 7의 개수 + 9의 개수
9의 개수 = 8의 개수
임을 알 수 있다.

이를 이용하면 최종 가지에 올 수 있는 숫자를 알 수 있고, 그 숫자의 개수가 총 가지수가 될 것이다.
"""
import sys

nums = [1 for _ in range(10)]
nums[0] = 0
N = int(sys.stdin.readline())
for _ in range(N-1):
    temp = [0 for _ in range(10)]
    for i in range(len(nums)):
        if i == 0:
            temp[i] = nums[i+1]
        elif i == 9:
            temp[i] = nums[i-1]
        else:
            temp[i] = nums[i-1] + nums[i+1]

    nums = temp
    

print(sum(nums)%1000000000)