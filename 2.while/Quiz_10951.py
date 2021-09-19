# import sys

# while(True):
#     print(sum([int(i) for i in sys.stdin.readline().strip().split()]))
    
import sys

for line in sys.stdin:
    a, b = map(int, line.split())
    print(a + b)