import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    void testGetZip_StandardAddress() {
        Person p = new Person("John", 'D', "Doe", 30, 'M', "123 Lane, Miami, FL 33139");
        assertEquals("33139", p.getZip(p.getPrimaryAddress())); //
    }

    @Test
    void testGetZip_WithQuotesAndSpaces() {
        // White-box: Testing the replaceAll("\"") logic in your code
        Person p = new Person("John", 'D', "Doe", 30, 'M', "\" 456 Blvd 32003 \"");
        assertEquals("32003", p.getZip(p.getPrimaryAddress()));
    }

    @Test
    void testGetZip_NoZipFound() {
        // Edge case: If no 5-digit number exists, the regex returns the original string
        Person p = new Person("John", 'D', "Doe", 30, 'M', "No Zip Here");
        assertEquals("No Zip Here", p.getZip(p.getPrimaryAddress()));
    }
    
    @Test
    public void testPersonSettersAndGetters() {
        // 1. Create a Patient or Doctor (since they are Persons)
        // Using your Patient constructor order: ...sex, insurance, address, password
        Patient p = new Patient("Initial", 'I', "User", 20, 'M', "None", "123 Old St", "pass");

        // 2. Test First Name
        p.setFirstName("Jane");
        assertEquals("Jane", p.getFirstName());

        // 3. Test Middle Initial
        p.setMiddleInitial('Z');
        assertEquals('Z', p.getMiddleInitial());

        // 4. Test Last Name
        p.setLastName("Doe");
        assertEquals("Doe", p.getLastName());

        // 5. Test Age
        p.setAge(35);
        assertEquals(35, p.getAge());

        // 6. Test Sex
        p.setSex('F');
        assertEquals('F', p.getSex());

        // 7. Test Primary Address
        p.setPrimaryAddress("789 New Way");
        assertEquals("789 New Way", p.getPrimaryAddress());
    }
}
