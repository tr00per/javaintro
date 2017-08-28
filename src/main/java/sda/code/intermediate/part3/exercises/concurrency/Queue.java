package sda.code.intermediate.part3.exercises.concurrency;

import java.util.concurrent.BlockingQueue;

public class Queue {
    private static final int NUM_OF_CONSUMER_THREADS = 5;
    private static BlockingQueue<Integer> queue;

    public static void consume() {
        System.err.println(Thread.currentThread().getName() + ": ");
    }

    /**
     * Zadanie: zastosować kolejkę do komunikacji między producentami i
     * konsumerami.
     */
    public static void main(String[] args) {
        for (int i = 0; i < NUM_OF_CONSUMER_THREADS * 10; ++i) {
            queue.add(i);
        }
        System.out.println("Everything added!");
    }

}
