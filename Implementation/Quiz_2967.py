"""
문제
최근에 고고학자들이 그리스-로마 건축을 발견했다. 이 장소는 R*C칸으로 모델링 되어 있다. 고고학자들은 각 칸에 빌딩이 있었는지 없었는지를 표시해 두었다.

고고학자들은 이 장소에 서로 다른 시대에 지어진 두 건물이 있었다는 사실을 알게되었다. 또, 두 건물의 바닥 모양은 정사각형이었다.

두 건물이 서로 다른 시대에 지어졌기 때문에, 바닥이 겹칠 수도 있다. 이때, 가능한 위치와 크기를 구하는 프로그램을 작성하시오. 

입력
첫째 줄에 발견한 장소의 크기인 R과 C가 주어진다. (1 ≤ R ≤ 100, 1 ≤ C ≤ 100)

다음 R개의 줄에는 C개의 문자가 주어진다. 각 문자는 '.' 또는 'x'이고, '.'인 경우에는 그 칸에 건물의 흔적이 없었다는 뜻이고, 'x'는 건물이 있었다는 뜻이다.

출력
두 건물의 바닥의 왼쪽 위 좌표와 크기를 출력한다. 항상 답이 존재하는 경우만 주어진다.

============================================================================================================================================================

Key Point - 정사각형 찾고 시작 부분에만 마킹한다. 
            또한, 정사각형의 순서를 저장하는 변수를 만든다.
"""
## 찾은 두 정사각형이 주어진 조건에 맞는지 확인한다.
def final_check(first, second):
    """
    1) 겹쳐져있는지.
    2) 모든 x 칸을 포함하고 있는지.
    """
    first_x, first_y, first_s = first
    second_x, second_y, second_s = second
    
    ## 포함관계인지 확인한다.
    if first_x <= second_x and first_y <= second_y and (second_x + second_s)<=(first_x + first_s) and (second_y + second_s)<=(first_y + first_s):
        return False
    
    ## 면적을 확인한다.
    unique = set()
    ## 첫번째 정사각형 면적
    for i in range(first_s):
        for j in range(first_s):
            unique.add((first_x+i,first_y+j))
    ## 두번쨰 정사각형 면적
    for i in range(second_s):
        for j in range(second_s):
            unique.add((second_x+i,second_y+j))
    ## 두 정사각형으로 모든 땅을 채워야한다.
    if len(unique) == total:
        return True
    
## 정사각형을 찾는 함수
def find_square(x, y):
    s_x = x
    s_y = y
    e_x = x
    square_flag = False ## 사각형 찾았는지 확인하는 flag
    ## 대각선으로 진행
    for i in range(r):
        nx = x + i
        ny = y + i
        if 0<=nx<r and 0<=ny<c:
            if drawing[nx][ny] == 'x':
                for j in range(1, i+1): ## 대각성분이 x일때 해당 범위가 정사각형인지 확인 필요하다.
                    if drawing[nx][ny-j] == '.' or drawing[nx-j][ny] == '.': ## 해당 범위에 .이 포함되면 정사각형이 아니다.
                        ## 정사각형이 아니라면 이전 칸까지가 정사각형이다.
                        e_x = nx - 1
                        square_flag = True ## 정사각형 찾았다.
                        break ## 멈춘다.
                if square_flag: break ## 정사각형 찾았다면 멈춰준다.
                ## 중간에 멈춰진것이 아닌 끝까지 탐색이 되었을 경우를 대비해 진행과정에서 저장해준다.
                e_x = nx
            else: ## '.' 칸일 경우
                e_x = nx -1 ## 이전 칸까지 정사각형이다.
                break
            
    ## 문제에서 0,0 이 아닌 1,1 부터 시작했으니 1씩 더해주고, size = e_x - s_x +1 이다.
    return [s_x+1, s_y+1, e_x-s_x+1]

r, c = map(int, input().split())
drawing = [list(map(str, input())) for _ in range(r)]
## 총 면적을 계산해준다.
total = 0
for i in range(r):
    for j in range(c):
        if drawing[i][j] == 'x':
            total += 1
## 첫 정사각형 찾는 flag
first_flag = True
## 두 번째까지 제대로 찾았는지 확인하는 flag
finish_flag = False

## 탐색 시작
for i in range(r):
    for j in range(c):
        if finish_flag: break ## 불필요한 탐색 생략
        if drawing[i][j] == 'x': ## 'x' 라면 정사각형 탐색 필요
            if first_flag: ## 첫번째라면
                first_info = find_square(i,j)
                first_flag = False
            else: ## 두번째라면
                second_info = find_square(i,j)
                
                ## 두번째까지 찾았다면 주어진 조건에 맞는지 확인해준다.
                if final_check(first_info, second_info):
                    print(' '.join(map(str, first_info)))
                    print(' '.join(map(str,second_info)))
                    finish_flag = True
if not finish_flag: ## 주어진 조건에 알맞은 답이 없다면 이는 정확히 겹쳐져 있는 경우를 의미한다.
    print(' '.join(map(str, first_info)))
    print(' '.join(map(str, first_info)))
