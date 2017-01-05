package sda.code.intermediate.part3.answers.concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class Access04 {

	private static AtomicLong accumulator = new AtomicLong(0L);

	private static void add() {
		accumulator.incrementAndGet();
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
