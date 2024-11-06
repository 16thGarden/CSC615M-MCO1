import os
import re

dir = os.listdir()
res = []
for file in dir:
    if (file.endswith('log')):
        res.append(file)
dir = res
if (len(dir) == 0):
    print('   no files found')
else:
    outputfile = open("raw.txt", "w")

    for log in dir:
        # if len(re.findall("^2024-10-0[5|6|7|8|9].*", log)) == 1:        # 5 days of data (600k+ words)
        if len(re.findall("^2024-09-(0[0-9]|1[1-5]).*", log)) == 1:     # 2 weeks of data
        # if len(re.findall("^2024-(09|10)-.*", log)) == 1:               # one month of data (10M+ words)
        # if len(re.findall("^2024-(08|09|10)-.*", log)) == 1:            # 2 months of data (20.3M+ words)
            file = open(log, "r")
            text = file.read()
            outputfile.write(text)
            file.close()
            print("completed:", log)

    outputfile.close()