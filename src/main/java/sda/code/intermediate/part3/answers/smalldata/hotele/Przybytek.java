package sda.code.intermediate.part3.answers.smalldata.hotele;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class Przybytek {
    private final String nazwaWłasna;
    private final String telefon;
    private final String email;
    private final CharakterUsług charakterUsług;
    private final KategoriaObiektu kategoriaObiektu;
    private final RodzajObiektu rodzajObiektu;
    private final String adres;
    private final KodPocztowy kodPocztowy;

    public static Przybytek fromCsvRecord(CSVRecord wpis) {
        return new Przybytek(
                wpis.get("Nazwa własna"),
                wpis.get("Telefon"),
                wpis.get("Email"),
                CharakterUsług.dlaNazwy(wpis.get("Charakter usług")),
                KategoriaObiektu.dlaNazwy(wpis.get("Kategoria obiektu")),
                RodzajObiektu.dlaNazwy(wpis.get("Rodzaj obiektu")),
                wpis.get("Adres"),
                new KodPocztowy(wpis.get("Adres").split(" ")[0])
        );
    }
}
