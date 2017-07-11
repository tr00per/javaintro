package sda.code.intermediate.part1.exercises;

import sda.code.intermediate.part1.exercises.data.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Wszystskie implementacje operają się o wykorzystanie funkcji Lambda lub
 * Streamów. Ćwiczenia są niemal dokładnym przeniesieniem ćwiczeń funkcyjnych w
 * języku JavaScript.
 */
public class Functional {

    /**
     * Obliczyć kwadraty elementów wejściowej listy.
     */
    public List<Integer> squares(List<Integer> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Zwrócić tylko parzyste liczby.
     */
    public List<Integer> even(List<Integer> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Policzyć liczby nieparzyste.
     */
    public Long countOdd(List<Integer> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Zwrócić najmniejszą liczbę z listy.
     */
    public Integer smallest(List<Integer> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja szkieletowa, odpowiada za złożenie wszystkich klocków w jedną
     * całość.
     */
    private BigDecimal bruttoSum(Predicate<Product> policy, Function<BigDecimal, BigDecimal> tax,
                                 List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Ta metoda zwraca funkcję (predykat), która musi zdecydować, czy dla
     * danego elementu będzie trzeba zastosować obliczanie podatku.
     */
    private Predicate<Product> taxOnItems() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Metoda zwraca funkcję, która otrzymawszy wartość netto przedmiotu zwraca
     * wartość brutto zgodnie z zadanym podatkiem.
     */
    private Function<BigDecimal, BigDecimal> itemTax(BigDecimal tax) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Obudować model danych sklepowych w funkcje, które cenę jednostkową
     * produktu zamienią na cenę z VATem 42% i sumują ceny brutto listy
     * produktów (zasada: opodatkowane są tylko przedmioty, nie usługi).
     * <p>
     * 3 funkcje: szkieletowa ("bruttoSum"), sprawdzająca politykę
     * ("taxOnItems"), obliczająca cenę z podatkiem ("itemTax"). Funkcja
     * szkieletowa powinny być f-cją wyższego rzędu, sparametryzowana (i
     * częściowo zaaplikowana na) polityce i bruttowniku; wewnętrznie używa map
     * i reduce. Wynikowa funkcja ("cartBruttoSum") przyjmuje listę produktów i
     * zwraca sumę cen brutto.
     */
    public BigDecimal cartBruttoSum(List<Product> products) {
        return bruttoSum(taxOnItems(), itemTax(new BigDecimal("0.42")), products);
    }

}
