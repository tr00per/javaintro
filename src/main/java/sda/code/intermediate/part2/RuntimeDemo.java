package sda.code.intermediate.part2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import sda.code.intermediate.SortingUtils;
import sda.code.intermediate.part1.answers.Algorithms;
import sda.code.intermediate.part1.answers.Sorting;

public class RuntimeDemo {

	public static void main(String args[]) throws IOException {
		final Sorting sort = new Sorting();
		List<Function<int[], int[]>> sorters = new ArrayList<>();
		sorters.add(sort::mergeSort);
		sorters.add(sort::quickSort);
		// sorters.add(sort::bubbleSort);

		System.out.println("Press Enter to continue...");
		System.in.read();

		System.out.println("Start |->");
		int inArr[] = new Algorithms().createRandomArray(10000);
		int outArr[];

		System.out.println("==================\nUnboxed values");
		Iterator<Function<int[], int[]>> sortersIter = sorters.iterator();
		while (sortersIter.hasNext()) {
			System.out.println("Running...");
			Function<int[], int[]> sorter = sortersIter.next();
			long start = System.nanoTime();
			outArr = sorter.apply(inArr);
			long stop = System.nanoTime();
			Boolean sorted = new SortingUtils().isSorted(outArr);
			System.out.println("Array sorted? " + sorted);
			System.out.println("Time spent: " + (stop - start) / 1000000L);
		}

		System.out.println("==================\nBoxed values");
		List<Integer> arrayList = new ArrayList<>(inArr.length);
		{
			long start = System.nanoTime();
			for (int x : inArr) {
				arrayList.add(x);
			}
			long stop = System.nanoTime();
			System.out.println("Time spent converting: " + (stop - start) / 1000000L);
		}
		{
			long start = System.nanoTime();
			arrayList.sort(Comparator.naturalOrder());
			long stop = System.nanoTime();
			System.out.println("Time spent sorting: " + (stop - start) / 1000000L);
		}
		System.out.println("Sorted " + arrayList.size() + " elements");
		System.out.println("->| Finish");
	}
}
