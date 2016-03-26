
f = open('Prob2Res.txt', 'r')
#contents = f.read()

trialSize = []
trialRange = []

mergeResults = [[],[],[],[]]
quickResults = [[],[],[],[]]



for line in f:
	words = line.strip().split()

	if (len(words) == 5):
		trialSize.append(words[2])
		trialRange.append(words[4])

	elif (len(words) == 8):
		if (words[0] == "mergesort"):
			pass
		else:
			mergewords = words[:4]
			quickwords = words[4:]

			for i,(res1,res2) in enumerate(zip(mergewords,quickwords)):
				mergeResults[i].append(res1)
				quickResults[i].append(res2)


mergeResultsfinal = []
quickResultsfinal = []
for list1,list2 in zip(mergeResults,quickResults):
	mergeResultsfinal.append([list1[i:i+100] for i in range(0, len(list1), 100)])
	quickResultsfinal.append([list2[i:i+100] for i in range(0, len(list2), 100)])



mergeAvgs = []
quickAvgs = []
for list1,list2 in zip(mergeResultsfinal,quickResultsfinal):
	for sublist1,sublist2 in zip(list1,list2):
		sublist1 = [int(x[:-2]) for x in sublist1]
		sublist2 = [int(x[:-2]) for x in sublist2]

		sublist1 = [x for x in sublist1 if x < 1000]
		sublist2 = [x for x in sublist2 if x < 1000]

		mergeAvgs.append(sum(sublist1)/len(sublist1))
		quickAvgs.append(sum(sublist2)/len(sublist2))

mergeAvgs = [mergeAvgs[i:i+6] for i in range(0, len(mergeAvgs), 6)]
quickAvgs = [quickAvgs[i:i+6] for i in range(0, len(quickAvgs), 6)]

print(mergeAvgs)
print(quickAvgs)





