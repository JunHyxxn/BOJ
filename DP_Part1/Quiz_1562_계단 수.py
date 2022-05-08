N = int(input())

## N x 10 x 1024
nums = [[[0]*1024 for _ in range(10)] for _ in range(N)]
mod = 1000000000
for i in range(1, 10):
    nums[0][i][1<<i] = 1

for time in range(1, N):
    for number in range(10):
        for prev in range(1, 1024):
            now = prev | (1<<number)
            if number == 0:
                if nums[time-1][number+1][prev]:
                    nums[time][number][now] = (nums[time][number][now] + nums[time-1][number+1][prev])%mod
            elif number == 9:
                if nums[time-1][number-1][prev]:
                    nums[time][number][now] = (nums[time][number][now]+nums[time-1][number-1][prev])%mod
            else:
                if nums[time-1][number-1][prev] or nums[time-1][number+1][prev]:
                    nums[time][number][now] = (nums[time][number][now]+nums[time-1][number-1][prev])%mod 
                    nums[time][number][now] = (nums[time][number][now] + nums[time-1][number+1][prev]) %mod

total = 0
for i in range(10):
    total += nums[N-1][i][(1<<10)-1]
print(total%1000000000)


