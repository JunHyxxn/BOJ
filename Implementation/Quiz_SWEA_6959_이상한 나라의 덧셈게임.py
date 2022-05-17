"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&problemLevel=4&contestProbId=AWjlH0k63joDFAVT&categoryId=AWjlH0k63joDFAVT&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=PYTHON&select-1=4&pageSize=10&pageIndex=6

이 문제의 핵심은 

자리 수들을 더한 값의 변화가 어떻게 되는지가 핵심이다.

123 은 각 자리수들을 전부 더해도 6으로 차례대로 12 3=> 33 => 6
                                            1 23=> 15 => 6
                                            으로 어떻게 더하든 변화가 없다.
                                            
55 같은 경우는 5 5 => 10 => 1 로 중간에 10이 되는데 
10이 되면 각 자리 수의 합이  1로 기존에 10에서 9만큼 빠진 수가 된다.

🔥 자리 수의 합이 10미만인 경우에는 어떻게 합치든 유지가 되지만
자리 수의 합이 10이 넘어가면 그 이후로는 -9만큼씩 줄어든다.

이를 이용하면 쉽게 해결할 수 있다.

모든 자리의 합이 몇번 9만큼씩 줄일 수 있는지 살펴보면 된다.

다만 주의할 점은, 각 자리의 합이 9, 18 ... 9의 배수인 경우 
9일때는 줄어들지 않기 때문에 -1만큼 진행해줘야한다.
"""
T = int(input())


for t in range(1, T+1):
    nums = input()
    digit = len(nums)
    nums = [int(num) for num in nums]
    total = sum(nums)
        
    turn = digit-1 + total//9
    if total %9 == 0:
        turn -= 1
    print(turn)

    if turn % 2:
        print("#{} {}".format(t, "A"))
    else:
        print("#{} {}".format(t, "B"))