package sda.code.intermediate.part1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sda.code.intermediate.SortingUtils;
import sda.code.intermediate.part1.exercises.Algorithms;
import sda.code.intermediate.part1.exercises.Sorting;
import sda.code.intermediate.part1.exercises.data.Person;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

//import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class SortingTest {

    private Sorting sut;
    private SortingUtils check;
    private Algorithms algo;

    private int[] evenIntArray;
    private int[] oddIntArray;
    private String[] stringArray;
    private ArrayList<String> numbersArray;
    private ArrayList<String> sortedNumbersArray;
    private Person[] comparableArray;
    private Person[] comparableSortedArray;

    @Before
    public void setUp() throws Exception {
        sut = new Sorting();
        check = new SortingUtils();
        algo = new Algorithms();
        evenIntArray = algo.createRandomArray(1000);
        oddIntArray = algo.createRandomArray(1001);
        stringArray = new String[]{"lWm12YWDDrr", "OUGQhWLv8kf", "OOxhtdZLlti", "0bSX2yj0x6q", "6h4hyTp62VX",
                "kyBZ4KxPiyS", "HaQMDRN6PZW", "2OE6ghl2bKX", "8kTG9pr3sI9", "AP1pimvBIyJ"};
        numbersArray = new ArrayList<String>(Arrays.asList("0", "1", "10", "11", "2", "21", "3"));
        sortedNumbersArray = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "10", "11", "21"));
        comparableArray = new Person[]{new Person("Mścisław", "Zima"), new Person("Eryk", "Stawski"),
                new Person("Wojciech", "Gniewek"), new Person("Zbigniew", "Pasternack"),
                new Person("Włodzisław", "Zima")};
        comparableSortedArray = new Person[]{new Person("Wojciech", "Gniewek"), new Person("Zbigniew", "Pasternack"),
                new Person("Eryk", "Stawski"), new Person("Mścisław", "Zima"), new Person("Włodzisław", "Zima")};
    }

    @Test
    public void testBubbleSortEven() {
        int outArray[] = sut.bubbleSort(evenIntArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    public void testQuickSortEven() {
        int outArray[] = sut.quickSort(evenIntArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    public void testMergeSortEven() {
        int outArray[] = sut.mergeSort(evenIntArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    public void testBubbleSortOdd() {
        int outArray[] = sut.bubbleSort(oddIntArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    public void testQuickSortOdd() {
        int outArray[] = sut.quickSort(oddIntArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    public void testMergeSortOdd() {
        int outArray[] = sut.mergeSort(oddIntArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    @Parameters({"0", "1"})
    public void testEdgeCasesBubbleSort(int length) {
        int outArray[] = sut.bubbleSort(algo.createRandomArray(length));
        assertEquals(length, outArray.length);
    }

    @Test
    @Parameters({"0", "1"})
    public void testEdgeCasesQuickSort(int length) {
        int outArray[] = sut.quickSort(algo.createRandomArray(length));
        assertEquals(length, outArray.length);
    }

    @Test
    @Parameters({"0", "1"})
    public void testEdgeCasesMergeSort(int length) {
        int outArray[] = sut.mergeSort(algo.createRandomArray(length));
        assertEquals(length, outArray.length);
    }

    @Test
    public void testSortStrings() {
        String outArray[] = sut.sortStrings(stringArray);
        assertTrue(check.isSorted(outArray));
    }

    @Test
    public void testSortWithComparator() {
        fail("Uncomment test");
//		ArrayList<String> outArray = sut.sortWithComparator(numbersArray, new Sorting.NumericIntegerComparator());
//		assertEquals(sortedNumbersArray, outArray);
    }

    @Test
    public void testSortComparable() {
        Person outArray[] = sut.sortComparable(comparableArray);
        assertArrayEquals(comparableSortedArray, outArray);
    }

}
