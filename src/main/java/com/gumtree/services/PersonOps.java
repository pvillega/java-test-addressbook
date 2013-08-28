package com.gumtree.services;

import com.gumtree.model.Person;

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


}
