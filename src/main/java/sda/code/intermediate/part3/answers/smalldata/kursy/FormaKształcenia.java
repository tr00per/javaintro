package sda.code.intermediate.part3.answers.smalldata.kursy;

import java.util.NoSuchElementException;

public enum FormaKształcenia {
    STACJONARNE("Stacjonarne"),
    NIESTACJONARNE("Niestacjonarne"),
    OBA("Niestacjonarne, Stacjonarne");

    private final String nazwa;

    FormaKształcenia(String nazwa) {
        this.nazwa = nazwa;
    }

    public static FormaKształcenia dlaNazwy(String formaKształcenia) {
        for (FormaKształcenia forma : FormaKształcenia.values()) {
            if (forma.nazwa.equals(formaKształcenia)) {
                return forma;
            }
        }
        throw new NoSuchElementException(formaKształcenia);
    }
    }
