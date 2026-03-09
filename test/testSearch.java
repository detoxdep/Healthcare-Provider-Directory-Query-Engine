package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import Doctor;
import Search;

public class testSearch {

    /**
     * Tests that the CSV file is found and loaded into the doctor list.
     * This ensures the application meets the requirement of storing and 
     * accessing provider data locally.
     */
    @Test
    public void testSearchLoadData() {
        ArrayList<Doctor> doctors = Search.search();
        
        // Ensure the list is not null and contains data
        assertNotNull("Doctor list should be initialized", doctors);
        
        // Validates the scope of 100-200 fictional physicians [cite: 9]
        assertTrue("Dataset should contain at least 100 doctors", doctors.size() >= 100);
    }

    /**
     * Tests the parsing accuracy of a single Doctor record.
     * Verifies that complex fields like specialization and insurance are 
     * correctly split and stored[cite: 11, 12].
     */
    @Test
    public void testDoctorDataParsing() {
        ArrayList<Doctor> doctors = Search.search();
        
        if (!doctors.isEmpty()) {
            Doctor firstDoc = doctors.get(0);
            
            // Verify name fields from the CSV are populated [cite: 14]
            assertNotNull("First name should not be null", firstDoc.getFirstName());
            assertNotNull("Last name should not be null", firstDoc.getLastName());
            
            // Verify specialty and insurance fields for filtering [cite: 11, 12]
            assertTrue("Specialization array should have at least one entry", 
                       firstDoc.getSpecialization().length > 0);
            assertTrue("Accepted Insurance array should have at least one entry", 
                       firstDoc.getAcceptedInsurance().length > 0);
        }
    }
}
