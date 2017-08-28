package sda.code.intermediate.part1extra;

import java.util.*;

public class GenerykiBardziej {


    public interface Zwierze {
        int getWaga();
    }

    public static class Pies implements Zwierze {
        @Override
        public int getWaga() {
            return 10;
        }
    }

    public static class Rekin implements Zwierze {
        @Override
        public int getWaga() {
            return 300;
        }
    }

    public static class Pterodaktyl implements Zwierze {
        @Override
        public int getWaga() {
            return 50;
        }
    }

    public interface LepszySumator<A, P> {
        A sumuj(A akumulator, P p);
    }

    // Patrz niżej na omówienie <T extends Zwierze>
    public static class SumatorWagZwierzat<Z extends Zwierze> implements LepszySumator<Integer, Z> {
        @Override
        public Integer sumuj(Integer suma, Z element) {
            return suma + element.getWaga();
        }
    }

    public static class ProstySumatorWagZwierzat implements LepszySumator<Integer, Zwierze> {
        @Override
        public Integer sumuj(Integer suma, Zwierze element) {
            return suma + element.getWaga();
        }
    }

    public static void main(String[] args) {
        List<Zwierze> l1 = new ArrayList<>();
        l1.add(new Pies());
        l1.add(new Rekin());
        l1.add(new Pterodaktyl());

        List<Pies> l2 = new ArrayList<>();
        l2.add(new Pies());
        l2.add(new Pies());

        List<Rekin> l3 = new LinkedList<>();
        l3.add(new Rekin());
        l3.add(new Rekin());

        Set<Pterodaktyl> l4 = new HashSet<>();
        l4.add(new Pterodaktyl());
        l4.add(new Pterodaktyl());

        suma(l1, 0, new SumatorWagZwierzat<>());
        suma(l1, 0, new ProstySumatorWagZwierzat());

        suma(l2, 0, new SumatorWagZwierzat<>());

        // bez automatycznego wnioskowania typów, które działa dla funkcji i konstruktorów, wszystko jawnie,
        // jak w deklaracji typów zmiennych (zobaczcie, że IDE podkreśla je jako zbędne)
        GenerykiBardziej.<Integer, Rekin>suma(l3, 0, new SumatorWagZwierzat<Rekin>());

        sumaSuper(l3, 0, new ProstySumatorWagZwierzat());

        suma(l4, 0, new SumatorWagZwierzat<>());

        // Dodatkowy parametr ze słowem "extends" na sumatorze jest wymagany, ponieważ:
        // List<Pies> jest pochodną Collection<Pies> (patrz: funkcja sumuj - wszystko wciąż działa),
        // ale NIE jest pochodną List<Zwierze> - u nas parametr z listy musi zgadzać się z parametrem LepszegoSumatora,
        // więc konkretnie w naszym przypadku - SumatorWagZwierzat extends LepszySumator<Integer, Zwierze>, który
        // mieliśmy, nie mógł zostać użyty w miejscu, które oczekiwało LepszySumator<Integer, Rekin>. Dlatego musieliśmy
        // rozszerzyć definicję SumatorWagZwierzat, by też była parametryzowana, ale ponieważ chcieliśmy wciąż mieć
        // możliwość używania go do wszystkich Zwierząt, to dekleracja parametru musi zawierać formułę
        // <T extends Zwierze>, a wewnątrz definicji klasy SumatorWagZwierzat używamy metod ze Zwierza, jak wcześniej
    }

    private static <A, T> A suma(Collection<T> lista, A wartośćPoczątkowa, LepszySumator<A, T> sumator) {
        A suma = wartośćPoczątkowa;
        for (T element : lista) {
            suma = sumator.sumuj(suma, element);
        }
        return suma;
    }

    // Osiągnęliśmy podobną elastyczność wprowadzając wildcard do typu oczekiwanego interfejsu
    // (który implementuje ProstySumatorWagZwierzat). Od nas zależy, którą drogę wybierzemy.
    // Mamy oba mechanizmy, dzięki czemu mamy większą elastyczność. Najczęściej jest tak, że nie mamy możliwości
    // swobodnej modyfikacji obu części układanki - przekazywanej klasy i wywoływanej metody - wtedy będzie pasować
    // tylko jedno z tych rozwiązań.
    //
    // <T extends Zwierze> wskazuje, że chcemy złapać typ T, który jest pochodną Zwierzęcia albo jest Zwierzęciem
    // <? super T> wskazuje, że chcemy interfejs oparty o cokolwiek (?), co jest nadklasą typu T (albo samym T).
    private static <A, T> A sumaSuper(Collection<T> lista, A wartośćPoczątkowa, LepszySumator<A, ? super T> sumator) {
        A suma = wartośćPoczątkowa;
        for (T element : lista) {
            suma = sumator.sumuj(suma, element);
        }
        return suma;
    }

}
