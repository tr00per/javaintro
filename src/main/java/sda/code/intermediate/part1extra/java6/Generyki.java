package sda.code.intermediate.part1extra.java6;

public class Generyki {

    static class Towar {
        public String nazwa;

        public Towar(String nazwa) {
            this.nazwa = nazwa;
        }
    }

    static class Użytkownik {
        public String login;

        public Użytkownik(String login) {
            this.login = login;
        }
    }

    public static Towar znajdź(String nazwa) {
        if (nazwa.equals("Telewizor")) {
            return new Towar("Telewizor");
        } else {
            return null;
        }
    }

    interface TowarKonsumer {
        void przyjmij(Towar t);
    }

    static class MaybeTowar {
        private final Towar towar;

        public MaybeTowar(Towar towar) {
            this.towar = towar;
        }

        public void wykonaj(TowarKonsumer operacja) {
            if (towar != null) {
                operacja.przyjmij(towar);
            }
        }
    }

    interface UżytkownikKonsumer {
        void przyjmij(Użytkownik t);
    }

    static class MaybeUżytkownik {
        private final Użytkownik użytkownik;

        public MaybeUżytkownik(Użytkownik towar) {
            this.użytkownik = towar;
        }

        public void wykonaj(UżytkownikKonsumer operacja) {
            if (użytkownik != null) {
                operacja.przyjmij(użytkownik);
            }
        }
    }


    interface Konsumer<Typ> {
        void przyjmij(Typ t);
    }

    static class Maybe<C> {
        private final C zawartość;

        public Maybe(C zawartość) {
            this.zawartość = zawartość;
        }

        public void wykonaj(Konsumer<C> operacja) {
            if (zawartość != null) {
                operacja.przyjmij(zawartość);
            }
        }
    }


    public static MaybeTowar maybeZnajdź(String nazwa) {
        return new MaybeTowar(znajdź(nazwa));
    }

    public static Maybe<Towar> znajdźTo(String nazwa) {
        return new Maybe<>(znajdź(nazwa));
    }

    public static void main(String[] args) {
        Towar towar1 = znajdź("Radio");
        if (towar1 != null) {
            System.out.println(towar1.nazwa.toUpperCase());
        }
        Towar towar2 = znajdź("Telewizor");
        if (towar2 != null) {
            System.out.println(towar2.nazwa.toUpperCase());
        }

        MaybeTowar maybeTowar1 = maybeZnajdź("Radio");
        maybeTowar1.wykonaj(t -> System.out.println(t.nazwa.toUpperCase()));

        MaybeTowar maybeTowar2 = maybeZnajdź("Telewizor");
        maybeTowar2.wykonaj((Towar t) -> {
            System.out.println(t.nazwa.toUpperCase());
        });


        Maybe<Towar> towar3 = znajdźTo("Radio");
        towar3.wykonaj((Towar t) -> {
            System.err.println(t.nazwa.toUpperCase());
        });

        Maybe<Towar> towar4 = znajdźTo("Telewizor");
        towar4.wykonaj(t -> System.err.println(t.nazwa.toUpperCase()));

        Maybe<Integer> mi = new Maybe<>(3);
    }
}

