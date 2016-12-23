package sda.code.intermediate.part1.exercises.patterns.observer;

/**
 * Publisher potrafi akceptować subskrybentów, jeśli obaj są zparametryzowani
 * tym samym typem T.
 */
public interface Publisher<T> {

	void subscribe(Subscriber<T> subscriber);
}
