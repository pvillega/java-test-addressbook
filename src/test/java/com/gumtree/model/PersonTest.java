package com.gumtree.model;

import static junit.framework.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for person class
 * User: pvillega
 */
@RunWith(JUnit4.class)
public class PersonTest {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    @Test
    public void testIsMale() {
        Person p = new Person("Male", Gender.MALE, new DateTime());
        assertTrue("Person should be male", p.isMale());
        assertFalse("Person should not be female", p.isFemale());
    }

    @Test
    public void testIsFemale() {
        Person p = new Person("Female", Gender.FEMALE, new DateTime());
        assertFalse("Person should not be male", p.isMale());
        assertTrue("Person should be female", p.isFemale());
    }

    @Test
    public void testHowManyDaysOlderThanwithNull() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));

        int result = p1.howManyDaysOlderThan(null);
        assertEquals("0 days when param is null", 0, result);
    }

    @Test
    public void testHowManyDaysOlderThan() {
        Person p1 = new Person("A", Gender.MALE, formatter.parseDateTime("01/01/1980"));
        Person p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("05/01/1980"));

        int result = p1.howManyDaysOlderThan(p2);
        assertEquals("p1 is 4 days older", 4, result);


        p2 = new Person("B", Gender.FEMALE, formatter.parseDateTime("01/02/1980"));

        result = p1.howManyDaysOlderThan(p2);
        assertEquals("p1 is 31 days older", 31, result);
    }
}
