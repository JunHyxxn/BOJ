numbers = list(map(int, input().split()))
hand = input().lower()


pad = dict()
pad[1] = (0,0)
pad[2] = (0,1)
pad[3] = (0,2)
pad[4] = (1,0)
pad[5] = (1,1)
pad[6] = (1,2)
pad[7] =(2,0)
pad[8] = (2,1)
pad[9] = (2,2)
pad['*'] = (3,0)
pad[0] = (3,1)
pad['#'] = (3,2)



def distance(x1,y1, x2,y2):
    return abs(x1-x2) + abs(y1-y2)

def solution(numbers, hand):
    sol = []
    cur_L = '*'
    cur_R = '#'
    for number in numbers:
        if number in [1,4,7,'*']:
            sol.append('L')
            cur_L = number
        elif number in [3,6,9, '#']:
            sol.append('R')
            cur_R = number
        else:
            dist_L = distance(pad[cur_L][0], pad[cur_L][1], pad[number][0], pad[number][1])
            dist_R = distance(pad[cur_R][0], pad[cur_R][1], pad[number][0], pad[number][1])
            if dist_L == dist_R:
                if hand == 'right':
                    sol.append('R')
                    cur_R = number
                elif hand == 'left':
                    sol.append('L')
                    cur_L = number
            elif dist_L < dist_R:
                sol.append('L')
                cur_L = number
            elif dist_R < dist_L:
                sol.append('R')
                cur_R = number

    return sol


sol = solution(numbers, hand)
print(''.join(sol))