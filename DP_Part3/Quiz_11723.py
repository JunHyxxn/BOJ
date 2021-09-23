"""
문제 - 집합
비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.

add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
all: S를 {1, 2, ..., 20} 으로 바꾼다.
empty: S를 공집합으로 바꾼다. 

입력
첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.

둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.

출력
check 연산이 주어질때마다, 결과를 출력한다.
"""
import sys
class S(object):
    def __init__(self):
        self.S = [0 for _ in range(21)]
    def add(self,x):
        if self.S[x] == 0:
            self.S[x] = 1
    def remove(self, x):
        if self.S[x]:
            self.S[x] = 0
    def check(self, x):
        return 1 if self.S[x] >0 else 0
    def toggle(self, x):
        self.S[x] = 0 if self.S[x] > 0 else 1
    def all(self):
        for i in range(1, len(self.S)):
            self.S[i] = 1
    def empty(self):
        for i in range(1, len(self.S)):
            self.S[i] = 0


M = int(sys.stdin.readline())
s = S()
for _ in range(M):
    commands = sys.stdin.readline().strip()
    if 'add' in commands:
        x = int(commands.split()[-1])
        s.add(x)
    elif 'remove' in commands:
        x = int(commands.split()[-1])
        s.remove(x)
    elif 'check' in commands:
        x = int(commands.split()[-1])
        output = s.check(x)
        print(output)
    elif 'toggle' in commands:
        x = int(commands.split()[-1])
        s.toggle(x)
    elif 'all' in commands:
        s.all()
    elif 'empty' in commands:
        s.empty() 
