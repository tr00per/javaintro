package sda.code.intermediate.part1.answers.patterns.observer;

public class UnhandledMessage extends RuntimeException {

	private static final long serialVersionUID = -6356263632841596251L;

	public UnhandledMessage(String message) {
		super(message);
	}

}
