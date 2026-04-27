import static org.junit.jupiter.api.Assertions.*; // JUnit 5
import org.junit.jupiter.api.Test; // Note: If using JUnit 5 completely, this should be org.junit.jupiter.api.Test
import java.util.Date; // Added this!

public class testDoctor {

    @Test
    public void testDoctorConstructorAndInheritance() {
        String[] languages = {"English", "Spanish"};
        String[] specializations = {"Radiology"};
        String[] insurance = {"Aetna", "BlueCross"};
        Date now = new Date();

        Doctor doc = new Doctor(
            "John", 'L', "Wilson", 60, 'M',
            "6046315842", now, languages, specializations,
            "6341 Pine Rd, Tallahassee, FL 32904", "Suite 100",
            insurance, true, "http://clinic.com",
            "555-0101", "555-0102", 4.5f
        );

        // Messages moved to the END for JUnit 5
        assertEquals("John", doc.getFirstName(), "First name should be John");
        assertEquals("Wilson", doc.getLastName(), "Last name should be Wilson");
        assertEquals("6046315842", doc.getNPI(), "NPI should match");
        assertEquals(4.5f, doc.getReviewAvg(), 0.01, "Review average should be 4.5");
        assertTrue(doc.isNewPatients(), "Should be accepting new patients");
    }

    @Test
    public void testArrayDataHandling() {
        String[] specializations = {"Urology", "Pediatrics"};
        Doctor doc = new Doctor("Jane", 'D', "Doe", 45, 'F', "12345", new Date(),
                                new String[]{"English"}, specializations, "Address",
                                "Secondary", new String[]{"Cigna"}, false, "URL",
                                "Phone1", "Phone2", 5.0f);

        assertArrayEquals(specializations, doc.getSpecialization(), 
                          "Specializations should store multiple values");
    }
}
