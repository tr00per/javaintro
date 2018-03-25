package sda.code.intermediate.part3.answers.smalldata;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import sda.code.intermediate.part3.answers.smalldata.kursy.FormaKształcenia;
import sda.code.intermediate.part3.answers.smalldata.kursy.Kurs;
import sda.code.intermediate.part3.answers.smalldata.kursy.PoziomKształcenia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class SmallDataStudia {
    public static void main(String[] args) throws IOException {
        List<Kurs> kursy = new LinkedList<>();

        // wczytanie kursów
        Path sciezkaWejściowa = Paths.get(System.getProperty("user.home"), "data", "informatyka.csv");
        try (CSVParser czytacz = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(Files.newBufferedReader(sciezkaWejściowa))) {
            for (CSVRecord wiersz : czytacz) {
                Kurs kurs = Kurs.fromCsvRecord(wiersz);
//                Kurs kurs = new Kurs(
//                        wiersz.get("Nazwa kierunku"),
//                        wiersz.get("Poziom kształcenia"),
//                        wiersz.get("Profil kształcenia"),
//                        wiersz.get("Tytuł zawodowy"),
//                        wiersz.get("Forma kształcenia"),
//                        wiersz.get("Dyscyplina (Obszar/Dziedzina)"),
//                        wiersz.get("Instytucja/jednostka")
//                );
                kursy.add(kurs);
            }
        }

        System.out.println("Rekordów: " + kursy.size());

        // wyciąganie statystyk
        System.out.println("Ile jest kierunków o nazwie “Informatyka”?");
        int licznikInformatyk = 0;
        for (Kurs kurs : kursy) {
            if (kurs.getNazwaKierunku().equalsIgnoreCase("informatyka")) {
                licznikInformatyk += 1;
            }
        }
        System.out.println(licznikInformatyk);

        System.out.println("Ile wydziałów oferuje studia informatyczna na dowolnm stopniu?");
        Set<String> wydziały = new HashSet<>();
        for (Kurs kurs : kursy) {
            wydziały.add(kurs.getInstytucja());
        }
        System.out.println(wydziały.size());

        System.out.println("Ile uczelni oferuje studia drugiego stopnia?");
        Set<String> drugiStopień = new HashSet<>();
        for (Kurs kurs : kursy) {
            if (kurs.getPoziomKształcenia().equals(PoziomKształcenia.DRUGI_STOPIEŃ)) {
                drugiStopień.add(kurs.getInstytucja().split(";")[0]);
            }
        }
        System.out.println(drugiStopień.size());

        // zapis wyniku
        Path sciezkaWyjściowa1 = Paths.get(System.getProperty("user.home"), "data", "informatyka_summary1.csv");
        try (CSVPrinter pisacz = CSVFormat.RFC4180.withHeader("Pytanie", "Wynik").print(Files.newBufferedWriter(sciezkaWyjściowa1))) {
            pisacz.printRecord("Ile jest kierunków o nazwie “Informatyka”?", licznikInformatyk);
            pisacz.printRecord("Ile wydziałów oferuje studia informatyczna na dowolnm stopniu?", wydziały.size());
            pisacz.printRecord("Ile uczelni oferuje studia drugiego stopnia?", drugiStopień.size());
        }

        System.out.println("Ile jest kierunków pierwszego lub drugiego stopnia? Podziel wyniki ze względu na formę kształcenia.");
        Map<FormaKształcenia, Integer> formy = new HashMap<>();
        for (Kurs kurs : kursy) {
            if (kurs.getPoziomKształcenia().equals(PoziomKształcenia.PIERWSZY_STOPIEŃ) ||
                    kurs.getPoziomKształcenia().equals(PoziomKształcenia.DRUGI_STOPIEŃ)) {
                int ilość = formy.getOrDefault(kurs.getFormaKształcenia(), 0);
                ilość += 1;
                formy.put(kurs.getFormaKształcenia(), ilość);
            }
        }
        System.out.println(formy);

        Path sciezkaWyjściowa2 = Paths.get(System.getProperty("user.home"), "data", "informatyka_summary2.csv");
        try (CSVPrinter pisacz = CSVFormat.RFC4180.withHeader("Forma", "Ilość").print(Files.newBufferedWriter(sciezkaWyjściowa2))) {
            for (Entry<FormaKształcenia, Integer> elem : formy.entrySet()) {
                pisacz.printRecord(elem.getKey(), elem.getValue());
            }
        }

        Map<FormaKształcenia, Map<PoziomKształcenia, Integer>> formyIPoziomy = new HashMap<>();
        for (Kurs kurs : kursy) {
            if (kurs.getPoziomKształcenia().equals(PoziomKształcenia.PIERWSZY_STOPIEŃ) ||
                    kurs.getPoziomKształcenia().equals(PoziomKształcenia.DRUGI_STOPIEŃ)) {
                Map<PoziomKształcenia, Integer> poziomy =
                        formyIPoziomy.getOrDefault(kurs.getFormaKształcenia(), new HashMap<>());

                int ilość = poziomy.getOrDefault(kurs.getPoziomKształcenia(), 0);
                ilość += 1;
                poziomy.put(kurs.getPoziomKształcenia(), ilość);

                formyIPoziomy.put(kurs.getFormaKształcenia(), poziomy);
            }
        }
        // wersja bez getOrDefault
//        for (Kurs kurs : kursy) {
//            if (kurs.getPoziomKształcenia().equals(PoziomKształcenia.PIERWSZY_STOPIEŃ) ||
//                    kurs.getPoziomKształcenia().equals(PoziomKształcenia.DRUGI_STOPIEŃ)) {
//
//                if (formyIPoziomy.containsKey(kurs.getFormaKształcenia())) {
//                    Map<PoziomKształcenia, Integer> poziomy = formyIPoziomy.get(kurs.getFormaKształcenia());
//
//                    if (poziomy.containsKey(kurs.getPoziomKształcenia())) {
//                        int ilość = poziomy.get(kurs.getPoziomKształcenia());
//                        ilość += 1;
//                        poziomy.put(kurs.getPoziomKształcenia(), ilość);
//                    }
//                    else {
//                        poziomy.put(kurs.getPoziomKształcenia(), 1);
//                    }
//                }
//                else {
//                    Map<PoziomKształcenia, Integer> poziomy = new HashMap<>();
//                    poziomy.put(kurs.getPoziomKształcenia(), 1);
//                    formyIPoziomy.put(kurs.getFormaKształcenia(), poziomy);
//                }
//
//            }
//        }
        System.out.println(formyIPoziomy);

        Path sciezkaWyjściowa3 = Paths.get(System.getProperty("user.home"), "data", "informatyka_summary3.csv");
        try (CSVPrinter pisacz = CSVFormat.RFC4180.withHeader("Forma", "Poziom", "Ilość").print(Files.newBufferedWriter(sciezkaWyjściowa3))) {
            for (Entry<FormaKształcenia, Map<PoziomKształcenia, Integer>> elemZewn : formyIPoziomy.entrySet()) {
                for (Entry<PoziomKształcenia, Integer> elemWewn : elemZewn.getValue().entrySet()) {
                    pisacz.printRecord(elemZewn.getKey(), elemWewn.getKey(), elemWewn.getValue());
                }
            }
        }
    }
}
