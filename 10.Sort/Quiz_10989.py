
### 아래와 같이 dict으로 저장을 해도 여전히 시간,메모리 문제가 발생한다.
# import sys

# N = int(sys.stdin.readline())

# numbers = dict()
# for i in range(N):
#     key_value =  int(sys.stdin.readline())
#     if key_value in numbers:
#         numbers[key_value] = numbers.get(key_value) + 1
#     else:
#         numbers[key_value] = 1



# keys = sorted(numbers.keys())
# values = numbers.values()

# numbers = dict(zip(keys, values))

# for k,v in numbers.items():
#     for i in range(v):
#         sys.stdout.write(str(k)+ "\n")


# ### 메모리를 절약하기 위해서 미리 배열의 크기를 10001로 지정해두는게 포인트이다.

import sys
N = int(sys.stdin.readline())

arr = [0] * 10001

for i in range(N):
    num = int(sys.stdin.readline())

    arr[num] = arr[num] + 1

for i in range(len(arr)):
    if arr[i] != 0:
        for j in range(arr[i]):
            sys.stdout.write(str(i) + "\n")