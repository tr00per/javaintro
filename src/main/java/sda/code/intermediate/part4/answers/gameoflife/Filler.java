package sda.code.intermediate.part4.answers.gameoflife;

@FunctionalInterface
public interface Filler {

	GameEntity fill(GameWorld w, int x, int y);
}
