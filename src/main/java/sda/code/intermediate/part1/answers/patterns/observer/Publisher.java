package sda.code.intermediate.part1.answers.patterns.observer;

public interface Publisher<T> {

    void subscribe(Subscriber<T> subscriber);
}
