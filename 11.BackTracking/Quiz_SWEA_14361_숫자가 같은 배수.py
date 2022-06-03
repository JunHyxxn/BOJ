"""
문제 출처 :  https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYCnY9Kqu6YDFARx&categoryId=AYCnY9Kqu6YDFARx&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
"""
T = int(input())


for t in range(1, T+1):
    N = input()
    nums = list(sorted(N, reverse=True))
    # l = len(nums)
    new_num = []
    visited = [False] * len(nums)
    result = False
    def BT(depth):
        global result
        if result: return
        if depth == len(N):
            origin, cand = int(N), int(''.join(map(str, new_num)))
            if cand>origin and cand%origin ==0:
                result = True
                return

        for i in range(len(nums)):
            if (nums[i] == '0' and len(new_num) == 0 ) or visited[i]: continue
            new_num.append(nums[i])
            visited[i] = True
            BT(depth+1)
            visited[i] = False
            new_num.pop()

    BT(0)
    print("#{} {}".format(t, "possible" if result else "impossible"))
