package project.eventmanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrganizerGUI {
    public static Scene dashboard(Organizer org) {
        BorderPane root = new BorderPane();
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
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            root.setStyle("-fx-background-color: lightgray;");
        }
        Button logoutbtn = new Button("Logout");
        logoutbtn.setPrefSize(300, 30);
        logoutbtn.setOnAction(e -> Main.get_stage().setScene(Main.Home()));
        root.setBottom(logoutbtn);
        Button profile = new Button("Profile");
        profile.setOnAction(e -> Main.get_stage().setScene(showProfile(org)));
        Button manageEvents = new Button("Manage Events");
        manageEvents.setOnAction(e -> Main.get_stage().setScene(manageEvents(org)));
        Button manageWallet = new Button("Manage Wallet");
        manageWallet.setOnAction(e -> Main.get_stage().setScene(manageWallet(org)));
        HBox buttonsPane = new HBox(profile, manageEvents, manageWallet);
        buttonsPane.setSpacing(10.0);
        buttonsPane.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome " + org.getUsername());
        welcomeLabel.setTextFill(Color.DARKGOLDENROD);
        welcomeLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        root.setCenter(buttonsPane);
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel,Pos.CENTER);
        return new Scene(root, 800, 600);
    }

    static Scene manageWallet(Organizer org) {
            BorderPane Pwallet= new BorderPane();
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
            Pwallet.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            Pwallet.setStyle("-fx-background-color: lightgray;");
        }
            Label Lwallet=new Label("Manage Wallet");
            Lwallet.setTextFill(Color.DARKGOLDENROD);
            Lwallet.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            Lwallet.setAlignment(Pos.CENTER);
            Pwallet.setTop(Lwallet);
            BorderPane.setAlignment(Lwallet,Pos.CENTER);
            VBox Details = new VBox();
            Details.setSpacing(10.0);
            Details.setAlignment(Pos.CENTER);
            Pwallet.setCenter(Details);
            TextField tfBalance = new TextField();
            tfBalance.setPrefSize(200, 20);;
            tfBalance.setMaxWidth(Region.USE_PREF_SIZE);
            Label statusbalance=new Label();
            Label LBalance=new Label("Wallet Balance: "+org.getWallet().getBalance() );
            LBalance.setTextFill(Color.DARKGOLDENROD);
            LBalance.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
            LBalance.setAlignment(Pos.CENTER);
            Button updatebtn= new Button("Update");
            updatebtn.setPrefSize(300, 30);
            updatebtn.setPrefWidth(200);
            updatebtn.setOnAction(e->{
                try {
                    double newBalance = Double.parseDouble(tfBalance.getText());
                    if (newBalance>0) {
                        org.getWallet().setBalance(newBalance);
                    }
                }
                catch (NumberFormatException ex) {
                    statusbalance.setText("Error: Please enter a valid number");
                    statusbalance.setStyle("-fx-text-fill: red;");
                    return;
                }
                Main.primaryStage.setScene(manageWallet(org));

            });
            Button backbtn = new Button("Go back");
            backbtn.setPrefSize(300, 30);
            backbtn.setOnAction(e->{
                Main.primaryStage.setScene(dashboard(org));
            });
            Details.getChildren().addAll(LBalance,tfBalance,statusbalance,updatebtn, backbtn);
            return new Scene(Pwallet,800,600);
        }

    static Scene manageEvents(Organizer org) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");
        Button addEventbtn = new Button("Add Event");
        addEventbtn.setPrefSize(300, 30);
        addEventbtn.setOnAction(e -> Main.get_stage().setScene(addEvent(org)));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        VBox eventsContainer = new VBox(10);
        eventsContainer = refreshEvents(eventsContainer,org,errorLabel);
        scrollPane.setContent(eventsContainer);
        vbox.getChildren().addAll(errorLabel, addEventbtn, scrollPane);
        BorderPane root = new BorderPane();
        Button backbtn = new Button("Back");
        backbtn.setPrefSize(300, 30);
        backbtn.setOnAction(e -> Main.get_stage().setScene(dashboard(org)));
        root.setBottom(backbtn);
        root.setCenter(vbox);
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
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            root.setStyle("-fx-background-color: lightgray;");
        }
        return new Scene(root, 800, 600);
    }
    private static VBox refreshEvents(VBox eventsContainer, Organizer org, Label errorLabel)
    {
            eventsContainer.getChildren().clear();

            for (Event evt : org.getEvents()) {
                Label name = new Label("Name: " + evt.getName());
                Label description = new Label("Description: " + evt.getDescription());
                Label category = new Label("Category: " + evt.getCategory().getName());
                Label price = new Label("Price: " + evt.getPrice());
                Label room = new Label("Room: " + evt.getRoom().getID());
                Button delete = new Button("Delete");
                delete.setOnAction(e -> {
                    try {
                        // Remove from room
                        evt.getRoom().removeEvent(evt);

                        // Remove from organizer
                        org.getEvents().remove(evt);

                        // Remove from database
                        Database.events.remove(evt);

                        // Update IDs - this might need reconsideration
                        for (int j = 0; j < Database.events.size(); j++) {
                            Database.events.get(j).setID();
                        }

                        // Refresh the UI
                        errorLabel.setText("");
                    } catch (Exception ex) {
                        errorLabel.setText("Error deleting event: " + ex.getMessage());
                    }
                    refreshEvents(eventsContainer,org,errorLabel);
                });
                HBox eventBox = new HBox(10, name, description, category, price, room, delete);
                eventBox.setPadding(new Insets(10));
                eventBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-radius: 5;");
                if (!evt.getAttendees().isEmpty()) {
                    VBox attendeesBox = new VBox(5);
                    attendeesBox.getChildren().add(new Label("Attendees:"));
                    for (User attendee : evt.getAttendees()) {
                        attendeesBox.getChildren().add(new Label("  • " + attendee.getUsername()));
                    }
                    eventBox.getChildren().add(attendeesBox);
                }
                eventBox.getChildren().add(new Label("Date: " + evt.getDate().getDate() + " / " + evt.getDate().getMonth() + " / " + evt.getDate().getYear()));
                eventsContainer.getChildren().add(eventBox);
            }
            return  eventsContainer;
    }
    private static Scene addEvent(Organizer org) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(15));
        mainLayout.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Event Name:");
        nameLabel.setTextFill(Color.DARKGOLDENROD);
        nameLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        nameLabel.setAlignment(Pos.CENTER);
        TextField nameField = new TextField();
        Label categoryLabel = new Label("Category:");
        categoryLabel.setTextFill(Color.DARKGOLDENROD);
        categoryLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        categoryLabel.setAlignment(Pos.CENTER);
        ComboBox<String> categoryCombo = new ComboBox<>();
        Set<String> addedCategories = new HashSet<>();
        int cnt = 1;
        for (int i = 0; i < Database.categories.size(); i++) {
            String name = Database.categories.get(i).getName();
            if (!addedCategories.contains(name)) {
                addedCategories.add(name);
                categoryCombo.getItems().add(name + " [" + cnt + "]");
                cnt++;
            }
        }
        categoryCombo.setPromptText("choose category");
        Label descLabel = new Label("Description:");
        descLabel.setTextFill(Color.DARKGOLDENROD);
        descLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        descLabel.setAlignment(Pos.CENTER);
        TextArea descArea = new TextArea();
        descArea.setPrefRowCount(3);

        // Price - Using TextField with validation
        Label priceLabel = new Label("Price:");
        priceLabel.setTextFill(Color.DARKGOLDENROD);
        priceLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        priceLabel.setAlignment(Pos.CENTER);
        TextField priceField = new TextField();
        priceField.setPromptText("Enter price as whole number");

        // Date Components - Using TextFields with validation
        GridPane dateGrid = new GridPane();
        dateGrid.setHgap(10);
        dateGrid.setVgap(5);

        Label dateLabel = new Label("Event Date:");
        dateLabel.setTextFill(Color.DARKGOLDENROD);
        dateLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        dateLabel.setAlignment(Pos.CENTER);
        dateGrid.add(dateLabel, 0, 0, 2, 1);

        // Year
        Label yearLabel = new Label("Year:");
        yearLabel.setTextFill(Color.DARKGOLDENROD);
        yearLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        yearLabel.setAlignment(Pos.CENTER);
        dateGrid.add(yearLabel, 0, 1);
        TextField yearField = new TextField(String.valueOf(LocalDateTime.now().getYear()));
        dateGrid.add(yearField, 1, 1);

        // Month
        Label monthLabel = new Label("Month (1-12):");
        monthLabel.setTextFill(Color.DARKGOLDENROD);
        monthLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        monthLabel.setAlignment(Pos.CENTER);
        dateGrid.add(monthLabel, 0, 2);
        TextField monthField = new TextField("1");
        dateGrid.add(monthField, 1, 2);

        // Day
        Label dayLabel = new Label("Day (1-31)");
        dayLabel.setTextFill(Color.DARKGOLDENROD);
        dayLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        dayLabel.setAlignment(Pos.CENTER);
        dateGrid.add(dayLabel, 0, 3);
        TextField dayField = new TextField("1");
        dateGrid.add(dayField, 1, 3);

        // Hour
        Label hourLabel = new Label("Hour (0-23):");
        hourLabel.setTextFill(Color.DARKGOLDENROD);
        hourLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        hourLabel.setAlignment(Pos.CENTER);
        dateGrid.add(hourLabel, 0, 4);
        TextField hourField = new TextField("12");
        dateGrid.add(hourField, 1, 4);

        // Submit Button
        Button submitebtn = new Button("Create Event");
        submitebtn.setPrefSize(300, 30);
        submitebtn.setStyle("-fx-base: #4CAF50; -fx-text-fill: white;");

        // Status Label
        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;");

        // Add all components to main layout
        mainLayout.getChildren().addAll(
                nameLabel, nameField,
                categoryLabel, categoryCombo,
                descLabel, descArea,
                priceLabel, priceField,
                dateGrid,
                submitebtn, statusLabel
        );

        // Submit Button Action
        submitebtn.setOnAction(e -> {
            try {
                // Validate inputs
                if (nameField.getText().isEmpty()) {
                    statusLabel.setText("Event name cannot be empty!");
                    return;
                }

                if (categoryCombo.getSelectionModel().isEmpty()) {
                    statusLabel.setText("Please select a category!");
                    return;
                }

                int catIndex = categoryCombo.getSelectionModel().getSelectedIndex();
                if (catIndex < 0 || catIndex >= Database.categories.size()) {
                    statusLabel.setText("Invalid category selection!");
                    return;
                }

                // Validate and parse price
                long price;
                try {
                    price = Long.parseLong(priceField.getText());
                    if (price < 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Please enter a valid positive price!");
                    return;
                }

                // Validate and parse date components
                int year, month, day, hour;
                try {
                    year = Integer.parseInt(yearField.getText());
                    if (year < LocalDateTime.now().getYear()) {
                        statusLabel.setText("Year cannot be in the past!");
                        return;
                    }

                    month = Integer.parseInt(monthField.getText());
                    if (month < 1 || month > 12) {
                        statusLabel.setText("Month must be between 1-12!");
                        return;
                    }

                    day = Integer.parseInt(dayField.getText());
                    if (day < 1 || day > 31 || (month == 2 && day > 28)) {
                        statusLabel.setText("Invalid day for selected month!");
                        return;
                    }

                    hour = Integer.parseInt(hourField.getText());
                    if (hour < 0 || hour > 23) {
                        statusLabel.setText("Hour must be between 0-23!");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Please enter valid numbers for date fields!");
                    return;
                }
                Date eventDate = new Date(year, month - 1, day, hour, 0);
                if(eventDate.before(new Date(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth(),LocalDateTime.now().getHour(),0)))
                {
                    statusLabel.setText("date can't be in the past!");
                    return;
                }
                // Show room selection dialog
                int roomNo = rentRoom(eventDate);
                if (roomNo == -1) {
                    statusLabel.setText("Room selection cancelled or no rooms available.");
                    return;
                }

                // Check wallet balance
                if (org.getWallet().getBalance() < Database.rooms.get(roomNo).getPrice()) {
                    statusLabel.setText("Insufficient funds!");
                    return;
                }

                // Deduct from wallet
                org.getWallet().setBalance(org.getWallet().getBalance() - Database.rooms.get(roomNo).getPrice());
                Database.appOwnerBalance += Database.rooms.get(roomNo).getPrice();

                // Create event
                Event newEvent = new Event(
                        nameField.getText(),
                        descArea.getText(),
                        Database.categories.get(catIndex),
                        price,
                        Database.rooms.get(roomNo),
                        eventDate,
                        org
                );

                org.getEvents().add(newEvent);
                if (!org.getRooms().contains(Database.rooms.get(roomNo))) {
                    org.getRooms().add(Database.rooms.get(roomNo));
                }

                statusLabel.setStyle("-fx-text-fill: green;");
                statusLabel.setText("Event created successfully!");

            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        BorderPane root = new BorderPane();
        Button backbtn = new Button("Back");
         backbtn.setPrefSize(300, 30);
        backbtn.setOnAction(e -> Main.get_stage().setScene(manageEvents(org)));
        root.setBottom(backbtn);
        root.setCenter(mainLayout);
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
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            root.setStyle("-fx-background-color: lightgray;");
        }
        return new Scene(root, 800, 600);
    }

    private static int rentRoom(Date eventDate) {
        Dialog<Integer> roomDialog = new Dialog<>();
        roomDialog.setTitle("Select a Room");
        roomDialog.setHeaderText("Available Rooms for the Selected Time");

        ButtonType selectButtonType = new ButtonType("Select", ButtonBar.ButtonData.OK_DONE);
        roomDialog.getDialogPane().getButtonTypes().addAll(selectButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ToggleGroup roomGroup = new ToggleGroup();
        int row = 0;

        for (int i = 0; i < Database.rooms.size(); i++) {
            if (Database.rooms.get(i).IsAvailable(eventDate)) {
                RadioButton roomRadio = new RadioButton(String.format("Room %d - $%d",
                        Database.rooms.get(i).getID(),
                        Database.rooms.get(i).getPrice()));
                roomRadio.setToggleGroup(roomGroup);
                roomRadio.setUserData(i);
                grid.add(roomRadio, 0, row);
                row++;
            }
        }

        if (row == 0) {
            grid.add(new Label("No rooms available for the selected time."), 0, 0);
            roomDialog.getDialogPane().lookupButton(selectButtonType).setDisable(true);
        }

        roomDialog.getDialogPane().setContent(grid);

        roomGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            roomDialog.getDialogPane().lookupButton(selectButtonType)
                    .setDisable(newVal == null);
        });

        roomDialog.setResultConverter(dialogButton -> {
            if (dialogButton == selectButtonType) {
                return (Integer) roomGroup.getSelectedToggle().getUserData();
            }
            return null;
        });

        return roomDialog.showAndWait().orElse(-1);
    }
    private static Label statusUsername = new Label();
    private static Label statuspassword = new Label();

    static Scene showProfile(Organizer org) {
        Label usernameLabel = new Label("Username:");
        usernameLabel.setTextFill(Color.DARKGOLDENROD);
        usernameLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        TextField usernameField = new TextField(org.getUsername());
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        usernameLabel.setLabelFor(usernameField);
        HBox username = new HBox(usernameLabel, usernameField);
        username.setAlignment(Pos.CENTER);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.DARKGOLDENROD);
        passwordLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        TextField passwordField = new TextField(org.getPassword());
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        passwordLabel.setLabelFor(passwordField);
        HBox password = new HBox(passwordLabel, passwordField);
        password.setAlignment(Pos.CENTER);
        Label dateOfBirthLabel = new Label("Date of Birth: " + org.getDateOfBirth().getDate() + " / " + org.getDateOfBirth().getMonth() + " / " + org.getDateOfBirth().getYear());
        dateOfBirthLabel.setTextFill(Color.DARKGOLDENROD);
        dateOfBirthLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        dateOfBirthLabel.setAlignment(Pos.CENTER);
        Label walletBalanceLabel = new Label("Balance: " + String.valueOf(org.getWallet().getBalance()));
        walletBalanceLabel.setTextFill(Color.DARKGOLDENROD);
        walletBalanceLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        walletBalanceLabel.setAlignment(Pos.CENTER);
        Label eventLabel = new Label("Events:");
        eventLabel.setTextFill(Color.DARKGOLDENROD);
        eventLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        eventLabel.setAlignment(Pos.CENTER);
        Button updatebtn = new Button("Update Information");
        updatebtn.setPrefSize(300, 30);
        updatebtn.setOnAction(e->{
            statuspassword.setText("");
            statusUsername.setText("");
            if (!usernameField.getText().isEmpty()) {
            if (!Character.isLetter(usernameField.getText().charAt(0))) {
                statusUsername.setText("Username must start with a letter (A-Z, a-z). Try again.");
                statusUsername.setStyle("-fx-text-fill: red;");
            }
            else if (usernameField.getText().contains(" ")) {
                statusUsername.setText("Username cannot contain spaces. Try again.");
                statusUsername.setStyle("-fx-text-fill: red;");
            }
            else if (usernameField.getText().length() < 4 || usernameField.getText().length() > 20) {
                statusUsername.setText("Username must be 4-20 characters long. Try again.");
                statusUsername.setStyle("-fx-text-fill: red;");
            }
            else if(!org.getUsername().equals(usernameField.getText())) {
                org.setUsername(usernameField.getText());
                statusUsername.setText("Updated");
                statusUsername.setStyle("-fx-text-fill: green;");
            }
        }

            if (!passwordField.getText().isEmpty()) {
                if (passwordField.getText().length() < 4 || passwordField.getText().length() > 20) {
                    statuspassword.setText("Password must be 4-20 characters long. Try again.");
                    statuspassword.setStyle("-fx-text-fill: red;");
                }
                else if(!org.getPassword().equals(passwordField.getText())) {
                        org.setPassword(passwordField.getText());
                        statuspassword.setText("Updated");
                        statuspassword.setStyle("-fx-text-fill: green;");
                }
            }
            Main.primaryStage.setScene(showProfile(org));
        });
        VBox vbox = new VBox(username, statusUsername, password, statuspassword, dateOfBirthLabel, walletBalanceLabel,  updatebtn, eventLabel);
        vbox.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        for (int i = 0; i < org.getEvents().size(); i++) {
            Label name = new Label("Name: " + org.getEvents().get(i).getName());
            Label description = new Label("Description: " + org.getEvents().get(i).getDescription());
            Label category = new Label("Category: " + org.getEvents().get(i).getCategory().getName());
            Label price = new Label("Price: " + org.getEvents().get(i).getPrice());
            Label room = new Label("Room: " + org.getEvents().get(i).getRoom().getID());
            HBox event = new HBox(name, description, category, price, room);
            for (int j = 0; j < org.getEvents().get(i).getAttendees().size(); j++) {
                event.getChildren().add(new Label("    Name: " + org.getEvents().get(i).getAttendees().get(j).getUsername()));
            }
            event.getChildren().add(new Label("Date of Event: " + org.getEvents().get(i).getDate().getDate() + " / " + org.getEvents().get(i).getDate().getMonth() + " / " + org.getEvents().get(i).getDate().getYear()));
            scrollPane.setContent(event);
        }
        vbox.getChildren().add(scrollPane);
        vbox.setSpacing(10);
        BorderPane root = new BorderPane();
        Button backbtn = new Button("Back");
        backbtn.setPrefSize(300, 30);
        backbtn.setOnAction(e -> {
            statusUsername.setText("");
            statuspassword.setText("");
            Main.get_stage().setScene(dashboard(org));});
        root.setBottom(backbtn);
        root.setCenter(vbox);
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
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            root.setStyle("-fx-background-color: lightgray;");
        }
        return new Scene(root,800,600);
    }
}
