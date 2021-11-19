def failure_function(text):
    m = len(text)
    failure = [0] * m
    i = 1
    j = 0
    
    while i < m:
        if text[i] == text[j]:
            failure[i] = j+1
            i += 1
            j += 1
        elif j > 0:
            j = failure[j-1]
        else:
            failure[i] = 0
            i += 1
    return failure

n = int(input())
text = input()
failure = failure_function(text)
print(n - failure[-1])