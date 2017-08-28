package sda.code.intermediate.part3.answers.concurrency;

import sda.code.intermediate.part3.RichPrint;

public class Access03 {

    private static volatile long accumulator = 0L;

    private static void add() {
        accumulator += 1L;
        RichPrint.println(accumulator);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            new Thread(Access03::add).start();
        }
        Thread.sleep(1000);
        System.out.println(accumulator);
    }

}
