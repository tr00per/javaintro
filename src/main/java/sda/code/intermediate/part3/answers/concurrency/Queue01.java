package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Queue01 {

    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    private static AtomicBoolean running = new AtomicBoolean(true);

    private static void printWorker() {
        int counter = 0;
        while (running.get()) {
            try {
                print(++counter);
            } catch (InterruptedException e) {
                RichPrint.println("Interrupted");
                break;
            }
        }
        RichPrint.println("Running: " + running.get());
    }

    private static void print(int counter) throws InterruptedException {
        final Integer item = queue.take();
        RichPrint.println(++counter + ": " + item);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService consumer = Executors.newSingleThreadExecutor();
        consumer.submit(Queue01::printWorker);
        consumer.shutdown();

        ExecutorService producers = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; ++i) {
            final int v = i;
            producers.submit(() -> {
                RichPrint.println(v);
                queue.add(v);
            });
        }
        producers.shutdown();
        // producers.awaitTermination(1, TimeUnit.SECONDS);
        // running.compareAndSet(true, false);
        // consumer.awaitTermination(1, TimeUnit.SECONDS);
    }

}
