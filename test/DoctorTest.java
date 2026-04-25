import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class DoctorTest {

	@Test
	public void testDoctorConstructorAndToString() {
	    // 1. Setup data matching your types
	    String[] langs = {"English", "Spanish"};
	    String[] specs = {"Cardiology"};
	    String[] insurance = {"Aetna"};
	    java.util.Date enumDate = new java.util.Date();
	    
	    // 2. Instantiate with the exact order and types you provided
	    Doctor d = new Doctor(
	        "Jane",           // firstName (String)
	        'Q',              // middleInitial (char)
	        "Smith",          // lastName (String)
	        45,               // age (int)
	        'F',              // sex (char)
	        "1234567890",     // NPI (String)
	        enumDate,         // enumerationDate (Date)
	        langs,            // languagesSpoke (String[])
	        specs,            // specialization (String[])
	        "123 Main St",    // primaryAddress (String - passed to super)
	        "456 Side St",    // secondaryAddress (String)
	        insurance,        // acceptedInsurance (String[])
	        true,             // newPatients (boolean)
	        "http://doc.com", // URL (String)
	        "555-0101",       // primaryPhone (String)
	        "555-0102",       // secondaryPhone (String)
	        4.8f              // reviewAvg (float - note the 'f')
	    );

	    // 3. Trigger the toString() method for coverage
	    String result = d.toString();
	    
	    // 4. Verification
	    assertNotNull(result);
	    assertTrue(result.contains("Doctor{"));
	    assertTrue(result.contains("reviewAvg=4.8"));
	    assertTrue(result.contains("English;Spanish")); // Tests the String.join logic
	}
	
	@Test
	public void testDoctorSettersAndGetters() {
	    // 1. Create a "blank" or initial doctor (use any valid constructor call)
	    String[] initialArray = {"None"};
	    Doctor d = new Doctor("Test", 'T', "Doc", 30, 'M', "000", new java.util.Date(), 
	                          initialArray, initialArray, "Addr1", "Addr2", 
	                          initialArray, false, "url", "000", "000", 0.0f);

	    // 2. Test every setter and getter highlighted in red
	    
	    // NPI
	    d.setNPI("9999999999");
	    assertEquals("9999999999", d.getNPI());

	    // Enumeration Date
	    java.util.Date newDate = new java.util.Date();
	    d.setEnumerationDate(newDate);
	    assertEquals(newDate, d.getEnumerationDate());

	    // URL
	    d.setURL("http://newdocs.com");
	    assertEquals("http://newdocs.com", d.getURL());

	    // Phones
	    d.setPrimaryPhone("111-2222");
	    assertEquals("111-2222", d.getPrimaryPhone());
	    d.setSecondaryPhone("333-4444");
	    assertEquals("333-4444", d.getSecondaryPhone());

	    // Review Avg (Note the 'f' for float)
	    d.setReviewAvg(4.9f);
	    assertEquals(4.9f, d.getReviewAvg(), 0.001);

	    // Boolean newPatients
	    d.setNewPatients(true);
	    assertTrue(d.isNewPatients() || d.newPatients); // depending on your getter name

	    // Secondary Address
	    d.setSecondaryAddress("Suite 100");
	    assertEquals("Suite 100", d.getSecondaryAddress());

	    // Array Setters (Languages, Specialization, Insurance)
	    String[] newLangs = {"French", "German"};
	    d.setLanguagesSpoke(newLangs);
	    assertArrayEquals(newLangs, d.languagesSpoke); 

	    String[] newSpecs = {"Neurology"};
	    d.setSpecialization(newSpecs);
	    assertArrayEquals(newSpecs, d.specialization);

	    String[] newInsurance = {"UnitedHealth"};
	    d.setAcceptedInsurance(newInsurance);
	    assertArrayEquals(newInsurance, d.acceptedInsurance);
	}
}
