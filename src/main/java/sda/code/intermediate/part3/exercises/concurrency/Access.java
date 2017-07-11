package sda.code.intermediate.part3.exercises.concurrency;

public class Access {

    private static long accumulator = 0L;

    private static void add() {
        accumulator += 1L;
        System.err.println(Thread.currentThread().getName() + ": " + accumulator);
    }

    /**
     * Zadanie: Zastosować jeden z mechanizmów synchronizacji, by otrzymać
     * przewidywalny wynik
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            new Thread(() -> add()).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
