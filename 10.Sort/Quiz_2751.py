import sys

N = int(sys.stdin.readline())

numbers = []
for i in range(N):
    numbers.append(int(sys.stdin.readline()))

sort_num = sorted(numbers)
for num in sort_num:
    print(num)