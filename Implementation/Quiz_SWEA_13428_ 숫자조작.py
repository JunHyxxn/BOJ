"""
문제 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AX4EJPs68IkDFARe
"""

from collections import defaultdict
from copy import deepcopy


T = int(input())
def swap(N, i, j):
    K = deepcopy(N)
    temp = K[i]
    K[i] = K[j]
    K[j] = temp    
    return ''.join(K)

results = []
for t in range(1, T+1):
    N = input()
    
    num_idx = {str(i) : -1 for i in range(10)}
    
    for i in range(len(N)-1, -1, -1):
        if num_idx[N[i]] == -1:
            num_idx[N[i]] = i
            
    min_result = N
    max_result = N
    i = 0
    N = list(N)
    for max_value in range(9, -1, -1):
        max_value = str(max_value)
        idx = num_idx[max_value]
        if idx == -1: continue
        if idx == i:
            i += 1
            continue
        for j in range(i, idx):
            if N[j] >= max_value: continue
            max_result = swap(N, idx, j)            
            break
        if max_result != "".join(N):
            break
    i = 0
    isDoubleCheck = 0
    for min_value, idx in num_idx.items():
        if idx == -1: continue
        if idx == i:
            i += 1
            continue
        for j in range(i, idx):
            if N[j] <= min_value or (min_value=="0" and j==0): 
                continue
            min_result = min(int(min_result), int(swap(N, idx, j)))
            isDoubleCheck += 1
            break
        if isDoubleCheck == 2:
            break
    
    results.append("#{} {} {}".format(t, min_result, max_result))
    
for result in results:
    print(result)
    
    
    

