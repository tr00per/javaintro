package sda.code.intermediate.part4.answers.goldretrofit;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

import static sda.code.intermediate.part4.answers.goldretrofit.Messages.PROMPT;
import static sda.code.intermediate.part4.answers.goldretrofit.Messages.fmt;

public class Main {

    private static final int DEAFULT_NUMBER_OF_DAYS = 90;

    public static void main(String[] args) {
        File summary = Paths.get(System.getProperty("user.home"), "data", "gold_summary.xlsx").toFile();
        GoldDigger digger = new GoldDigger("http://api.nbp.pl/api/", summary.getAbsolutePath());

        try (Scanner inp = new Scanner(System.in)) {
            readAndExec(inp, digger);
        }
    }

    private static void readAndExec(Scanner inp, GoldDigger digger) {
        System.out.print(fmt(PROMPT, DEAFULT_NUMBER_OF_DAYS));
        String userInput = inp.nextLine();
        if (userInput.isEmpty()) {
            digger.execute(DEAFULT_NUMBER_OF_DAYS);
        } else {
            int days = Integer.parseInt(userInput);
            digger.execute(days);
        }
    }

}
