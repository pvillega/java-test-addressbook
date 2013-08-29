package com.gumtree.application;


import com.gumtree.model.AddressBook;
import com.gumtree.model.Person;
import com.gumtree.services.AddressBookFactory;

public class AddressBookApplication {

    static String FILE = "./AddressBook";

    public static void main(String[] args) throws Exception {
        // Read file and create data structure
        AddressBook addressBook = AddressBookFactory.getAddressBookFromPath(FILE);

        // 1. How many males are in the address book?
        System.out.println("Males in list: " + addressBook.countMales());

        // 2. Who is the oldest person in the address book?
        System.out.println("Oldest person is: " + addressBook.findOldestPerson());

        // 3. How many days older is Bill than Paul?
        Person bill = addressBook.findByNameInList("Bill McKnight");
        Person paul = addressBook.findByNameInList("Paul Robinson");
        System.out.println("Bill is " + bill.howManyDaysOlderThan(paul) + " older than Paul");
    }


}
