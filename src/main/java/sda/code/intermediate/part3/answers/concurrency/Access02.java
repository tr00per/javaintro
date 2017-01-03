package sda.code.intermediate.part3.answers.concurrency;

public class Access02 {

	private static int accumulator = 0;

	private static synchronized void add() {
		accumulator += 1;
		System.out.println(accumulator);
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 20; ++i) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					add();
				}
			}).start();
		}
		Thread.sleep(1000);
		System.out.println(accumulator);
	}

}
