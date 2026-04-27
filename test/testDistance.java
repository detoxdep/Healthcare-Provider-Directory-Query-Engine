import static org.junit.Assert.*;
import org.junit.Test;

// NO PACKAGE LINE HERE
// NO IMPORT PERSON OR DISTANCE HERE

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
        // This assumes your Person constructor takes (String, char, String, int, char, String)
        Person testPerson = new Person("Test", 'T', "User", 30, 'M', address);

        int expectedZip = 34205;
        int actualZip = testPerson.getZip(address);

        assertEquals("The ZIP code should be extracted correctly from the address string.",
                     expectedZip, actualZip);
    }

    @Test
    public void testDistancePlaceholder() {
        // Testing the new Distance class logic
        String patientZip = "32505";
        String doctorZip = "34205";

        Distance dist = new Distance(patientZip, doctorZip);
        
        // Now that we built Distance.java, we can check if it exists
        assertNotNull("Distance object should be instantiable.", dist);
        
        // Optional: Test the calculation we just built
        double miles = dist.calculateHaversine();
        assertTrue("Distance should be a positive number", miles >= 0);
    }
}
