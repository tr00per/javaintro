package sda.code.intermediate.part1.exercises.patterns;

/**
 * Wyjątek informujący użytkownika o błędzie podczas pre-processingu w builderze.
 * 
 * @see SettingMissing
 */
public class BuilderPreparationFailed extends RuntimeException {

	private static final long serialVersionUID = -6117911393182672373L;

	public BuilderPreparationFailed(String msg) {
		super(msg);
	}

}
