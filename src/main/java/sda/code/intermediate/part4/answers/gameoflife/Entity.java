package sda.code.intermediate.part4.answers.gameoflife;

public class Entity {
	private static final Entity DEAD = new DeadEntity();
	private static final Entity NEWBORN = new Entity(0);

	int age;

	private Entity(int age) {
		this.age = age;
	}

	public boolean isAlive() {
		return age >= 0;
	}

	public Entity descendant() {
		return new Entity(age + 1);
	}

	public static Entity newborn() {
		return NEWBORN;
	}

	public static Entity dead() {
		return DEAD;
	}

	private static class DeadEntity extends Entity {
		private DeadEntity() {
			super(-1);
		}

		@Override
		public boolean isAlive() {
			return false;
		}

		@Override
		public Entity descendant() {
			throw new IllegalStateException("Dead entities cannot have descendants");
		}
	}
}
