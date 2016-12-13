package sda.code.intermediate.part1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import sda.code.intermediate.part1.exercises.data.Product;
import sda.code.intermediate.part1.exercises.patterns.ProductFactory;
import sda.code.intermediate.part1.exercises.patterns.observer.Cart;
import sda.code.intermediate.part1.exercises.patterns.observer.Event;
import sda.code.intermediate.part1.exercises.patterns.observer.ListOfProducts;
import sda.code.intermediate.part1.exercises.patterns.observer.Subscriber;
import sda.code.intermediate.part1.exercises.patterns.observer.UnhandledMessage;

public class ObserverTest {

	private final static BigDecimal TEST_PRICE = new BigDecimal(ProductFactory.TEST_PRODUCT_PRICE);
	private final static BigDecimal DOUBLE_TEST_PRICE = TEST_PRICE.add(TEST_PRICE);

	private ListOfProducts products;

	@Before
	public void setUp() throws Exception {
		products = new ListOfProducts();
	}

	@Test
	public void priceUpdates() {
		Cart cart = new Cart(products);
		assertEquals(BigDecimal.ZERO, cart.getTotal());

		products.add(ProductFactory.testProduct());
		assertEquals(TEST_PRICE, cart.getTotal());

		products.add(ProductFactory.testProduct());
		assertEquals(DOUBLE_TEST_PRICE, cart.getTotal());

		products.remove(ProductFactory.testProduct());
		assertEquals(TEST_PRICE, cart.getTotal());
	}

	@Test
	public void priceInitialization() {
		products.add(ProductFactory.testProduct());
		Cart cart = new Cart(products);

		assertEquals(TEST_PRICE, cart.getTotal());
	}

	@Test
	public void priceInitializationForSecondSubscriber() {
		products.add(ProductFactory.testProduct());
		Cart cart1 = new Cart(products);
		Cart cart2 = new Cart(products);

		assertEquals(TEST_PRICE, cart1.getTotal());
		assertEquals(TEST_PRICE, cart2.getTotal());
	}

	@Test(expected = UnhandledMessage.class)
	public void unhandledMessage() {
		Cart cart = new Cart(products);
		cart.handle(new Event<Product>() {
			@Override
			public Product getContext() {
				return null;
			}
		});
	}

	@Test
	public void doesntFireUnnecessaryEvent() {
		products.subscribe(new Subscriber<Product>() {
			@Override
			public void handle(Event<Product> event) {
				fail("Fired unnecessary event");
			}
		});
		products.remove(ProductFactory.testProduct());
	}
}
