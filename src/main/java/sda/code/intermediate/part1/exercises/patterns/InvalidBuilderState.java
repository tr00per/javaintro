package sda.code.intermediate.part1.exercises.patterns;

/**
 * Wyjątek informujący użytkownika o nieprawidłowym stanie buildera.
 *
 * @see SettingMissing
 */
public class InvalidBuilderState extends RuntimeException {

    private static final long serialVersionUID = 3992517656138461008L;

    public InvalidBuilderState(String msg) {
        super(msg);
    }

}
