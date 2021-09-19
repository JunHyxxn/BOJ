# 직사각형 만들기 - 네 번째 점 //  축에 평행한 직사각형이므로 좀 쉽게 가능

x_dict = {}
y_dict = {}
for i in range(3):
    x, y = map(int, input().split())

    if len(x_dict) == 0:
        x_dict[x] = 1
    else:
        if x in x_dict.keys():
            x_dict[x] = x_dict.get(x) +1
        else:
            x_dict[x] = 1

    if len(y_dict) == 0:
        y_dict[y] = 1
    else:
        if y in y_dict.keys():
            y_dict[y] = y_dict.get(y) +1
        else:
            y_dict[y] = 1


for k,v in x_dict.items():
    if v == 1:
        print(k, end =" ")

for k,v in y_dict.items():
    if v == 1:
        print(k)