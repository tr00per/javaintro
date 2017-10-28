package sda.code.intermediate.part1.exercises.patterns;

import sda.code.intermediate.FileLoaders;

import java.util.Properties;

/**
 * Klasa Settings ma przechowywać jedyną kopię wczytanej konfiguracji
 * (Singleton) oraz stanowić adapter dla wbudowanej klasy Properties.
 */
public class Settings {

    private final Properties props;

    private Settings() {
        props = new FileLoaders().loadDefaultProperties();
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
