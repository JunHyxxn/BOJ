import sys

N = int(input())
plots = []

for _ in range(N):
    plots.append(tuple(map(int, sys.stdin.readline().split())))

plots.sort(key=lambda x:(x[1], x[0]))

for plot in plots:
    print('{0} {1}'.format(plot[0], plot[1]))