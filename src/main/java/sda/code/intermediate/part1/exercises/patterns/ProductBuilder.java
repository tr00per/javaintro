package sda.code.intermediate.part1.exercises.patterns;

import java.math.BigDecimal;

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

	protected void prepare() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	protected void validate() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public abstract R build();
}
