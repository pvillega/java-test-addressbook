package com.gumtree.support;

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
public class FileUtilsTest {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    @Test
    public void testMapToPersonListwithNullList() {
        List<Person> result = FileUtils.mapToPersonList(null);

        assertTrue("List should be empty", result.isEmpty());
    }

    @Test
    public void testMapToPersonListwithEmptyList() {
        List<String> data = new ArrayList<>();

        List<Person> result = FileUtils.mapToPersonList(data);

        assertTrue("List should be empty", result.isEmpty());
    }

    @Test
    public void testMapToPersonList() {

        Person a = new Person("A", Gender.FEMALE, formatter.parseDateTime("01/01/1980"));
        Person b = new Person("B", Gender.MALE, formatter.parseDateTime("01/03/1930"));

        List<String> data = new ArrayList<>();
        data.add("  A  ,  Female  ,   01/01/1980");
        data.add("   B , Male, 01/03/1930");

        List<Person> result = FileUtils.mapToPersonList(data);

        assertEquals("We expect 2 persons in list", 2, result.size() );
        assertEquals("We expect first person to be A", a, result.get(0));
        assertEquals("We expect second person to be B", b, result.get(1));
    }


    @Test
    public void testLoadFileContentwithNull() throws IOException {
        List<String> result = FileUtils.loadFileContent(null);

        assertTrue("List should be empty", result.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testLoadFileContentwithMissingFile() throws IOException {
        File file = new File("ThisWillNotExist.ForSure");

        FileUtils.loadFileContent(file);
    }

    @Test
    public void testLoadFileContent() throws IOException {

        URL path = this.getClass().getResource("/AddressBook");
        File file = new File(path.getPath());

        List<String> result = FileUtils.loadFileContent(file);

        assertEquals("We expect 5 persons in list", 5, result.size() );

        assertEquals("We expect first person to be Bill","Bill McKnight, Male, 16/03/77", result.get(0));
        assertEquals("We expect second person to be Paul","Paul Robinson, Male, 15/01/85", result.get(1));
        assertEquals("We expect third person to be Gemma","Gemma Lane, Female, 20/11/91", result.get(2));
        assertEquals("We expect fourth person to be Sarah","Sarah Stone, Female, 20/09/80", result.get(3));
        assertEquals("We expect fifth person to be Wes","Wes Jackson, Male, 14/08/74", result.get(4));
    }

}
