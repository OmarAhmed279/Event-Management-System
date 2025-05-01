package project.eventmanagementsystem;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.util.Date;

class UserGUI {
    public static Scene signupRegistration() {
        Pane Signup = new Pane();
        Label signupLabel = new Label("Create a new account");
        signupLabel.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 35));
        signupLabel.setAlignment(Pos.CENTER);
        signupLabel.setTextFill(Color.WHITE);
        signupLabel.setLayoutX(300);
        signupLabel.setLayoutY(20);
        Label username = new Label("Username");
        username.setTextFill(Color.WHITE);
        username.setLayoutX(50);
        username.setLayoutY(80);
        Label password = new Label("Password");
        password.setTextFill(Color.WHITE);
        password.setLayoutX(50);
        password.setLayoutY(120);
        TextField usernameField = new TextField();
        usernameField.setTooltip(new Tooltip("Enter Valid Username"));
        usernameField.setEditable(Boolean.TRUE);
        usernameField.setLayoutX(120);
        usernameField.setLayoutY(80);
        usernameField.setPrefSize(200, 20);
        TextField passwordField = new TextField();
        passwordField.setTooltip(new Tooltip("Enter Password"));
        passwordField.setEditable(Boolean.TRUE);
        passwordField.setLayoutX(120);
        passwordField.setLayoutY(120);
        passwordField.setPrefSize(200, 20);
        // Create DatePicker
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select date of birth");
        datePicker.setLayoutX(80);
        datePicker.setLayoutY(160);
// Get the selected date
        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null) {
                int day = selectedDate.getDayOfMonth();
                int month = selectedDate.getMonthValue();
                int year = selectedDate.getYear();
                System.out.printf("Selected date: %d/%d/%d%n", day, month, year);
            }
        });

        Scene SignupScene = new Scene(Signup, 800 , 520);
        try {
            // Load the image
            Image image = new Image(UserGUI.class.getResource("/Background3.png").toExternalForm());
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
        Button createAccount = new Button("Create a new User");
        createAccount.setStyle("-fx-background-color: lightgray;");
        createAccount.setLayoutX(150);
        createAccount.setLayoutY(180);
        createAccount.setPrefSize(100, 30);
        createAccount.setAlignment(Pos.CENTER);
        createAccount.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 16));
        Label message_name = new Label("Correct Name : ");
        Label message_password = new Label("Correct Password : ");
        Signup.getChildren().add(message_name);
        Signup.getChildren().add(message_password);
        message_name.setLayoutX(330);
        message_name.setLayoutY(85);
        message_name.setTextFill((Color.WHITE));
        message_name.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        message_password.setLayoutX(330);
        message_password.setLayoutY(120);
        message_password.setPrefSize(100, 30);
        message_password.setTextFill((Color.WHITE));
        createAccount.setOnAction(e -> {
            boolean correctName = true;
            boolean correctPassword = true;



            if (usernameField.getText().isEmpty()) {
                correctName = false;
                message_name.setText("Please enter your Username");

            }
            if (passwordField.getText().isEmpty()) {
                correctPassword = false;
                message_password.setText("The password must have value");

            }
            if  (!Character.isLetter(usernameField.getText().charAt(0))){
                correctName = false;
                message_name.setText("Username must start with a letter (A-Z, a-z). Try again.: ");

            }

            if (usernameField.getText().contains(" ")){
                correctName = false;

                message_name.setText("Username cannot contain spaces. Try again): ");

            }
            if (usernameField.getText().length() < 4 || usernameField.getText().length() > 20){
                correctName = false;
                message_name.setText("Username must be 4-20 characters long. Try again.): ");

            }
            if (passwordField.getText().length() < 4 || passwordField.getText().length() > 20){
             correctPassword = false;
                message_password.setText("password must be 4-20 characters long. Try again.): ");


            }
            for (int i = 0; i < Database.users.size(); i++) {
                if (usernameField.getText().equals(Database.users.get(i).getUsername())) {
                correctName = false;}
                message_name.setText("Username already exists. Try again.): ");

            }
//            if (correctName && correctPassword) {
//                selectedDate = new Date(year, month, day);
//                User newuser = new Organizer(usernameField.getText(), passwordField.getText().length(), dateOfbirth);
//                Database.users.add(newuser);
//                Database.organizers.add((Organizer) newuser);
//            }
        });




        Signup.getChildren().addAll(signupLabel, username, password, datePicker, usernameField, passwordField , createAccount );

        return SignupScene;
    }
}






