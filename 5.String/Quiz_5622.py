dialog  = {1 : "" , 2 : "ABC", 3 : "DEF",4:"GHI", 5 :"JKL", 6 : "MNO",
7 : "PQRS", 8 : "TUV", 9 : "WXYZ", 0 : "*"}

texts = input()
time = 0
    
for text in texts:
    for k, v in dialog.items():
        if(text in v):
            time += k+1

print(time)