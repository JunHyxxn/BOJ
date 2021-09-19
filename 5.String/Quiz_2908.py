def rev(numbers):
    reverseNum = ""
    for num in numbers:
        reverseNum = num + reverseNum
    return reverseNum
text = list(input().split())

first = rev(text[0])
second = rev(text[1])

print((first if int(first) > int(second) else second))
