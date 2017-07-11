package sda.code.intermediate.part1.answers;

import java.math.BigInteger;

public class Algorithms {

    public int smallest(int array[]) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("Array empty or null!");
        }
        int result = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < result) {
                result = array[i];
            }
        }
        return result;
    }

    public int largest(int array[]) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("Array empty or null!");
        }
        int result = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > result) {
                result = array[i];
            }
        }
        return result;
    }

    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Can't compute factorial of a negative number!");
        }
        long result = 1L;
        for (int i = 1; i <= n; ++i) {
            result *= i;
        }
        return result;
    }

    public long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Can't compute fibonacci number of a negative index!");
        }
        if (n == 0) {
            return 0L;
        }
        long prev = 0L;
        long result = 1L;
        for (int i = 2; i <= n; ++i) {
            long nextPrev = result;
            result = result + prev;
            prev = nextPrev;
        }
        return result;
    }

    public BigInteger bigFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Can't compute fibonacci number of a negative index!");
        }
        if (n == 0) {
            return BigInteger.ZERO;
        }
        BigInteger prev = BigInteger.ZERO;
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; ++i) {
            BigInteger nextPrev = result;
            result = result.add(prev);
            prev = nextPrev;
        }
        return result;
    }

    public int[] createRandomArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Cannot create an array of negative length");
        }
        int array[] = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (int) Math.round(2L * Math.random() * Integer.MAX_VALUE + Integer.MIN_VALUE);
        }
        return array;
    }

}
