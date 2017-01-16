package sda.code.intermediate.part4.answers.gameoflife.core;

import sda.code.intermediate.part4.answers.gameoflife.GameEntity;
import sda.code.intermediate.part4.answers.gameoflife.GameEntityFactory;

public class EntityFactory implements GameEntityFactory {
	private static final Entity DEAD = new DeadEntity();
	private static final Entity NEWBORN = new Entity(0);

	@Override
	public GameEntity newborn() {
		return NEWBORN;
	}

	@Override
	public GameEntity dead() {
		return DEAD;
	}

}
