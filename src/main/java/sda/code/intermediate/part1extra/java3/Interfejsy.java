package sda.code.intermediate.part1extra.java3;

import org.apache.poi.util.NotImplemented;

public class Interfejsy {
    public interface Witacz {
        default void przywitaj(String imie) {
            System.out.println(imie + "? Co tu robisz?");
        }
    }

    public interface Żegnacz {
        void pożegnaj(String imie);
    }

    public static abstract class WitaczoŻegnacz implements Witacz, Żegnacz {
    }

    public static class ZwykłyWitacz extends WitaczoŻegnacz {
        @Override
        public void przywitaj(String imie) {
            System.out.println("Witaj, " + imie);
        }

        @Override
        public void pożegnaj(String imie) {
            System.out.println("Żegnaj, " + imie);
        }
    }

    public static class RozentuzjazmowanyWitacz implements Witacz {
        @Override
        public void przywitaj(String imie) {
            System.out.println("Witaj, " + imie + "!");
        }
    }

    public static abstract class DekoracyjnyWitacz implements Witacz {
        protected String dekoruj(String wejscie) {
            return "[" + wejscie + "]";
        }
    }

    public static class ZwykłyDekoracyjnyWitacz extends DekoracyjnyWitacz {
        @Override
        public void przywitaj(String imie) {
            System.err.println(dekoruj("Cześć, " + imie));
        }
    }

    public static class SmutnyDekoracyjnyWitacz extends DekoracyjnyWitacz {
        @Override
        public void przywitaj(String imie) {
            System.err.println(dekoruj("Cześć, " + imie + "."));
        }
    }

    public static void hello(Witacz witacz) {
//        System.out.println("1");
        witacz.przywitaj("Baltazarze");
    }

    public static void main(String[] args) {
        ZwykłyWitacz zw = new ZwykłyWitacz();
        hello(zw);
        zw.pożegnaj("Baltazarze");
        hello(new RozentuzjazmowanyWitacz());
        hello(new ZwykłyDekoracyjnyWitacz());
        hello(new SmutnyDekoracyjnyWitacz());

        hello(new ZwykłyWitacz() {
            @Override
            public void przywitaj(String imie) {
                System.out.println("Witaj, " + imie + "!!!");
            }
        });

        hello(new DekoracyjnyWitacz() {
            @Override
            public void przywitaj(String imie) {
                System.out.println(dekoruj("Elo, " + imie));
            }
        });
        hello(new Witacz() {
            @Override
            public void przywitaj(String imie) {
                System.out.println("Dzień dobry, " + imie);
            }
        });
        hello(new WitaczoŻegnacz() {
            @Override
            public void przywitaj(String imie) {
                System.out.println("Witam i żegnam, " + imie);
            }

            @Override
            @NotImplemented
            public void pożegnaj(String imie) {

            }
        });
        hello(new Witacz() {
        });
    }
}
