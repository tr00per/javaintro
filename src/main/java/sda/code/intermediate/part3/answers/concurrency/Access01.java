package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

public class Access01 {

    private static long accumulator = 0L;

    public static void add() {
        accumulator += 1L;
        RichPrint.println(accumulator);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            new Thread(Access01::add).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
