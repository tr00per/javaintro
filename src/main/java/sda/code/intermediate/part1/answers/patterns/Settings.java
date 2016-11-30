package sda.code.intermediate.part1.answers.patterns;

import java.util.Properties;

import sda.code.intermediate.FileUtils;

public enum Settings {
	CONFIG;

	private final Properties props;

	private Settings() {
		props = new FileUtils().loadDefaultProperties();
	}

	public String getString(String name) {
		String value = props.getProperty(name);
		if (value == null) {
			throw new SettingMissing(name);
		}
		return value;
	}

	public int getInteger(String name) {
		String value = props.getProperty(name);
		if (value == null) {
			throw new SettingMissing(name);
		}
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String name) {
		String value = props.getProperty(name);
		if (value == null) {
			throw new SettingMissing(name);
		}
		return Boolean.parseBoolean(value);
	}

}
