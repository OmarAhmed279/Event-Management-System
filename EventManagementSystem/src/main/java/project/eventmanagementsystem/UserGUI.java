package project.eventmanagementsystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

class UserGUI {
    public static Scene signupRegistration() {
        Pane Signup = new Pane();


        try {
            // Load the image
            Image image = new Image(UserGUI.class.getResource("/Background4.png").toExternalForm());

            // Create background image that fills the entire pane
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            100, 100,  // width/height percentages (100% for both)
                            true,       // width as percentage
                            true,       // height as percentage
                            true,       // contain within bounds
                            true        // cover entire area
                    )
            );

            // Set the background
            Signup.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            Signup.setStyle("-fx-background-color: lightgray;");
        }

        VBox content = new VBox();
        content.setAlignment(Pos.TOP_LEFT);
        Image image2 = new Image(UserGUI.class.getResource("/Background1.png").toExternalForm());

        // Create background image that fills the entire pane
        ImageView imageView = new ImageView(image2);
        ScrollPane catPane = new ScrollPane(imageView);
        catPane.setPrefSize(200, 200);
        catPane.setLayoutX(120);
        catPane.setLayoutY(270);
        Signup.getChildren().add(catPane);
        Scene SignupScene = new Scene(Signup, 800, 600);

        // Title Label
        Label signupLabel = new Label("Create a new account");
        signupLabel.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 40));
        signupLabel.setAlignment(Pos.CENTER);
        signupLabel.setTextFill(Color.DARKGOLDENROD);
        signupLabel.setLayoutX(270);
        signupLabel.setLayoutY(5);

        // Username Field
        Label usernameLabel = new Label("Username");
        usernameLabel.setTextFill(Color.WHEAT);
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
        passwordLabel.setTextFill(Color.WHEAT);
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
        dobLabel.setTextFill(Color.WHEAT);
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
        usernameMessage.setTextFill(Color.YELLOWGREEN);
        passwordMessage.setTextFill(Color.YELLOWGREEN);
        dobMessage.setTextFill(Color.YELLOWGREEN);

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
        addressLabel.setTextFill(Color.WHEAT);
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
                dobMessage.setTextFill(Color.INDIANRED);
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
            message.setTextFill(Color.INDIANRED);
        } else if (username.contains(" ")) {
            message.setText("No spaces allowed");
            message.setTextFill(Color.INDIANRED);
        } else if (username.length() < 4 || username.length() > 20) {
            message.setText("4-20 characters required");
            message.setTextFill(Color.INDIANRED);
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
            message.setTextFill(Color.INDIANRED);
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
    public static Scene LoginScene() {
        GridPane login = new GridPane();
        login.setAlignment(Pos.CENTER);
        login.setHgap(10);
        login.setVgap(10);

        try {
            // Load the image
            Image image = new Image(UserGUI.class.getResource("/Background4.png").toExternalForm());

            // Create background image that fills the entire pane
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            100, 100,  // width/height percentages (100% for both)
                            true,       // width as percentage
                            true,       // height as percentage
                            true,       // contain within bounds
                            true        // cover entire area
                    )
            );

            // Set the background
            login.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            login.setStyle("-fx-background-color: lightgray;");
        }

        Label loginLabel = new Label("Login");
        loginLabel.setTextFill(Color.DARKGOLDENROD);
        loginLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        login.add(loginLabel, 0, 0);
        Label username = new Label("Username");
        username.setTextFill(Color.DARKGOLDENROD);
        username.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        login.add(username, 0, 1);
        Label password = new Label("Password");
        password.setTextFill(Color.DARKGOLDENROD);
        password.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        login.add(password, 0, 2);
        TextField usernameField = new TextField();
        login.add(usernameField, 1, 1);
        TextField passwordField = new TextField();
        login.add(passwordField, 1, 2);
        Button loginButton = new Button("Login");
        login.add(loginButton, 1, 3);
        Scene loginScene = new Scene(login, 800, 600);
        Label message = new Label("Username or Password incorrect");
        message.setTextFill(Color.DARKRED);
        message.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        message.setText("");
        login.add(message, 3, 2);
        loginButton.setOnAction(e -> {
            boolean isfound = false;
            for (int i=0 ; i<Database.users.size(); i++){
                if (!isfound) {
                    if (usernameField.getText().equals(Database.users.get(i).getUsername()) && passwordField.getText().equals(Database.users.get(i).getPassword())){
                        if (!Database.users.get(i).getIsSuspended()) {
                            if (Database.users.get(i) instanceof Organizer) {
                                Main.get_stage().setScene(OrganizerGUI.dashboard((Organizer)Database.users.get(i)));
                            }
                            else if(Database.users.get(i) instanceof Attendee){
                                Main.get_stage().setScene(AttendeeGUI.AttendeeDashboard((Attendee) Database.users.get(i)));
                            }
                            else {
                                Main.get_stage().setScene(AdminGUI.dashboardScene((Admin)Database.users.get(i)));
                            }
                        } else {
                            isfound = true;
                            message.setText("User is suspended.");
                        }
                    }
                    else {
                        message.setText("Incorrect Username or Password.");
                    }
                } else {
                    break;
                }

            }

        });
        return loginScene;
    }
}







