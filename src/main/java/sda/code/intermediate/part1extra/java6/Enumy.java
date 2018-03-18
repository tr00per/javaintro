package sda.code.intermediate.part1extra.java6;

import java.util.function.Function;

public class Enumy {
    public static final int STAŁA = 0;


    public enum Koszulki /* implements Function<Integer, Integer> */ {
        XS(1),
        S,
        M,
        L,
        XL(1);

        private final int xs;

        Koszulki() {
            this.xs = 0;
        }

        Koszulki(int xs) {
            this.xs = xs;
        }

        public int getXs() {
            return xs;
        }

        public String pretty() {
            return "***" + this.toString() + "***";
        }
    }

    public static void main(String[] args) {
        Koszulki[] rozmiary = Koszulki.values();
        System.out.println(rozmiary[0]);
        System.out.println(Koszulki.valueOf("L"));
//        System.out.println(Koszulki.valueOf("XL"));

        System.out.println(Koszulki.L.getXs());
        System.out.println(Koszulki.XL.getXs());

        System.out.println(Koszulki.M.pretty());
    }

}
