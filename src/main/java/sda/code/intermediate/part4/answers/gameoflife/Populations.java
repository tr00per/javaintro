package sda.code.intermediate.part4.answers.gameoflife;

import java.util.Random;

public class Populations {
	private static Random rng = new Random();

	public static Filler randomPopulation(double newbornRatio) {
		return (w, x, y) -> rng.nextDouble() < newbornRatio ? Entity.newborn() : Entity.dead();
	}

	public static Filler classic() {
		return (w, x, y) -> {
			final int neighbours = countNeighbours(w, x, y);
			final GameEntity current = w.get(x, y);
			if (current.isAlive()) {
				if (neighbours == 2 || neighbours == 3) {
					return current.descendant();
				} else {
					return Entity.dead();
				}
			} else {
				if (neighbours == 3) {
					return Entity.newborn();
				} else {
					return Entity.dead();
				}
			}
		};
	}

	private static int countNeighbours(GameWorld w, int x, int y) {
		int count = 0;
		for (int dx = -1; dx <= 1; ++dx) {
			for (int dy = -1; dy <= 1; ++dy) {
				if (dx == 0 && dy == 0) {
					continue;
				}
				if (w.get(x + dx, y + dy).isAlive()) {
					count += 1;
				}
			}
		}
		return count;
	}
}
