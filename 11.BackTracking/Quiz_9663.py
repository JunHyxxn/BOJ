
N = int(input())
col = [False for _ in range(N)]
r_u = [False for _ in range(2*N - 1)]
r_b = [False for _ in range(2*N - 1)]
cnt = 0
def solve(depth):
    global cnt
    if depth == N:
        cnt += 1
        return
    
    for y in range(N):
        if (col[y] | r_u[depth+y] | r_b[depth-y+N-1]):
            continue
        col[y] = True
        r_u[depth+y] = True
        r_b[depth-y+N-1] = True
        solve(depth+1)
        col[y] = False
        r_u[depth+y] = False
        r_b[depth-y+N-1] = False

solve(0)
print(cnt)


# depth = 0
# col = set()
# ru = set() ## x+y
# rb = set() ## x-y
# def solution(n):
#     global depth
    
#     if depth == n:
#         return 1
#     answer = 0
    
#     for i in range(n):
#         if i in col or depth+i in ru or depth-i in rb:
#             continue
#         col.add(i)
#         ru.add(depth+i)
#         rb.add(depth-i)
#         depth += 1
#         answer += solution(n)
#         depth -= 1
#         col.remove(i)
#         ru.remove(depth+i)
#         rb.remove(depth-i)
    
#     return answer
# print(solution(8))