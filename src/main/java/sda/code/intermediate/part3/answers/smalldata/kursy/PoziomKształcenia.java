package sda.code.intermediate.part3.answers.smalldata.kursy;

import lombok.Getter;

import java.util.NoSuchElementException;

public enum PoziomKształcenia {
    PIERWSZY_STOPIEŃ("Pierwszego stopnia"),
    DRUGI_STOPIEŃ("Drugiego stopnia"),
    JEDNOLITE_MAGISTERSKIE("Jednolite magisterskie");

    @Getter
    private final String nazwa;

    PoziomKształcenia(String nazwa) {
        this.nazwa = nazwa;
    }

    public static PoziomKształcenia dlaNazwy(String poziomKształcenia) {
        for (PoziomKształcenia poziom : PoziomKształcenia.values()) {
            if (poziom.nazwa.equals(poziomKształcenia)) {
                return poziom;
            }
        }
        throw new NoSuchElementException(poziomKształcenia);
    }
}
