package com.gumtree.application;


import com.gumtree.model.Person;
import com.gumtree.services.PersonOps;
import com.gumtree.support.FileUtils;

import java.util.List;

public class AddressBookApplication {

    static String FILE = "./AddressBook";

    public static void main(String[] args) throws Exception {
        // Read file and create data structure
        List<Person> persons = FileUtils.loadDataFile(FILE);
        System.out.println("List loaded: " + persons.toString());

        // 1. How many males are in the address book?
        int maleCount = PersonOps.countMales(persons);
        System.out.println("Males in list: " + maleCount);

        // 2. Who is the oldest person in the address book?
        Person oldest = PersonOps.findOldestPerson(persons);
        System.out.println("Oldest person is: " + oldest.toString());

        // 3. How many days older is Bill than Paul?
        Person bill = PersonOps.findByNameInList("Bill McKnight", persons);
        Person paul = PersonOps.findByNameInList("Paul Robinson", persons);
        int days = PersonOps.howMuchOlder(bill, paul);
        System.out.println("Bill is "+ days +" older than Paul");
    }


}
