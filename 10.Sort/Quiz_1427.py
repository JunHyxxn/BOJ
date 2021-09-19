# 소트인사이드

N = input()
numbers = []
for i in N:
    numbers.append(i)

numbers = sorted(numbers, reverse = True)

print(''.join(numbers))