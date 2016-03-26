
f = open('Prob3Res.txt', 'r')
#contents = f.read()

trialSize = []
trialRange = []

results = [[],[],[],[],[]]




for line in f:
	words = line.strip().split()




	if (len(words) == 5):
		if (words[0][-2:] != 'ms'):
			pass
		else:
			for i, word in enumerate(words):
				results[i].append(word)



print(len(results))
print(len(results[0]))

avgRes = [[],[],[],[],[]]

for i,reslist in enumerate(results):
	reslist = [reslist[i:i+100] for i in range(0,len(reslist),100)]
	for sublist in reslist:
		sublist = [int(x[:-2]) for x in sublist]
		sublist = [x for x in sublist if x < 1500]
		avgRes[i].append(sum(sublist)/len(sublist))



print(avgRes)



