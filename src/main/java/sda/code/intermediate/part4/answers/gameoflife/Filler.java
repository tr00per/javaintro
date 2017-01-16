package sda.code.intermediate.part4.answers.gameoflife;

@FunctionalInterface
public interface Filler {

	Entity fill(World w, int x, int y);
}
