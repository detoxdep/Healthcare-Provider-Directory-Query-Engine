package src.src;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Call search() and get the list of doctors
       ArrayList<Doctor> doctors = Search.search();

        // Print all doctors
        for (Doctor d : doctors) {
            System.out.println(d.getZip(d.getPrimaryAddress()));
           // System.out.println(d);
        }
    }
}