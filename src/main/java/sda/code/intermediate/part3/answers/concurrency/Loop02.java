package sda.code.intermediate.part3.answers.concurrency;

public class Loop02 {

	public static void main(String[] args) {
		for (int i = 0; i < 20; ++i) {
			final int v = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(v);
				}
			}).start();
		}
	}

}
