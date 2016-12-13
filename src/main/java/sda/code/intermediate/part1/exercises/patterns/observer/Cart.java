package sda.code.intermediate.part1.exercises.patterns.observer;

import java.math.BigDecimal;

import sda.code.intermediate.part1.exercises.data.Product;

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
