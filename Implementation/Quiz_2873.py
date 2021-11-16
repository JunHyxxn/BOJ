"""
문제
상근이는 우리나라에서 가장 유명한 놀이 공원을 운영하고 있다. 이 놀이 공원은 야외에 있고, 다양한 롤러코스터가 많이 있다.

어느 날 벤치에 앉아있던 상근이는 커다란 황금을 발견한 기분이 들었다. 
자신의 눈 앞에 보이는 이 부지를 구매해서 롤러코스터를 만든다면, 세상에서 가장 재미있는 롤러코스터를 만들 수 있다고 생각했다.

이 부지는 직사각형 모양이고, 상근이는 R행 C열의 표 모양으로 나누었다. 
롤러코스터는 가장 왼쪽 위 칸에서 시작할 것이고, 가장 오른쪽 아래 칸에서 도착할 것이다. 
롤러코스터는 현재 있는 칸과 위, 아래, 왼쪽, 오른쪽으로 인접한 칸으로 이동할 수 있다.
각 칸은 한 번 방문할 수 있고, 방문하지 않은 칸이 있어도 된다.

각 칸에는 그 칸을 지나갈 때, 탑승자가 얻을 수 있는 기쁨을 나타낸 숫자가 적혀있다. 
롤러코스터를 탄 사람이 얻을 수 있는 기쁨은 지나간 칸의 기쁨의 합이다. 
가장 큰 기쁨을 주는 롤러코스터는 어떻게 움직여야 하는지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 R과 C가 주어진다. (2 ≤ R, C ≤ 1000) 둘째 줄부터 R개 줄에는 각 칸을 지나갈 때 얻을 수 있는 기쁨이 주어진다. 이 값은 1000보다 작은 양의 정수이다.

출력
첫째 줄에 가장 가장 큰 기쁨을 주는 롤러코스터는 가장 왼쪽 위 칸부터 가장 오른쪽 아래 칸으로 어떻게 움직이면 되는지를 출력한다. 
위는 U, 오른쪽은 R, 왼쪽은 L, 아래는 D로 출력한다. 정답은 여러 가지 일 수도 있다.
===============================================================================================================================================

U : (-1, 0) R : (0, 1)  L : (0, -1) D : (1, 0)

Row, Col 이 짝수, 짝수인 경우가 문제가 된다. 이 경우에는 x+y 가 홀수가 되는 칸 중 한 칸만 제외하면 모든칸을 순회할 수 있다.
그렇다면 그 칸들 중 가장 값이 작은 칸을 제외하고 전부 돌면 된다.

1. First Method - DFS 시도.
    DFS로 접근하면 Memory Overflow, Time Overflow 발생한다. 따라서 다른 방법이 필요하다.
    
2.  2줄을 기준으로 제외할 칸이 해당된 줄을 제외하고는 한 방향으로 번갈아가며 순회하면 된다.
    또한 front와 back 양 방향으로 접근한다.
"""

## U = 0        R = 1       L = 2       D = 3
def odd_even(path, direction, x, y): ## 짝수, 홀수
    ## recursion function
    """
    -> -> -> ->
               |
    <- <- <- <- 
    |
    
    이런 방향으로 진행한다.
    """
    if x == (row-1) and y == (col-1):
        return path
    if direction == 1:  ## 오른쪽 진행
        if x < row and y == (col-1): ## 오른쪽 끝에 도달시 아래로 가고 진행방향 반대로 전환
            path.append('D') 
            odd_even(path, 2, x+1,y)
        else:
            path.append('R')
            odd_even(path, 1, x, y+1)
    elif direction == 2: ## 왼쪽으로 진행
        if x < row and y ==0: ## 왼쪽 끝에 도달시 아래로 가고 진행방향 반대로 전환
            path.append('D')
            odd_even(path, 1,x+1,y)
        else:
            path.append('L')
            odd_even(path, 2, x, y-1)
    return path

def even_odd(path, direction, x, y): ## 홀수, 짝수
    """
    |  |->
    |  |
    |  |
    |->|
    
    이런 방향으로 진행한다.
    """
    if x == (row-1) and y == (col-1):
        return path
    if direction == 3: ## 아래로 진행
        if x == (row-1) and y < col: ## 아래 끝에 도달시 오른쪽으로 가고 진행방향 반대로 전환
            path.append('R')
            even_odd(path, 0, x,y+1)
        else:
            path.append('D')
            even_odd(path, 3, x+1, y)
    elif direction ==0: ## 위로 진행
        if x ==0 and y < col: ## 위 끝에 도달시 오른쪽으로 가고 진행방향 반대로 전환
            path.append('R')
            even_odd(path, 3,x,y+1)
        else:
            path.append('U')
            even_odd(path, 0, x-1, y)
    return path


## 짝수 x 짝수
def even_even():
    path = ''
    min_val = 1000
    min_x= -1
    min_y = -1
    ## min_value에 해당하는 칸의 위치를 찾는다.
    for i in range(0, row, 2):
        for j in range(1,col,2):
            if map[i][j] < min_val:
                min_val = map[i][j]
                min_x = i
                min_y = j
    for i in range(1,row,2):
        for j in range(0,col,2):
            if map[i][j] < min_val:
                min_val = map[i][j]
                min_x = i
                min_y = j
    
    ## front와 back 양쪽에서 진행한다. 서로 마주치도록 
    front = ''
    back = ''
    
    """
    Front
    ->->->-> ...-> 
                  |
    <-<-<-<-....<- 
    |                  
    제외되는 칸이 포함된 2줄 직전까지 진행
    
    
    Back    
                  |
    <-<-<-<-... <-
    |
    ->->->-> ... ->
    제외되는 칸이 포함되는 2줄까지 진행한다.
    """
    front += ('R'*(col-1) + 'D' + 'L'*(col-1)+'D') * ((min_x)//2)
    back += ('D' + 'L'*(col-1) + 'D' + 'R'*(col-1)) * ((row-min_x-1)//2)
    
    """
    기본적으로 Front는 DRUR 반복, Back은 RURD 반복 => 4칸이 남는다.
    
    짝수 줄이라면 
    
    front,      제외되는 칸
    공백,        back
    상태가 된다. 최종적으로 front + DR + back 하면 된다.
    
    
    홀수 줄이라면
    
    front,         공백
    제외되는칸,     back
    상태가 된다.  최종적으로 front + RD + back 하면 된다.
    """
    if min_x%2 == 0:
        front += ('DRUR' * (min_y//2))
        back = ('RURD' * ((col-min_y-1)//2)) + back
        path = front + 'DR' + back
    elif min_x%2 ==1:
        front += ('DRUR' * (min_y//2))
        back = ('RURD' * ((col-min_y-1)//2)) + back
        path = front + 'RD' + back
    return path
    
U = (-1, 0) 
R = (0, 1)  
L = (0, -1) 
D = (1, 0)
move = [U, R, L, D]
row, col = map(int, input().split())
map = [list(map(int, input().split())) for _ in range(row)]

if row %2 ==0 and col %2 ==0: 
    path = even_even()
    print(path)
    
elif row%2 ==0 and col%2 ==1:
    path = []
    path = even_odd(path, 3, 0, 0)
    print(''.join(path))
elif row%2 == 1 and col%2 ==0:
    path = []
    path = odd_even(path, 1, 0, 0)
    print(''.join(path))
else:
    path = []
    path = odd_even(path, 1, 0, 0)
    print(''.join(path))