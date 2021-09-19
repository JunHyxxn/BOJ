"""
d(n) => n + n//10...0   ... + n%10
ㄴ=> 각 자리수의 합

self number : 생성자가 자기 자신 ex) 1, 3, 5, 
"""

def product(n):
    total = n
    while(True):
        if n // 10 != 0:
            total += n %10
            n = n//10
        elif n // 10 ==0:
            total += n 
            break
    return total


numbers = {i for i in range(1, 10000+1)}
non_self_numbers = set()
for i in range(1, 10001):
    selfnumber = product(i)
    if selfnumber > 10000:
        continue
    non_self_numbers.add(selfnumber)


for i in non_self_numbers:
    numbers.remove(i)

for number in numbers:
    print(number)


# print(product(9971))
# print(9971+9+9+7+1)