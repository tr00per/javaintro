package sda.code.intermediate.part1.exercises.patterns;

import java.math.BigDecimal;

@SuppressWarnings("unchecked")
public abstract class ProductBuilder<T, R> {
	protected String name;
	protected BigDecimal price;

	public T withName(String name) {
		this.name = name;
		return (T) this;
	}

	public T withPrice(String price) {
		this.price = new BigDecimal(price);
		return (T) this;
	}

	protected void validate() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public abstract R build();
}
