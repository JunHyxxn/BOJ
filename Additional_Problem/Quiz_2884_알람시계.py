"""
BOJ - 2884 알람시계
hours min  - 45min
"""

hours, min = [int(i) for i in input().split()]

new = hours * 60 + min - 45
hours, min = new//60, new%60
if hours < 0:
    hours += 24
print(hours, min)