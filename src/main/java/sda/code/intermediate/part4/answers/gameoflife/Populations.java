package sda.code.intermediate.part4.answers.gameoflife;

public class Populations {

	public static Filler randomPopulation(double ratio) {
		return (w, x, y) -> Entity.dead();
	}

	public static Filler classic() {
		return (w, x, y) -> Entity.dead();
	}

}
