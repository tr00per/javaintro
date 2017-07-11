package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.ThreadUtils;

import java.util.concurrent.*;

public class Queue03 {

    private static final int NUM_OF_CONSUMER_THREADS = 5;
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    private static void printAll() {
        int counter = 0;
        while (true) {
            try {
                Integer item = print(++counter);
                if (item < 0) {
                    ThreadUtils.println("Marker received!");
                    break;
                }
            } catch (InterruptedException e) {
                ThreadUtils.println("Interrupted");
                break;
            }
        }
        ThreadUtils.println("Finished");
    }

    private static Integer print(int counter) throws InterruptedException {
        final Integer item = queue.take();
        ThreadUtils.println(counter + ": " + item);
        return item;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService consumers = Executors.newFixedThreadPool(NUM_OF_CONSUMER_THREADS);
        for (int i = 0; i < NUM_OF_CONSUMER_THREADS; ++i) {
            consumers.submit(() -> printAll());
        }
        consumers.shutdown();

        for (int i = 0; i < NUM_OF_CONSUMER_THREADS * 5; ++i) {
            queue.add(i);
        }

        for (int i = 0; i < NUM_OF_CONSUMER_THREADS; ++i) {
            queue.add(-1);
        }
        consumers.awaitTermination(1, TimeUnit.SECONDS);
    }

}
