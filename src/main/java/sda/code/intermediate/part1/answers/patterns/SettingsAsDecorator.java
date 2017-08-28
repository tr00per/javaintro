package sda.code.intermediate.part1.answers.patterns;

import sda.code.intermediate.FileLoaders;

import java.util.Properties;

public class SettingsAsDecorator {

    private final Properties props;

    public SettingsAsDecorator(Properties props) {
        this.props = props;
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
