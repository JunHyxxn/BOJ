## TLE
# from collections import deque

# A = input()
# T = input()
# origin = deque(T)

# def left(queue, pattern):
#     temp = deque()
#     l = len(pattern)
#     while queue:
#         now_str = queue.popleft()
#         temp.append(now_str)
#         if list(temp)[-1*l:] == list(pattern):
#             temp = list(temp)[:-1*l] + list(queue)
            
#             return True, deque(temp)
    
#     return False, temp
    
    
# def right(queue, pattern):
#     temp = deque()
#     l = len(pattern)
#     while queue:
#         now_str = queue.pop()
#         temp.appendleft(now_str)
#         if list(temp)[:l] == list(pattern):
#             temp = list(queue) + list(temp)[l:]
            
#             return True, deque(temp)
    
#     return False, temp


# flag = True
# while flag:
#     flag, origin = left(origin, A)    
#     if flag:
#         flag, origin = right(origin, A)
# print(''.join(map(str,origin)))


## TLE
# A = input()
# T = input()
# flag = True
# while True:
#     if flag:
#         res = T.find(A)
#     else:
#         res = T.rfind(A)
    
#     if res == -1:
#         print(T)
#         break
#     else:
#         flag = not flag
#         T = T[:res] + T[res+len(A):]


"""
TLE 해결하기 위해서
왼쪽에서 패턴이 발견될 때까지 하나의 스택에 담고
왼쪽에서 발견된 후 오른쪽에서 패턴 발견될 때까지 하나의 스택에 담는데
이를 left pointer가 right pointer보다 크거나 같아질떄까지 한다.
이렇게 하면 중간 만나는 지점까지 진행하게 된다.

다만 중간지점에 왼쪽과 오른쪽에 패턴이 나눠서 들어가 있을 수 있다.
그렇기 때문에 왼쪽 스택과 오른쪽 스택을 합친 후 다시 한 번 패턴 검사가 필요하다.
"""
A = input()
T = input()

def find(T, A):
    l = len(A)
    left_idx = 0
    right_idx = len(T)-1
    l_str = []
    r_str = []
    while left_idx <= right_idx:
        while left_idx <= right_idx:
            l_str.append(T[left_idx])
            left_idx +=1
            if ''.join(l_str[-1*l:]) == A:
                del l_str[-1*l:]
                break
            
        while left_idx <= right_idx:
            r_str.append(T[right_idx])
            right_idx -=1
            if ''.join(r_str[-1 * l:]) == ''.join(list(reversed(A))):
                del r_str[-1*l:]
                break
        
    return l_str + r_str[::-1]

T = ''.join(find(T, A))
T_idx = 0
while True:
    if T_idx == -1: break
    T_idx = T.find(A)
    if T_idx != -1:
        T = T[:T_idx] + T[T_idx+len(A):]
print(T)

