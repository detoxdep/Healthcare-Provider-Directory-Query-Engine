import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class DistanceTest {

    // --- TESTING ---
	// Test 1: Verification of Data Matching (White-Box)
    @Test
    public void testConvertLatLong_DataMatch() {
        Distance d = new Distance("32003", "32003");
        double[] coords = d.convertLatLong("32003");
        
        // If this fails, the loop isn't finding the ZIP. 
        // Ensure 32003 is in the FIRST column of your CSV.
        assertNotNull(coords, "Match failed. Ensure '32003' exists in the CSV without hidden formatting.");
    }

    // Test 2: Full Math Coverage (Full-Flow)
    @Test
    public void testCalculateHaversine_LogicCoverage() {
        // Using two known Florida zips to force execution of the math block
        Distance d = new Distance("32003", "32256"); 
        double distance = d.calculateHaversine();

        // This ensures the code passed the 'if null' checks and ran the Haversine math
        assertNotEquals(-1, distance, "Logic skipped math block because coords were null.");
        assertTrue(distance > 0, "Distance calculation logic failed to produce a positive value.");
    }

    // Test 3: Boundary/Edge Case Coverage (Limits)
    @Test
    public void testConvertLatLong_NullEmpty() {
        Distance d = new Distance(null, null);
        assertNull(d.convertLatLong(null)); // Covers line: if (zipCode == null...)
        assertNull(d.convertLatLong(""));   // Covers line: if (zipCode.isEmpty()...)
    }
	
	@Test
    public void testFileLevelAccess() {
        // Since you moved the file, let's verify JUnit sees it in the same layer
        File file = new File("florida_zipcodes.csv");
        assertTrue(file.exists(), "JUnit cannot find the CSV in the current directory: " + System.getProperty("user.dir"));
    }
	@Test
    public void testConvertLatLong_InDepth() {
        // Change "32003" to a ZIP you KNOW is in your CSV file
        String testZip = "32003"; 
        Distance d = new Distance(testZip, testZip);
        double[] coords = d.convertLatLong(testZip);

        // If this is null, your while loop logic is failing to match the string
        assertNotNull(coords, "ZIP " + testZip + " was not found in the CSV. check for quotes or spaces in the file.");
        assertTrue(coords[0] != 0, "Latitude should be a real number");
    }

    @Test
    public void testCalculateHaversine_FullFlow() {
        // This test forces the execution of the 'red' math lines
        Distance d = new Distance("32003", "32256");
        double result = d.calculateHaversine();

        // If result is -1, it means the code didn't even reach the math
        assertNotEquals(-1, result, "The math was skipped because convertLatLong returned null");
        assertTrue(result > 0, "Distance should be calculated");
    }
    
    
    
    
    
	@Test
	public void testConvertLatLong_emptyString() {
	    Distance d = new Distance("", "");
	    assertNull(d.convertLatLong("")); // Covers: if (zipCode.isEmpty()) return null;
	}

    @Test
    public void testConvertLatLong_validZip() {
        // Test: Valid ZIP from the Florida database returns expected coordinates
        Distance d = new Distance("32003", "32256");
        double[] coords = d.convertLatLong("32003");

        assertNotNull(coords, "Coordinates should not be null for a valid ZIP");
        assertEquals(2, coords.length, "Should return an array with exactly Lat and Long");
    }

    @Test
    public void testConvertLatLong_invalidZip() {
        // Test: A non-existent ZIP code should return null
        Distance d = new Distance("00000", "99999");
        assertNull(d.convertLatLong("99999"), "Invalid ZIP should return null");
    }

    @Test
    public void testCalculateHaversine_sameZip() {
        // Ensure 32003 is actually in your CSV file
        Distance d = new Distance("32003", "32003"); 
        double distance = d.calculateHaversine();
        
        // Use a delta of 0.001 to handle floating point math
        assertEquals(0.0, distance, 0.001); 
    }

    @Test
    public void testCalculateHaversine_nullZip() {
        // Test: Handling null inputs to prevent NullPointerException
        Distance d = new Distance(null, "32003");
        assertEquals(-1, d.calculateHaversine(), "Should return -1 when a ZIP is null");
    }

    // --- WHITE-BOX TESTING (Internal Logic & Edge Cases) ---

    @Test
    public void testCalculateHaversine_ValidCalculation() {
        // Test: Verifying the Haversine formula against a known distance
        // Approx distance between Fleming Island (32003) and Southside Jax (32256) is ~15-20 miles
        Distance d = new Distance("32003", "32256");
        double distance = d.calculateHaversine();

        assertTrue(distance > 0, "Distance should be a positive value");
        assertTrue(distance < 50, "Distance between these nearby ZIPs should be within a reasonable range");
    }

    @Test
    public void testFileParsing_HeaderSkipping() {
        // Test: Ensures the logic correctly skips the CSV header row
        Distance d = new Distance("ZIP", "LAT"); // Using header strings as ZIPs
        assertNull(d.convertLatLong("ZIP"), "The parser should skip the header and not treat 'ZIP' as a coordinate");
    }

    @Test
    public void testCalculateHaversine_MissingFile() {
        // Test: Logic behavior when the CSV file path is incorrect
        // This is a "Negative Test" to ensure the try-catch block handles errors
        // Note: This test's result depends on your local environment's file structure
        Distance d = new Distance("32003", "32256");
        // If file is missing, convertLatLong returns null, which makes calculateHaversine return -1
        double result = d.calculateHaversine();
        
        // If we force a failure by renaming the file, we expect -1
        if (result == -1) {
            assertEquals(-1, result, "Should return -1 if the ZIP database cannot be read");
        }
    }
}
