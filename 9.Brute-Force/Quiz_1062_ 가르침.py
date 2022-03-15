"""
문제
남극에 사는 김지민 선생님은 학생들이 되도록이면 많은 단어를 읽을 수 있도록 하려고 한다. 
그러나 지구온난화로 인해 얼음이 녹아서 곧 학교가 무너지기 때문에, 김지민은 K개의 글자를 가르칠 시간 밖에 없다. 
김지민이 가르치고 난 후에는, 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다. 
김지민은 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지 고민에 빠졌다.

남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다.
남극언어에 단어는 N개 밖에 없다고 가정한다. 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 단어의 개수 N과 K가 주어진다. N은 50보다 작거나 같은 자연수이고, K는 26보다 작거나 같은 자연수 또는 0이다. 
둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다. 
단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다. 모든 단어는 중복되지 않는다.

출력
첫째 줄에 김지민이 K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력한다.
"""
from itertools import combinations

n, k = map(int , input().split())

words = []
for _ in range(n):
    words.append(input())
    
if k < 5: ## anta, tica 도 학습을 못한다. => 단어 학습 불가.
    print(0)
elif k ==26: ## 모든 알파벳을 학습하면 모든 단어 학습 가능
    print(n)
else:
    ## anta, tica => a, c, i, n, t 는 기본으로 학습한다.
    basic = ['a','c','i','n','t']

    
    Words = [] ## 각 단어에서 basic 알파벳을 제거하고 남은 알파벳
    
    candidate = set() ## 모든 단어들에 포함된 알파벳 => 학습할 알파벳 후보군이 된다.
    
   ## words => 알파벳 제거 전 온전한 단어
    for word in words:
        dupli = set() ## 중복 제거하고 각 단어에 포함된 알파벳만 남긴다.
        
        for alpha in word:
            if alpha not in basic: ## basic에 포함된 단어는 이미 학습했으니 버린다.
                dupli.add(alpha)
                candidate.add(alpha)
                
        Words.append(list(dupli))
        
        
    ## 학습 단어인지 판단 function
    def check(ascii, Words):
        """
        ascii : 학습한 단어
        Words : 각 단어들을 anta, tica를 제외한 알파벳
        """
        cnt =0 
        for w in Words:
            flag = True
            ## 각 단어에 포함된 알파벳 중 하나라도 학습하지 않았다면 flag = False로 하고 break 걸어 시간 단축
            for alpha in w:
                if not ascii[ord(alpha)]:
                    flag = False
                    break
            ## 단어에 포함된 알파벳 모두 학습했다면 cnt + 1
            if flag:
                cnt += 1
                
        return cnt
    
    
    if len(candidate) <= k-5: 
        ## 남은 후보군 알파벳 j h g   // k = 21 => k-5 = 16 // 16개 알파벳 선택하는 경우에 안에 무조건 j, h, g 선택
        ## 남은 후보 알파벳이 선택할 개수보다 작으면 모든 단어 학습 가능함
        print(n)
    else:
        ## 남은 알파벳 후보 중 k-5 개 선택해 학습할 수 있는 단어 체크한다.
        c = list(combinations(candidate, k-5))
        result = float("-INF")
        
        ## 'z' => 122
        ascii = [False] * 123
        for cc in c:
            ## 선택할 알파벳 집합
            for alpha in cc:
                ## 선택한 알파벳들을 True로 만들어준다.
                ascii[ord(alpha)] = True
                        
            cnt = check(ascii, Words)
            ## max value만 남긴다.
            result = max(cnt, result)
            
            ascii = [False] * 123   
            
        print(result)