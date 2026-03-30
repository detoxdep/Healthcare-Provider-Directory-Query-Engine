import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        boolean flag = true;
        String insuranceProvider = null;

        // Call search() and get the list of doctors
        ArrayList<Doctor> doctors = Search.search();

        // Create a patient object
        Patient patient = new Patient("John", 'm', "Doe", 30, 'M', "12345", "123 Main St", "password");
        String patientZip = null; // Store patient zip code for distance calculations

        // Basic Menu
        while (flag) {
            System.out.println("----- MAIN MENU -----");
            System.out.println("1. Provide Insurance Information");
            System.out.println("2. Search Doctors");
            System.out.println("3. Enter Zipcode");
            System.out.println("0. Exit Program");
            System.out.print("Enter choice: ");

            if (scn.hasNextInt()) {
                int choice = scn.nextInt();
                scn.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("----- CHOOSE PROVIDER -----");
                        System.out.println("1. Aetna");
                        System.out.println("2. BlueCross");
                        System.out.println("3. Cigna");
                        System.out.println("4. Humana");
                        System.out.println("5. Kaiser");
                        System.out.println("6. UnitedHealth");
                        int insurance = scn.nextInt();
                        scn.nextLine(); // consume newline
                        switch (insurance) {
                            case 1 -> insuranceProvider = "Aetna";
                            case 2 -> insuranceProvider = "BlueCross";
                            case 3 -> insuranceProvider = "Cigna";
                            case 4 -> insuranceProvider = "Humana";
                            case 5 -> insuranceProvider = "Kaiser";
                            case 6 -> insuranceProvider = "UnitedHealth";
                            default -> System.out.println("Invalid Option.");
                        }
                        if (insuranceProvider != null) {
                            System.out.println("Provider Set As: " + insuranceProvider);
                        }
                        break;

                    case 2:
                    
                        if (insuranceProvider == null) {
                            System.out.println("No Insurance Provided.");
                            break;
                        }

                        Filter filter = new Filter();
                        Integer maxDistance = null;
                        boolean inFilterMenu = true;

                        while (inFilterMenu) {
                            System.out.println("\n----- FILTER MENU -----");
                            System.out.println("1. Set Maximum Distance");
                            System.out.println("2. Accepting New Patients");
                            System.out.println("3. Languages Spoken");
                            System.out.println("4. Specializations");
                            System.out.println("5. Minimum Review Average");
                            System.out.println("6. Perform Search");
                            System.out.println("0. Back to Main Menu");
                            System.out.print("Enter choice: ");

                            String filterChoice = scn.nextLine().trim();

                            switch (filterChoice) {
                                case "1":
                                    System.out.print("Enter maximum distance in miles (or press Enter to skip): ");
                                    String input = scn.nextLine().trim();
                                    if (!input.isEmpty()) maxDistance = Integer.parseInt(input);
                                    break;

                                case "2":
                                    System.out.print("Require accepting new patients? (yes/no): ");
                                    String ans = scn.nextLine().trim().toLowerCase();
                                    filter.setAcceptsNewPatients(ans.equals("yes"));
                                    break;

                                case "3":
                                    System.out.print("Enter languages spoken separated by comma: ");
                                    String[] langs = scn.nextLine().trim().split(",");
                                    filter.setLanguages(Arrays.asList(langs));
                                    break;

                                case "4":
                                    System.out.print("Enter specializations separated by comma: ");
                                    String[] specs = scn.nextLine().trim().split(",");
                                    filter.setSpecializations(Arrays.asList(specs));
                                    break;

                                case "5":
                                    System.out.print("Enter minimum review average: ");
                                    String review = scn.nextLine().trim();
                                    if (!review.isEmpty()) filter.setMinReviewAvg(Float.parseFloat(review));
                                    break;

                                case "6":
                                    System.out.println("\nSearching doctors...\n");
                                    for (Doctor d : doctors) {
                                        // Primary address
                                        if (d.getPrimaryAddress() != null) {
                                            Distance distPrimary = new Distance(
                                                    patientZip,
                                                    d.getZip(d.getPrimaryAddress())
                                            );
                                            double milesPrimary = distPrimary.calculateHaversine();
                                            if (filter.applyFilters(d, (int) milesPrimary, maxDistance)) {
                                                System.out.println(d);
                                            }
                                        }
                                        // Secondary address
                                        if (d.getSecondaryAddress() != null) {
                                            Distance distSecondary = new Distance(
                                                    patientZip,
                                                    d.getZip(d.getSecondaryAddress())
                                            );
                                            double milesSecondary = distSecondary.calculateHaversine();
                                            if (filter.applyFilters(d, (int) milesSecondary, maxDistance)) {
                                                System.out.println(d);
                                            }
                                        }
                                    }
                                    break;

                                case "0":
                                    inFilterMenu = false;
                                    break;

                                default:
                                    System.out.println("Invalid choice.");
                                    break;
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Enter your zipcode: ");
                        patientZip = scn.nextLine().trim();
                    
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        flag = false;
                        break;

                    default:
                        System.out.println("Invalid Option.");
                }
            } else {
                scn.nextLine(); // consume invalid input
            }
        }

        scn.close();
    }
}