import sys
from collections import Counter

def average(numbers):
    return round((sum(numbers))/len(numbers))
def median(numbers):
    numbers = sorted(numbers)
    if len(numbers)%2 == 0:
        median = (numbers[len(numbers)//2] + numbers[(len(numbers)//2) - 1]) / 2
        return median
    else:
        return numbers[(len(numbers)//2)]

def mode(numbers):
    numbers = sorted(numbers)
    c = Counter(numbers)
    order = c.most_common()

    if len(numbers) > 1:
        if order[0][1] == order[1][1]:
            return order[1][0]
        else:
            return order[0][0]
    else:
        return order[0][0]


def arrange(numbers):
    return 0 if len(numbers)<2 else (max(numbers) - min(numbers))

N = int(sys.stdin.readline())

numbers = []
for i in range(N):
    numbers.append(int(sys.stdin.readline()))

print(average(numbers))
print(median(numbers))
print(mode(numbers))
print(arrange(numbers))




