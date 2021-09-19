# num = int(input())
# i=1
# while True:
#     if num*2 <= i *(i+1):
#         last = i*(i+1) // 2
#         break
#     else:
#         i +=1

# order = last - num
# if i%2 == 1:
#     print('{0}/{1}'.format(1+order, i-order))
# else:
#     print('{0}/{1}'.format(i-order, 1+order))









num = int(input())
i = 1
total = 0
while(True):
    # total = sum(num for num in range(i+1))
    if num <= i* (i+1) /2:
        break
    i += 1

diff = int((i*(i+1)/2) - num)
if i % 2 == 0:
    print('{0}/{1}'.format(i-diff, 1+diff))
elif i % 2 == 1:
    print('{0}/{1}'.format(1+diff, i-diff))


