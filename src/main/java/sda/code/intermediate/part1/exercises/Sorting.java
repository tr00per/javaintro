package sda.code.intermediate.part1.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

	public int[] bubbleSort(int array[]) {
		int out[] = array.clone();
		bubbleSortInner(out);
		return out;
	}

	private void bubbleSortInner(int array[]) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public int[] quickSort(int array[]) {
		int out[] = array.clone();
		quickSortInner(out, 0, out.length - 1);
		return out;
	}

	private void quickSortInner(int[] out, int low, int high) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public int[] mergeSort(int array[]) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public String[] sortStrings(String[] array) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> sortWithComparator(ArrayList<String> array, Comparator<String> comp) {
		ArrayList<String> out = (ArrayList<String>) array.clone();
		out.sort(comp);
		return out;
	}

	public <T extends Comparable<? super T>> T[] sortComparable(T array[]) {
		T[] out = array.clone();
		sortComparableInner(out);
		return out;
	}

	private <T extends Comparable<? super T>> void sortComparableInner(T[] array) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
