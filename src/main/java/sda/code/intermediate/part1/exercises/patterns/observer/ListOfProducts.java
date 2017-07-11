package sda.code.intermediate.part1.exercises.patterns.observer;

import sda.code.intermediate.part1.exercises.data.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * Lista produktów potrafi rozgłaszać zmianę swojego stanu do kogokolwiek, kto
 * chce słuchać.
 */
public class ListOfProducts implements Publisher<Product> {

    final List<Product> products;
    final List<Subscriber<Product>> subscribers;

    public ListOfProducts() {
        products = new LinkedList<>();
        subscribers = new LinkedList<>();
    }

    public void add(Product product) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void remove(Product product) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void subscribe(Subscriber<Product> subscriber) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * W miarę implementowania pozostałych metod powinien pojawić się powtarzany
     * fragment. To jest miejsce na ten fragment.
     */
    private void fireEvent(Event<Product> event) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
