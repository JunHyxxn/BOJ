# N = int(input())
# cnt = 0
# for i in range(N):
#     inStr = input()
#     str_list = []
#     for s in inStr:
#         if len(str_list) == 0:
#             str_list.append(s)
#         elif str_list[-1] == s:
#             continue
#         elif str_list[-1] != s:
#             str_list.append(s)
#     str_set = set(str_list)
#     if len(str_list) == len(str_set):
#         cnt += 1
# print(cnt)



import sys

num = int(input())
cnt = 0
for _ in range(num):
    words = sys.stdin.readline().strip()
    alpha_set = set()
    prev = ''
    first_flag = True
    for i in range(len(words)):
        if prev != words[i] and words[i] in alpha_set:
            break
        alpha_set.add(words[i])
        prev = words[i]
        if i == len(words) -1:
            cnt += 1
        
print(cnt)