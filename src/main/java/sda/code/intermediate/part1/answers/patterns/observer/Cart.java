package sda.code.intermediate.part1.answers.patterns.observer;

import sda.code.intermediate.part1.answers.data.Product;

import java.math.BigDecimal;

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
        if (event instanceof ProductAdded) {
            total = total.add(event.getContext().getPrice());
        } else if (event instanceof ProductRemoved) {
            total = total.subtract(event.getContext().getPrice());
        } else {
            throw new UnhandledMessage(event.getClass().getName());
        }
    }

}
