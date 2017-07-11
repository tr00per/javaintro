package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.ThreadUtils;

import java.util.concurrent.atomic.AtomicLong;

public class Access05 {

    private static AtomicLong accumulator = new AtomicLong(0L);

    private static void add() {
        accumulator.incrementAndGet();
        ThreadUtils.println(accumulator);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            new Thread(() -> add()).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
