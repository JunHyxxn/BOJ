"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWLL7kaaAPsDFAUW
"""
T = int(input())
keypad = {chr(i) : 1 for i in range(ord('a'), ord('z')+1)}
def init(keypad):
    for i in range(ord('a'), ord('c')+1):
        keypad[chr(i)] = '2'
    for i in range(ord('d'), ord('f')+1):
        keypad[chr(i)] = '3'
    for i in range(ord('g'), ord('i')+1):
        keypad[chr(i)] = '4'
    for i in range(ord('j'), ord('l')+1):
        keypad[chr(i)] = '5'
    for i in range(ord('m'), ord('o')+1):
        keypad[chr(i)] = '6'
    for i in range(ord('p'), ord('s')+1):
        keypad[chr(i)] = '7'
    for i in range(ord('t'), ord('v') + 1):
        keypad[chr(i)] = '8'
    for i in range(ord('w'), ord('z')+1):
        keypad[chr(i)] = '9'
init(keypad)

for t in range(1, T+1):
    S, N = input().split()
    N = int(N)
    words = input().split()
    temp = []
    for word in words:
        if len(word) == len(S):
            temp.append(word)
    words = temp

    pos = [1] * len(words)
    for i in range(len(S)):
        for j in range(len(words)):
            if not pos[j]: continue
            if keypad[words[j][i]] != S[i]:
                pos[j] = 0
    print("#{} {}".format(t, sum(pos)))


