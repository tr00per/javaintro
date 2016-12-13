package sda.code.intermediate.part1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import sda.code.intermediate.SortingUtils;
import sda.code.intermediate.part1.exercises.Algorithms;
import sda.code.intermediate.part1.exercises.Sorting;

public class RuntimeDemo {

	public static void main(String args[]) throws IOException {
		final Sorting sort = new Sorting();
		List<Function<int[], int[]>> sorters = new ArrayList<>();
		sorters.add(sort::mergeSort);
		sorters.add(sort::quickSort);
		sorters.add(sort::bubbleSort);

		System.out.println("Press Enter to continue...");
		System.in.read();

		System.out.println("Start");
		int inArr[], outArr[];
		Iterator<Function<int[], int[]>> sortersIter = sorters.iterator();
		while (sortersIter.hasNext()) {
			System.out.println("Running...");
			Function<int[], int[]> sorter = sortersIter.next();
			inArr = new Algorithms().createRandomArray(100000);
			long start = System.nanoTime();
			outArr = sorter.apply(inArr);
			long stop = System.nanoTime();
			Boolean sorted = new SortingUtils().isSorted(outArr);
			System.out.println("Array sorted? " + sorted);
			System.out.println("Time spend: " + (stop - start) / 1000000L);
		}
		System.out.println("Finish");
	}
}
