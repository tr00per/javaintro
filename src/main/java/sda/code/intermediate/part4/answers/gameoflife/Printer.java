package sda.code.intermediate.part4.answers.gameoflife;

import java.util.function.Consumer;

public interface Printer extends Consumer<GameEntity> {

	void reset();

	@Override
	void accept(GameEntity e);
}
