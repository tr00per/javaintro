package sda.code.intermediate.part1.exercises.patterns;

/**
 * Wyjątek informujący użytkownika o brakującym ustawieniu. Jest zgłaszany jako
 * wyjątek niesprawdzany, ponieważ przez większość czasu, jeśli użytkownik
 * próbuje odwołać się do nieistniejącego ustawienia, to jest to błąd w
 * konfiguracji bądź błąd w użyciu. I podobnie, przez większość czasu ocekujemy,
 * że operacja pobrania ustawienia z konfiguracji zakończy się powodzeniem, więc
 * zmuszanie użytkownika do jawnej obsługi tego wyjątku doprowadziłoby go do
 * napisania własnego adaptera, który przerzucałby wyjątki sprawdzane na
 * niesprawdzane.
 */
public class SettingMissing extends RuntimeException {

    /**
     * serialVersionUID - potrzebne przy używaniu javowej serializacji binarnej.
     * Wartość powinna zostać zmieniona, jeśli zmieni się coś w środku klasy.
     */
    private static final long serialVersionUID = 5534548210197090062L;

    public SettingMissing(String name) {
        super("Key not found: " + name);
    }

}
