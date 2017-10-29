package sda.code.intermediate.part1extra.java4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Generyki {
    public static class StringPojemnik {
        final String wartość;

        public StringPojemnik(String wartość) {
            this.wartość = wartość;
        }

        public String weźWartość() {
            return wartość;
        }

        public boolean porównaj(StringPojemnik tenDrugi) {
            return Objects.equals(weźWartość(), tenDrugi.weźWartość());
        }
    }

    public static class IntegerPojemnik {
        final Integer wartość;

        public IntegerPojemnik(Integer wartość) {
            this.wartość = wartość;
        }

        public Integer weźWartość() {
            return wartość;
        }

        public boolean porównaj(IntegerPojemnik tenDrugi) {
            return Objects.equals(weźWartość(), tenDrugi.weźWartość());
        }
    }

    public static class Pojemnik<Coś> {
        final Coś wartość;

        public Pojemnik(Coś wartość) {
            this.wartość = wartość;
        }

        public Coś weźWartość() {
            return wartość;
        }

        public boolean porównaj(Pojemnik<Coś> tenDrugi) {
            return Objects.equals(weźWartość(), tenDrugi.weźWartość());
        }

        // a teraz z Pojemnika zrobimy funktor
        public <CośInego> Pojemnik<CośInego> map(Function<Coś, CośInego> func) {
            return new Pojemnik<>(func.apply(wartość));
        }
    }

    // może być więcej, niż jeden parametr typu do jednej klasy (lub metody)
    public static class PodwójnyPojemnik<Coś1, Coś2> {
        final Coś1 wartość1;
        final Coś2 wartość2;

        public PodwójnyPojemnik(Coś1 wartość1, Coś2 wartość2) {
            this.wartość1 = wartość1;
            this.wartość2 = wartość2;
        }

        public Coś1 weźWartość1() {
            return wartość1;
        }

        public Coś2 weźWartość2() {
            return wartość2;
        }

        // metody w klasie mogą deklarować swoje własne, dodatkowe parametry typu
        public <T> void dekorowane2(T dekorator) {
            System.out.println(dekorator);
            System.out.println(wartość2);
            System.out.println(dekorator);
        }

        public boolean porównajPierwszy(Pojemnik<Coś1> tenDrugi) {
            return Objects.equals(weźWartość1(), tenDrugi.weźWartość());
        }
    }

    public static void main(String args[]) {
        StringPojemnik p1 = new StringPojemnik("Artur");
        StringPojemnik p2 = new StringPojemnik("Zbigniew");
        StringPojemnik p3 = new StringPojemnik("Maciej");
        StringPojemnik p4 = new StringPojemnik("Marcin");
        StringPojemnik p5 = new StringPojemnik("Marcin");

        System.out.println(p2.weźWartość());
        System.out.println(p4.weźWartość());
        System.out.println("Równe: " + p1.porównaj(p3));
        System.out.println("Równe: " + p4.porównaj(p5));

        IntegerPojemnik l1 = new IntegerPojemnik(1);
        IntegerPojemnik l2 = new IntegerPojemnik(2);
        System.out.println("Liczby równe: " + l1.porównaj(l2));

        Pojemnik<String> p6 = new Pojemnik<>("Baltazar");
        Pojemnik<String> p7 = new Pojemnik<>("Baltazar");
        Pojemnik<Integer> l3 = new Pojemnik<>(3);
        Pojemnik<Integer> l4 = new Pojemnik<>(4);
        // nie mogę, bo są różne typy trzymane wewnątrz pojemników
        //        p6.porównaj(l3);

        System.out.println("-----------------------------");
        System.out.println("Równe: " + p6.porównaj(p7));
        System.out.println("Liczby równe: " + l3.porównaj(l4));

        wyświetl(p7);
        Integer int3 = wyświetlIWyłuskaj(l3);
        Integer int4 = wyświetlIWyłuskaj(l4);

        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        System.out.println("Znalezione? " + lista.contains("ABC"));

        Pojemnik<Interfejsy.ZwykłyWitacz> witacz = new Pojemnik<>(new Interfejsy.ZwykłyWitacz());
        wyświetlIPrzywitaj(witacz);
    }

    // Pojemnik<?> - mamy generyczny pojemnik, ale nie interesuje nas, co jest w nim zawarte
    public static void wyświetl(Pojemnik<?> pojemnik) {
        System.out.println("Wartość: " + pojemnik.weźWartość());
    }

    // <T> - deklaracja typu generycznego
    // T wyświetlIWyłuskaj - metoda wyświetlIWyłuskaj zwraca coś typu T
    // wyświetlIWyłuskaj(Pojemnik<T> ...) - wyświetlIWyłuskaj ma parametr
    // z generycznym typem
    public static <T> T wyświetlIWyłuskaj(Pojemnik<T> pojemnik) {
        System.out.println("Wartość: " + pojemnik.weźWartość());
        return pojemnik.weźWartość();
    }

    // <T extends Interfejsy.Witacz> - metody Witacza będą dostępna na
    // obiektach typu T
    public static <T extends Interfejsy.Witacz> void wyświetlIPrzywitaj(Pojemnik<T> pojemnik) {
        System.out.println("Wartość: " + pojemnik.weźWartość());
        pojemnik.weźWartość().witaj("Alojzy");
    }
}