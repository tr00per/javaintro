package sda.code.intermediate.part3.answers.concurrency;

public class ThreadUtils {

	public static void println(String msg) {
		System.err.println(Thread.currentThread().getName() + ": " + msg);
	}

	public static void println(Object obj) {
		if (obj == null) {
			println("null");
		} else {
			println(obj.toString());
		}
	}
}
