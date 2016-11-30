package sda.code.intermediate.part1.exercises.patterns.observer;

public interface Subscriber<T> {

	void handle(Event<T> event);
}
