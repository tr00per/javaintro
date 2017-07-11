package sda.code.intermediate.part1.exercises.patterns.observer;

import sda.code.intermediate.part1.exercises.data.Product;

import java.math.BigDecimal;

/**
 * Koszyk ma dostęp tylko do instancji listy produktów, nie pośredniczy w
 * operacjach jej dotyczących. Koszyk zapisuje się na zdarzenia, które będą
 * emitowane przez listę, żeby aktualizować swoją całkowitą wartość.
 */
public class Cart implements Subscriber<Product> {

    private BigDecimal total;

    public Cart(ListOfProducts products) {
        this.total = BigDecimal.ZERO;
        products.subscribe(this);
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void handle(Event<Product> event) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
