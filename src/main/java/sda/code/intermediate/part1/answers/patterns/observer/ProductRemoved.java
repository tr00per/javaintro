package sda.code.intermediate.part1.answers.patterns.observer;

import sda.code.intermediate.part1.answers.data.Product;

public class ProductRemoved implements Event<Product> {

    private final Product context;

    public ProductRemoved(Product context) {
        this.context = context;
    }

    @Override
    public Product getContext() {
        return context;
    }

}
