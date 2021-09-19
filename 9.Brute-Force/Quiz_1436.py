# # 영화감독 숌

# N번째 영화의 제목에 들어간 숫자를 출력하는 프로그램을 작성하시오. 
# 숌은 이 시리즈를 항상 차례대로 만들고, 다른 영화는 만들지 않는다.

# N번째 영화의 제목에 들어간 수를 출력한다.??


N = int(input())


num = 666
cnt = 0
while True:
    if cnt == N:
        print(num-1)
        break
    else:
        if "666" in str(num):
            cnt += 1
        num += 1



