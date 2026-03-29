import java.io.File;
import java.util.Scanner;

public class Distance {
    private String patientZip;
    private String doctorZip;

    public Distance(String patientZip, String doctorZip) {
        this.patientZip = patientZip;
        this.doctorZip = doctorZip;
    }

    /**
     * Scans the florida_zipcodes.csv to find latitude and longitude.
     * Assumes CSV format: zip, latitude, longitude
     */
    public double[] convertLatLong(String zipCode) {
        double[] latLong = new double[2];
        // Path to your ZIP database
        File file = new File("src/main/resources/florida_zipcodes.csv");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].trim().equals(zipCode.trim())) {
                    latLong[0] = Double.parseDouble(parts[1]); // Latitude
                    latLong[1] = Double.parseDouble(parts[2]); // Longitude
                    return latLong;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading ZIP database: " + e.getMessage());
        }
        return null; // Return null if ZIP not found
    }

    /**
     * Calculates distance in miles using the Haversine formula.
     */
    public double calculateHaversine() {
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
        return R * c; // Distance in miles
    }
}
