package sda.code.intermediate.part3.answers.book;

import java.io.File;
import java.nio.file.Paths;

public class UserDir {

    /**
     * Demonstracja pobrania w przenośny sposób lokalizacji katalogu użytkownika
     * i plików w nim zawartych. Przenośny - tak samo będzie wyglądać na
     * Windowsie, Linuksie i Macu.
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        File source = Paths.get(System.getProperty("user.home"), "Desktop", "Shakespeare.txt").toFile();
        System.out.println(source.getAbsolutePath());
    }
}
