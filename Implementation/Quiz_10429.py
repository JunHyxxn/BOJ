"""
문제
Quento는 Q42에서 만든 게임이다. 이 게임의 게임판은 항상 3×3 크기이며, 오른쪽 그림과 같이 검정색 칸에는 숫자, 흰색 칸에는 +또는 -가 적혀져 있다.

게임판의 상단에는 만들어야하는 숫자 N과 사용해야 하는 숫자의 개수 M이 주어진다. 
이제 숫자에서 스와이프를 시작해 +/-로 이동한 다음, 다시 숫자로 스와이프를 하고, 이런식으로 숫자를 M개 지났을 때, 결과가 N이 되어야 한다. 
이미 방문한 칸은 재방문 할 수 없으며, 다시 지나가는 것도 불가능하다.

예를 들어, 7을 숫자 2개를 이용해서 만들려면, 4+3, 9-2는 가능하다. 하지만, 5+3-1은 숫자 3개를 이용했기 때문에 불가능하다.

N과 M, 그리고 게임판에 쓰여 있는 숫자와 +/-가 주어진다. 이때, 숫자 M개를 이용해서 N을 만드는 방법을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 45, 2 ≤ M ≤ 5) 둘째 줄부터 3개의 줄에 걸쳐 게임판에 써잇는 숫자와 +/-가 주어진다. 숫자는 항상 한자리 숫자이고, 0이 아니다.

출력
M개의 숫자를 이용해서 N을 만들 수 있으면, 첫째 줄에 1을 출력하고, 없는 경우에는 0을 출력한다. 
그 다음, 숫자를 만들 수 있는 경우에는 방문한 칸을 총 2*M-1개 줄에 걸쳐서 출력한다. 
가장 왼쪽 윗칸의 좌표는 (0, 0), 왼쪽 아랫칸의 좌표는 (2, 0), 오른쪽 윗칸의 좌표는 (0, 2), 오른쪽 아랫칸의 좌표는 (2, 2)이다.

가능한 방법이 여러 가지인 경우 아무거나 하나만 출력한다.
=============================================================================================================================================================================
Key Point - 전형적인 DFS로 보인다.
3x3 이기때문에 모든 vertex에서 DFS를 진행하더라도 1초 안에 충분히 수행 가능할 것 으로 예상된다.
"""
def calc(stack):
    ## initialized value
    loc = stack[0] 
    out = quento[loc[0]][loc[1]]
    for i in range(1, len(stack),2): ## calculate
        x,y = stack[i]
        if quento[x][y] == '+':
            out = out+quento[stack[i+1][0]][stack[i+1][1]]
        elif quento[x][y] =='-':
            out = out - quento[stack[i+1][0]][stack[i+1][1]]
    return out

def dfs(x,y,cnt):
    global flag
    stack.append([x,y])
    visited[x][y] = 1
    if cnt == m: ## 숫자 m번 포함시켰으면 calc 진행
        result = calc(stack)
        if result == n: ## result가 n과 같다면
            flag = True ## flag True
            print(int(flag))
            for s in stack: ## 경로를 출력한다.
                print(' '.join(map(str, s)))
            return 
        else: return

    ## 이미 한번 발견 됐으면 종료시킨다.
    if flag: return

    ## DFS
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if 0<=nx<3 and 0<=ny<3 and not visited[nx][ny]:
            if (nx+ny)%2 ==0:
                dfs(nx,ny,cnt+1)
                stack.pop()
                visited[nx][ny] = 0
            elif (nx+ny)%2==1:
                dfs(nx,ny,cnt)
                stack.pop()
                visited[nx][ny] = 0


n,m = map(int, input().split())
quento = [[i for i in input()] for _ in range(3)]
for i in range(len(quento)):
    for j in range(len(quento[0])):
        ## '0' => ASCII : 48            '9' => ASCII : 57
        if 48<= ord(quento[i][j]) <=57: ## ASCII 코드 활용
            quento[i][j] = int(quento[i][j])
dx = [0,1,0,-1]  ## E S W N
dy = [1,0,-1,0]  ## E S W N
source = [] ## 숫자 칸에서만 시작한다. source vertex를 지정해준다.
for i in range(3):
    for j in range(3):
        if (i+j)%2==0: source.append((i,j))


flag = False
for s in source:
    ## stack과 visited는 시작점이 바뀌면 초기화 필요하다
    visited = [[0]*3 for _ in range(3)]
    stack = []
    if not visited[s[0]][s[1]]: 
        cnt = 1
        dfs(s[0],s[1],cnt)
if not flag: ## 탐색 실패 시
    print(0)