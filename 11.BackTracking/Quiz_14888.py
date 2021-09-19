# # state = ' '.join(map(str, [str(1), '+',str(2)]))

# # print(state)
# # state = state.split()


# # def calc(state):
# #     operand1, oper, operand2 = state
# #     if oper == '+':
# #         out = int(operand1) + int(operand2)
# #     elif oper == '-':
# #         out = int(operand1) - int(operand2)
# #     elif oper == '*':
# #         out = int(operand1) * int(operand2)
# #     elif oper == '/':
# #         out = int(operand1) // int(operand2)
# #     return out


# # N = int(input())
# # nums = list(map(int, input().split()))
# # plus, minus, multiple, divide = map(int, input().split())
# # operators = ['+'] * plus + ['-'] * minus + ['*'] * multiple + ['//'] * divide
# # isused = [False] * (plus + minus + multiple + divide)
# # exp = ''
# # expressions = []
# # def solve(depth):
# #     global exp
# #     global expressions
# #     if depth == N:
# #         expressions.append(exp)
# #         return
    
# #     for i in range(len(isused)):
# #         if isused[i] is False:
# #             if depth == 1:
# #                 isused[i] = True
# #                 exp = ' '.join(map(str, [str(nums[depth-1]) ,operators[i], str(nums[depth])]))
# #                 solve(depth+1)
# #                 isused[i] = False
# #             else:
# #                 isused[i] = True
# #                 exp = ' '.join(map(str, [exp, operators[i], str(nums[depth])]))
# #                 solve(depth+1)
# #                 isused[i] = False
           
    
# #     return 0

# # solve(1)
# # print(exp)
# # print(expressions)
# # print(len(expressions))

