package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part1.answers.Algorithms;
import sda.code.intermediate.part1.answers.Sorting;
import sda.code.intermediate.part3.RichPrint;

import java.util.concurrent.*;

public class ConcurrentComputations00 {

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
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pula = Executors.newFixedThreadPool(3);
//        ExecutorService pula = Executors.newCachedThreadPool();
        Computation c0 = new Computation();
        Computation c1 = new Computation();
        Computation c2 = new Computation();
        Computation c3 = new Computation();

        long start = System.nanoTime();
        // źle: blokujemy od razu w oczekiwaniu na wynik - rezygnujemy
        // tym samym z równoległego wykonania zadań
//        long t1 = pula.submit(() -> { return c1.compute(); }).get();
//        long t2 = pula.submit(() -> c2.compute()).get();
//        long t3 = pula.submit(c3::compute).get();

        // dobrze:

        // zlecam zadania (4 równoważne sposoby)
        // 1. anonimowa implementacja interfejsu
        Future<Long> t0future = pula.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return c0.compute();
            }
        });
        // 2. lambda (funkcja anonimowa) w wersji nadmiarowej
        // (nadmiarowej, bo jest tylko return w środku)
        Future<Long> t1future = pula.submit(() -> {
            return c1.compute();
        });
        // 3. lambda "po prostu"
        Future<Long> t2future = pula.submit(() -> c2.compute());
        // 4. referencja do metody obiektu c3
        Future<Long> t3future = pula.submit(c3::compute);

        // bonus #1: przekazuję instancję klasy, która implementuje Callable<String>
        Future<String> zeStringiem1 = pula.submit(new CallableClass("Kopytko1"));
        // bonus #2: róważna konstrukcja za pomocą funkcji anonimowej
        final String wiadomosc = "Kopytko2";
        Future<String> zeStringiem2 = pula.submit(() -> wiadomosc + " ABC " + start);

        // 1. blokuje możliwość dodania nowych zadań
        // 2. kiedy zadania zostaną wykonane - wątki będą wygaszone
        pula.shutdown();

        // ... więcej skomplikowanych działań ...

        // zbieram wyniki
        long t0 = t0future.get();
        long t1 = t1future.get();
        long t2 = t2future.get();
        long t3 = t3future.get();

        long stop = System.nanoTime();

        System.out.println("Suma czasów: " + (t0 + t1 + t2 + t3) / 1000000L);
        System.out.println("Całkowity czas: " + (stop - start) / 1000000L);

        System.err.println(zeStringiem1.get());
        System.err.println(zeStringiem2.get());
    }

    public static class CallableClass implements Callable<String> {

        private final String wartosc;

        public CallableClass(String wartosc) {
            this.wartosc = wartosc;
        }

        @Override
        public String call() throws Exception {
            return wartosc + " ABC";
        }
    }
}
