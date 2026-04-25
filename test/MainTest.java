import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainTest {

    @Test
    public void testFullMainFlow() {
        // This string simulates a user doing the following:
        // 1 -> 1: Select Insurance (Aetna)
        // 3 -> 32256: Enter Zipcode
        // 2: Enter Search/Filter Menu
        //    1 -> 10: Set Max Distance
        //    2 -> yes: Set New Patients
        //    3 -> English: Set Language
        //    4 -> Cardiology: Set Specialization
        //    5 -> 4.0: Set Review Avg
        //    6: Perform Search
        //    0: Back to Main Menu
        // 0: Exit Program
        String simulatedInput = "1\n1\n" + 
                                "3\n32256\n" + 
                                "2\n1\n10\n2\nyes\n3\nEnglish\n4\nCardiology\n5\n4.0\n6\n0\n" + 
                                "0\n";

        // Feed this string to System.in
        InputStream savedIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            // Run the main method
            Main.main(new String[0]);
        } finally {
            // Restore System.in for other tests
            System.setIn(savedIn);
        }
    }

    @Test
    public void testInvalidInputs() {
        // Test what happens with invalid choices or skipping insurance
        // 99: Invalid Main Menu
        // 2: Search without insurance (triggers error)
        // 1 -> 99: Invalid Insurance
        // 0: Exit
        String simulatedInput = "99\n2\n1\n99\n0\n";

        InputStream savedIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            Main.main(new String[0]);
        } finally {
            System.setIn(savedIn);
        }
    }
    
    @Test
    public void testEverythingInMain() {
        // This input string is a 'speedrun' of your entire program:
        String simulatedInput = 
            "1\n1\n" +      // Case 1: Insurance -> Aetna
            "1\n2\n" +      // Insurance -> BlueCross
            "1\n3\n" +      // Insurance -> Cigna
            "1\n4\n" +      // Insurance -> Humana
            "1\n5\n" +      // Insurance -> Kaiser
            "1\n6\n" +      // Insurance -> UnitedHealth
            "1\n99\n" +     // Insurance -> Invalid Option
            "3\n99999\n" +  // Case 3: Set an invalid/non-existent ZIP to force the -1 distance error
            "2\n" +         // Case 2: Enter Filter Menu
            "6\n" +         // Filter Case 6: Perform search (triggers the "Could not be calculated" branch)
            "99\n" +        // Filter Menu: Invalid choice (hits default: Invalid choice)
            "0\n" +         // Filter Menu: Exit to main
            "notANumber\n"+ // Hits the final scn.nextLine() "invalid input" branch
            "0\n";         // Case 0: Exit program

        InputStream savedIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            Main.main(new String[0]);
        } finally {
            System.setIn(savedIn);
        }
    }
}
