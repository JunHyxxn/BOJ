"""
BOJ - 11729 하노이 탑 이동순서
"""

def Hanoi(start, destination, n):
    if n ==1: 
        print(start, destination)
        return 0
    Hanoi(start, 6-start-destination, n-1) ## n-1만큼은 시작점에서 도착지도 시작지도 아닌 지점으로 이동
    print(start, destination) ## n번째 원반 도착지로 이동
    Hanoi(6-start-destination, destination, n-1) ## n-1만큼의 원반 이제 도착지로 이동
    return 0
N = int(input())
print(2**N-1)
Hanoi(1,3, N)