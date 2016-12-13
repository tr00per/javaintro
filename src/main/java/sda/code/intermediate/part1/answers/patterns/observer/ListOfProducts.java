package sda.code.intermediate.part1.answers.patterns.observer;

import java.util.LinkedList;
import java.util.List;

import sda.code.intermediate.part1.answers.data.Product;

public class ListOfProducts implements Publisher<Product> {

	final List<Product> products;
	final List<Subscriber<Product>> subscribers;

	public ListOfProducts() {
		products = new LinkedList<>();
		subscribers = new LinkedList<>();
	}

	public void add(Product product) {
		products.add(product);
		fireEvent(new ProductAdded(product));
	}

	public void remove(Product product) {
		if (products.remove(product)) {
			fireEvent(new ProductRemoved(product));
		}
	}

	@Override
	public void subscribe(Subscriber<Product> subscriber) {
		subscribers.add(subscriber);
		products.forEach(p -> subscriber.handle(new ProductAdded(p)));
	}

	private void fireEvent(Event<Product> event) {
		subscribers.forEach(p -> p.handle(event));
	}
}
