"""
SWEA - 1247 [S/W 문제해결 응용] 3일차 - 최적 경로 D5

전형적인 TSP 문제입니다.

points : 회사 좌표로 시작해서, N명의 고객의 좌표를 기록해둡니다.

고객 N, 고객 N-1, 고객 N-2 ... 고객 1, 회사 를 bit 로 표현하면 다음과 같습니다.
초기 상태 
ex) N = 5
0 0 0 0 0 1 => 회사만 방문한 상태

이렇게 모든 고객을 방문하게 된다면 
1 1 1 1 1 1 => 회사에서 출발해 모든 고객 방문 => 집으로 돌아가야 합니다.
따라서 탐색하면서 현재 방문한 상태가 (1<<(N+1))-1  [모든 고객 방문] 된다면 이제 집으로 가는 비용을 리턴해줍니다.

해당 경로를 가기 위한 비용들을 기록해둔다. 
기록해둔 비용들을 이용해 이미 경로가 존재한다면 더이상 탐색하지 않고 그 값을 이용한다.

Top-Down 방식으로 해결합니다.

"""
INF = float("inf")
def distance(x, y):
    return abs(x[0] - y[0]) + abs(x[1] - y[1])

def TSP(start, visited, destination):
    ## 회사에서 출발해 모든 고객 방문 상태
    if visited == ((1<<(N+1))-1):
        return distance(points[start], destination)

    ## Memoization
    if dp[visited][start] != INF: return dp[visited][start]
    
    ## 탐색
    for i in range(1, N+1):
        if (visited & (1<<i)) == 0: ## 아직 방문하지 않았다면 탐색
            ## 기존에 기록된 비용과 탐색한 결과를 비교해 더 작은 값으로 갱신한다
            dp[visited][start] = min(dp[visited][start], TSP(i, visited | (1<<i), destination) + distance(points[start], points[i]))
    
    ## 회사만 방문한 상태에 최단경로의 결과가 담기게 됩니다.
    return dp[visited][start]

T = int(input())

for t in range(1, T+1):
    N = int(input())
    cmd = list(map(int, input().split()))
    source, destination = cmd[:2], cmd[2:4]
    points = []
    points.append(source)
    for i in range(4, len(cmd), 2):
        points.append(cmd[i:i+2])
    
    dp = [[INF]*(N+2) for _ in range(2**(N+2))]
    
    print("#{} {}".format(t, TSP(0, 1, destination)))