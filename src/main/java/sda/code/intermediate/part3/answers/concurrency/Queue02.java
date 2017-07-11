package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.ThreadUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue02 {

    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    private static void printWorker() {
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
        ExecutorService consumer = Executors.newSingleThreadExecutor();
        consumer.submit(Queue02::printWorker);
        consumer.shutdown();

        ExecutorService producers = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; ++i) {
            final int v = i;
            producers.submit(() -> queue.add(v));
        }
        producers.shutdown();
        // producers.awaitTermination(1, TimeUnit.SECONDS);

        queue.add(-1);
        // consumer.awaitTermination(1, TimeUnit.SECONDS);
    }

}
