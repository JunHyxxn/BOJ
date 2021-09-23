
"""
BOJ - 2753 윤년계산
4의 배수 and 100의 배수 아닐때  // 400의 배수
"""

year = int(input())
print(int(((year %4 == 0 and year%100!=0) or (year%400==0))))