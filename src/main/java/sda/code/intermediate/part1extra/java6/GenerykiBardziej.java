package sda.code.intermediate.part1extra.java6;

import java.util.ArrayList;
import java.util.List;

public class GenerykiBardziej {

    interface Konsumer<T> {
        void przyjmij(T t);
    }

    static class Maybe<T> {
        private final T zawartość;


        public Maybe(T zawartość) {
            this.zawartość = zawartość;
        }

        public T dajLiczbę() {
            return zawartość;
        }

        public void wykonaj(Konsumer<T> operacja) {
            if (zawartość != null) {
                operacja.przyjmij(zawartość);
            }
        }
    }

    static class LiczbaCałkowita implements Dodawalne<LiczbaCałkowita> {
        public final int x;

        public LiczbaCałkowita(int x) {
            this.x = x;
        }

        public void pokaż() {
            System.out.println(x);
        }

        @Override
        public LiczbaCałkowita wyciągnij() {
            return this;
        }

        @Override
        public LiczbaCałkowita dodaj(Dodawalne<LiczbaCałkowita> x) {
            return new LiczbaCałkowita(this.x + x.wyciągnij().x);
        }

    }

    interface Dodawalne<T> {
        T wyciągnij();

        T dodaj(Dodawalne<T> x);
    }

    public static void maybeDodaj(Maybe<LiczbaCałkowita> x, Maybe<LiczbaCałkowita> y) {
        // umawiamy się, że sprawdziłem, że y ma coś w środku ;)
        x.wykonaj(coś -> System.out.println(coś.dodaj(y.dajLiczbę()).wyciągnij()));
    }

//    public static <T> void maybeDodajCośNiewiadomego(Maybe<T> x, Maybe<T> y) {
//        // umawiamy się, że sprawdziłem, że y ma coś w środku ;)
//        x.wykonaj(coś -> coś.dodaj(y.dajLiczbę()).pokaż());
//    }

    public static <T extends Dodawalne<T>> void maybeDodajCośDodawalnego(Maybe<T> x, Maybe<T> y) {
        // umawiamy się, że sprawdziłem, że y ma coś w środku ;)
        x.wykonaj(coś -> System.out.println(coś.dodaj(y.dajLiczbę()).wyciągnij()));
    }

    public static void main(String[] args) {
        LiczbaCałkowita x = new LiczbaCałkowita(2);
        LiczbaCałkowita y = new LiczbaCałkowita(2);

        LiczbaCałkowita z = x.dodaj(y);
        z.pokaż();

        Maybe<LiczbaCałkowita> a = new Maybe<>(new LiczbaCałkowita(2));
        Maybe<LiczbaCałkowita> b = new Maybe<>(new LiczbaCałkowita(3));
        a.dajLiczbę().dodaj(b.dajLiczbę()).pokaż();

        maybeDodaj(a, b);
        maybeDodajCośDodawalnego(a, b);

        List<B> listaB = new ArrayList<B>();
        ArrayList<? extends B> arrayListaBaleC = new ArrayList<C>();
        List<? extends B> listaBaleC = new ArrayList<C>();
        List<? super B> listaBaleA = new ArrayList<A>();
    }

    static class A {}
    static class B extends A {}
    static class C extends B {}
}
