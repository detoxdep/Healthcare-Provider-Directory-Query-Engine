import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PatientTest {

  
    @Test
    void testPatientInheritance() {
        // Correct Order: ... sex, insurance, address, password
        Patient pat = new Patient("Bob", 'J', "Smith", 25, 'M', "BlueCross", "999 St 32256", "pass123");
        
        assertEquals("BlueCross", pat.getInsurance()); // Now this will pass
        assertEquals("999 St 32256", pat.getPrimaryAddress());
        assertEquals("pass123", pat.getPassword());
    }

    @Test
    public void testPatientConstructorAndGetters() {
        // Correct Order: ... sex, insurance, address, password
        Patient p = new Patient("John", 'D', "Doe", 30, 'M', "Aetna", "123 Main St, Miami, FL 33139", "securePass123");

        assertEquals("Aetna", p.getInsurance());
        assertEquals("securePass123", p.getPassword());
        assertEquals("Doe", p.getLastName());
    }
    
    @Test
    public void testPatientSetters() {
        // 1. Create a patient
        Patient p = new Patient("John", 'D', "Doe", 30, 'M', "Aetna", "123 Main St", "oldPass");

        // 2. Explicitly call the setters that are red
        p.setInsurance("Blue Cross");
        p.setPassword("newSecurePass123");

        // 3. Verify they changed (this forces the coverage to record the 'set' lines as executed)
        assertEquals("Blue Cross", p.getInsurance());
        assertEquals("newSecurePass123", p.getPassword());
    }
}
