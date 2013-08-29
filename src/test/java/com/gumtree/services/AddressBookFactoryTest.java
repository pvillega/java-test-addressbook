package com.gumtree.services;

import com.gumtree.model.AddressBook;
import com.gumtree.model.Gender;
import com.gumtree.model.Person;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for file utils
 * User: pvillega
 */
@RunWith(JUnit4.class)
public class AddressBookFactoryTest {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");

    @Test
    public void testMapToPersonListwithNullList() {
        List<Person> result = AddressBookFactory.mapToPersonList(null);

        assertTrue("List should be empty", result.isEmpty());
    }

    @Test
    public void testMapToPersonListwithEmptyList() {
        List<String> data = new ArrayList<>();

        List<Person> result = AddressBookFactory.mapToPersonList(data);

        assertTrue("List should be empty", result.isEmpty());
    }

    @Test
    public void testMapToPersonList() {

        Person a = new Person("A", Gender.FEMALE, formatter.parseDateTime("01/01/80"));
        Person b = new Person("B", Gender.MALE, formatter.parseDateTime("01/03/30"));

        List<String> data = new ArrayList<>();
        data.add("  A  ,  Female  ,   01/01/80");
        data.add("   B , Male, 01/03/30");

        List<Person> result = AddressBookFactory.mapToPersonList(data);

        assertEquals("We expect 2 persons in list", 2, result.size() );
        assertEquals("We expect first person to be A", a, result.get(0));
        assertEquals("We expect second person to be B", b, result.get(1));
    }


    @Test
    public void testLoadFileContentwithNull() throws IOException {
        List<String> result = AddressBookFactory.loadFileContent(null);

        assertTrue("List should be empty", result.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testLoadFileContentwithMissingFile() throws IOException {
        File file = new File("ThisWillNotExist.ForSure");

        AddressBookFactory.loadFileContent(file);
    }

    @Test
    public void testGetAddressBookFromFile() throws IOException {
        URL path = this.getClass().getResource("/AddressBook");
        File file = new File(path.getPath());

        AddressBook addressBook  = AddressBookFactory.getAddressBookFromFile(file);

        assertEquals("We expect 5 persons in address book", 5, addressBook.size() );
    }

    @Test
    public void testGetAddressBookFromPath() throws IOException {
        URL path = this.getClass().getResource("/AddressBook");

        AddressBook addressBook  = AddressBookFactory.getAddressBookFromPath(path.getPath());

        assertEquals("We expect 5 persons in address book", 5, addressBook.size() );
    }

}
