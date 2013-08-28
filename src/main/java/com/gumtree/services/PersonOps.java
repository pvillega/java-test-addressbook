package com.gumtree.services;

import com.gumtree.model.Person;
import org.joda.time.Days;

import java.util.List;

/**
 * Operations related to persons
 * User: pvillega
 */
public class PersonOps {

    /**
     * Returns how many males exist in the given list
     *
     * @param persons list of persons
     * @return number of males in the list
     */
    public static int countMales(List<Person> persons) {
        int counter = 0;
        if (persons != null) {
            for (Person p : persons) {
                if(p.isMale()){
                    counter = counter + 1;
                }
            }
        }
        return counter;
    }

    /**
     * Finds the oldest person in the list. If two or more oldest person exist, returns the first one in the list
     * @param persons list of persons
     * @return the oldest person in the given list, or null if the list is empty or null
     */
    public static Person findOldestPerson(List<Person> persons){
        Person oldest = null;
        if(persons != null){
            for(Person p: persons){
                if(oldest == null || p.getDob().isBefore(oldest.getDob())){
                    oldest = p;
                }
            }
        }
        return oldest;
    }


    /**
     * Finds a person in the list with the given name. If many persons have the same name only the first is returned
     * @param name name of the person. It has to be the full name and match exactly
     * @param persons list of persons to search
     * @return a person in the list with the given name or null if not found or some of the parameters is null
     */
    public static Person findByNameInList(String name, List<Person> persons) {
        Person person = null;
        if(name != null && persons != null) {
            for(Person p: persons) {
                if(name.equals(p.getName())) {
                    person = p;
                    break;
                }
            }
        }
        return person;
    }

    /**
     * Returns how many days older is old than young
     * If the youngest person is given first, the result may be a negative number
     * @param old oldest person
     * @param young young person
     * @return how many days older is old than young. 0 if any of them is null
     */
    public static int howMuchOlder(Person old, Person young) {
        int days = 0;
        if(old != null && young != null) {
            days = Days.daysBetween(old.getDob(), young.getDob()).getDays();
        }
        return days;
    }
}
