package sda.code.intermediate.part1.exercises.patterns;

import java.util.Properties;

import sda.code.intermediate.FileUtils;

public class Settings {

	private final Properties props;

	private Settings() {
		props = new FileUtils().loadDefaultProperties();
	}

	public String getString(String name) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public int getInteger(String name) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public boolean getBoolean(String name) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
