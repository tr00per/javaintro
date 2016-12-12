package sda.code.intermediate.part1.answers.patterns;

public class InvalidBuilderState extends RuntimeException {

	private static final long serialVersionUID = 3992517656138461008L;

	public InvalidBuilderState(String msg) {
		super(msg);
	}

}
