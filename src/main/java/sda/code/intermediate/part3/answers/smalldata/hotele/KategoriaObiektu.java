package sda.code.intermediate.part3.answers.smalldata.hotele;

import java.util.NoSuchElementException;

public enum KategoriaObiektu {
    HOTEL_MEGA("*****"),
    HOTEL_SUPER("****"),
    HOTEL_ZWYKŁY("***"),
    HOTEL_KIEPSKI("**"),
    HOTEL_NĘDZNY("*"),
    NOCLEGOWNIA_MEGA("III"),
    NOCLEGOWNIA_ZWYKŁA("II"),
    NOCLEGOWNIA_NĘDZNA("I"),
    NIEZNANA("");

    private String nazwa;

    KategoriaObiektu(String nazwa) {
        this.nazwa = nazwa;
    }

    public static KategoriaObiektu dlaNazwy(String nazwa) {
        for (KategoriaObiektu kategoria : KategoriaObiektu.values()) {
            if (kategoria.nazwa.equals(nazwa)) {
                return kategoria;
            }
        }
        throw new NoSuchElementException(nazwa);
    }
}
