package sda.code.intermediate.part1extra.java3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Generyki {

    public static <T> void drukuj(T coś) {
        System.out.println(coś);
    }

    public static <T, R> R drukuj(T coś, R cośInnego) {
        System.out.println(coś.equals(cośInnego));
        return cośInnego;
    }

    public static class Pojemnik<T> {
        private T schowek;

        public Pojemnik(T coś) {
            this.schowek = coś;
        }

        public T dajZawartość() {
            return schowek;
        }

        public <V> boolean porównaj(V toDrugie) {
            return Objects.equals(schowek, toDrugie);
        }
    }

    public static void main(String[] args) {
        Pojemnik<Integer> p1 = new Pojemnik<>(2);
        Pojemnik<String> p2 = new Pojemnik<>("Marchewka");

        drukuj("ABC");
        drukuj("1", "2");
        drukuj("1", 1);

        List<Double> liczby = new ArrayList<>();
        liczby.add(1.0);
        liczby.add(2.5);
        liczby.add(3.0);

        // no problem! działa "type erasure"
        List a = new ArrayList<Double>();

        List<Integer> liczbyCałkowite = new ArrayList<>();
        liczbyCałkowite.add(4);
        liczbyCałkowite.add(5);
        liczbyCałkowite.add(6);

        double suma = sumaDouble(liczby);
        System.out.println(suma);
        int sumaLiczbCałkowitych = sumaInteger(liczbyCałkowite);
        System.out.println(sumaLiczbCałkowitych);


        Integer generycznaSuma = suma(
                liczbyCałkowite,
                0,
                new Sumator<Integer>() {
                    @Override
                    public Integer połącz(Integer suma, Integer element) {
                        return suma + element;
                    }
                }
        );
        System.err.println(generycznaSuma);

        Double generycznaSuma2 = suma(
                liczby,
                0.0,
                new Sumator<Double>() {
                    @Override
                    public Double połącz(Double suma, Double element) {
                        return suma + element;
                    }
                }
        );
        System.err.println(generycznaSuma2);
    }

    public interface Sumator<T> {
        T połącz(T suma, T element);
    }

    private static <T> T suma(List<T> lista, T wartośćPoczątkowa, Sumator<T> sumator) {
        T suma = wartośćPoczątkowa;
        for (T element : lista) {
            suma = sumator.połącz(suma, element);
        }
        return suma;
    }

    private static int sumaInteger(List<Integer> liczby) {
        Integer suma = 0;
        for (Integer element : liczby) {
            suma += element;
        }
        return suma;
    }

    private static double sumaDouble(List<Double> liczby) {
        Double suma = 0.0;
        for (Double element : liczby) {
            suma += element;
        }
        return suma;
    }


}
