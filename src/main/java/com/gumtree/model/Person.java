package com.gumtree.model;

import org.joda.time.DateTime;

/**
 * Represents a person in the address book
 * User: pvillega
 */
public class Person {

    private String name;
    private Gender gender;
    private DateTime dob;

    /**
     * Constructor
     * @param name name of the person in the address book
     * @param gender gender of the person
     * @param dob date of birth of the person
     */
    public Person(String name, Gender gender, DateTime dob){
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!dob.equals(person.dob)) return false;
        if (gender != person.gender) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + dob.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public DateTime getDob() {
        return dob;
    }

    public void setDob(DateTime dob) {
        this.dob = dob;
    }

    public boolean isMale(){
        return getGender().equals(Gender.MALE);
    }

    public boolean isFemale(){
        return getGender().equals(Gender.FEMALE);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
