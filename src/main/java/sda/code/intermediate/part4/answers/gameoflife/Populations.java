package sda.code.intermediate.part4.answers.gameoflife;

import java.util.Optional;
import java.util.Random;

public class Populations {
	private static Random rng = new Random();

	private GameEntityFactory entityFactory;

	public Populations(GameEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public Filler randomPopulation(double newbornRatio) {
		return (w, x, y) -> rng.nextDouble() < newbornRatio ? entityFactory.newborn() : entityFactory.dead();
	}

	public Filler classic() {
		return (w, x, y) -> {
			final int neighbours = countNeighbours(w, x, y);
			final Optional<GameEntity> current = w.get(x, y);
			if (current.isPresent() && current.get().isAlive()) {
				if (neighbours == 2 || neighbours == 3) {
					return current.get().descendant();
				} else {
					return entityFactory.dead();
				}
			} else {
				if (neighbours == 3) {
					return entityFactory.newborn();
				} else {
					return entityFactory.dead();
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
				final Optional<GameEntity> neighbour = w.get(x + dx, y + dy);
				if (neighbour.isPresent() && neighbour.get().isAlive()) {
					count += 1;
				}
			}
		}
		return count;
	}
}
