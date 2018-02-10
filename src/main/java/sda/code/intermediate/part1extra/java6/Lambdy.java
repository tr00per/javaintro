package sda.code.intermediate.part1extra.java6;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Lambdy {

    interface Dodawacz {
        Integer dodaj1(Integer x);
    }

    public static void wypiszSumę(Dodawacz dodawacz) {
        Integer losowa = new Random().nextInt();
        System.out.println(losowa + " => " + dodawacz.dodaj1(losowa));
    }

    public static Integer zwiększ(Integer x) {
        return x + 1;
    }

    public static void main(String[] args) {
        wypiszSumę(new Dodawacz() {
            @Override
            public Integer dodaj1(Integer x) {
                return x + 1;
            }
        });

        wypiszSumę((Integer x) -> {
            return x + 1;
        });

        wypiszSumę(x -> {
            return x + 1;
        });

        wypiszSumę(x -> x + 1);

        wypiszSumę(x -> zwiększ(x));

        wypiszSumę(Lambdy::zwiększ);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Jestem w wątku!");
//            }
//        }).start();
//
//        new Thread(() -> {
//            System.out.println("Jestem w wątku!");
//        }).start() ;
//
//        new Thread(() -> System.out.println("Jestem w wątku!")).start();
//

        System.out.println("-------------------");
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 19);
        Integer sumaListy = 0;
        for (Integer x : lista) {
            if (x < 5) {
                sumaListy += x * x;
            }
        }
        System.out.println(sumaListy);

        System.out.println("-------------------");
        Integer suma = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 19)
                .parallel()
                .filter(x -> x < 5)
                .map(x -> x * x)
                .reduce(0, (acc, x) -> {
                    System.out.println(x + " + " + acc);
                    return x + acc;
                });
        System.out.println(suma);



        //-----------------------
//        filmyUżytkownika("Artur")           // Stream<Film>
// zamiast:   .map(f -> weźKomentarze(f))     // Stream<Stream<Komentarz>
// to:        .flatMap(f -> weźKomentarze(f)) // Stream<Komentarz>
//            .filter(k -> k.ocena > 4)
//            .count();
    }
}
