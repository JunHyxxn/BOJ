import sys

N = int(sys.stdin.readline())

numbers = []
for i in range(N):
    numbers.append(int(sys.stdin.readline()))

sort_num = sorted(numbers)
for num in sort_num:
    print(num)




# ## 함수가 아닌 배열을 이용해서 구현해보자.

# N = int(input())


# arr = []
# for i in range(N):
#     arr.insert(i, int(input()))

# for i in range(N):
#     for j in range(i+1, N):
#         if arr[i] > arr[j]:
#             temp = arr[i]
#             arr[i] = arr[j]
#             arr[j] = temp
    
# for num in arr:
#     print(num)