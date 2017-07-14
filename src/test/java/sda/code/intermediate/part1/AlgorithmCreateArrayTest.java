package sda.code.intermediate.part1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sda.code.intermediate.SortingChecks;
import sda.code.intermediate.part1.exercises.Algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnitParamsRunner.class)
public class AlgorithmCreateArrayTest {

    Algorithms sut;

    @Before
    public void setUp() throws Exception {
        sut = new Algorithms();
    }

    @Test
    @Parameters({"0", "1"})
    public void testCreateRandomVeryShortArray(int length) {
        int[] array = sut.createRandomArray(length);
        assertEquals(length, array.length);
    }

    @Test
    @Parameters({"10", "100", "1000"})
    public void testCreateRandomArray(int length) {
        int[] array = sut.createRandomArray(length);
        assertEquals(length, array.length);
        assertFalse(new SortingChecks().isSorted(array));
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryTestCreateRandomArray() {
        sut.createRandomArray(-1);
    }
}
