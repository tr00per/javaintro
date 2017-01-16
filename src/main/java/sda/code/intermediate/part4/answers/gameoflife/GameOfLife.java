package sda.code.intermediate.part4.answers.gameoflife;

public class GameOfLife {

	private GameWorld world;
	private GameWorld oldWorld;
	private Filler stepFiller;
	private Printer printer;

	public GameOfLife(int width, int height, Filler initialFiller, Filler stepFiller, Printer printer) {
		this.stepFiller = stepFiller;
		this.printer = printer;
		world = new World(width, height, initialFiller);
	}

	public boolean hasChanged() {
		return !world.equals(oldWorld);
	}

	public void runStep() {
		oldWorld = world;
		world = world.step(stepFiller);
	}

	public void print() {
		printer.reset();
		world.forEach(printer);
	}

}
