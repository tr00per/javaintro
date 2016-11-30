package sda.code.intermediate.part1.exercises.data;

import java.math.BigDecimal;

public class Service extends Product {

	private final Integer time;

	public Service(String name, BigDecimal price, Integer time) {
		super(name, price);
		this.time = time;
	}

	public Integer getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		if (!(obj instanceof Service)) {
			return false;
		}
		if (!super.equals(obj)) {
			return false;
		}
		Service other = (Service) obj;
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}
}
