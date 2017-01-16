package sda.code.intermediate.part4.answers.gameoflife;

public class Main {

	public static void main(String[] args) {
		System.out.println("Started");
		GameOfLife gol = new GameOfLife(30, 30, Populations.randomPopulation(0.5), Populations.classic(),
				new TextPrinter(30, 30));
		while (gol.hasChanged()) {
			gol.runStep();
			gol.print();
		}
		System.out.println("Finished");
	}
}
