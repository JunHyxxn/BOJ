# 나이순 정렬
import sys
N = int(sys.stdin.readline())
infoes = []
for i in range(N):
    infoes.append([i+1, sys.stdin.readline().split()])

infoes.sort(key=lambda x:[int(x[1][0]), x[0]])

for info in infoes:
    print('{0} {1}'.format(info[1][0], info[1][1]))
     