package sda.code.intermediate.part3.exercises.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import sda.code.intermediate.part3.ThreadUtils;

public class Queue {

	private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	private static AtomicBoolean running = new AtomicBoolean(true);

	private static void printWorker() {
		// ...?
		ThreadUtils.println(running.get());
	}

	public static void main(String[] args) {

	}

}
