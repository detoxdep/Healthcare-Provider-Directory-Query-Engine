import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class testSearch {

    private ArrayList<Doctor> doctors;

    @BeforeEach
    public void setUp() {
        doctors = Search.search();
    }

    @Test
    public void testSearchLoadData() {
        assertNotNull(doctors, "Doctor list should be initialized");
        assertTrue(doctors.size() >= 100, "Dataset should contain at least 100 doctors");
    }

    @Test
    public void testDoctorDataParsing() {
        if (!doctors.isEmpty()) {
            Doctor firstDoc = doctors.get(0);

            assertNotNull(firstDoc.getFirstName(), "First name should not be null");
            assertNotNull(firstDoc.getLastName(), "Last name should not be null");

            assertTrue(firstDoc.getSpecialization().length > 0,
                       "Specialization array should have at least one entry");
            assertTrue(firstDoc.getAcceptedInsurance().length > 0,
                       "Accepted Insurance array should have at least one entry");
        }
    }
}
