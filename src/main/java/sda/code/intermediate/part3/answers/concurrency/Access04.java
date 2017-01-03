package sda.code.intermediate.part3.answers.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Access04 {

	private static AtomicInteger accumulator = new AtomicInteger(0);

	private static void add() {
		System.out.println(accumulator.incrementAndGet());
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
