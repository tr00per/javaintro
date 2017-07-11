package sda.code.intermediate.part1.exercises.patterns;

import java.math.BigDecimal;

/**
 * Ta klasa abstrakcyjna stanowi podstawę pozostałych Builderów. Ma dwa
 * parametry szablonu: T - typ buildera-dziecka, potrzebne, by dało się łatwo
 * stworzyć fluent API; R - typ obiektu zwracanego przez builder-dziecko, typ
 * zwracany przez metodę "build".
 */
@SuppressWarnings("unchecked")
public abstract class ProductBuilder<T, R> {
    protected String name;
    protected BigDecimal price;
    private String maybePrice;

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public T withPrice(String price) {
        maybePrice = price;
        return (T) this;
    }

    /**
     * Metoda wywołuje preprocessing dla danych znajdujących sie w
     * Builderze-rodzicu (ProductBuilder)
     */
    protected void prepare() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Metoda sprawdza warunki, jakie muszą spełniać dane przeznaczone dla
     * obiektu wyjściowego
     */
    protected void validate() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public abstract R build();
}
