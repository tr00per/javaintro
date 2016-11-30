package sda.code.intermediate.part1.answers.patterns;

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
		try {
			this.price = new BigDecimal(price);
		} catch (NumberFormatException e) {
			throw new InvalidBuilderState(e);
		}
		return (T) this;
	}

	protected void validate() {
		if (name == null) {
			throw new InvalidBuilderState("Name cannot be null");
		}
		if (price == null) {
			throw new InvalidBuilderState("Price cannot be null");
		}
		if (price.doubleValue() <= 0.0) {
			throw new InvalidBuilderState("Price cannot be zero or less");
		}
	}

	public abstract R build();
}
