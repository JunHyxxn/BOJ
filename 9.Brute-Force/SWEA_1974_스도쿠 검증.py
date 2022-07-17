"""
SWEA 1974 - 스도쿠 검증

9x9 퍼즐을 검사하여 스도쿠가 정상적으로 완성되었는지 판단한다.

이를 위해 9x9 퍼즐판을 총 3번 순회하면 쉽게 해결할 수 있다.

9 * 9 * 3 = 273
10번의 TC => 2730으로 굉장히 적은 연산 수이기 때문에 충분히 해결할 수 있다.
"""

T = int(input())
CHECK = sum([i for i in range(1,10)])
n = 9
def vertical_check(puzzle):
    for i in range(n):
        total = 0
        for j in range(n):
            total += puzzle[j][i]            
        if CHECK != total:
            return False
    return True
def horizontal_check(puzzle):
    for i in range(n):
        if CHECK != sum(puzzle[i]):
            return False
    return True
def square_check(puzzle):
    for i in range(0, 7, 3):
        for j in range(0, 7, 3):
            total = 0
            for k in range(3):
                for l in range(3):
                    total+= puzzle[i+k][j+l]
            if CHECK != total:
                return False
    return True

for t in range(1, T+1):
    puzzle = [list(map(int, input().split())) for _ in range(n)]
    res = 0
    if vertical_check(puzzle) and horizontal_check(puzzle) and square_check(puzzle):
        res = 1
    
    print("#{} {}".format(t, res))