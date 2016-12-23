package sda.code.intermediate.part1.exercises.patterns;

import sda.code.intermediate.part1.exercises.data.Item;

/**
 * @see ProductBuilder
 */
public class ItemBuilder extends ProductBuilder<ItemBuilder, Item> {
	private Double weight;

	public ItemBuilder withWeight(Double weight) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public Item build() {
		prepare();
		validate();
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
