package sda.code.intermediate.part1.exercises.patterns.observer;

public interface Publisher<T> {

	void subscribe(Subscriber<T> subscriber);
}
