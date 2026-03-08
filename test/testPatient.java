package test;

import static org.junit.Assert.*;
import org.junit.Test;
// Importing the Patient class to verify profile and insurance logic
import Patient; 

public class testPatient {

    /**
     * Testing the Patient constructor and inheritance from Person.
     * Validates that basic info and insurance are stored correctly for the user profile.
     */
    @Test
    public void testPatientConstructorAndInheritance() {
        // Sample data matching your user_profile.csv format
        String firstName = "Jane";
        char middleInitial = 'D';
        String lastName = "Doe";
        int age = 28;
        char sex = 'F';
        String insurance = "BlueCross";
        String address = "123 Maple St, Orlando, FL 32801";
        String password = "securePassword123";

        // Create Patient object
        Patient testPatient = new Patient(firstName, middleInitial, lastName, 
                                          age, sex, insurance, address, password);

        // Test inherited Person fields
        assertEquals("First name should be Jane", firstName, testPatient.getFirstName());
        assertEquals("Last name should be Doe", lastName, testPatient.getLastName());
        assertEquals("ZIP should be extracted correctly", 32801, testPatient.getZip(address));

        // Test Patient-specific fields
        assertEquals("Insurance provider should be BlueCross", insurance, testPatient.getInsurance());
        assertEquals("Password should be stored correctly", password, testPatient.getPassword());
    }

    /**
     * Testing the ability to update insurance information.
     * This aligns with the Main Menu option "Provide Insurance Information".
     */
    @Test
    public void testSetInsurance() {
        Patient testPatient = new Patient("John", 'M', "Smith", 35, 'M', 
                                          "Aetna", "Address", "pass");
        
        testPatient.setInsurance("UnitedHealth");
        assertEquals("Insurance should be updated to UnitedHealth", 
                     "UnitedHealth", testPatient.getInsurance());
    }
}
