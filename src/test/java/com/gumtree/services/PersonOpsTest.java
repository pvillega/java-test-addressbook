package com.gumtree.services;

import com.gumtree.model.Gender;
import com.gumtree.model.Person;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Tests for PersonOps class
 * User: pvillega
 */
@RunWith(JUnit4.class)
public class PersonOpsTest {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    @Test
    public void testCountMaleswithNull() {
        int result = PersonOps.countMales(null);
        assertEquals("We have 0 males", 0, result);
    }

    @Test
    public void testCountMaleswithEmptyList() {
        List<Person> list = new ArrayList<>();

        int result = PersonOps.countMales(list);
        assertEquals("We have 0 males", 0, result);
    }

    @Test
    public void testCountMaleswithNoMales() {
        Person p1 = new Person("A", Gender.FEMALE, new DateTime());
        Person p2 = new Person("A", Gender.FEMALE, new DateTime());
        Person p3 = new Person("A", Gender.FEMALE, new DateTime());

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        int result = PersonOps.countMales(list);
        assertEquals("We have 0 males", 0, result);
    }

    @Test
    public void testCountMales() {
        Person p1 = new Person("A", Gender.MALE, new DateTime());
        Person p2 = new Person("A", Gender.FEMALE, new DateTime());
        Person p3 = new Person("A", Gender.MALE, new DateTime());

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        int result = PersonOps.countMales(list);
        assertEquals("We have 2 males", 2, result);
    }

    @Test
    public void testFindOldestPersonwithNull() {
        Person result = PersonOps.findOldestPerson(null);
        assertNull("We have no oldest person", result);
    }

    @Test
    public void testFindOldestPersonwithEmptyList() {
        List<Person> list = new ArrayList<>();

        Person result = PersonOps.findOldestPerson(list);
        assertNull("We have no oldest person", result);
    }

    @Test
    public void testFindOldestPerson() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));
        Person p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("01/01/1990"));
        Person p3 = new Person("C", Gender.MALE, formatter.parseDateTime("01/01/1999"));

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Person result = PersonOps.findOldestPerson(list);
        assertEquals("Oldest person is p1", p1, result);
    }

    @Test
    public void testFindOldestPersonwithManyOld() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));
        Person p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("01/01/1990"));
        Person p3 = new Person("C", Gender.MALE, formatter.parseDateTime("01/01/1980"));

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Person result = PersonOps.findOldestPerson(list);
        assertEquals("Oldest person is p1", p1, result);
    }

    @Test
    public void testFindNameInListwithNull() {
        Person result = PersonOps.findByNameInList(null, null);
        assertNull("No person found when both null", result);

        result = PersonOps.findByNameInList("", null);
        assertNull("No person found when list is null", result);

        result = PersonOps.findByNameInList(null, new ArrayList<Person>());
        assertNull("No person found when name null", result);
    }

    @Test
    public void testFindNameInListwithEmptyList() {
        List<Person> list = new ArrayList<>();

        Person result = PersonOps.findByNameInList("", list);
        assertNull("No person found in empty list", result);
    }

    @Test
    public void testFindNameInList() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));
        Person p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("01/01/1990"));
        Person p3 = new Person("C", Gender.MALE, formatter.parseDateTime("01/01/1999"));

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Person result = PersonOps.findByNameInList("A", list);
        assertEquals("Found by name is p1", p1, result);
    }

    @Test
    public void testFindNameInListwithManyEqual() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));
        Person p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("01/01/1990"));
        Person p3 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Person result = PersonOps.findByNameInList("A", list);
        assertEquals("Found by name is p1", p1, result);
    }

    @Test
    public void testHowMuchOlderwithNull() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));

        int result = PersonOps.howMuchOlder(null, null);
        assertEquals("0 days when both are null", 0, result);

        result = PersonOps.howMuchOlder(null, p1);
        assertEquals("0 days when once is null", 0, result);

        result = PersonOps.howMuchOlder(p1, null);
        assertEquals("0 days when once is null", 0, result);
    }

    @Test
    public void testHowMuchOlder() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));
        Person p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("05/01/1980"));

        int result = PersonOps.howMuchOlder(p1, p2);
        assertEquals("p1 is 4 days older", 4, result);


        p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("01/02/1980"));

        result = PersonOps.howMuchOlder(p1, p2);
        assertEquals("p1 is 31 days older", 31, result);
    }
}
