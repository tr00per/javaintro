package sda.code.intermediate.part1.answers.patterns;

import sda.code.intermediate.part1.answers.data.Person;

public class PersonBuilder {
    private String firstName;
    private String lastName;

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    private void validate() {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidBuilderState("First name must be defined and non-empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidBuilderState("Last name must be defined and non-empty");
        }
    }

    public Person build() {
        validate();
        return new Person(firstName, lastName);
    }
}
