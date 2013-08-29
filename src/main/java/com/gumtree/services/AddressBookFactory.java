package com.gumtree.services;

import com.gumtree.model.AddressBook;
import com.gumtree.model.Gender;
import com.gumtree.model.Person;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates instances of AddressBook
 * User: pvillega
 */
public class AddressBookFactory {

    /**
     * Create an address book from a file located in the given path. The file has to follow the address book format
     * @param path path of a file to load
     * @return an address book with the data contained on the file.
     * @throws java.io.IOException we could not read the file
     */
    public static AddressBook getAddressBookFromPath(String path) throws IOException {
        File file = new File(path);
        return getAddressBookFromFile(file);
    }

    /**
     * Create an address book from the given file. The file has to follow the address book format
     * @param source file to load into the address book
     * @return an address book with the data contained on the file.
     * @throws java.io.IOException we could not read the file
     */
    public static AddressBook getAddressBookFromFile(File source) throws IOException {
        // load file into string array
        List<String> data = loadFileContent(source);

        // process strings into persons
        List<Person> persons =  mapToPersonList(data);

        return new AddressBook(persons);
    }

    /**
     * Converts a list of strings with csv data to a list of persons
     * Default access to allow testing, as mockito can't mock private methods
     *
     * @param data string list with csv data to convert to persons
     * @return a list of persons built from the data provided. Empty list if no person is provided or data is null
     */
    static List<Person> mapToPersonList(List<String> data) {
        List<Person> list = new ArrayList<>();

        if (data != null) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");

            //iterate and convert each csv line
            for (String csv : data) {
                String[] details = csv.split(",");

                // which gender?
                Gender gender = Gender.FEMALE;
                if (details[1].trim().equals("Male")) {
                    gender = Gender.MALE;
                }

                // get date
                DateTime dt = formatter.parseDateTime(details[2].trim());

                // add to list
                Person p = new Person(details[0].trim(), gender, dt);
                list.add(p);
            }
        }

        return list;
    }


    /**
     * Loads the contents of the file to an string array
     * Default access to allow testing, as mockito can't mock private methods
     *
     * @param file file to load
     * @return string list with the contents of the file, one line per entry. Empty list if file is null.
     * @throws java.io.IOException we could not read the file
     */
    static List<String> loadFileContent(File file) throws IOException {
        List<String> list = new ArrayList<>();

        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();

                while (line != null) {
                    list.add(line);
                    line = br.readLine();
                }
            }
        }

        return list;
    }

}
