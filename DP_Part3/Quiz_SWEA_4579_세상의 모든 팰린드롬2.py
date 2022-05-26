from pprint import pprint

T = int(input())
for t in range(1, T+1):
    words = input()
    n = len(words)
    palindrome = [[False] * n for _ in range(n)]
    ## init
    for i in range(n):
        palindrome[i][i] = True
    for i in range(n-1):
        if words[i] == words[i+1] or words[i] == "*" or words[i+1] == "*":
            palindrome[i][i+1] = True
    if n <= 2:
        print("#{} {}".format(t, "Exist" if palindrome[0][n - 1] else "Not exist"))
        continue
    for i in range(2, n+1):
        for j in range(n-i):
            ## j, j+i 는 양 끝을 의미
            if words[j] == "*" or words[j+i] == "*":
                palindrome[j][j+i] = True
                continue
            else:
                if palindrome[j+1][j+i-1] == True and words[j] == words[j+i]:
                    palindrome[j][j+i] = True
    print("#{} {}".format(t, "Exist" if palindrome[0][n - 1] else "Not exist"))
