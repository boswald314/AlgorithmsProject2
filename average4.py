
f = open('Prob1Res.txt', 'r')
#contents = f.read()

trialSize = []
trialRange = []

mergeResults = []
bottomUpResults = []


for line in f:
	words = line.strip().split()

	if (len(words) == 5):
		trialSize.append(words[2])
		trialRange.append(words[4])

	elif (len(words) == 2):
		if (words[0] == "mergesort"):
			pass
		else:
			mergeResults.append(words[0])
			bottomUpResults.append(words[1])


mergeResults = [mergeResults[i:i+100] for i in range(0, len(mergeResults), 100)]
bottomUpResults = [bottomUpResults[i:i+100] for i in range(0,len(bottomUpResults),100)]


mergeAvgs = []
bottomAvgs = []

for mergelist,bottlist in zip(mergeResults,bottomUpResults):
	mergeTrialRes = [int(x[:-2]) for x in mergelist]
	bottomTrialRes = [int(x[:-2]) for x in bottlist]

	mergeAvgs.append(sum(mergeTrialRes)/len(mergeTrialRes))
	bottomAvgs.append(sum(bottomTrialRes)/len(bottomTrialRes))

print(mergeAvgs)
print(bottomAvgs)



