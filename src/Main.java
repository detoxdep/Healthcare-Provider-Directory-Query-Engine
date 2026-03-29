import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        boolean flag = true;
        String insuranceProvider = null;
        // Call search() and get the list of doctors
        ArrayList<Doctor> doctors = Search.search();

        //Basic Menu
        while(flag) {
            System.out.println("----- MAIN MENU -----");
            System.out.println("1. Provide Insurance Information");
            System.out.println("2. Search Doctors");
            System.out.println("0. Exit Program");
            System.out.print("Enter choice: ");

            if(scn.hasNextInt()) {
                int choice = scn.nextInt();

                switch (choice) {
                    //Basic Insurance select
                    case 1:
                        System.out.println("----- CHOOSE PROVIDER -----");
                        System.out.println("1. Aetna");
                        System.out.println("2. BlueCross");
                        System.out.println("3. Cigna");
                        System.out.println("4. Humana");
                        System.out.println("5. Kaiser");
                        System.out.println("6. UnitedHealth");
                        int insurance = scn.nextInt();
                        switch (insurance) {
                            case 1:
                                insuranceProvider = "Aetna";
                                break;
                            case 2:
                                insuranceProvider = "BlueCross";
                                break;
                            case 3:
                                insuranceProvider = "Cigna";
                                break;
                            case 4:
                                insuranceProvider = "Humana";
                                break;
                            case 5:
                                insuranceProvider = "Kaiser";
                                break;
                            case 6:
                                insuranceProvider = "UnitedHealth";
                                break;
                            default:
                                System.out.println("Invalid Option.");
                        }
                        System.out.println("Provider Set As: " + insuranceProvider);
                        break;

                    case 2:
                        System.out.println("Searching Doctors Based on Insurance...\n");
                        if(insuranceProvider == null){
                            System.out.println("No Insurance Provided.");
                            break;
                        }
                        else {
                            for (Doctor d : doctors) {
                                //Prints All Doctors that have insuranceProvider
                                for(String provider : d.acceptedInsurance) {
                                    if(provider.trim().equalsIgnoreCase(insuranceProvider.trim())){
                                        System.out.println(d.getFirstName() + " " + d.getLastName() + ", Address: " + d.getPrimaryAddress());
                                    }
                                }
                                // System.out.println(d);
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid Option.");
                }
            }


        }

    }
}