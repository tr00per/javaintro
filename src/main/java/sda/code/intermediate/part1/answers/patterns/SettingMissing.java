package sda.code.intermediate.part1.answers.patterns;

public class SettingMissing extends RuntimeException {

    private static final long serialVersionUID = 5534548210197090062L;

    public SettingMissing(String name) {
        super("Key not found: " + name);
    }

}
