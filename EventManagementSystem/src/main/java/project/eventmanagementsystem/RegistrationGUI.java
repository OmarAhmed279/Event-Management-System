package project.eventmanagementsystem;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Date;

public class RegistrationGUI {

    public static Scene createOrganizerRegistrationScene(Stage primaryStage) {
        return createRegistrationScene(primaryStage, "Organizer");
    }

    public static Scene createAttendeeRegistrationScene(Stage primaryStage) {
        return createRegistrationScene(primaryStage, "Attendee");
    }

    public static Scene createRegistrationScene(Stage primaryStage, String userType) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Title
        Label title = new Label(userType + " Registration");
        title.setFont(new Font(20));
        grid.add(title, 0, 0, 2, 1);

        // Username
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label usernameError = new Label();
        usernameError.setStyle("-fx-text-fill: red;");
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(usernameError, 1, 2);

        // Password
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label passwordError = new Label();
        passwordError.setStyle("-fx-text-fill: red;");
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(passwordError, 1, 4);

        // Date of Birth
        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();
        Label dobError = new Label();
        dobError.setStyle("-fx-text-fill: red;");
        grid.add(dobLabel, 0, 5);
        grid.add(dobPicker, 1, 5);
        grid.add(dobError, 1, 6);

        // Additional fields for Attendee
        VBox attendeeFields = new VBox(10);
        if ("Attendee".equals(userType)) {
            // Gender
            Label genderLabel = new Label("Gender (M/F):");
            TextField genderField = new TextField();
            Label genderError = new Label();
            genderError.setStyle("-fx-text-fill: red;");

            // Address
            Label addressLabel = new Label("Address:");
            TextField addressField = new TextField();

            // Interests
            Label interestLabel = new Label("Select Interest:");
            ComboBox<String> interestCombo = new ComboBox<>();
            Database.categories.forEach(cat ->
                    interestCombo.getItems().add(cat.getName()));

            attendeeFields.getChildren().addAll(
                    genderLabel, genderField, genderError,
                    addressLabel, addressField,
                    interestLabel, interestCombo
            );
            grid.add(attendeeFields, 0, 7, 2, 1);
        }

        // Submit Button
        Button submitBtn = new Button("Register");
        submitBtn.setOnAction(e -> {
            if (validateAndRegister(
                    usernameField, usernameError,
                    passwordField, passwordError,
                    dobPicker, dobError,
                    "Attendee".equals(userType) ? attendeeFields : null,
                    userType, primaryStage
            )) {
                // Registration successful
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.setContentText(userType + " account created successfully!");
                success.showAndWait();
                primaryStage.setScene(UserGUI.showHome());
            }
        });

        // Back Button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> primaryStage.setScene(UserGUI.showHome()));

        HBox buttonBox = new HBox(10, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 8, 2, 1);

        return new Scene(grid, 600, 600);
    }

    private static boolean validateAndRegister(
            TextField usernameField, Label usernameError,
            PasswordField passwordField, Label passwordError,
            DatePicker dobPicker, Label dobError,
            VBox attendeeFields, String userType, Stage primaryStage) {

        // Reset errors
        usernameError.setText("");
        passwordError.setText("");
        dobError.setText("");

        // Validate username
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            usernameError.setText("Username cannot be empty");
            return false;
        }
        if (!Character.isLetter(username.charAt(0))) {
            usernameError.setText("Must start with a letter");
            return false;
        }
        if (username.contains(" ")) {
            usernameError.setText("Cannot contain spaces");
            return false;
        }
        if (username.length() < 4 || username.length() > 20) {
            usernameError.setText("Must be 4-20 characters");
            return false;
        }
        if (Database.users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            usernameError.setText("Username already exists");
            return false;
        }

        // Validate password
        String password = passwordField.getText().trim();
        if (password.isEmpty()) {
            passwordError.setText("Password cannot be empty");
            return false;
        }
        if (password.length() < 4 || password.length() > 20) {
            passwordError.setText("Must be 4-20 characters");
            return false;
        }

        // Validate date of birth
        LocalDate dob = dobPicker.getValue();
        if (dob == null) {
            dobError.setText("Please select a date");
            return false;
        }
        if (dob.getYear() > 2015) {
            dobError.setText("You are too young");
            return false;
        }
        if (dob.getYear() < 1900) {
            dobError.setText("You are too old");
            return false;
        }
        Date dateOfBirth = Date.from(dob.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

        // Create user based on type
        if ("Organizer".equals(userType)) {
            User newUser = new Organizer(username, password, dateOfBirth);
            Database.users.add(newUser);
            Database.organizers.add((Organizer) newUser);
            return true;
        }
        else if ("Attendee".equals(userType)) {
            // Validate attendee fields
            TextField genderField = (TextField) attendeeFields.getChildren().get(1);
            TextField addressField = (TextField) attendeeFields.getChildren().get(3);
            ComboBox<String> interestCombo = (ComboBox<String>) attendeeFields.getChildren().get(6);

            String gender = genderField.getText().trim().toUpperCase();
            if (!gender.equals("M") && !gender.equals("F")) {
                ((Label) attendeeFields.getChildren().get(2)).setText("Must be M or F");
                return false;
            }

            String address = addressField.getText().trim();
            if (address.isEmpty()) {
                // You could add an error label for address if needed
                return false;
            }

            if (interestCombo.getValue() == null) {
                // You could add an error label for interest if needed
                return false;
            }

            Category selectedCategory = Database.categories.stream()
                    .filter(c -> c.getName().equals(interestCombo.getValue()))
                    .findFirst()
                    .orElse(null);

            User newUser = new Attendee(username, password, dateOfBirth, gender, address, selectedCategory);
            Database.users.add(newUser);
            Database.attendees.add((Attendee) newUser);
            return true;
        }

        return false;
    }
}
