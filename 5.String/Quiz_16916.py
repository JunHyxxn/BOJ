"""
문제
문자열 S의 부분 문자열이란, 문자열의 연속된 일부를 의미한다.

예를 들어, "aek", "joo", "ekj"는 "baekjoon"의 부분 문자열이고, "bak", "p", "oone"는 부분 문자열이 아니다.

문자열 S와 P가 주어졌을 때, P가 S의 부분 문자열인지 아닌지 알아보자.

입력
첫째 줄에 문자열 S, 둘째 줄에 문자열 P가 주어진다. 두 문자열은 빈 문자열이 아니며, 길이는 100만을 넘지 않는다. 또, 알파벳 소문자로만 이루어져 있다.

출력
P가 S의 부분 문자열이면 1, 아니면 0을 출력한다.

==========================================================================================================================================================

KMP Algorithms
- prefix, suffix
"""

# ## Brute-Force 는 100만 길이가 된다면 시간초과가 발생한다.
# def brute_force(string, sub):
#     for i in range(len(string) - len(sub)+1):
#         j = 0
#         while j < len(sub) and string[i+j] == sub[j]:
#             j += 1
#         if j == len(sub):
#             return 1
#     return 0
  
def failure(sub):
    fail = [0]*len(sub)
    m = len(sub)
    i = 1
    j = 0
    while i < m:
        if sub[i] == sub[j]:
            fail[i] = j+1
            i += 1
            j += 1
        elif j > 0:
            j = fail[j-1]
        else:
            fail[i] = 0
            i += 1
    return fail
            
def kmp(string, sub, fail):
    n = len(string)
    m = len(sub)
    i = 0 
    j = 0
    
    while i < n:
        
        if string[i] == sub[j]:
            if j == m-1:
                return 1
            i += 1
            j += 1
        elif j > 0:
            j = fail[j-1]
        elif j ==0:
            i += 1
    return 0
            
    
string = input()
sub = input()
failure = failure(sub)
print(kmp(string, sub, failure))