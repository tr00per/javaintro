package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Access04 {

    private static long accumulator = 0L;
    private static Lock lock = new ReentrantLock();

    private static void add() {
        try {
            lock.lock();
            accumulator += 1L;
            RichPrint.println(accumulator);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            new Thread(() -> add()).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
