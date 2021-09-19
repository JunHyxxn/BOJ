# 단어 정렬

import sys

N = int(input())
words = set()
for _ in range(N):
    words.add(sys.stdin.readline().strip())

words = sorted(words, key=lambda x:[len(x), x])

for word in words:
    print(word)