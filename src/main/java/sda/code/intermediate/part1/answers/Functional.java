package sda.code.intermediate.part1.answers;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sda.code.intermediate.part1.answers.data.Item;
import sda.code.intermediate.part1.answers.data.Product;

public class Functional {

	public List<Integer> squares(List<Integer> list) {
		return list.stream().map(x -> x * x).collect(Collectors.toList());
	}

	public List<Integer> even(List<Integer> list) {
		return list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
	}

	public Long countOdd(List<Integer> list) {
		return list.stream().filter(x -> Math.abs(x % 2) == 1).collect(Collectors.counting());
	}

	public Integer smallest(List<Integer> list) {
		return list.stream().reduce(Math::min).orElseThrow(IllegalArgumentException::new);
	}

	private BigDecimal bruttoSum(Predicate<Product> policy, Function<BigDecimal, BigDecimal> tax,
			List<Product> products) {
		return products.stream().map((Product p) -> policy.test(p) ? tax.apply(p.getPrice()) : p.getPrice())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private Predicate<Product> taxOnItems() {
		return p -> p instanceof Item;
	}

	private Function<BigDecimal, BigDecimal> itemTax(BigDecimal tax) {
		BigDecimal percent = tax.add(BigDecimal.ONE);
		return p -> p.multiply(percent);
	}

	public BigDecimal cartBruttoSum(List<Product> products) {
		return bruttoSum(taxOnItems(), itemTax(new BigDecimal("0.42")), products);
	}

}
