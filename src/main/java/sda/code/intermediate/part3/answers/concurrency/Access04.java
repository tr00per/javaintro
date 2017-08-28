package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Access04 {

    private static long accumulator = 0L;
    private static Lock lock = new ReentrantLock();

    private static void add() {
        try {
            inc();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void inc() throws InterruptedException {
        if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                accumulator += 1L;
                RichPrint.println(accumulator);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            new Thread(Access04::add).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
