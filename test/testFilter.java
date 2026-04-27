import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class testFilter {

    private Doctor sampleDoctor;
    private Filter filter;

    @BeforeEach
    public void setUp() {
        // Sample Doctor to test against
        sampleDoctor = new Doctor(
            "Alice",
            'M',
            "Smith",
            45,
            'F',
            "123456789",
            null,                        // enumerationDate
            new String[]{"English", "French"}, // languages
            new String[]{"Cardiology", "Neurology"}, // specializations
            "123 Main St",               // primaryAddress
            "456 Side St",               // secondaryAddress
            new String[]{"Aetna", "BlueCross"}, // insurance
            true,                        // newPatients
            "https://example.com",       // URL
            "111-222-3333",              // primaryPhone
            "444-555-6666",              // secondaryPhone
            4.5f                         // reviewAvg
        );

        filter = new Filter();
    }

    @Test
    public void testAcceptsNewPatientsFilter() {
        filter.setAcceptsNewPatients(true);
        assertTrue(filter.applyFilters(sampleDoctor, 5, 10));

        filter.setAcceptsNewPatients(false);
        assertFalse(filter.applyFilters(sampleDoctor, 5, 10));
    }

    @Test
    public void testInsuranceFilter() {
        filter.setInsurance("Aetna");
        assertTrue(filter.applyFilters(sampleDoctor, 0, null));

        filter.setInsurance("Cigna");
        assertFalse(filter.applyFilters(sampleDoctor, 0, null));
    }

    @Test
    public void testLanguagesFilter() {
        filter.setLanguages(Arrays.asList("French"));
        assertTrue(filter.applyFilters(sampleDoctor, 0, null));

        filter.setLanguages(Arrays.asList("Spanish"));
        assertFalse(filter.applyFilters(sampleDoctor, 0, null));
    }

    @Test
    public void testSpecializationsFilter() {
        filter.setSpecializations(Arrays.asList("Neurology"));
        assertTrue(filter.applyFilters(sampleDoctor, 0, null));

        filter.setSpecializations(Arrays.asList("Dermatology"));
        assertFalse(filter.applyFilters(sampleDoctor, 0, null));
    }

    @Test
    public void testMinReviewAvgFilter() {
        filter.setMinReviewAvg(4.0f);
        assertTrue(filter.applyFilters(sampleDoctor, 0, null));

        filter.setMinReviewAvg(5.0f);
        assertFalse(filter.applyFilters(sampleDoctor, 0, null));
    }

    @Test
    public void testDistanceFilter() {
        // Doctor within max distance
        assertTrue(filter.applyFilters(sampleDoctor, 5, 10));
        // Doctor outside max distance
        assertFalse(filter.applyFilters(sampleDoctor, 15, 10));
    }

    @Test
    public void testCombinedFilters() {
        filter.setAcceptsNewPatients(true);
        filter.setInsurance("BlueCross");
        filter.setLanguages(Arrays.asList("English"));
        filter.setSpecializations(Arrays.asList("Cardiology"));
        filter.setMinReviewAvg(4.0f);

        // Should pass all filters
        assertTrue(filter.applyFilters(sampleDoctor, 5, 10));

        // Change one to fail
        filter.setMinReviewAvg(5.0f);
        assertFalse(filter.applyFilters(sampleDoctor, 5, 10));
    }
}
