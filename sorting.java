import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class sorting {
	
	private static int[] arr;
	private static int[] arrCopy;
	private static int[] arrCopy2;
	private static BufferedReader read;
	private static Random randomGenerator;
	
	private static int size, rounds;
	private static int random;

	private long start, finish;
	

	private static List<String> nextRow = new ArrayList<String>();



	// Utility functions
	private static void printArray(String msg) {
		System.out.print(msg + " [" + arr[0]);
		for(int i=1; i<size; i++) {
			System.out.print(", " + arr[i]);
		}
		System.out.println("]");
	}

	private static boolean isSorted(int low, int high) {
		if (low == high) {
			return true;
		}
		while (low < high) {
			if (arr[low] > arr[high]) {
				return false;
			}
			low++;
		}
		return true;
	}




	// Insertion Sort
	public static void insertSort(int left, int right) {
		// insertSort the subarray arr[left, right]
		int i, j;

		for(i=left+1; i<=right; i++) {
		int temp = arr[i];           // store a[i] in temp
		j = i;                       // start shifts at i
		// until one is smaller,
		while(j>left && arr[j-1] >= temp) {
			arr[j] = arr[j-1];        // shift item to right
			--j;                      // go left one position
		}
		arr[j] = temp;              // insert stored item
		}  // end for
	}  // end insertSort()
	
	public static void insertionSort() {
		insertSort(0, size-1);  
	} // end insertionSort()





	// Heapsort and auxilliary functions
	public static void maxheapify(int i, int n) { 
		 // Pre: the left and right subtrees of node i are max heaps.
		 // Post: make the tree rooted at node i as max heap of n nodes.
		int max = i;
		int left=2*i+1;
		int right=2*i+2;
		
		if(left < n && arr[left] > arr[max]) max = left;
		if(right < n && arr[right] > arr[max]) max = right;
		
		if (max != i) {  // node i is not maximal
			exchange(i, max);
			maxheapify(max, n);
		}
	}
	
	public static void exchange(int i, int j){
		int t=arr[i];
		arr[i]=arr[j];
		arr[j]=t; 
	 }
	
	public static void heapsort(){
		// Build an in-place bottom up max heap
		for (int i=size/2; i>=0; i--) maxheapify(i, size);
		 
		for(int i=size-1;i>0;i--) {
			exchange(0, i);       // move max from heap to position i.
			maxheapify(0, i);     // adjust heap
		}
	}
	




	// Merge sort and variations
	private static void bottomupsort(int low, int high) {
		// Variation on merge sort, iterates along list rather than recursively bifurcating
		if (low < high) {	// else empty subarray is trivially sorted
			int t = 1; 		// This currently leaves initial element unsorted (need to fix)
			while (t < high) {
				int s = t; t = 2*s; int i = -1;
				while ((i + t) <= high) {
					merge(i+1,i+s,i+t);
					i = i + t;
				}
				if ((i + s) < high) {
					merge(i+1,i+s,high);
				}
			}
		}
	}

	private static void mergesort(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void mergesortA(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			if ( !(isSorted(low,middle)) && (middle - low) >= 100) {
				mergesort(low, middle);
			} else { insertSort(low, middle); }
			// Sort the right side of the array
			if ( !(isSorted(middle + 1, high)) && (high - (middle + 1) >= 100)) {
				mergesort(middle + 1, high);
			}
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void mergesortB(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			if (!(isSorted(low,middle))) {
				mergesort(low, middle);
			}
			// Sort the right side of the array
			if (!(isSorted(middle + 1, high))) {
				mergesort(middle + 1, high);
			}
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void mergesortC(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			if ((middle - low) >= 100) {
				mergesort(low, middle);
			} else { insertSort(low, middle); }
			// Sort the right side of the array
			if ((high - (middle + 1)) >= 100) {
				mergesort(middle + 1, high);
			} else { insertSort(middle + 1, high); }
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void merge(int low, int middle, int high) {

		// Copy first part into the arrCopy array
		for (int i = low; i <= middle; i++) {
			arrCopy2[i] = arr[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (arrCopy2[i] <= arr[j]) {
			arr[k] = arrCopy2[i];
			i++;
			} else {
			arr[k] = arr[j];
			j++;
			}
			k++;
		}
	
		// Copy the rest of the left part of the array into the target array
		while (i <= middle) {
			arr[k] = arrCopy2[i];
			k++;
			i++;
		}

	}





	// Quicksort and variations
	private static void quicksort(int low, int high) {
		int i = low, j = high;

		// Get the pivot element from the middle of the list
		int pivot = arr[(high+low)/2];

		// Divide into two lists
		while (i <= j) {
				// If the current value from the left list is smaller then the pivot
				// element then get the next element from the left list
				while (arr[i] < pivot) i++;
				
				// If the current value from the right list is larger then the pivot
				// element then get the next element from the right list
				while (arr[j] > pivot) j--;

				// If we have found a value in the left list which is larger than
				// the pivot element and if we have found a value in the right list
				// which is smaller then the pivot element then we exchange the
				// values.
				// As we are done we can increase i and j
				if (i < j) {
				exchange(i, j);
				i++;
				j--;
				} else if (i == j) { i++; j--; }
		}

		// Recursion
		if (low < j)
				quicksort(low, j);
		if (i < high)
				quicksort(i, high);
	}

	private static void quicksortA(int low, int high) {
		int i = low, j = high;

		// Get the pivot element from the middle of the list
		int pivot = arr[(high+low)/2];

		// Divide into two lists
		while (i <= j) {
				// If the current value from the left list is smaller then the pivot
				// element then get the next element from the left list
				while (arr[i] < pivot) i++;
				
				// If the current value from the right list is larger then the pivot
				// element then get the next element from the right list
				while (arr[j] > pivot) j--;

				// If we have found a value in the left list which is larger than
				// the pivot element and if we have found a value in the right list
				// which is smaller then the pivot element then we exchange the
				// values.
				// As we are done we can increase i and j
				if (i < j) {
				exchange(i, j);
				i++;
				j--;
				} else if (i == j) { i++; j--; }
		}

		// Recursion
		if (low < j && !(isSorted(low,j)) && !((j-low) < 100)) {
			quicksort(low, j);
		} else {
				insertSort(low,j);
			}
		if (i < high && !(isSorted(i,high)) && !((high-i) < 100)) {
			quicksort(i, high);
		} else {
				insertSort(i,high);
		}
	}

	private static void quicksortB(int low, int high) {
		int i = low, j = high;

		// Get the pivot element from the middle of the list
		int pivot = arr[(high+low)/2];

		// Divide into two lists
		while (i <= j) {
				// If the current value from the left list is smaller then the pivot
				// element then get the next element from the left list
				while (arr[i] < pivot) i++;
				
				// If the current value from the right list is larger then the pivot
				// element then get the next element from the right list
				while (arr[j] > pivot) j--;

				// If we have found a value in the left list which is larger than
				// the pivot element and if we have found a value in the right list
				// which is smaller then the pivot element then we exchange the
				// values.
				// As we are done we can increase i and j
				if (i < j) {
				exchange(i, j);
				i++;
				j--;
				} else if (i == j) { i++; j--; }
		}

		// Recursion
		if (low < j && !(isSorted(low,j)))
				quicksort(low, j);
		if (i < high && !(isSorted(i,high)))
				quicksort(i, high);
	}

	private static void quicksortC(int low, int high) {
		int i = low, j = high;

		// Get the pivot element from the middle of the list
		int pivot = arr[(high+low)/2];

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (arr[i] < pivot) i++;

			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (arr[j] > pivot) j--;

			// If we have found a value in the left list which is larger than
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i < j) {
				exchange(i, j);
				i++;
				j--;
			} else if (i == j) { i++; j--; }
		}

		// Recursion
		if (low < j && !((j-low) < 100)) {
				quicksort(low, j);
		} else {
			insertSort(low,j);
		}
		if (i < high && !((high-i) < 100)) {
				quicksort(i, high);
		} else {
			insertSort(i,high);
		}
	}

	private static void test(String func) {
		long start = System.currentTimeMillis();

		switch(func) {
			case "Arrays.sort":
				if (size < 101) printArray("Initial array:");
				Arrays.sort(arr);
				if (size < 101) printArray("out");
				break;

			case "heapsort":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				heapsort();
				if (size < 101) printArray("out");
				break;

			case "mergesort":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				mergesort(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "mergesortA":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				mergesortA(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "mergesortB":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				mergesortB(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "mergesortC":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				mergesortC(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "bottomupsort":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				bottomupsort(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "quicksort":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				quicksort(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "quicksortA":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				quicksortA(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "quicksortB":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				quicksortB(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "quicksortC":
				for(int i=0; i<size; i++) arr[i] = arrCopy[i];
				if (size < 101) printArray("in");
				quicksortC(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "quicksort on nearly-sorted":
				if (size < 101) printArray("in");
				quicksort(0, size-1);
				if (size < 101) printArray("out");
				break;

			case "insertsort on nearly-sorted":
				for(int i=0; i<size; i++) arr[i] = arrCopy2[i];
				if (size < 101) printArray("in");
				insertionSort();
				if (size < 101) printArray("out");
				break;

		}

		long finish = System.currentTimeMillis();
		nextRow.add( ((finish-start) + "ms").toString() );

	}

	private static void runTests() {
		read = new BufferedReader(new InputStreamReader(System.in));
		randomGenerator = new Random();
		TableBuilder tb = new TableBuilder();

		try {
			System.out.print("Please enter number of instances to try: ");
			rounds = Integer.parseInt(read.readLine());

			System.out.print("Please enter array size : ");
			size = Integer.parseInt(read.readLine());
			
			System.out.print("Please enter the random range : ");
			random = Integer.parseInt(read.readLine());
		} catch(Exception ex){
			ex.printStackTrace();
		}

		
		tb.addRow("Arrays.sort","heapsort","mergesort","A","B","C","bottomup","quicksort","A","B","C","qs-NS","insert-NS");
		
		for (int x = 0; x < rounds; ++x) {
			//System.out.print(x+"\t");
			nextRow.clear();

			// create array
			arr = new int[size];
			arrCopy = new int[size];
			arrCopy2 = new int[size];
				
			// fill array
			for(int i=0; i<size; i++) 
				 arr[i] = arrCopy[i] = randomGenerator.nextInt(random);


			//System.out.print("\n--BEGIN TRIALS--\n");

			// built-in sort
			test("Arrays.sort");		


			// Heap sort 
			test("heapsort");     

				 
			// Merge sort
			test("mergesort");
			test("mergesortA");
			test("mergesortB");
			test("mergesortC");

			// Bottom up sort
			test("bottomupsort");


			// Quick sort
			test("quicksort");
			test("quicksortA");
			test("quicksortB");
			test("quicksortC");


			// arr[0..size-1] is already sorted. We randomly swap 100 pairs to make it nearly-sorted.
			for (int i = 0; i < 100; i++) {
				int j  = randomGenerator.nextInt(size);
				int k  = randomGenerator.nextInt(size);
				exchange(j, k);
			}
			for(int i=0; i<size; i++) arrCopy2[i] = arr[i];

			// Quick sort on nearly-sorted array
			test("quicksort on nearly-sorted");

			// Insert sort on nearly-sorted array      
			test("insertsort on nearly-sorted");

			String[] rowArray = new String[nextRow.size()];
			tb.addRow(nextRow.toArray(rowArray));
	
		}
		System.out.println(tb.toString());
	}

	private static void comparePerformance(String[] sortFuncs) {
		randomGenerator = new Random();
		
		rounds = 100;
		//String[] sortFuncs = {"mergesort", "bottomupsort"};
		Integer[] ranges = {1000, 1000000};

		for (Integer y = 1000000; y <= 4000000; y *= 2) {
			size = y;
			//size = size / 100; // used in testing to reduce runtime
			for (Integer random: ranges) {

				TableBuilder tb = new TableBuilder();
				tb.addRow("Array size: " + size, "range: " + random);
				tb.addRow(sortFuncs);
				
				for (int x = 0; x < rounds; ++x) {
					nextRow.clear();

					// create array
					arr = new int[size];
					arrCopy = new int[size];
					arrCopy2 = new int[size];
						
					// fill array
					for(int i=0; i<size; i++) 
						 arr[i] = arrCopy[i] = randomGenerator.nextInt(random);

					for (String f: sortFuncs) {
						test(f);
					}

					String[] rowArray = new String[nextRow.size()];
					tb.addRow(nextRow.toArray(rowArray));
				}
				System.out.println(tb.toString());
				System.out.println();
			}
		}
	}

	public static void bottomtest(String[] sortFuncs) {
		randomGenerator = new Random();
		
		rounds = 100;
		//String[] sortFuncs = {"mergesort", "bottomupsort"};
		Integer[] ranges = {10, 100};

		size = 25;
		//size = size / 100; // used in testing to reduce runtime
		for (Integer random: ranges) {

			TableBuilder tb = new TableBuilder();
			tb.addRow("Array size: " + size, "range: " + random);
			tb.addRow(sortFuncs);
			
			for (int x = 0; x < rounds; ++x) {
				nextRow.clear();

				// create array
				arr = new int[size];
				arrCopy = new int[size];
				arrCopy2 = new int[size];
					
				// fill array
				for(int i=0; i<size; i++) 
					 arr[i] = arrCopy[i] = randomGenerator.nextInt(random);

				for (String f: sortFuncs) {
					test(f);
				}

				String[] rowArray = new String[nextRow.size()];
				tb.addRow(nextRow.toArray(rowArray));
			}
			System.out.println(tb.toString());
			System.out.println();
		}
	}


	public static void main(String[] args) {
		
		while (true) {
			String[] toRun = {"bottomupsort"};
			String[] toRun1 = {"mergesort", "bottomupsort"};
			String[] toRun2 = {"mergesort", "mergesortA", "mergesortB", "mergesortC", "quicksort", "quicksortA", "quicksortB", "quicksortC"};
			runTests();
			//comparePerformance(toRun1);
			//comparePerformance(toRun2);
			//bottomtest(toRun);
			
			break;
			/*
			System.out.println("Would you like to run another test? (yes/no)");
			try {
				String checkAgain = read.readLine();
				if (checkAgain.equals("no")) break;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println();
			*/
		}
		
	}
}







