package sda.code.intermediate.part4.answers.gameoflife.core;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import sda.code.intermediate.part4.answers.gameoflife.Filler;
import sda.code.intermediate.part4.answers.gameoflife.GameEntity;
import sda.code.intermediate.part4.answers.gameoflife.GameWorld;

public class World implements GameWorld {

	private final int width;
	private final int height;
	private final GameEntity[] universe;

	public World(int width, int height, Filler filler) {
		this.width = width;
		this.height = height;
		universe = fillUniverse(filler);
	}

	private World(GameEntity[] universe, int width, int height) {
		this.width = width;
		this.height = height;
		this.universe = universe;
	}

	@Override
	public GameWorld step(Filler stepFiller) {
		final GameEntity[] bigBang = fillUniverse(stepFiller);
		return new World(bigBang, width, height);
	}

	@Override
	public void forEach(Consumer<GameEntity> entityEnjoyer) {
		Stream.of(universe).forEach(entityEnjoyer);
	}

	@Override
	public Optional<GameEntity> get(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return Optional.empty();
		}
		return Optional.of(universe[y * width + x]);
	}

	private GameEntity[] fillUniverse(Filler filler) {
		return (GameEntity[]) IntStream.range(0, width * height)
				.mapToObj(idx -> filler.fill(this, idx % width, idx / width)).toArray(GameEntity[]::new);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(universe);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof World)) {
			return false;
		}
		World other = (World) obj;
		if (!Arrays.equals(universe, other.universe)) {
			return false;
		}
		return true;
	}

}
