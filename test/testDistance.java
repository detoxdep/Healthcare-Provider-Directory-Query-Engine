package test;

import static org.junit.Assert.*;
import org.junit.Test;
// Importing the Person class to test the zip extraction used for distance
import Person; 

public class testDistance {

    /**
     * Iteration 2 groundwork: Testing the ZIP extraction logic.
     * The Distance class relies on correctly parsed ZIP codes from the 
     * address strings found in the CSV.
     */
    @Test
    public void testZipExtraction() {
        // Sample address from your random_doctors_florida_zipcodes.csv
        String address = "4126 Washington Ave, Pensacola, FL 34205";
        
        // Creating a temporary person object to access the getZip logic
        Person testPerson = new Person("Test", 'T', "User", 30, 'M', address);
        
        int expectedZip = 34205;
        int actualZip = testPerson.getZip(address);
        
        assertEquals("The ZIP code should be extracted correctly from the address string.", 
                     expectedZip, actualZip);
    }

    @Test
    public void testDistancePlaceholder() {
        // Placeholder for Iteration 2 Haversine logic
        // Once Distance.java is implemented, tests for miles calculation go here
        String patientZip = "32505";
        String doctorZip = "34205";
        
        Distance dist = new Distance(patientZip, doctorZip);
        assertNotNull("Distance object should be instantiable for Iteration 2.", dist);
    }
}package test;

