package project.eventmanagementsystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

class UserGUI {
    public static Scene signupRegistration() {
        Pane Signup = new Pane();
        VBox content = new VBox();
        content.setAlignment(Pos.TOP_LEFT);
        ScrollPane catPane = new ScrollPane();
        catPane.setPrefSize(200, 200);
        catPane.setLayoutX(120);
        catPane.setLayoutY(270);
        catPane.setStyle("-fx-background-color: lightgray;");
        Signup.getChildren().add(catPane);
        Scene SignupScene = new Scene(Signup, 800, 520);

        // Title Label
        Label signupLabel = new Label("Create a new account");
        signupLabel.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 30));
        signupLabel.setAlignment(Pos.CENTER);
        signupLabel.setTextFill(Color.BLACK);
        signupLabel.setLayoutX(200);
        signupLabel.setLayoutY(10);

        // Username Field
        Label usernameLabel = new Label("Username");
        usernameLabel.setTextFill(Color.BLACK);
        usernameLabel.setLayoutX(20);
        usernameLabel.setLayoutY(70);
        usernameLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));

        TextField usernameField = new TextField();
        usernameField.setTooltip(new Tooltip("Enter Valid Username"));
        usernameField.setEditable(true);
        usernameField.setLayoutX(120);
        usernameField.setLayoutY(70);
        usernameField.setPrefSize(200, 20);

        // Password Field
        Label passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Color.BLACK);
        passwordLabel.setLayoutX(20);
        passwordLabel.setLayoutY(110);
        passwordLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));


        PasswordField passwordField = new PasswordField(); // Changed to PasswordField for security
        passwordField.setTooltip(new Tooltip("Enter Password"));
        passwordField.setEditable(true);
        passwordField.setLayoutX(120);
        passwordField.setLayoutY(110);
        passwordField.setPrefSize(200, 20);

        // Date of Birth
        Label dobLabel = new Label("Date of Birth");
        dobLabel.setTextFill(Color.BLACK);
        dobLabel.setLayoutX(20);
        dobLabel.setLayoutY(150);
        dobLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select date of birth");
        datePicker.setLayoutX(120);
        datePicker.setLayoutY(150);
        datePicker.setPrefSize(200, 20);

        // Validation Messages
        Label usernameMessage = new Label("Enter a username");
        Label passwordMessage = new Label("Enter a password");
        Label dobMessage = new Label("Select your date of birth");
        usernameMessage.setTextFill(Color.BLUE);
        passwordMessage.setTextFill(Color.BLUE);
        dobMessage.setTextFill(Color.BLUE);

        // Message styling
        Font messageFont = Font.font("Times New Roman", FontWeight.BOLD, 16);
        usernameMessage.setFont(messageFont);
        passwordMessage.setFont(messageFont);
        dobMessage.setFont(messageFont);

        usernameMessage.setLayoutX(330);
        usernameMessage.setLayoutY(73);
        passwordMessage.setLayoutX(330);
        passwordMessage.setLayoutY(113);
        dobMessage.setLayoutX(330);
        dobMessage.setLayoutY(152);

        // Create Account Button
        Button createAccount = new Button("Create Account");
        createAccount.setStyle("-fx-background-color: lightgray;");
        createAccount.setLayoutX(120);
        createAccount.setLayoutY(480);
        createAccount.setPrefSize(200, 30);
        createAccount.setAlignment(Pos.CENTER);
        createAccount.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 16));

        Label accountTypeLabel = new Label("Account Type:");
        accountTypeLabel.setTextFill(Color.WHITE);
        accountTypeLabel.setLayoutX(50);
        accountTypeLabel.setLayoutY(180); // Adjust position as needed

        ToggleGroup accountTypeGroup = new ToggleGroup();

        ToggleGroup genderTypeGroup = new ToggleGroup();

        RadioButton organizerRadio = new RadioButton("Organizer");
        organizerRadio.setTextFill(Color.WHITE);
        organizerRadio.setToggleGroup(accountTypeGroup);
        organizerRadio.setLayoutX(150);
        organizerRadio.setLayoutY(180);
        organizerRadio.setSelected(true); // Default selection

        RadioButton attendeeRadio = new RadioButton("Attendee");
        attendeeRadio.setTextFill(Color.WHITE);
        attendeeRadio.setToggleGroup(accountTypeGroup);
        attendeeRadio.setLayoutX(250);
        attendeeRadio.setLayoutY(180);

        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setTextFill(Color.WHITE);
        maleRadio.setToggleGroup(genderTypeGroup);
        maleRadio.setLayoutX(250);
        maleRadio.setLayoutY(240);
        maleRadio.setSelected(true);

        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setTextFill(Color.WHITE);
        femaleRadio.setToggleGroup(genderTypeGroup);
        femaleRadio.setLayoutX(150);
        femaleRadio.setLayoutY(240);

        Label addressLabel = new Label("Address");
        addressLabel.setTextFill(Color.BLACK);
        addressLabel.setLayoutX(20);
        addressLabel.setLayoutY(210);
        addressLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));

        TextField addressField = new TextField();
        addressField.setTooltip(new Tooltip("Enter Valid Username"));
        addressField.setEditable(true);
        addressField.setLayoutX(120);
        addressField.setLayoutY(210);
        addressField.setPrefSize(200, 20);

        // Add to your pane
        Signup.getChildren().addAll(accountTypeLabel, organizerRadio, attendeeRadio);

        ArrayList<CheckBox> catCheckboxes = new ArrayList<>();

        for (int i = 0; i < Database.categories.size(); i++)
        {
            CheckBox temp = new CheckBox(Database.categories.get(i).getName());
            temp.setLayoutX(0);
            temp.setLayoutY(90 + (40*i));
            catCheckboxes.add(temp);
        }

        attendeeRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(isNowSelected)
                {
                    Signup.getChildren().addAll(addressLabel,addressField,maleRadio,femaleRadio);
                    for (int i = 0; i < catCheckboxes.size(); i++)
                    {
                        content.getChildren().add(catCheckboxes.get(i));
                    }
                    catPane.setContent(content);
                    catPane.setStyle("-fx-background-color: white;");
                }
                if (wasPreviouslySelected)
                {
                    Signup.getChildren().removeAll(addressLabel,addressField,maleRadio,femaleRadio);
                    for (int i = 0; i < catCheckboxes.size(); i++)
                    {
                        content.getChildren().remove(catCheckboxes.get(i));
                    }
                }
            }
        });

        // validation for username
        usernameField.textProperty().addListener((obs, oldVal, newVal) -> {
            validateUsername(newVal, usernameMessage);
            updateCreateButtonState(createAccount, usernameField, passwordField, datePicker);
        });

        // validation for password
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            validatePassword(newVal, passwordMessage);
            updateCreateButtonState(createAccount, usernameField, passwordField, datePicker);
        });

        // DatePicker validation
        datePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                dobMessage.setText("Valid date selected");
                dobMessage.setTextFill(Color.GREEN);
            } else {
                dobMessage.setText("Please select date of birth");
                dobMessage.setTextFill(Color.RED);
            }
            updateCreateButtonState(createAccount, usernameField, passwordField, datePicker);
        });

        // Background Image
        try {
            Image image = new Image(UserGUI.class.getResource("/Background4.png").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, true)
            );
            Signup.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            Signup.setStyle("-fx-background-color: lightgray;");
        }

        // Add all components to the pane
        Signup.getChildren().addAll(
                signupLabel,
                usernameLabel, usernameField, usernameMessage,
                passwordLabel, passwordField, passwordMessage,
                dobLabel, datePicker, dobMessage,
                createAccount
        );


        createAccount.setOnAction(e -> {
            if (isFormValid(usernameField, passwordField, datePicker)) {
                Date dop = new Date(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth());
                // Here you would typically create the user account
                boolean isOrganizer = organizerRadio.isSelected();

                if (isOrganizer) {
                    Organizer organizer = new Organizer(usernameField.getText(), passwordField.getText(), dop);

                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText("Organizer Account Created");
                    success.setContentText("Welcome, " + organizer.getUsername() + "!");
                    success.showAndWait();
                    Main.get_stage().setScene(Main.Home());
                } else {
                    String g = "";
                    if (maleRadio.selectedProperty().get())
                    {
                        g = "M";
                    } else {
                        g = "F";
                    }
                    String Address = addressField.getText();
                    ArrayList<Category> cats = new ArrayList<>();
                    for (int i = 0; i < catCheckboxes.size(); i++)
                    {
                        if (catCheckboxes.get(i).selectedProperty().get())
                        {
                            cats.add(Database.categories.get(i));
                        }
                    }
                    Attendee attendee = new Attendee(usernameField.getText(), passwordField.getText(), dop, g, Address, cats);

                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText("Attendee Account Created");
                    success.setContentText("Welcome, " + attendee.getUsername() + "!");
                    success.showAndWait();
                    Main.get_stage().setScene(Main.Home());
                }

            }
        });

        return SignupScene;
    }

    // Validation methods
    private static void validateUsername(String username, Label message) {
        if (username.isEmpty()) {
            message.setText("Please enter username");
            message.setTextFill(Color.WHITE);
        } else if (!Character.isLetter(username.charAt(0))) {
            message.setText("Must start with a letter");
            message.setTextFill(Color.DARKRED);
        } else if (username.contains(" ")) {
            message.setText("No spaces allowed");
            message.setTextFill(Color.DARKRED);
        } else if (username.length() < 4 || username.length() > 20) {
            message.setText("4-20 characters required");
            message.setTextFill(Color.DARKRED);
        } else {
            message.setText("Valid username");
            message.setTextFill(Color.GREEN);
        }
    }

    private static void validatePassword(String password, Label message) {
        if (password.isEmpty()) {
            message.setText("Please enter password");
            message.setTextFill(Color.WHITE);
        } else if (password.length() < 4 || password.length() > 20) {
            message.setText("4-20 characters required");
            message.setTextFill(Color.DARKRED);
        } else {
            message.setText("Valid password");
            message.setTextFill(Color.GREEN);
        }
    }
  // hi people
    private static boolean isFormValid(TextField username, PasswordField password, DatePicker dob) {
        return !username.getText().isEmpty() &&
                !password.getText().isEmpty() &&
                dob.getValue() != null ;
    }

    private static void updateCreateButtonState(Button button, TextField username,
                                                PasswordField password, DatePicker dob) {
        button.setDisable(!isFormValid(username, password, dob));
    }
}







