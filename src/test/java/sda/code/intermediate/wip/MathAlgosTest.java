package sda.code.intermediate.wip;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MathAlgosTest {

    MathAlgos sut;

    @Before
    public void setUp() {
        sut = new MathAlgos();
    }

    @Test
    public void rootsOfQuadraticEquation() {
        assertArrayEquals("x^2+x -> x=-1 or x=0", new double[]{-1.0, 0.0}, sut.rootsOfQuadraticEquation(1, 1, 0), 0.0001);
        assertArrayEquals("3x^2-6x+3 -> x=1", new double[]{1.0}, sut.rootsOfQuadraticEquation(3, -6, 3), 0.0001);
        assertArrayEquals("0.5x^2+2x-4 -> x=-5.4641 or x=1.4641", new double[]{-5.4641, 1.4641}, sut.rootsOfQuadraticEquation(0.5, 2, -4), 0.0001);
        assertArrayEquals("x^2+x+1 -> no roots", new double[]{}, sut.rootsOfQuadraticEquation(1, 1, 1), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rootsOfQuadraticEquationError() {
        sut.rootsOfQuadraticEquation(0, 1, 1);
    }

    @Test
    public void euclideanGCD() {
        assertEquals(21, sut.euclideanGCD(252, 105));
        assertEquals(21, sut.euclideanGCD(105, 252));
        assertEquals(21, sut.euclideanGCD(1071, 462));
        assertEquals(21, sut.euclideanGCD(462, 1071));
        assertEquals(21, sut.euclideanGCD(462, sut.euclideanGCD(105, 252)));
        assertEquals(21, sut.euclideanGCD(21, 252));
        assertEquals(51, sut.euclideanGCD(1989, 867));
        assertEquals(51, sut.euclideanGCD(867, 1989));
        assertEquals(6, sut.euclideanGCD(174, 12));
        assertEquals(6, sut.euclideanGCD(12, 174));
    }

    @Test
    public void euclideanGCDNegativeInput() {
        assertEquals(21, sut.euclideanGCD(105, -252));
        assertEquals(21, sut.euclideanGCD(-1071, 462));
    }

    @Test
    public void euclideanGCDNoResult() {
        assertEquals(1, sut.euclideanGCD(2671, 6911));
        assertEquals(1, sut.euclideanGCD(3943, 7823));
    }

    @Test
    public void transposeMatrix() {
        int data[][] = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        int expected[][] = {{1, 6, 11, 16, 21}, {2, 7, 12, 17, 22}, {3, 8, 13, 18, 23}, {4, 9, 14, 19, 24}, {5, 10, 15, 20, 25}};
        sut.transposeMatrix(data);
        assertArrayEquals(flatten(expected), flatten(data));
    }

    private int[] flatten(int[][] data) {
        int outSize = 0;
        for (int[] row : data) {
            outSize += row.length;
        }
        int[] out = new int[outSize];
        int outIndex = 0;
        for (int[] row : data) {
            for (int elem : row) {
                out[outIndex] = elem;
                outIndex += 1;
            }
        }
        return out;
    }

}
