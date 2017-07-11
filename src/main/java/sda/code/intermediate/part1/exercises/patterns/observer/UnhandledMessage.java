package sda.code.intermediate.part1.exercises.patterns.observer;

/**
 * Wyjątek, który sygnalizuje, że subskrybent nie rozpoznał wiadomości, którą
 * otrzymał. Mogłoby to sie zdarzyć, jeśli implementacje byłyby utrzymywane
 * przez różne zespoły albo po prostu ktoś zapomniał dokonać zmiany po
 * wprowadzeniu nowego typu zdarzenia.
 */
public class UnhandledMessage extends RuntimeException {

    private static final long serialVersionUID = -6356263632841596251L;

    public UnhandledMessage(String message) {
        super(message);
    }

}
