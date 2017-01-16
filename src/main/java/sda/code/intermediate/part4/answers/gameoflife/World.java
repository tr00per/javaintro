package sda.code.intermediate.part4.answers.gameoflife;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class World {

	private final int width;
	private final int height;
	private final Entity[] universe;

	public World(int width, int height, Filler filler) {
		this.width = width;
		this.height = height;
		universe = fillUniverse(filler);
	}

	private World(Entity[] universe, int width, int height) {
		this.width = width;
		this.height = height;
		this.universe = universe;
	}

	public World step(Filler stepFiller) {
		final Entity[] bigBang = fillUniverse(stepFiller);
		return new World(bigBang, width, height);
	}

	public void forEach(Consumer<Entity> entityEnjoyer) {
		Stream.of(universe).forEach(entityEnjoyer);
	}

	private Entity[] fillUniverse(Filler filler) {
		return (Entity[]) IntStream.range(0, width * height)
				.mapToObj(idx -> filler.fill(this, idx % width, idx / width)).toArray(Entity[]::new);
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
