package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part1.answers.Algorithms;
import sda.code.intermediate.part1.answers.Sorting;
import sda.code.intermediate.part3.RichPrint;

import java.util.concurrent.*;

public class ConcurrentComputations01 {

    private static class Computation implements Callable<Long> {
        private int inArr[] = new Algorithms().createRandomArray(10000);
        private int outArr[];

        @Override
        public Long call() {
            RichPrint.println("Pierwszy element: " + inArr[0]);
            long start = System.nanoTime();
            outArr = new Sorting().bubbleSort(inArr);
            long stop = System.nanoTime();
            RichPrint.println("Pierwszy element: " + outArr[0]);
            return stop - start;
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Computation c1 = new Computation();
        Computation c2 = new Computation();
        Computation c3 = new Computation();

        ExecutorService es = Executors.newFixedThreadPool(2);

        long start = System.nanoTime();
        Future<Long> f1 = es.submit(c1);
        Future<Long> f2 = es.submit(c2);
        es.shutdown();
        long t3 = c3.call();
        long t1 = f1.get();
        long t2 = f2.get();
        long stop = System.nanoTime();

        System.out.println("Suma czasów: " + (t1 + t2 + t3) / 1000000L);
        System.out.println("Całkowity czas: " + (stop - start) / 1000000L);
    }

}
