# 직각삼각형 알아내기 - 피타고라스 공식 사용

while True:
    numbers = list(map(int, input().split()))
    if 0 in numbers:
        break
    numbers.sort()

    x = numbers[0]
    y = numbers[1]
    z = numbers[2]

    if z**2 == x**2 + y**2:
        print("right")
    else:
        print("wrong")