package project.eventmanagementsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;

public class OrganizerGUI {
    public static Scene dashboard(Organizer org) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Logout = new Button("Logout");
        Logout.setOnAction(e -> Main.get_stage().setScene(Main.Home()));
        root.setBottom(Logout);
        Button profile = new Button("Profile");
        profile.setOnAction(e -> Main.get_stage().setScene(showProfile(org)));
        Button manageEvents = new Button("Manage Events");
        manageEvents.setOnAction(e -> Main.get_stage().setScene(manageEvents(org)));
        Button manageWallet = new Button("Manage Wallet");
        manageWallet.setOnAction(e -> Main.get_stage().setScene(manageWallet(org)));
        HBox buttonsPane = new HBox(profile, manageEvents, manageWallet);
        buttonsPane.setSpacing(10.0);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setStyle("-fx-background-color: transparent;");
        root.setCenter(buttonsPane);
        return new Scene(root, 800, 520);
    }

    static Scene manageWallet(Organizer org) {
        TextField balanceField = new TextField(String.valueOf(org.getWallet().getBalance()));
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");
        Button addMoney = new Button("Add Money");
        addMoney.setOnAction(e -> {
            try {
                int amount = Integer.parseInt(balanceField.getText().trim());
                if (amount > 0) {
                    org.wallet.setBalance(org.wallet.getBalance() + amount);
                    balanceField.setText(String.valueOf(org.getWallet().getBalance()));
                    errorLabel.setText("");
                } else {
                    errorLabel.setText("Invalid amount!");
                }
            } catch (InputMismatchException ex) {
                errorLabel.setText("Invalid input!");
            }
        });
        VBox vbox = new VBox(errorLabel,balanceField, addMoney);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Back = new Button("Back");
        Back.setOnAction(e -> Main.get_stage().setScene(dashboard(org)));
        root.setBottom(Back);
        root.setCenter(vbox);
        return new Scene(root,800,520);
    }

    static Scene manageEvents(Organizer org) {
        // Main container
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        // Error label
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        // Add Event button
        Button addEvent = new Button("Add Event");
        addEvent.setOnAction(e -> Main.get_stage().setScene(addEvent(org)));

        // Create a scroll pane to hold all events
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        VBox eventsContainer = new VBox(10);
        eventsContainer = refreshEvents(eventsContainer,org,errorLabel);
        scrollPane.setContent(eventsContainer);
        // Main layout
        vbox.getChildren().addAll(errorLabel, addEvent, scrollPane);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button back = new Button("Back");
        back.setOnAction(e -> Main.get_stage().setScene(dashboard(org)));
        root.setBottom(back);
        root.setCenter(vbox);

        return new Scene(root, 800, 600); // Added reasonable dimensions
    }
    private static VBox refreshEvents(VBox eventsContainer, Organizer org, Label errorLabel)
    {
            eventsContainer.getChildren().clear();

            for (Event evt : org.events) {
                // Create event details labels
                Label name = new Label("Name: " + evt.getName());
                Label description = new Label("Description: " + evt.getDescription());
                Label category = new Label("Category: " + evt.getCategory().getName());
                Label price = new Label("Price: " + evt.getPrice());
                Label room = new Label("Room: " + evt.getRoom().getID());

                // Delete button
                Button delete = new Button("Delete");
                delete.setOnAction(e -> {
                    try {
                        // Remove from room
                        evt.getRoom().removeEvent(evt);

                        // Remove from organizer
                        org.events.remove(evt);

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

                // Create event box
                HBox eventBox = new HBox(10, name, description, category, price, room, delete);
                eventBox.setPadding(new Insets(10));
                eventBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-radius: 5;");

                // Add attendees if any
                if (!evt.getAttendees().isEmpty()) {
                    VBox attendeesBox = new VBox(5);
                    attendeesBox.getChildren().add(new Label("Attendees:"));
                    for (User attendee : evt.getAttendees()) {
                        attendeesBox.getChildren().add(new Label("  • " + attendee.getUsername()));
                    }
                    eventBox.getChildren().add(attendeesBox);
                }

                // Add date
                eventBox.getChildren().add(new Label("Date: " + evt.getDate()));

                eventsContainer.getChildren().add(eventBox);
            }
            return  eventsContainer;
    }
    private static Scene addEvent(Organizer org) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(15));
        mainLayout.setAlignment(Pos.CENTER);

        // Event Name
        Label nameLabel = new Label("Event Name:");
        TextField nameField = new TextField();

        // Category Selection
        Label categoryLabel = new Label("Category:");
        ComboBox<String> categoryCombo = new ComboBox<>();
        for (int i = 0; i < Database.categories.size(); i++) {
            categoryCombo.getItems().add(Database.categories.get(i).getName() + " [" + (i + 1) + "]");
        }
        categoryCombo.setPromptText("Select a category");

        // Description
        Label descLabel = new Label("Description:");
        TextArea descArea = new TextArea();
        descArea.setPrefRowCount(3);

        // Price - Using TextField with validation
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        priceField.setPromptText("Enter price as whole number");

        // Date Components - Using TextFields with validation
        GridPane dateGrid = new GridPane();
        dateGrid.setHgap(10);
        dateGrid.setVgap(5);

        Label dateLabel = new Label("Event Date:");
        dateGrid.add(dateLabel, 0, 0, 2, 1);

        // Year
        dateGrid.add(new Label("Year:"), 0, 1);
        TextField yearField = new TextField(String.valueOf(LocalDateTime.now().getYear()));
        dateGrid.add(yearField, 1, 1);

        // Month
        dateGrid.add(new Label("Month (1-12):"), 0, 2);
        TextField monthField = new TextField("1");
        dateGrid.add(monthField, 1, 2);

        // Day
        dateGrid.add(new Label("Day (1-31):"), 0, 3);
        TextField dayField = new TextField("1");
        dateGrid.add(dayField, 1, 3);

        // Hour
        dateGrid.add(new Label("Hour (0-23):"), 0, 4);
        TextField hourField = new TextField("12");
        dateGrid.add(hourField, 1, 4);

        // Submit Button
        Button submitButton = new Button("Create Event");
        submitButton.setStyle("-fx-base: #4CAF50; -fx-text-fill: white;");

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
                submitButton, statusLabel
        );

        // Submit Button Action
        submitButton.setOnAction(e -> {
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

                // Show room selection dialog
                int roomNo = rentRoom(eventDate);
                if (roomNo == -1) {
                    statusLabel.setText("Room selection cancelled or no rooms available.");
                    return;
                }

                // Check wallet balance
                if (org.wallet.getBalance() < Database.rooms.get(roomNo).getPrice()) {
                    statusLabel.setText("Insufficient funds!");
                    return;
                }

                // Deduct from wallet
                org.wallet.setBalance(org.wallet.getBalance() - Database.rooms.get(roomNo).getPrice());
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

                org.events.add(newEvent);
                if (!org.ReservedRooms.contains(Database.rooms.get(roomNo))) {
                    org.ReservedRooms.add(Database.rooms.get(roomNo));
                }

                statusLabel.setStyle("-fx-text-fill: green;");
                statusLabel.setText("Event created successfully!");

            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Back = new Button("Back");
        Back.setOnAction(e -> Main.get_stage().setScene(manageEvents(org)));
        root.setBottom(Back);
        root.setCenter(mainLayout);
        return new Scene(root, 800, 520);
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

    private static void showAvailableRooms(Date eventDate, GridPane grid) {
        grid.getChildren().clear(); // Clear previous room listings

        int row = 0;
        for (int i = 0; i < Database.rooms.size(); i++) {
            if (Database.rooms.get(i).IsAvailable(eventDate)) {
                Label roomLabel = new Label("Room " + Database.rooms.get(i).getID() +
                        " - Price: $" + Database.rooms.get(i).getPrice());
                grid.add(roomLabel, 0, row);
                row++;
            }
        }

        if (row == 0) {
            grid.add(new Label("No rooms available for the selected time."), 0, 0);
        }
    }
    static Scene showProfile(Organizer org) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(org.getUsername());
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        usernameLabel.setLabelFor(usernameField);
        HBox username = new HBox(usernameLabel, usernameField);
        username.setAlignment(Pos.CENTER);
        username.setSpacing(5);
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField(org.getPassword());
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        passwordLabel.setLabelFor(passwordField);
        HBox password = new HBox(passwordLabel, passwordField);
        password.setAlignment(Pos.CENTER);
        password.setSpacing(5);
        Label dateOfBirthLabel = new Label("Date of Birth: " + org.getDateOfBirth().toString());
        dateOfBirthLabel.setAlignment(Pos.CENTER);
        Label walletBalanceLabel = new Label("Balance: " + String.valueOf(org.getWallet().getBalance()));
        walletBalanceLabel.setAlignment(Pos.CENTER);
        Label eventLabel = new Label("Events:");
        eventLabel.setAlignment(Pos.CENTER);
        Label statusUsername = new Label();
        Label statuspassword = new Label();
        Button updateInfo = new Button("Update Information");
        updateInfo.setOnAction(e->{if (!usernameField.getText().isEmpty()) {
            if (!Character.isLetter(usernameField.getText().charAt(0))) {
                System.out.println("Username must start with a letter (A-Z, a-z). Try again.");
                statusUsername.setText("Username must start with a letter (A-Z, a-z). Try again.");
                statusUsername.setStyle("-fx-text-fill: red;");
                return;


            }
            if (usernameField.getText().contains(" ")) {
                System.out.println("Username cannot contain spaces. Try again.");
                statusUsername.setText("Username cannot contain spaces. Try again.");
                statusUsername.setStyle("-fx-text-fill: red;");
                return;


            }
            if (usernameField.getText().length() < 4 || usernameField.getText().length() > 20) {
                System.out.println("Username must be 4-20 characters long. Try again.");
                statusUsername.setText("Username must be 4-20 characters long. Try again.");
                statusUsername.setStyle("-fx-text-fill: red;");
                return;

            }
            org.setUsername(usernameField.getText());
            System.out.println("Username changed successfully.");
            statusUsername.setText("Updated");
            statusUsername.setStyle("-fx-text-fill: green;");

        }

            if (!passwordField.getText().isEmpty()) {//password


                if (passwordField.getText().length() < 4 || passwordField.getText().length() > 20) {
                    System.out.println("Password must be 4-20 characters long. Try again.");
                    statuspassword.setText("Password must be 4-20 characters long. Try again.");
                    statuspassword.setStyle("-fx-text-fill: red;");
                    return;
                }
                org.setPassword(passwordField.getText());
                statuspassword.setText("Updated");
                statuspassword.setStyle("-fx-text-fill: green;");
            }
            Main.primaryStage.setScene(showProfile(org));
        });
        VBox vbox = new VBox(username, statusUsername, password, statuspassword, dateOfBirthLabel, walletBalanceLabel,  updateInfo, eventLabel);
        vbox.setAlignment(Pos.CENTER);
        for (int i = 0; i < org.events.size(); i++) {
            Label name = new Label("Name: " + org.events.get(i).getName());
            Label description = new Label("Description: " + org.events.get(i).getDescription());
            Label category = new Label("Category: " + org.events.get(i).getCategory().getName());
            Label price = new Label("Price: " + org.events.get(i).getPrice());
            Label room = new Label("Room: " + org.events.get(i).getRoom().getID());
            HBox event = new HBox(name, description, category, price, room);
            for (int j = 0; j < org.events.get(i).getAttendees().size(); j++) {
                event.getChildren().add(new Label("    Name: " + org.events.get(i).getAttendees().get(j).getUsername()));
            }
            event.getChildren().add(new Label("Date of Event: " + org.events.get(i).getDate()));
            vbox.getChildren().add(event);
        }
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Back = new Button("Back");
        Back.setOnAction(e -> Main.get_stage().setScene(dashboard(org)));
        root.setBottom(Back);
        root.setCenter(vbox);
        return new Scene(root,800,520);
    }
}
