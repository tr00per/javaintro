package sda.code.intermediate.part1extra.java4;

public class Interfejsy {
    public static final int STAŁA = 123;

    public interface Witacz {
        public static final int STAŁA = 456;

        void witaj(String imię);
    }

    public interface Żegnacz {
        void żegnaj(String imię);

        default void blah() {
            System.err.println("Blah!");
        }
    }

    public static class ZwykłyWitacz implements Witacz {
        public void witaj(String imię) {
            System.out.println("Witaj, " + imię);
        }

        // ta metoda nie jest widoczna, kiedy używamy klasy
// a pomocą interfejsu
        public void hello() {
            System.out.println("!!! Jestem spoza interfejsu!");
        }
    }

    public static class DekoracyjnyWitacz implements Witacz {
        public void witaj(String imię) {
            System.out.println(".::[ Witaj, " + imię + " ]::.");
        }
    }

    public static class ZwykłyŻegnacz implements Żegnacz {
        public void żegnaj(String imię) {
            System.out.println("Żegnaj, " + imię);
        }
    }

    // klasa abstrakcyjna (bo nie posiada wszystkich wymaganych definicji)
    // zbiera nam kilka interfejsów w jeden byt
    //
    // klasa abstrakcyjna mogłaby dostarczyć domyślne implementacje
    // niektórych albo wszystkich metod, te metody mogłyby zostać nadpisane
    // w klasach pochodnych, ale wciaż, ponieważ WitaczoŻegnacz jest oznaczony
    // słowe "abstract", to nie możemy stworzyć jego instancji/obiektu
    public static abstract class WitaczoŻegnacz implements Witacz, Żegnacz {
    }

    //    public static class ZwykłyWitaczoŻegnacz implements Witacz, Żegnacz {
    public static class ZwykłyWitaczoŻegnacz extends WitaczoŻegnacz {
        public void witaj(String imię) {
            System.out.println("Witaj, " + imię + "!");
        }

        public void żegnaj(String imię) {
            System.out.println("Żegnaj, " + imię + "!");
        }
    }

    //    public static class RozentuzjazmowanyWitaczoŻegnacz implements Witacz, Żegnacz {
    public static class RozentuzjazmowanyWitaczoŻegnacz extends WitaczoŻegnacz {
        public void witaj(String imię) {
            System.out.println("Witaj, " + imię + ", jak dobrze cię widzieć!");
        }

        public void żegnaj(String imię) {
            System.out.println("Do zobaczenia wkrótce, " + imię + "!");
        }
    }

    public static void main(String args[]) {
        // odwołanie do stałej z bieżącej klasy
        System.err.println(STAŁA);
        // odołanie do stałej z interfejsu Witacz
        System.err.println(Witacz.STAŁA);

        // nowa instancja ZwykłegoWitacza, zapisana w zmiennej w1,
        // która patrzy na niego jak na dowolnego Witacza
        Witacz w1 = new ZwykłyWitacz();
        // nowa instancja ZwykłegoWitacza, zapisana w zmiennej zw1,
        // która rozumie tylko ZwykłeWitacze
        ZwykłyWitacz zw1 = new ZwykłyWitacz();

        w1.witaj("Baltazarze");
        zw1.witaj("Gniewomirze");
        zw1.hello();

        // mogę podmienić wartość w1 na inny Witacz
        w1 = new DekoracyjnyWitacz();
        w1.witaj("Baltazarze");
        // ale nie mogę tego zrobić ze zmienną zw1
//        zw1 = new DekoracyjnyWitacz();


        // metoda, która akceptuje Witacze może przyjąć dowolną implementację
        // interfejsu jako argument
        przywitajUżytkownika(new ZwykłyWitacz());
        przywitajUżytkownika(w1); // new DekoracyjnyWitacz()
        // analogicznie, metoda akceptująca Żegnacze może przyjąć
        // dowolną implementację Żegnacza
        pożegnajUżytkownika(new ZwykłyŻegnacz());

        // instancja klasy, która implementuje więcej niż jeden interfejs
        // może być użyta w miejscu, które akceptuje dowolny z nich.
        // wewnątrz takiej metody mamy dostęp wciąż tylko do operacji
        // zadeklarowanych na tym jednym interfejsie
        przywitajUżytkownika(new ZwykłyWitaczoŻegnacz());
        pożegnajUżytkownika(new ZwykłyWitaczoŻegnacz());

//        spotkajUżytkownika(new DekoracyjnyWitacz(), new ZwykłyŻegnacz());
//        spotkajUżytkownika(new ZwykłyWitaczoŻegnacz(), new ZwykłyWitaczoŻegnacz());
        spotkajUżytkownika(new ZwykłyWitaczoŻegnacz());
        RozentuzjazmowanyWitaczoŻegnacz rozWitŻeg = new RozentuzjazmowanyWitaczoŻegnacz();
//        spotkajUżytkownika(rozWitŻeg, rozWitŻeg);
        spotkajUżytkownika(new RozentuzjazmowanyWitaczoŻegnacz());

        // anonimowe instancje
        // -> instancja anonimowej implementacji klasy, która implementuje zadany interfejs
        Żegnacz anonim = new Żegnacz() {
            public void żegnaj(String imię) {
                System.out.println("Anonimowo żegnam, " + imię);
            }
        };
        anonim.żegnaj("Filemonie");
        pożegnajUżytkownika(anonim);
        pożegnajUżytkownika(new Żegnacz() {
            public void żegnaj(String imię) {
                System.out.println("Pa, " + imię);
            }
        });
    }


    // nie robić!
    public static void bliskoZwiązanyPrzywitajUżytkownika() {
        System.out.println("-----------------------");
        new ZwykłyWitacz().witaj("Hermenegildo");
        System.out.println("-----------------------");
    }

    public static void przywitajUżytkownika(Witacz witacz) {
        System.out.println("-----------------------");
        witacz.witaj("Hermenegildo");
        System.out.println("-----------------------");
    }

    public static void pożegnajUżytkownika(Żegnacz żegnacz) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
        żegnacz.żegnaj("Hermenegildo");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
        żegnacz.blah();
    }

    //    public static void spotkajUżytkownika(Witacz witacz, Żegnacz żegnacz) {
    public static void spotkajUżytkownika(WitaczoŻegnacz witaczoŻegnacz) {
        System.out.println("+++++++++++++++++++++++");
        witaczoŻegnacz.witaj("Bonifacy");
        System.out.println("+++++++++++++++++++++++");
        witaczoŻegnacz.żegnaj("Bonifacy");
        System.out.println("+++++++++++++++++++++++");
    }
}
