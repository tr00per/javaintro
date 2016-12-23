package sda.code.intermediate.part1.exercises.patterns;

import sda.code.intermediate.part1.exercises.data.Product;

/**
 * Fabryka produktów powinna korzystać z wcześniej utworzonych klas typu
 * builder.
 */
public class ProductFactory {

	public static final String TEST_PRODUCT_NAME = "Test product";
	public static final String TEST_PRODUCT_PRICE = "0.01";

	public static ItemBuilder newItem() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public static ServiceBuilder newService() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public static Product testProduct() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
