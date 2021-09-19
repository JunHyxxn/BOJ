inStr = input().lower()


inStr_set = set()
for s in inStr:
    inStr_set.add(s)

d = {
    instr : 0 for instr in inStr_set
}
dd ={}
for k,v in d.items():
    for instr in inStr:
        if k == instr:
            v += 1
    dd[k] = v
dd = sorted(dd.items(), key=lambda x: x[1], reverse=True)

if len(dd) == 1:
    print(dd[0][0])
elif dd[0][1] == dd[1][1]:
    print("?")
else:
    print(dd[0][0])