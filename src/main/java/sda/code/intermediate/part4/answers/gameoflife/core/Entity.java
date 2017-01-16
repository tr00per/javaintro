package sda.code.intermediate.part4.answers.gameoflife.core;

import sda.code.intermediate.part4.answers.gameoflife.GameEntity;

public class Entity implements GameEntity {

	final int age;

	Entity(int age) {
		this.age = age;
	}

	@Override
	public boolean isAlive() {
		return age >= 0;
	}

	@Override
	public GameEntity descendant() {
		return new Entity(age + 1);
	}

}
