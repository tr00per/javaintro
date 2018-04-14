package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

public class Access02 {

    private static long accumulator = 0L;
//    private static final Object lock = new Object();

    private static synchronized void add() {
//        if (accumulator < 500) {
//            synchronized (lock) {
                accumulator += 1L;
//            }
//        }
        RichPrint.println(accumulator);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            new Thread(Access02::add).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
