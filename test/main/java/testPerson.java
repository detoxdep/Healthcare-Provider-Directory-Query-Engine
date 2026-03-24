package test;

import static org.junit.Assert.*;
import org.junit.Test;
// Importing the Person class to test base functionality and ZIP extraction
import Person; 

public class testPerson {

    /**
     * Tests the core ZIP extraction logic found in the Person class.
     * This regex-based method is critical for filtering doctors by their 
     * area in the Florida dataset.
     */
    @Test
    public void testGetZip() {
        // Sample address from the provided random_doctors_florida_zipcodes.csv
        String address = "4126 Washington Ave, Pensacola, FL 34205";
        
        // Creating a Person instance to test the method
        Person testPerson = new Person("Roger", 'A', "Arias", 25, 'M', address);
        
        int expectedZip = 34205;
        int actualZip = testPerson.getZip(address);
        
        assertEquals("The ZIP code should be correctly extracted as 34205", 
                     expectedZip, actualZip);
    }

    /**
     * Tests standard getters and setters to ensure basic data integrity.
     */
    @Test
    public void testPersonData() {
        Person p = new Person("Michael", 'M', "McCarthy", 40, 'M', "123 UNF Drive");
        
        p.setAge(41);
        assertEquals("Age should be updated to 41", 41, p.getAge());
        
        p.setLastName("Smith");
        assertEquals("Last name should be updated to Smith", "Smith", p.getLastName());
    }
}
