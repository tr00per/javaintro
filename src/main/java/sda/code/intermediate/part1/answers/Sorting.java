package sda.code.intermediate.part1.answers;

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
		boolean changed;
		do {
			changed = false;
			for (int i = 1; i < array.length; ++i) {
				if (array[i - 1] > array[i]) {
					int tmp = array[i];
					array[i] = array[i - 1];
					array[i - 1] = tmp;
					changed = true;
				}
			}
		} while (changed);
	}

	public int[] quickSort(int array[]) {
		int out[] = array.clone();
		quickSortInner(out, 0, out.length - 1);
		return out;
	}

	private void quickSortInner(int[] out, int low, int high) {
		if (low < high) {
			int p = quickSortInnerPartition(out, low, high);
			quickSortInner(out, low, p - 1);
			quickSortInner(out, p + 1, high);
		}
	}

	private int quickSortInnerPartition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low;
		for (int k = low; k < high; ++k) {
			if (array[k] <= pivot) {
				int tmp = array[k];
				array[k] = array[i];
				array[i] = tmp;
				i += 1;
			}
		}
		int tmp = array[high];
		array[high] = array[i];
		array[i] = tmp;
		return i;
	}

	public int[] mergeSort(int array[]) {
		if (array.length <= 1) {
			return array;
		}

		int halfLength = array.length / 2;
		int odd = array.length % 2 == 1 ? 1 : 0;
		int left[] = new int[halfLength];
		int right[] = new int[halfLength + odd];
		for (int i = 0; i < halfLength; ++i) {
			left[i] = array[i];
		}
		for (int i = halfLength, o = 0; i < array.length; ++i, ++o) {
			right[o] = array[i];
		}

		return mergeSortInnerMerge(mergeSort(left), mergeSort(right));
	}

	private int[] mergeSortInnerMerge(int[] left, int[] right) {
		int result[] = new int[left.length + right.length];

		int leftIdx = 0;
		int rightIdx = 0;
		for (int i = 0; i < result.length; ++i) {
			if (rightIdx >= right.length || leftIdx < left.length && left[leftIdx] < right[rightIdx]) {
				result[i] = left[leftIdx];
				leftIdx += 1;
			} else {
				result[i] = right[rightIdx];
				rightIdx += 1;
			}
		}

		return result;
	}

	public String[] sortStrings(String[] array) {
		String[] out = array.clone();
		Arrays.sort(out);
		return out;
	}

	public static class NumericIntegerComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return Integer.parseInt(o1) - Integer.parseInt(o2);
		}
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
		boolean changed;
		do {
			changed = false;
			for (int i = 1; i < array.length; ++i) {
				if (array[i - 1].compareTo(array[i]) > 0) {
					T tmp = array[i];
					array[i] = array[i - 1];
					array[i - 1] = tmp;
					changed = true;
				}
			}
		} while (changed);
	}

}
