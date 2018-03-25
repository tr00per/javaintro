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
        Computation c4 = new Computation();
        Computation c5 = new Computation();
        Computation c6 = new Computation();
        Computation c7 = new Computation();
        Computation c8 = new Computation();
        Computation c9 = new Computation();
        Computation c10 = new Computation();
        Computation c11 = new Computation();
        Computation c12 = new Computation();

        ExecutorService es = Executors.newFixedThreadPool(6);

        long start = System.nanoTime();
        Future<Long> f1 = es.submit(c1);
        Future<Long> f2 = es.submit(c2);
        Future<Long> f3 = es.submit(c3);
        Future<Long> f4 = es.submit(c4);
        Future<Long> f5 = es.submit(c5);
        Future<Long> f6 = es.submit(c6);
        Future<Long> f7 = es.submit(c7);
        Future<Long> f8 = es.submit(c8);
        Future<Long> f9 = es.submit(c9);
        Future<Long> f10 = es.submit(c10);
        Future<Long> f11 = es.submit(c11);
        Future<Long> f12 = es.submit(c12);
        es.shutdown();
        long t1 = f1.get();
        long t2 = f2.get();
        long t3 = f3.get();
        long t4 = f4.get();
        long t5 = f5.get();
        long t6 = f6.get();
        long t7 = f7.get();
        long t8 = f8.get();
        long t9 = f9.get();
        long t10 = f10.get();
        long t11 = f11.get();
        long t12 = f12.get();
        long stop = System.nanoTime();

        System.out.println("Suma czasów: " +
                (t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11 + t12) / 1000000L);
        System.out.println("Całkowity czas: " + (stop - start) / 1000000L);
    }

}
