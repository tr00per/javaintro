package sda.code.intermediate.part1.answers.data;

import java.math.BigDecimal;

public class Item extends Product {

    private final Double weight;

    public Item(String name, BigDecimal price, Double weight) {
        super(name, price);
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Item other = (Item) obj;
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        } else if (!weight.equals(other.weight)) {
            return false;
        }
        return true;
    }

}
