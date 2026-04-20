import java.io.File;
import java.util.Scanner;

public class Distance {
    private String patientZip;
    private String doctorZip;
    // Updated path to be more flexible or match standard project structures
    private static final String FILE_PATH = "florida_zipcodes.csv";

    public Distance(String patientZip, String doctorZip) {
        this.patientZip = patientZip;
        this.doctorZip = doctorZip;
    }

    /**
     * Scans the florida_zipcodes.csv to find latitude and longitude.
     * Skips the header row and matches the ZIP code.
     */
    public double[] convertLatLong(String zipCode) {
        if (zipCode == null || zipCode.isEmpty()) return null;

        File file = new File(FILE_PATH);
        try (Scanner scanner = new Scanner(file)) {
            // SKIP HEADER: Checks if there is a line and consumes it
            if (scanner.hasNextLine()) {
                scanner.nextLine(); 
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split by comma, but handle potential spaces
                String[] parts = line.split(",");
                
                if (parts.length >= 3) {
                    String csvZip = parts[0].trim();
                    if (csvZip.equals(zipCode.trim())) {
                        double lat = Double.parseDouble(parts[1].trim());
                        double lon = Double.parseDouble(parts[2].trim());
                        return new double[]{lat, lon};
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading ZIP database: " + e.getMessage());
        }
        return null; 
    }

    /**
     * Calculates distance in miles using the Haversine formula.
     */
    public double calculateHaversine() {
        // Validation: if either ZIP is missing, we can't calculate
        if (patientZip == null || doctorZip == null) {
            return -1;
        }

        double[] pCoords = convertLatLong(this.patientZip);
        double[] dCoords = convertLatLong(this.doctorZip);

        if (pCoords == null || dCoords == null) return -1;

        double lat1 = pCoords[0];
        double lon1 = pCoords[1];
        double lat2 = dCoords[0];
        double lon2 = dCoords[1];

        double R = 3958.8; // Radius of the Earth in miles
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; 
    }
}