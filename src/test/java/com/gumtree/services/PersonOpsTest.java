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
}
