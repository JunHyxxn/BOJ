T = int(input())
## 2^31 만큼 필요하다면 => 21억
results = []
for t in range(1, T+1):
    command = input()
    a, b = 1, 1
    for elem in command:
        if elem == "L":
            b = a+b
        elif elem == "R":
            a = a+b
    
    results.append("#{} {} {}".format(t, a, b))

for result in results:
    print(result)

