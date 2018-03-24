package sda.code.intermediate.part3.answers.smalldata.hotele;

import java.util.NoSuchElementException;

public enum RodzajObiektu {
    NIEZNANY(""),
    HOTEL("hotel"),
    MOTEL("motel"),
    PENSJONAT("pensjonat"),
    KEMPING("kemping"),
    DOM_WYCIECZKOWY("dom wycieczkowy"),
    SCHRONISKO_MŁODZIEŻOWE("schronisko młodzieżowe"),
    POLE_BIWAKOWE("pole biwakowe"),
    SCHRONISKO("schronisko");

    private String nazwa;

    RodzajObiektu(String nazwa) {
        this.nazwa = nazwa;
    }

    public static RodzajObiektu dlaNazwy(String nazwa) {
        for (RodzajObiektu rodzaj : RodzajObiektu.values()) {
            if (rodzaj.nazwa.equals(nazwa)) {
                return rodzaj;
            }
        }
        throw new NoSuchElementException(nazwa);
    }
}
