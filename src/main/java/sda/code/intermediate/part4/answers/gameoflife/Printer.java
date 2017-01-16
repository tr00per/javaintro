package sda.code.intermediate.part4.answers.gameoflife;

import java.util.function.Consumer;

public interface Printer extends Consumer<Entity> {

	void reset();

	@Override
	void accept(Entity e);
}
