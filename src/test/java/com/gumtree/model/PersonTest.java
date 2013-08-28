package com.gumtree.model;

import static junit.framework.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for person class
 * User: pvillega
 */
@RunWith(JUnit4.class)
public class PersonTest {


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
}
