package sda.code.intermediate.part4.answers.gameoflife;

public class TextPrinter implements Printer {

	private final int width;
	private int idx;

	public TextPrinter(int width) {
		this.width = width;
	}

	@Override
	public void reset() {
		idx = 0;
		System.out.println();
		System.out.flush();
	}

	@Override
	public void accept(GameEntity e) {
		if (e.isAlive()) {
			System.out.print('@');
		} else {
			System.out.print(' ');
		}
		idx += 1;
		if (idx % width == 0) {
			System.out.println();
		}
	}

}
