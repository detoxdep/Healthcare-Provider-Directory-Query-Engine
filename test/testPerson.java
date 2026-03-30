import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;


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

        assertEquals(expectedZip, actualZip, "The ZIP code should be correctly extracted as 34205");
    }

    /**
     * Tests standard getters and setters to ensure basic data integrity.
     */
    @Test
    public void testPersonData() {
        Person p = new Person("Michael", 'M', "McCarthy", 40, 'M', "123 UNF Drive");

        p.setAge(41);
        assertEquals(41, p.getAge(), "Age should be updated to 41");

        p.setLastName("Smith");
        assertEquals("Smith", p.getLastName(), "Last name should be updated to Smith");
    }
}

