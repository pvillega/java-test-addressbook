package com.gumtree.model;

import java.util.List;

/**
 * Represents an address book
 * User: pvillega
 */
public class AddressBook {

    private List<Person> addressBook;

    /**
     * Constructor
     *
     * @param persons list of persons to populate the address book
     */
    public AddressBook(List<Person> persons) {
        if (persons == null) {
            throw new IllegalArgumentException("Constructor argument [persons] can't be null");
        }
        addressBook = persons;
    }

    /**
     * Returns how many males exist in the address book
     *
     * @return number of males in the address book
     */
    public int countMales() {
        int counter = 0;
        for (Person p : addressBook) {
            if (p.isMale()) {
                counter = counter + 1;
            }
        }
        return counter;
    }

    /**
     * Finds the oldest person in the address book. If two or more oldest person exist, returns the first one in the list
     * @return the oldest person in the address book, or null if the address book is empty
     */
    public Person findOldestPerson() {
        Person oldest = null;
        for (Person p : addressBook) {
            if (oldest == null || p.getDob().isBefore(oldest.getDob())) {
                oldest = p;
            }
        }
        return oldest;
    }


    /**
     * Finds a person in the address book with the given name. If many persons have the same name only the first is returned
     *
     * @param name    name of the person. It has to be the full name and match exactly
     * @return a person in the address book with the given name or null if not found the name is null
     */
    public Person findByNameInList(String name) {
        Person person = null;
        if (name != null) {
            for (Person p : addressBook) {
                if (name.equals(p.getName())) {
                    person = p;
                    break;
                }
            }
        }
        return person;
    }

    /**
     * Returns the number of entries in the address book
     * @return number of entries in the address book
     */
    public int size() {
        return addressBook.size();
    }

    /**
     * True if the address book has no entries, false otherwise
     * @return true if the address book has no entries, false otherwise
     */
    public boolean isEmpty() {
       return size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressBook)) return false;

        AddressBook that = (AddressBook) o;

        return addressBook.equals(that.addressBook);

    }

    @Override
    public int hashCode() {
        return addressBook.hashCode();
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "addressBook=" + addressBook +
                '}';
    }
}
