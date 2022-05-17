"""
문제 출처 : https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
"""
T = int(input())
results=  []
for t in range(1, T+1):
    N = int(input())
    nums = list(map(int, input().split()))
    total = 0
    for num in nums:
        total ^= num
    if total == 0:
        results.append("#{} {}".format(t, sum(nums)-min(nums)))
        continue
    elif total != 0:
        results.append("#{} {}".format(t, "NO"))

for res in results:
    print(res)


