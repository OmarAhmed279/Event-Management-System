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

import java.util.InputMismatchException;

public class OrganizerGUI {
    public static Scene dashboard(Organizer org) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Logout = new Button("Logout");
        Logout.setOnAction(e -> Main.get_stage().setScene(UserGUI.signupRegistration()));
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
        scrollPane.setContent(eventsContainer);

        // Refresh method to update the UI
        Runnable refreshEvents = () -> {
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
        };

        // Initial load
        refreshEvents.run();

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

    private static Scene addEvent(Organizer org) {
        //still not finished
        BorderPane root = new BorderPane();
        return new Scene(root,800,520);
    }

    static Scene showProfile(Organizer org) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(org.getUsername());
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox username = new HBox(usernameLabel, usernameField);
        username.setSpacing(5);
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField(org.getPassword());
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox password = new HBox(passwordLabel, passwordField);
        password.setSpacing(5);
        Label dateOfBirthLabel = new Label("Date of Birth:");
        TextField dateOfBirthField = new TextField(org.getDateOfBirth().toString());
        dateOfBirthField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox dateOfBirth = new HBox(dateOfBirthLabel, dateOfBirthField);
        dateOfBirth.setSpacing(5);
        Label walletBalanceLabel = new Label("Balance");
        TextField walletBalanceField = new TextField(String.valueOf(org.getWallet().getBalance()));
        walletBalanceField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox walletBalance = new HBox(walletBalanceLabel, walletBalanceField);
        Button updateInfo = new Button("Update Information");
        //updateInfo.setOnAction(e->{7OT HENA EL UPDATE INFO BETA3ET USER BETA3ET EL TAW2AM WE ZABATOOHA});
        VBox vbox = new VBox(username, password, dateOfBirth, walletBalance, updateInfo);
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
