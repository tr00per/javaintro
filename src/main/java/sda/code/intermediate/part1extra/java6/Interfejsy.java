package sda.code.intermediate.part1extra.java6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Interfejsy {
    interface Witacz {
        void witaj(String imię);

        default String dekoruj(String imię) {
            return ":: " + imię + " ::";
        }
    }

    interface Żegnacz {
        void żegnaj(String imię);
    }

    static class ZwykłyWitacz implements Witacz {
        @Override
        public void witaj(String imię) {
            System.out.println("Witaj, " + dekoruj(imię));
        }
    }

    static class DziwnyWitacz extends ZwykłyWitacz {
        @Override
        public String dekoruj(String imię) {
            return "!@# " + imię + " $%^$";
        }
    }

    static class KrzykliwyWitacz implements Witacz {
        @Override
        public void witaj(String imię) {
            System.out.println("CZEŚĆ, " + imię.toUpperCase() + "!");
        }
    }

    static class ZwykłyŻegnacz implements Żegnacz {
        @Override
        public void żegnaj(String imię) {
            System.out.println("Żegnaj, " + imię);
        }
    }

    static abstract class WitaczoŻegnacz implements Witacz, Żegnacz {
    }

    static class HawajskiWitaczoŻegnacz extends WitaczoŻegnacz {
        @Override
        public void witaj(String imię) {
            System.out.println("Aloha, " + imię + "!");
        }

        @Override
        public void żegnaj(String imię) {
            System.out.println("Aloha, " + imię);
        }
    }

    static class Impreza {
        private final Witacz witacz;
        private final Żegnacz żegnacz;
        private String[] goście = {"Baltazar", "Klaudia", "Zofia", "Tomek"};

        public Impreza(Witacz witacz, Żegnacz żegnacz) {
            this.witacz = witacz;
            this.żegnacz = żegnacz;
        }

        public Impreza(WitaczoŻegnacz witaczoŻegnacz) {
            this.witacz = witaczoŻegnacz;
            this.żegnacz = witaczoŻegnacz;
        }

        public void start() {
            for (String g : goście) {
                witacz.witaj(g);
            }
        }

        public void stop() {
            for (String g : goście) {
                żegnacz.żegnaj(g);
            }
        }
    }



    public static void main(String[] args) {
        Witacz zwykły = new ZwykłyWitacz();
        Witacz zwykły2 = new ZwykłyWitacz();
        zwykły.witaj("Baltazar");
        zwykły2.witaj("Klauda");

        Witacz krzykliwy = new KrzykliwyWitacz();
        krzykliwy.witaj("Zofia");

        Impreza biba = new Impreza(new ZwykłyWitacz(), new ZwykłyŻegnacz());
        biba.start();

        Żegnacz żegnacz = new ZwykłyŻegnacz();
        żegnacz.żegnaj("Eryk");

        biba.stop();

        Impreza wyspiarska = new Impreza(new HawajskiWitaczoŻegnacz());
        wyspiarska.start();
        wyspiarska.stop();

        Witacz dziwny = new DziwnyWitacz();
        dziwny.witaj("Mścigniew");

        Witacz anonimowy = new Witacz() {
            @Override
            public void witaj(String imię) {
                System.err.println("Bój się, " + imię);
            }
        };
        anonimowy.witaj("Internecie");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Jestem w wątku!");
            }
        }).start();

        Iterable<Integer> itererable = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }
        };
    }
}
