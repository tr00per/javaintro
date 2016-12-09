package sda.code.intermediate.part1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import sda.code.intermediate.part1.exercises.data.Item;
import sda.code.intermediate.part1.exercises.data.Product;
import sda.code.intermediate.part1.exercises.data.Service;
import sda.code.intermediate.part1.exercises.patterns.InvalidBuilderState;
import sda.code.intermediate.part1.exercises.patterns.ItemBuilder;
import sda.code.intermediate.part1.exercises.patterns.ProductFactory;
import sda.code.intermediate.part1.exercises.patterns.ServiceBuilder;
import sda.code.intermediate.part1.exercises.patterns.SettingMissing;

public class DesignPatternsTest {

	@Test
	public void loadingConfig() {
		fail("Uncomment test");
//		assertTrue(Settings.CONFIG.getBoolean("success"));
//		assertEquals(42, Settings.CONFIG.getInteger("answer"));
//		assertEquals("World", Settings.CONFIG.getString("hello.msg"));
	}

	@Test(expected = SettingMissing.class)
	public void loadingMissingConfig() {
		fail("Uncomment test");
//		Settings.CONFIG.getBoolean("non-existent");
	}

	@Test
	public void buildItem() {
		Item expected = new Item("TV", new BigDecimal("500"), 10.0);
		Item actual = new ItemBuilder().withName("TV").withPrice("500").withWeight(10.0).build();
		assertEquals(expected, actual);
	}

	@Test
	public void buildService() {
		Service expected = new Service("Movie stream", new BigDecimal("10"), 2);
		Service actual = new ServiceBuilder().withName("Movie stream").withPrice("10").withTime(2).build();
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidBuilderState.class)
	public void incompleteItemBuild() {
		new ItemBuilder().build();
	}

	@Test(expected = InvalidBuilderState.class)
	public void incompleteServiceBuild() {
		new ItemBuilder().withName("Calculator watch").build();
	}

	@Test(expected = InvalidBuilderState.class)
	public void invalidItemBuilder() {
		new ItemBuilder().withName("TV").withPrice("500").withWeight(-1.0).build();
	}

	@Test(expected = InvalidBuilderState.class)
	public void invalidBuilderConvertedException() {
		new ServiceBuilder().withName("MMO").withPrice("X");
	}

	@Test
	public void productFactoryForItems() {
		Item expected = new Item("TV", new BigDecimal("500"), 10.0);
		Item actual = ProductFactory.newItem().withName("TV").withPrice("500").withWeight(10.0).build();
		assertEquals(expected, actual);
	}

	@Test
	public void productFactoryForService() {
		Service expected = new Service("Movie stream", new BigDecimal("10"), 2);
		Service actual = ProductFactory.newService().withName("Movie stream").withPrice("10").withTime(2).build();
		assertEquals(expected, actual);
	}

	@Test
	public void productFactorySomethingDifferent() {
		Product actual = ProductFactory.testProduct();
		assertEquals("Test product", actual.getName());
		assertEquals(new BigDecimal("0.01"), actual.getPrice());
	}

}
