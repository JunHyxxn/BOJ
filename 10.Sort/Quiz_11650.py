# ## 좌표 정렬하기
# # x로 정렬하고 x가 같은 경우만 y값으로 비교 
# import sys

# # def Sort(plots):
# #     for i in range(1, len(plots)):
# #         for j in range(0, i+1):
# #             if plots[i][0] < plots[j][0]:
# #                 temp = plots[j]
# #                 plots[j] = plots[i]
# #                 plots[i] = temp

# #     for i in range(1, len(plots)):
# #         for j in range(0, i+1):
# #             if plots[i][0] == plots[j][0] and plots[i][1] < plots[j][1]:
# #                 temp = plots[j]
# #                 plots[j] = plots[i]
# #                 plots[i] = temp
# #     return plots



# N = int(sys.stdin.readline())
# plots = []

# for i in range(N):
#     plots.append(tuple(map(int, sys.stdin.readline().split())))


# plots = Sort(plots)

# for plot in plots:
#     print('{0} {1}'.format(plot[0], plot[1]))





## 위와 같은 정렬 방식은 시간 초과의 오류가 발생한다.
## 이를 방지하기 위해선 힙-정렬, 병합-정렬등의 O(nlogn) 방식이 필요
## 이를 함수로 쉽게 구현해본다.

import sys

N = int(sys.stdin.readline())
plots = []

for i in range(N):
    plots.append(tuple(map(int, sys.stdin.readline().split())))

plots.sort(key=lambda x:(x[0], x[1]))



for plot in plots:
    print('{0} {1}'.format(plot[0], plot[1]))


# # 아이템 첫 번째 인자를 기준으로 오름차순으로 먼저 정렬하고,
# # 그리고 그 안에서 다음 두 번째 인자를 기준으로 내림차순으로 정렬하게 하려면,
# #  다음과 같이 할 수 있다.
# # e = [(1, 3), (0, 3), (1, 4), (1, 5), (0, 1), (2, 4)]
# # f = sorted(e, key = lambda x : (x[0], -x[1])) ★