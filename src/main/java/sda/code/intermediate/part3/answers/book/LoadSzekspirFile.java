package sda.code.intermediate.part3.answers.book;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class LoadSzekspirFile {
    public Scanner fromAbsolute() throws FileNotFoundException {
        return new Scanner(new File("/home/czajka/data/Shakespeare.txt"));
    }

    public BufferedReader brFromAbsolute() throws FileNotFoundException {
        return new BufferedReader(new FileReader("/home/czajka/data/Shakespeare.txt"));
    }

    public Scanner fromAnotherAbsolutePath() throws FileNotFoundException {
        File source = Paths.get(System.getProperty("user.home"), "Desktop", "Shakespeare.txt").toFile();
        return new Scanner(source);
    }

    public Scanner fromResource() throws FileNotFoundException {
        InputStream istream = LoadSzekspirFile.class.getResourceAsStream("/Shakespeare.txt");
        if (istream == null) {
            throw new FileNotFoundException("resource:/Shakespeare.txt");
        }
        return new Scanner(istream);
    }

    public BufferedReader brFromResource() throws FileNotFoundException {
        InputStream istream = LoadSzekspirFile.class.getResourceAsStream("/Shakespeare.txt");
        if (istream == null) {
            throw new FileNotFoundException("resource:/Shakespeare.txt");
        }
        return new BufferedReader(new InputStreamReader(istream));
    }

}
