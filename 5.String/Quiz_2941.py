words = ["c=", "c-","dz=","d-","lj","nj","s=","z="]

inStr = input()


for word in words:
    inStr = inStr.replace(word, "!")

print(len(inStr))