package sda.code.intermediate.part1;

import org.junit.Before;
import org.junit.Test;
import sda.code.intermediate.part1.exercises.Algorithms;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class AlgorithmsTest {

    Algorithms sut;

    @Before
    public void setUp() throws Exception {
        sut = new Algorithms();
    }

    @Test
    public void testSmallest() {
        assertEquals(1, sut.smallest(new int[]{1, 2, 3, 4, 5}));
        assertEquals(-1, sut.smallest(new int[]{1, 2, 3, -1, 4, 5}));
        assertEquals(0, sut.smallest(new int[]{0}));
        assertEquals(0, sut.smallest(new int[]{0, 0}));
    }

    @Test
    public void testLargest() {
        assertEquals(5, sut.largest(new int[]{1, 2, 3, 4, 5}));
        assertEquals(8, sut.largest(new int[]{1, 2, 8, 3, 4, 5}));
        assertEquals(0, sut.largest(new int[]{0}));
        assertEquals(0, sut.largest(new int[]{0, 0}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryTestSmallest() {
        sut.smallest(new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryTestLargest() {
        sut.largest(new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTestSmallest() {
        sut.smallest(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTestLargest() {
        sut.largest(null);
    }

    @Test
    public void testFactorial() {
        assertEquals(1, sut.factorial(0));
        assertEquals(1, sut.factorial(1));
        assertEquals(2, sut.factorial(2));
        assertEquals(120, sut.factorial(5));
        assertEquals(3628800, sut.factorial(10));
    }

    @Test
    public void testFibonacciBeginning() {
        assertEquals(0, sut.fibonacci(0));
        assertEquals(1, sut.fibonacci(1));
        assertEquals(1, sut.fibonacci(2));
        assertEquals(2, sut.fibonacci(3));
    }

    @Test
    public void testFibonacciAfterBeginning() {
        assertEquals(3, sut.fibonacci(4));
        assertEquals(5, sut.fibonacci(5));
        assertEquals(8, sut.fibonacci(6));
    }

    @Test
    public void testFibonacciFurther() {
        assertEquals(12586269025L, sut.fibonacci(50));
    }

    @Test
    public void testBigFibonacciBeginning() {
        assertEquals(new BigInteger("0"), sut.bigFibonacci(0));
        assertEquals(new BigInteger("1"), sut.bigFibonacci(1));
        assertEquals(new BigInteger("1"), sut.bigFibonacci(2));
        assertEquals(new BigInteger("2"), sut.bigFibonacci(3));
        assertEquals(new BigInteger("3"), sut.bigFibonacci(4));
        assertEquals(new BigInteger("5"), sut.bigFibonacci(5));
        assertEquals(new BigInteger("8"), sut.bigFibonacci(6));
    }

    @Test
    public void testBigFibonacciFurther() {
        assertEquals(new BigInteger("12586269025"), sut.bigFibonacci(50));
        assertEquals(new BigInteger("354224848179261915075"), sut.bigFibonacci(100));
        final BigInteger expected = new BigInteger(
                "43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875");
        assertEquals(expected, sut.bigFibonacci(1000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryTestFactorial() {
        sut.factorial(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryTestFibonacci() {
        sut.fibonacci(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryTestBigFibonacci() {
        sut.bigFibonacci(-1);
    }

}
