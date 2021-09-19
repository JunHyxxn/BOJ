# Black Jack

# 각 카드에는 양의 정수.
# 딜러는 N장의 카드를 모두 숫자가 보이도록 바닥에 둔다.
# 딜러는 숫자 M을 크게 외찬다.

# 플레이어는 제한 시간 안에 N장의 카드 중에서 3장의 카드를 골라야 한다.
# 이 때, 카드의 합은 M을 넘지 않으며 M과 가장 가깝게 만들어야 한다.



N, M = map(int, input().split())
deck = list(map(int, input().split()))

max = 0
for first in deck:
    for second in deck:
        if first == second:
            continue
        for third in deck:
            if third == first or third == second:
                continue
            sum = first + second + third
            if max <= sum and sum <= M:
                max = sum

print(max)

