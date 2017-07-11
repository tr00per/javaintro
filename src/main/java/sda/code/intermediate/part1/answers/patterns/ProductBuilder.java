package sda.code.intermediate.part1.answers.patterns;

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
        try {
            price = new BigDecimal(maybePrice);
        } catch (NumberFormatException e) {
            throw new BuilderPreparationFailed("Price must be a number");
        } catch (NullPointerException e) {
            throw new InvalidBuilderState("Price must be set");
        }
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
