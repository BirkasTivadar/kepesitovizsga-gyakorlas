package hu.nive.ujratervezes.kepesitovizsga.covid;

import java.util.Objects;

public class Person {

    private String name;

    private int age;

    private ChronicDisease chronic;

    private Pregnancy pregnant;

    public Person(String name, int age, ChronicDisease chronic, Pregnancy pregnant) {
        this.name = name;
        this.age = age;
        this.chronic = chronic;
        this.pregnant = pregnant;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ChronicDisease getChronic() {
        return chronic;
    }

    public Pregnancy getPregnant() {
        return pregnant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && chronic == person.chronic && pregnant == person.pregnant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, chronic, pregnant);
    }
}
