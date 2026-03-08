package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;
import Doctor;

public class testDoctor {

    @Test
    public void testDoctorConstructorAndInheritance() {
        // Data for testing
        String[] languages = {"English", "Spanish"};
        String[] specializations = {"Radiology"};
        String[] insurance = {"Aetna", "BlueCross"};
        Date now = new Date();

        // Creating a Doctor object (Inherits from Person)
        Doctor doc = new Doctor(
            "John", 'L', "Wilson", 60, 'M', 
            "6046315842", now, languages, specializations, 
            "6341 Pine Rd, Tallahassee, FL 32904", "Suite 100", 
            insurance, true, "http://clinic.com", 
            "555-0101", "555-0102", 4.5f
        );

        // Test inherited attributes from Person
        assertEquals("First name should be John", "John", doc.getFirstName());
        assertEquals("Last name should be Wilson", "Wilson", doc.getLastName());
        
        // Test Doctor-specific attributes
        assertEquals("NPI should match", "6046315842", doc.getNPI());
        assertEquals("Review average should be 4.5", 4.5f, doc.getReviewAvg(), 0.01);
        assertTrue("Should be accepting new patients", doc.isNewPatients());
    }

    @Test
    public void testArrayDataHandling() {
        String[] specializations = {"Urology", "Pediatrics"};
        Doctor doc = new Doctor("Jane", 'D', "Doe", 45, 'F', "12345", new Date(), 
                                new String[]{"English"}, specializations, "Address", 
                                "Secondary", new String[]{"Cigna"}, false, "URL", 
                                "Phone1", "Phone2", 5.0f);

        assertArrayEquals("Specializations should store multiple values", 
                          specializations, doc.getSpecialization());
    }
}
