package sda.code.intermediate.part1.exercises.patterns.observer;

public interface Event<T> {

	T getContext();
}
