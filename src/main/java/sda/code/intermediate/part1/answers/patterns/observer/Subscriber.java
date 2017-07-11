package sda.code.intermediate.part1.answers.patterns.observer;

public interface Subscriber<T> {

    void handle(Event<T> event);
}
