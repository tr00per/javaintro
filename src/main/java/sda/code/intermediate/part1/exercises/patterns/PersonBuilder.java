package sda.code.intermediate.part1.exercises.patterns;

import sda.code.intermediate.part1.exercises.data.Person;

public class PersonBuilder {
    private String firstName;
    private String lastName;

    public PersonBuilder withFirstName(String firstName) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public PersonBuilder withLastName(String lastName) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void validate() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Person build() {
        validate();
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
