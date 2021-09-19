text = input().rstrip(" ")
text = text.lstrip(" ")
words = [str(x) for x in text.split(" ")]

print(len(words))