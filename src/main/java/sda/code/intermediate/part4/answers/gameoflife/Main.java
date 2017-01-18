package sda.code.intermediate.part4.answers.gameoflife;

import sda.code.intermediate.part4.answers.gameoflife.console.TextPrinter;
import sda.code.intermediate.part4.answers.gameoflife.core.EntityFactory;
import sda.code.intermediate.part4.answers.gameoflife.core.GameOfLife;

public class Main {

	private static final int WORLD_HEIGHT = 30;
	private static final int WORLD_WIDTH = 60;

	public static void main(String[] args) throws InterruptedException {
		GameEntityFactory entityFactory = new EntityFactory();
		Populations populations = new Populations(entityFactory);
		System.out.println("-----------------------\n Conway's Game of Life\n-----------------------");
		GameOfLife gol = new GameOfLife(WORLD_WIDTH, WORLD_HEIGHT, populations.random(0.5), populations.classic(),
				new TextPrinter(WORLD_WIDTH));
		int rounds = 0;
		while (gol.hasChanged() && rounds++ < 500) {
			gol.print();
			gol.runStep();
			Thread.sleep(500);
		}
		System.out.println("Done.");
	}
}
