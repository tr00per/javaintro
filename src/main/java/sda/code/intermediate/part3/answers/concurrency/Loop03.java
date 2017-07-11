package sda.code.intermediate.part3.answers.concurrency;

public class Loop03 {

    public static void main(String[] args) {
        for (int i = 0; i < 20; ++i) {
            new MyThread(i).start();
        }
    }

    private static class MyThread extends Thread {
        private int value;

        public MyThread(int value) {
            super();
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println(value);
        }
    }
}
