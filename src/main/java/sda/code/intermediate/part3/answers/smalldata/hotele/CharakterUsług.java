package sda.code.intermediate.part3.answers.smalldata.hotele;

import java.util.NoSuchElementException;

public enum CharakterUsług {
    STAŁY("stały"),
    SEZONOWY("sezonowy");

    private String nazwa;

    CharakterUsług(String nazwa) {
        this.nazwa = nazwa;
    }

    public static CharakterUsług dlaNazwy(String nazwa) {
        for (CharakterUsług charakter : CharakterUsług.values()) {
            if (charakter.nazwa.equals(nazwa)) {
                return charakter;
            }
        }
        throw new NoSuchElementException(nazwa);
    }
}
