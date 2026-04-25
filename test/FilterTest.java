import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class FilterTest {
    private Filter filter;
    private Doctor doc;

    @BeforeEach
    void setUp() {
        filter = new Filter();
        // Create a standard test doctor based on your Doctor constructor
        doc = new Doctor("Jane", 'Q', "Smith", 45, 'F', "12345", 
            new Date(), new String[]{"English", "Spanish"}, 
            new String[]{"Cardiology"}, "123 Main St 32003", null, 
            new String[]{"Aetna", "Cigna"}, true, "http://doc.com", 
            "555-0101", "555-0102", 4.5f);
    }
    
    @Test
    public void testApplyFilters_AllRejections() {
        // 1. Create a Doctor to test against
        String[] docLangs = {"English"};
        String[] docSpecs = {"Cardiology"};
        String[] docIns = {"Aetna"};
        Doctor d = new Doctor("Jane", 'Q', "Smith", 45, 'F', "123", new java.util.Date(), 
                              docLangs, docSpecs, "Addr", "Addr", docIns, 
                              true, "url", "000", "000", 3.5f);

        // 2. Initialize the Filter using the default (empty) constructor
        Filter filter = new Filter();

        // 3. Test Distance Rejection (The maxDistance logic)
        // This hits: if (maxDistance != null && distance > maxDistance)
        assertFalse(filter.applyFilters(d, 50, 10), "Should fail because 50 > 10");

        // 4. Test Review Average Rejection
        filter.setMinReviewAvg(4.5f); 
        assertFalse(filter.applyFilters(d, 5, 10), "Should fail because 3.5 < 4.5");

        // 5. Test Insurance Rejection
        filter.setMinReviewAvg(null); // Reset review filter
        filter.setInsurance("Blue Cross");
        assertFalse(filter.applyFilters(d, 5, 10), "Should fail because Blue Cross != Aetna");
    }

    @Test
    public void testFilterGettersAndSetters() {
        Filter f = new Filter();
        java.util.List<String> langs = java.util.Arrays.asList("Spanish");
        
        // Testing the setters/getters that were red
        f.setLanguages(langs);
        assertEquals(langs, f.getLanguages());
        
        f.setAcceptsNewPatients(true);
        assertTrue(f.getAcceptsNewPatients());
    }

    @Test
    void testApplyFilters_AllMatch() {
        filter.setInsurance("Aetna");
        filter.setAcceptsNewPatients(true);
        filter.setMinReviewAvg(4.0f);
        // Should pass all checks
        assertTrue(filter.applyFilters(doc, 10, 50));
    }

    @Test
    void testApplyFilters_InsuranceMismatch() {
        filter.setInsurance("BlueCross"); // Doc only has Aetna/Cigna
        assertFalse(filter.applyFilters(doc, 5, 20));
    }

    @Test
    void testApplyFilters_DistanceExceeded() {
        // Black-box: Testing the boundary
        assertFalse(filter.applyFilters(doc, 51, 50));
    }

    @Test
    void testApplyFilters_LanguageOverlap() {
        // White-box: Nested loop check
        filter.setLanguages(Arrays.asList("French", "Spanish")); // Matches Spanish
        assertTrue(filter.applyFilters(doc, 5, 100));
        
        filter.setLanguages(Collections.singletonList("German")); // No match
        assertFalse(filter.applyFilters(doc, 5, 100));
    }

    @Test
    void testApplyFilters_NullFilterValues() {
        // Logic check: Null filters should be ignored (return true)
        Filter emptyFilter = new Filter();
        assertTrue(emptyFilter.applyFilters(doc, 1000, null));
    }
    
    @Test
    public void testApplyFilters_RemainingRedLines() {
        // 1. Create a Doctor who has specific traits
        String[] docSpecs = {"Cardiology"};
        Doctor d = new Doctor("Jane", 'Q', "Smith", 45, 'F', "123", new java.util.Date(), 
                              new String[]{"English"}, docSpecs, "Addr", "Addr", 
                              new String[]{"Aetna"}, true, "url", "000", "000", 4.5f);

        Filter filter = new Filter();

        // --- TEST 1: The "New Patients" rejection ---
        // Doctor is TRUE, we set filter to FALSE. This hits: 
        // if (acceptsNewPatients != null && doctor.isNewPatients() != acceptsNewPatients)
        filter.setAcceptsNewPatients(false);
        assertFalse(filter.applyFilters(d, 5, 10));

        // --- TEST 2: The Specialization Loops & !match rejection ---
        // Reset the first filter so it doesn't block the next one
        filter.setAcceptsNewPatients(null); 
        
        // Set filter to "Neurology" (Doctor is "Cardiology")
        java.util.List<String> searchSpecs = new java.util.ArrayList<>();
        searchSpecs.add("Neurology");
        filter.setSpecializations(searchSpecs);
        
        // This executes the double-for loop, finds NO match, and hits:
        // if (!match) return false;
        assertFalse(filter.applyFilters(d, 5, 10));

        // --- TEST 3: The Getters & Setters ---
        // Simply calling these triggers the "return" lines in your getters
        filter.setInsurance("Medicare");
        assertEquals("Medicare", filter.getInsurance());
        assertEquals(searchSpecs, filter.getSpecializations());
        
        filter.setMinReviewAvg(4.0f);
        assertEquals(4.0f, filter.getMinReviewAvg(), 0.01);
    }
}
