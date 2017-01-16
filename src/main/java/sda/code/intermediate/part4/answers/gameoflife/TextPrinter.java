package sda.code.intermediate.part4.answers.gameoflife;

public class TextPrinter implements Printer {

	private final int width;
	private final int height;
	private int idx;

	public TextPrinter(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void reset() {
		idx = 0;
	}

	@Override
	public void accept(Entity e) {
		if (e.isAlive()) {
			System.out.print('@');
		} else {
			System.out.print(' ');
		}
		idx += 1;
		if (idx % width == 0) {
			System.out.println();
		}
		if (idx == width * height) {
			System.out.println();
		}
	}

}
