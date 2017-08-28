package sda.code.intermediate.part3.exercises.concurrency;

import sda.code.intermediate.part1.answers.Algorithms;
import sda.code.intermediate.part1.answers.Sorting;
import sda.code.intermediate.part3.RichPrint;

public class ConcurrentComputations {

    private static class Computation {
        private int inArr[] = new Algorithms().createRandomArray(10000);
        private int outArr[];

        public long compute() {
            RichPrint.println("Pierwszy element: " + inArr[0]);
            // do mierzenia czasu wykonania operacji
            // należy używać nanoTime(), nigdy currentTimeMillis()
            long start = System.nanoTime();
            outArr = new Sorting().bubbleSort(inArr);
            long stop = System.nanoTime();
            RichPrint.println("Pierwszy element: " + outArr[0]);
            return stop - start;
        }
    }

    /**
     * Zadanie: Przerobić program tak, by długo działające zadania wykonały się
     * współbieżnie. Algorytm sortowania powinien pozostać bez zmian. W efekcie
     * suma czasów będzie większa od całkowitego czasu.
     */
    public static void main(String[] args) {
        Computation c1 = new Computation();
        Computation c2 = new Computation();
        Computation c3 = new Computation();

        long start = System.nanoTime();
        long t1 = c1.compute();
        long t2 = c2.compute();
        long t3 = c3.compute();
        long stop = System.nanoTime();

        System.out.println("Suma czasów: " + (t1 + t2 + t3) / 1000000L);
        System.out.println("Całkowity czas: " + (stop - start) / 1000000L);
    }

}
