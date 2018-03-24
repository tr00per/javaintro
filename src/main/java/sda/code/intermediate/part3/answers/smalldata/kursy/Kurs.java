package sda.code.intermediate.part3.answers.smalldata.kursy;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class Kurs {
    private final String nazwaKierunku;
    private final PoziomKształcenia poziomKształcenia;
    private final String profilKształcenia;
    private final String tytułZawodowy;
    private final FormaKształcenia formaKształcenia;
    private final String dyscyplina;
    private final String instytucja;

    public Kurs(String nazwaKierunku, String poziomKształcenia, String profilKształcenia, String tytułZawodowy, String formaKształcenia, String dyscyplina, String instytucja) {
        this.nazwaKierunku = nazwaKierunku;
        this.poziomKształcenia = PoziomKształcenia.dlaNazwy(poziomKształcenia);
        this.profilKształcenia = profilKształcenia;
        this.tytułZawodowy = tytułZawodowy;
        this.formaKształcenia = FormaKształcenia.dlaNazwy(formaKształcenia);
        this.dyscyplina = dyscyplina;
        this.instytucja = instytucja;
    }

    public static Kurs fromCsvRecord(CSVRecord wiersz) {
        return new Kurs(
                wiersz.get("Nazwa kierunku"),
                wiersz.get("Poziom kształcenia"),
                wiersz.get("Profil kształcenia"),
                wiersz.get("Tytuł zawodowy"),
                wiersz.get("Forma kształcenia"),
                wiersz.get("Dyscyplina (Obszar/Dziedzina)"),
                wiersz.get("Instytucja/jednostka")
        );
    }
}
