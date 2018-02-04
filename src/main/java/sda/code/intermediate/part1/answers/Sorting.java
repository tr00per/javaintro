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
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; ++i) {
                if (array[i - 1] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public int[] quickSort(int array[]) {
        int out[] = array.clone();
        quickSortInner(out, 0, out.length - 1);
        return out;
    }

    private void quickSortInner(int[] out, int low, int high) {
        if (low < high) {
            int p = partition(out, low, high);
            quickSortInner(out, low, p - 1);
            quickSortInner(out, p + 1, high);
        }
    }

    private int partition(int[] out, int low, int high) {
        int pivot = out[high];
        int i = low - 1;
        for (int j = low; j < high; ++j) {
            if (out[j] < pivot) {
                i = i + 1;
                int tmp = out[i];
                out[i] = out[j];
                out[j] = tmp;
            }
        }
        if (out[high] < out[i + 1]) {
            int tmp = out[i + 1];
            out[i + 1] = out[high];
            out[high] = tmp;
        }
        return i + 1;
    }

    public int[] mergeSort(int array[]) {
        if (array.length <= 1) {
            return array;
        }

        int[] left = new int[array.length / 2];
        int[] right = new int[array.length / 2 + (array.length % 2)];

        for (int i = 0, l = 0, r = 0; i < array.length; ++i) {
            if (i < array.length / 2) {
                left[l] = array[i];
                l += 1;
            } else {
                right[r] = array[r++];
            }
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int l = 0, r = 0, i = 0;
        for (; l < left.length && r < right.length; ++i) {
            if (left[l] < right[r]) {
                result[i] = left[l++];
            } else {
                result[i] = right[r];
                r += 1;
            }
        }

        while (l < left.length) {
            result[i++] = left[l++];
        }
        while (r < right.length) {
            result[i++] = right[r++];
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
