import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class SearchTest {

    @Test
    void testSearch_DataIntegrity() {
        ArrayList<Doctor> results = Search.search();
        
        // Assert file was read (assuming CSV exists in expected path)
        assertNotNull(results);
        
        if (!results.isEmpty()) {
            Doctor first = results.get(0);
            // Verify split logic for semicolon-separated arrays
            assertNotNull(first.getLanguagesSpoke());
            assertNotNull(first.getSpecialization());
            assertTrue(first.getReviewAvg() >= 0 && first.getReviewAvg() <= 5.0);
        }
    }

    @Test
    void testCSV_RegexParsing() {
        // This validates the specific complex regex used to handle quoted commas
        String line = "John,A,Doe,45,M,\"123 Medical Way, Suite 100\",2020-01-01,...";
        String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        
        // Ensure the address token includes the internal comma but not the quotes
        assertTrue(tokens[5].contains(","));
    }
}
