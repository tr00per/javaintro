package sda.code.intermediate.part3.answers.smalldata;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import sda.code.intermediate.part3.answers.smalldata.hotele.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class SmallDataHotele {

    public static final Path KATALOG_Z_DANYMI = Paths.get(System.getProperty("user.home"), "data");
    public static final Województwo NIEZNANE_WOJEWÓDZTWO = new Województwo("POGRANICZE RZECZYWISTOŚCI");

    public static void main(String[] args) throws IOException {
        List<Przybytek> przybytki = new LinkedList<>();

        Path ścieżkaWejściowa = KATALOG_Z_DANYMI.resolve(Paths.get("hotele.csv"));
        try (CSVParser czytacz = CSVFormat.RFC4180.withDelimiter(';').withFirstRecordAsHeader().parse(Files.newBufferedReader(ścieżkaWejściowa))) {
            for (CSVRecord wpis : czytacz) {
                Przybytek przybytek = Przybytek.fromCsvRecord(wpis);
                przybytki.add(przybytek);
            }
        }

        System.out.println("Rekordów: " + przybytki.size());

        System.out.println("Ile jest obiektów z każdego rodzaju?");
        Map<RodzajObiektu, Integer> rodzajeObiektów = new HashMap<>();
        for (Przybytek przybytek : przybytki) {
            int ilość = rodzajeObiektów.getOrDefault(przybytek.getRodzajObiektu(), 0);
            ilość += 1;
            rodzajeObiektów.put(przybytek.getRodzajObiektu(), ilość);
        }
        System.out.println(rodzajeObiektów);

        Path ścieżkaWyjściowa1 = KATALOG_Z_DANYMI.resolve(Paths.get("hotele_summary1.csv"));
        try (CSVPrinter pisacz = CSVFormat.RFC4180.withHeader("Rodzaj", "Ilość").print(Files.newBufferedWriter(ścieżkaWyjściowa1))) {
            for (Entry<RodzajObiektu, Integer> elem : rodzajeObiektów.entrySet()) {
                pisacz.printRecord(elem.getKey(), elem.getValue());
            }
        }

        System.out.println("Ile jest obiektów z każdego rodzaju? Podziel wyniki na charakter stały i sezonowy.");
        Map<CharakterUsług, Map<RodzajObiektu, Integer>> charakteryIRodzaje = new HashMap<>();
        for (Przybytek przybytek : przybytki) {
            Map<RodzajObiektu, Integer> rodzaje = charakteryIRodzaje.getOrDefault(przybytek.getCharakterUsług(), new HashMap<>());

            int ilość = rodzaje.getOrDefault(przybytek.getRodzajObiektu(), 0);
            ilość += 1;
            rodzaje.put(przybytek.getRodzajObiektu(), ilość);

            charakteryIRodzaje.put(przybytek.getCharakterUsług(), rodzaje);
        }
        System.out.println(charakteryIRodzaje);

        Path sciezkaWyjściowa2 = KATALOG_Z_DANYMI.resolve(Paths.get("hotele_summary2.csv"));
        try (CSVPrinter pisacz = CSVFormat.RFC4180.withHeader("Charakter usług", "Rodzaj obiektu", "Ilość").print(Files.newBufferedWriter(sciezkaWyjściowa2))) {
            for (Entry<CharakterUsług, Map<RodzajObiektu, Integer>> elemZewn : charakteryIRodzaje.entrySet()) {
                for (Entry<RodzajObiektu, Integer> elemWewn : elemZewn.getValue().entrySet()) {
                    pisacz.printRecord(elemZewn.getKey(), elemWewn.getKey(), elemWewn.getValue());
                }
            }
        }

        System.out.println("Ile jest obiektów z każdej kategorii? Podziel wyniki ze względu na województwa?");
        zPodziałemNaWojewództwa(przybytki);
    }

    private static void zPodziałemNaWojewództwa(List<Przybytek> przybytki) throws IOException {
        Map<KodPocztowy, Województwo> kody = wczytajKody();

        Map<Województwo, Map<KategoriaObiektu, Integer>> województwaIKategorie = new HashMap<>();
        for (Przybytek przybytek : przybytki) {
            Województwo woj = kody.getOrDefault(przybytek.getKodPocztowy(), NIEZNANE_WOJEWÓDZTWO);

            Map<KategoriaObiektu, Integer> kategorie = województwaIKategorie.getOrDefault(woj, new HashMap<>());

            int ilość = kategorie.getOrDefault(przybytek.getKategoriaObiektu(), 0);
            ilość += 1;
            kategorie.put(przybytek.getKategoriaObiektu(), ilość);

            województwaIKategorie.put(woj, kategorie);
        }
        System.out.println(województwaIKategorie);

        Path sciezkaWyjściowa3 = KATALOG_Z_DANYMI.resolve(Paths.get("hotele_summary3.csv"));
        try (CSVPrinter pisacz = CSVFormat.RFC4180.withHeader("Województwo", "Kategoria obiektu", "Ilość").print(Files.newBufferedWriter(sciezkaWyjściowa3))) {
            for (Entry<Województwo, Map<KategoriaObiektu, Integer>> elemZewn : województwaIKategorie.entrySet()) {
                for (Entry<KategoriaObiektu, Integer> elemWewn : elemZewn.getValue().entrySet()) {
                    pisacz.printRecord(elemZewn.getKey().getNazwa(), elemWewn.getKey(), elemWewn.getValue());
                }
            }
        }
    }

    private static Map<KodPocztowy, Województwo> wczytajKody() throws IOException {
        Map<KodPocztowy, Województwo> kody = new TreeMap<>();

        Path ścieżkaKodów = KATALOG_Z_DANYMI.resolve(Paths.get("kody.txt"));
        try (BufferedReader czytacz = Files.newBufferedReader(ścieżkaKodów)) {
            String linia;
            while ((linia = czytacz.readLine()) != null) {
                String[] para = linia.split(" ");
                if (para.length != 2) {
                    throw new IllegalStateException("Linia nie zawiera pojedynczej spacji: " + linia);
                }
                KodPocztowy kod = new KodPocztowy(para[0]);
                Województwo woj = new Województwo(para[1]);

                kody.put(kod, woj);
            }
        }

        System.out.println("Wczytano " + kody.size() + " kodów pocztowych");
        return kody;
    }
}
