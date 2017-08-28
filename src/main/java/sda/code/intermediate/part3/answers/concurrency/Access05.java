package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

import java.util.concurrent.atomic.AtomicLong;

public class Access05 {

    private static AtomicLong accumulator = new AtomicLong(0L);

    private static void add() {
        long acc = accumulator.incrementAndGet();
        RichPrint.println(acc);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            new Thread(Access05::add).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
