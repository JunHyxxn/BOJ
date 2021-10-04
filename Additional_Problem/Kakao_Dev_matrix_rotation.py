import numpy as np

def solution(rows, columns, queries):
    answer = []

    matrix = [[0]*columns for _ in range(rows)]
    for i in range(rows):
        for j in range(columns):
            matrix[i][j] = (i)*columns + j+1
    matrix = np.array(matrix)
    for i in range(len(queries)):
        x1,y1,x2,y2 = queries[i]
        x1 -=1
        y1 -=1
        x2 -=1
        y2 -=1

        up = matrix[x1, y1:y2].copy()
        right = matrix[x1:x2,y2].copy()
        down = matrix[x2,y1+1:y2+1].copy()
        left = matrix[x1+1:x2+1, y1].copy()
        total = np.concatenate((up, right, down, left))
        
        matrix[x1,y1+1:y2+1] = up
        matrix[x1+1:x2+1, y2] = right
        matrix[x2,y1:y2] = down
        matrix[x1:x2,y1] = left
        
        answer.append(int(min(total)))
        
        
    return answer