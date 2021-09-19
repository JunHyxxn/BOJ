# # 골드바흐의 추측
# 2보다 큰 짝수는 두 소수의 합으로 나타낼 수 있다.
# 이러한 수를 골드바흐 수라 한다.
# 이러한 두 소수의 합으로 나타내는 표현을 골드바흐 파티션이라고 한다.
# 예를 들어 4 = 2+ 2
#          6 = 3 + 3
#          8 = 3 + 5

#          10 = 3 + 7
#          10 = 5 + 5
         
#          12 = 5 + 7
         
#          14 = 3 + 11
#          14 = 7 + 7
         
#          16 = 5 + 11
#          18 = 7 + 11
#          20 = 7 + 13
# 여러 가지인 경우 두 소수간의 차이가 적은 것으로 출력.

# n이 주어지면 n//2 가 소수인지 확인하고 소수라면 그 소수끼리의 합 출력
# 아니라면 그와 가장 가까운 소수를 찾아 그 차이만큼 더한 수도 소수인지 확인?
# 2 3 5 7  11 13 17 19

# 우선 에리스토테리스 체 이용해서 소수부터 만든다.

def Prime(n):
    prime_list= [False, False] + [True] *(n-1)
    PL = []
    i = 2
    while i**2 <= n:
        if prime_list[i] is True:
            j = i*i
            while j <= n:
                prime_list[j] = False
                j += i
        i += 1

    for i in range(len(prime_list)):
        if prime_list[i] is True:
            PL.append(i)
    return PL


def Half_prime(primes, n):
    half_prime = []
    for prime in primes:
        if prime <= n//2:
            half_prime.append(prime)
        else:
            break
    return half_prime

T = int(input())
for i in range(T):
    n = int(input())
    primes = Prime(n)
    half_primes = Half_prime(primes, n)
    half_primes.sort(reverse= True)


    for prime in half_primes:
        if prime == n//2:
            print("{0} {0}".format(prime))
            break
        elif (n - prime) in primes:
            print("{0} {1}".format(prime, n - prime))
            break
        else:
            continue



