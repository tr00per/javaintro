package sda.code.intermediate.part3.answers.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorService01 {

	private static AtomicInteger accumulator = new AtomicInteger(0);

	private static void add() {
		System.out.println(accumulator.incrementAndGet());
	}

	public static void main(String[] args) throws InterruptedException {
		final ExecutorService pool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; ++i) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					add();
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.SECONDS);
		System.out.println(accumulator);
	}

}
