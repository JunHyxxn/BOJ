import sys


N = int(sys.stdin.readline())

numbers = list(map(int, sys.stdin.readline().strip().split()))
assert N == len(numbers)
temp = set(numbers)
new_nums = [i for i in range(len(temp))]
dict_data = {}
for i,j in zip(sorted(temp), new_nums):
    dict_data[i] = j
for val in numbers:
    sys.stdout.write('{} '.format(dict_data.get(val)))