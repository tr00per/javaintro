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

	/**
	 * https://en.wikipedia.org/wiki/Bubble_sort#Pseudocode_implementation
	 */
	private void bubbleSortInner(int array[]) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public int[] quickSort(int array[]) {
		int out[] = array.clone();
		quickSortInner(out, 0, out.length - 1);
		return out;
	}

	/**
	 * https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme
	 */
	private void quickSortInner(int[] out, int low, int high) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	/**
	 * https://en.wikipedia.org/wiki/Merge_sort#Top-down_implementation_using_lists
	 */
	public int[] mergeSort(int array[]) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	/**
	 * Tu trzeba skorzystać z kopii wcześniej zaimplementowanego algorytmu,
	 * dostosowując go do porównywania łańcuchów znakowych.
	 */
	public String[] sortStrings(String[] array) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	/**
	 * Potrzebna jest implementacja Comparatora, który potrafi porównywać liczby
	 * zapisane w łańcuchach znakowych tak, jakby były zwykłymi liczbami.
	 */
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

	/**
	 * Potrzebna jest kopia jednego z wcześniejszych algorytmów, korzystająca z
	 * metody compareTo obiektów klasy Person. Implementacja compareTo klasy
	 * Person jest pusta. Sortowanie powinno nastąpić po nazwiskach, a potem po
	 * imionach.
	 */
	private <T extends Comparable<? super T>> void sortComparableInner(T[] array) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
