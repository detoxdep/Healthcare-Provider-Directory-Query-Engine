import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


public class GUI extends Application {

    private String patientZip = null;
    private String insuranceProvider = null;

    private Filter filter = new Filter();
    private Integer maxDistance = null;

    private ArrayList<Doctor> doctors;

    @Override
    public void start(Stage stage) {
        doctors = Search.search(); // Load CSV once

        stage.setTitle("Doctor Finder");

        Scene homeScene = buildHomeScene(stage);
        stage.setScene(homeScene);
        stage.show();
    }

    // ---------------------------------------------------------
    // HOME SCREEN
    // ---------------------------------------------------------
    private Scene buildHomeScene(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Doctor Finder");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField zipField = new TextField();
        zipField.setPromptText("Enter your ZIP code");

        ComboBox<String> insuranceBox = new ComboBox<>();
        insuranceBox.getItems().addAll("Aetna", "BlueCross", "Cigna", "Humana", "Kaiser", "UnitedHealth");
        insuranceBox.setPromptText("Select Insurance Provider");

        Button nextBtn = new Button("Continue to Filters");
        nextBtn.setOnAction(e -> {
            patientZip = zipField.getText().trim();
            insuranceProvider = insuranceBox.getValue();

            if (patientZip.isEmpty() || insuranceProvider == null) {
                showAlert("Missing Information", "Please enter ZIP and insurance provider.");
                return;
            }

            filter.setInsurance(insuranceProvider);
            stage.setScene(buildFilterScene(stage));
        });

        root.getChildren().addAll(title, zipField, insuranceBox, nextBtn);
        return new Scene(root, 400, 300);
    }

    // ---------------------------------------------------------
    // FILTER SCREEN
    // ---------------------------------------------------------
    private Scene buildFilterScene(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Filters");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField maxDistField = new TextField();
        maxDistField.setPromptText("Max Distance (optional)");

        CheckBox newPatientsBox = new CheckBox("Accepting New Patients");

        TextField languagesField = new TextField();
        languagesField.setPromptText("Languages (comma-separated)");

        TextField specsField = new TextField();
        specsField.setPromptText("Specializations (comma-separated)");

        TextField reviewField = new TextField();
        reviewField.setPromptText("Minimum Review Average");

        Button searchBtn = new Button("Search Doctors");
        searchBtn.setOnAction(e -> {
            filter = new Filter();
            // Apply filters
            if (!maxDistField.getText().trim().isEmpty())
                maxDistance = Integer.parseInt(maxDistField.getText().trim());

            filter.setAcceptsNewPatients(newPatientsBox.isSelected());

            if (!languagesField.getText().trim().isEmpty())
                filter.setLanguages(Arrays.asList(languagesField.getText().split(",")));

            if (!specsField.getText().trim().isEmpty())
                filter.setSpecializations(Arrays.asList(specsField.getText().split(",")));

            if (!reviewField.getText().trim().isEmpty())
                filter.setMinReviewAvg(Float.parseFloat(reviewField.getText().trim()));

            stage.setScene(buildResultsScene(stage));
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> stage.setScene(buildHomeScene(stage)));

        root.getChildren().addAll(
                title, maxDistField, newPatientsBox,
                languagesField, specsField, reviewField,
                searchBtn, backBtn
        );

        return new Scene(root, 450, 400);
    }

    // ---------------------------------------------------------
    // RESULTS SCREEN
    // ---------------------------------------------------------
    private Scene buildResultsScene(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Search Results");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox resultsBox = new VBox(10);

        for (Doctor d : doctors) {
            boolean matched = false;

            // Primary address

            if (d.getPrimaryAddress() != null) {
                Distance dist = new Distance(patientZip, d.getZip(d.getPrimaryAddress()));
                double miles = dist.calculateHaversine();

                if (filter.applyFilters(d, (int) miles, maxDistance)) {
                    resultsBox.getChildren().add(makeDoctorCard(d, miles, "Primary"));
                    matched = true;
                }
            }

            // Secondary address
            if (!matched && d.getSecondaryAddress() != null) {
                Distance dist = new Distance(patientZip, d.getZip(d.getSecondaryAddress()));
                double miles = dist.calculateHaversine();

                if (filter.applyFilters(d, (int) miles, maxDistance)) {
                    resultsBox.getChildren().add(makeDoctorCard(d, miles, "Secondary"));
                }
            }

        }

        ScrollPane scroll = new ScrollPane(resultsBox);
        scroll.setFitToWidth(true);

        Button backBtn = new Button("Back to Filters");
        backBtn.setOnAction(e -> {filter = new Filter(); maxDistance = null; stage.setScene(buildFilterScene(stage));});

        root.getChildren().addAll(title, scroll, backBtn);
        return new Scene(root, 600, 500);
    }

    // ---------------------------------------------------------
    // DOCTOR CARD UI
    // ---------------------------------------------------------
    private HBox makeDoctorCard(Doctor d, double miles, String addrType) {
        VBox box = new VBox(5);

        String distanceText;

        if(miles == -1) {
            distanceText = "Distance: ZIP code not found";
        } else {
            distanceText = String.format("Distance(%s): %.2fmiles", addrType, miles);
        }


        Label name = new Label(d.getFirstName() + " " + d.getLastName());
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label spec = new Label("Specializations: " + String.join(", ", d.getSpecialization()));
        Label lang = new Label("Languages: " + String.join(", ", d.getLanguagesSpoke()));
        Label rev = new Label("Review Avg: " + d.getReviewAvg());
       // Label dist = new Label("Distance (" + addrType + "): " + (int)miles + " miles");
        Label distLabel = new Label(distanceText);

        box.getChildren().addAll(name, spec, lang, rev, distLabel);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: black; -fx-border-radius: 5;");

        return new HBox(box);
    }

    // ---------------------------------------------------------
    // ALERT HELPER
    // ---------------------------------------------------------
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
