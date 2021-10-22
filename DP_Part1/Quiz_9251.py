"""
문제
LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

입력
첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

출력
첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.

============================================================================================================================================================

    ''  C   A   P   C   A   K
''  0   0   0   0   0   0   0
A   0   0   1   1   1   1   1
C   0   1   1   1   2   2   2
A   0   1   2   2   2   3   3
Y   0   1   2   2   2   3   3
K   0   1   2   2   2   3   4
P   0   1   2   3   3   3   4
위 과정이 LCS 구하는 과정이다. 전형적인 DP 문제이다.
"""
string1 = input()
string2 = input()
lcs = [[0]*(len(string2)+1) for _ in range(len(string1)+1)]

for i in range(len(string1)):
    for j in range(len(string2)):
        if string1[i] == string2[j]:
            lcs[i+1][j+1] = lcs[i][j]+1
        else:
            lcs[i+1][j+1] = max(lcs[i+1][j], lcs[i][j+1])
print(lcs[-1][-1])