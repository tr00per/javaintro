package sda.code.intermediate.part1.exercises.patterns.observer;

/**
 * Subscriber potrafi obsługiwać nadchodzące zdażenia sparametryzowane danym typem T
 */
public interface Subscriber<T> {

    void handle(Event<T> event);
}
