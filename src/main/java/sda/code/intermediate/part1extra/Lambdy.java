package sda.code.intermediate.part1extra;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambdy {
    public static class Person {

        public enum Sex {
            PREFER_NOT_TO_SPECIFY, FEMALE, MALE
        }

        private final String name;
        private final LocalDate birthday;
        private final Sex gender;
        private final String emailAddress;

        public Person(String name, LocalDate birthday, Sex gender) {
            this.name = name;
            this.birthday = birthday;
            this.gender = gender;
            this.emailAddress = null;
        }

        public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
            this.name = name;
            this.birthday = birthday;
            this.gender = gender;
            this.emailAddress = emailAddress;
        }

        public Person withEmail(String email) {
            return new Person(getName(), getBirthday(), getGender(), email);
        }

        public String getName() {
            return name;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public Sex getGender() {
            return gender;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Person{");
            sb.append("name='").append(name).append('\'');
            sb.append(", birthday=").append(birthday);
            sb.append(", gender=").append(gender);
            sb.append(", emailAddress='").append(emailAddress).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        final List<Person> osoby = Arrays.asList(
                new Person("Margaret Hamilton", LocalDate.of(1936, 8, 17), Person.Sex.FEMALE),
                new Person("Alojzy Kleks", LocalDate.of(1820, 6, 15), Person.Sex.MALE),
                new Person("Baltazar Gąbka", LocalDate.of(1960, 1, 13), Person.Sex.MALE),
                new Person("Bartolini herbu Zielona Pietruszka", LocalDate.of(1960, 1, 13), Person.Sex.PREFER_NOT_TO_SPECIFY),
                new Person("Ada Lovelace's Evil Brother", LocalDate.of(1815, 10, 12), Person.Sex.MALE),
                new Person("Ada Lovelace", LocalDate.of(1815, 10, 12), Person.Sex.FEMALE)
        );

        osoby.forEach(Lambdy::printPerson);

        System.out.println("--------------------------------------");

        osoby.stream() // albo .parallelStream() -- "darmowa" paralelizacja
                .filter(Lambdy::isWoman)
                .map(Lambdy::nadajEmail)
                .sorted(Comparator.comparing(Person::getBirthday).thenComparing(Person::getGender))
                .forEach(Lambdy::printPerson);
    }

    private static Person nadajEmail(Person p) {
        return p.withEmail(makeEmailAddress(p));
    }

    private static boolean isWoman(Person osoba) {
        return osoba.getGender() == Person.Sex.FEMALE;
    }

    private static String makeEmailAddress(Person osoba) {
        return osoba.getName().trim().replace(' ', '.') + "@from.net";
    }

    private static void printPerson(Person osoba) {
        System.out.println(osoba.getName() + ", " + osoba.getBirthday() + ", " + osoba.getGender() + ", " + osoba.getEmailAddress());
    }

}
