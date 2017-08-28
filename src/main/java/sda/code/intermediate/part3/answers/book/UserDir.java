package sda.code.intermediate.part3.answers.book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class UserDir {

    /**
     * Demonstracja pobrania w przenośny sposób lokalizacji katalogu użytkownika
     * i plików w nim zawartych. Przenośny - tak samo będzie wyglądać na
     * Windowsie, Linuksie i Macu.
     */
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.home"));
        File source = Paths
                .get(System.getProperty("user.home"), "Desktop", "Shakespeare.txt")
                .toFile();
        try (Scanner scan = new Scanner(source)) {
            run(scan);
        }
        System.out.println(source.getAbsolutePath());

        Map<String, Integer> mapa = new HashMap<>();
        Set<Entry<String, Integer>> entries = mapa.entrySet();
    }

    private static void run(Scanner ins) {
        // tu kod
    }
}
