package sda.code.intermediate.part3.answers.concurrency;

public class Access02 {

	private static long accumulator = 0L;

	private static synchronized void add() {
		accumulator += 1L;
		System.err.println(accumulator);
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 20; ++i) {
			new Thread(() -> add()).start();
		}
		Thread.sleep(1000);
		System.out.println(accumulator);
	}

}
