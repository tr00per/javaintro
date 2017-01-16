package sda.code.intermediate.part4.answers.gameoflife;

public class Main {

	private static final int WORLD_HEIGHT = 30;
	private static final int WORLD_WIDTH = 60;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("-----------------------\n Conway's Game of Life\n-----------------------");
		GameOfLife gol = new GameOfLife(WORLD_WIDTH, WORLD_HEIGHT, Populations.randomPopulation(0.5),
				Populations.classic(), new TextPrinter(WORLD_WIDTH));
		int rounds = 0;
		while (gol.hasChanged() && rounds++ < 500) {
			gol.print();
			gol.runStep();
			Thread.sleep(1000);
		}
		System.out.println("Done.");
	}
}
