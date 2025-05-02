package project.eventmanagementsystem;

import javafx.application.Application;
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
    public static Scene dashboard() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Logout = new Button("Logout");
        Logout.setOnAction(e -> Main.get_stage().setScene(UserGUI.signupRegistration()));
        root.setBottom(Logout);
        Button profile = new Button("Profile");
        profile.setOnAction(e -> Main.get_stage().setScene(showProfile()));
        Button manageEvents = new Button("Manage Events");
        manageEvents.setOnAction(e -> Main.get_stage().setScene(manageEvents()));
        Button manageWallet = new Button("Manage Wallet");
        manageWallet.setOnAction(e -> Main.get_stage().setScene(manageWallet()));
        HBox buttonsPane = new HBox(profile, manageEvents, manageWallet);
        buttonsPane.setSpacing(10.0);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setStyle("-fx-background-color: transparent;");
        root.setCenter(buttonsPane);
        return new Scene(root, 800, 520);
    }

    static Scene manageWallet() {
        TextField balanceField = new TextField(this.getWallet().getBalance());
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");
        Button addMoney = new Button("Add Money");
        addMoney.setOnAction(e -> {
            try {
                int amount = Integer.parseInt(balanceField.getText().trim());
                if (amount > 0) {
                    this.wallet.setBalance(this.wallet.getBalance() + amount);
                    balanceField.setText(this.getWallet().getBalance());
                    errorLabel.setText("");
                } else {
                    errorLabel.setText("Invalid amount!");
                }
            } catch (InputMismatchException ex) {
                errorLabel.setText("Invalid input!");
            }
        });
        VBox vbox = new VBox(balanceField, addMoney);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Back = new Button("Back");
        Back.setOnAction(e -> Main.get_stage().setScene(dashboard()));
        root.setBottom(Back);
        root.setCenter(vbox);
        return new Scene(root);
    }

    static Scene manageEvents() {
        Button addEvent = new Button("Add Event");
        addEvent.setAlignment(Pos.BOTTOM_CENTER);
        addEvent.setOnAction(e->Main.get_stage().setScene(addEvent()));
        VBox vbox = new VBox(addEvent);
        for (int i = 0; i < this.events.size(); i++) {
            Label name = new Label("Name: " + this.events.get(i).getName());
            Label description = new Label("Description: " + this.events.get(i).getDescription());
            Label category = new Label("Category: " + this.events.get(i).getCategory().getName());
            Label price = new Label("Price: " + this.events.get(i).getPrice());
            Label room = new Label("Room: " + this.events.get(i).getRoom().getID());
            Button delete = new Button("Delete");
            int id = this.events.get(i).getID();
            delete.setOnAction(e->{
                while (id > Database.events.size() || id > 0) {
                    System.out.println("Invalid Input. Try again.");
                }
                Database.events.get(id).getRoom().removeEvent(Database.events.get(id));
                this.events.remove(Database.events.get(id));
                Database.events.remove(id);
                for (int j = 0; j < Database.events.size(); j++) {
                    Database.events.get(j).setID();
                }});
            HBox event = new HBox(name, description, category, price, room,delete);
            for (int j = 0; j < this.events.get(i).getAttendees().size(); j++) {
                event.getChildren().add(new Label("    Name: " + this.events.get(i).getAttendees().get(j).getUsername()));
            }
            event.getChildren().add(new Label("Date of Event: " + this.events.get(i).getDate()));
            vbox.getChildren().add(event);
        }
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Back = new Button("Back");
        Back.setOnAction(e -> Main.get_stage().setScene(dashboard()));
        root.setBottom(Back);
        root.setCenter(vbox);
        return new Scene(root);
    }

    private static Scene addEvent() {
        //still not finished
        return new Scene(root);
    }

    static Scene showProfile() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(this.getusername());
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox username = new HBox(usernameLabel, usernameField);
        username.setSpacing(5);
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField(this.getpassword());
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox password = new HBox(passwordLabel, passwordField);
        password.setSpacing(5);
        Label dateOfBirthLabel = new Label("Date of Birth:");
        TextField dateOfBirthField = new TextField(this.getDateOfBirth());
        dateOfBirthField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox dateOfBirth = new HBox(dateOfBirthLabel, dateOfBirthField);
        dateOfBirth.setSpacing(5);
        Label walletBalanceLabel = new Label("Balance");
        TextField walletBalanceField = new TextField(this.getWallet().getBalance());
        walletBalanceField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        HBox walletBalance = new HBox(walletBalanceLabel, walletBalanceField);
        Button updateInfo = new Button("Update Information");
        //updateInfo.setOnAction(e->{7OT HENA EL UPDATE INFO BETA3ET USER BETA3ET EL TAW2AM WE ZABATOOHA});
        VBox vbox = new VBox(username, password, dateOfBirth, walletBalance, updateInfo);
        vbox.setAlignment(Pos.CENTER);
        for (int i = 0; i < this.events.size(); i++) {
            Label name = new Label("Name: " + this.events.get(i).getName());
            Label description = new Label("Description: " + this.events.get(i).getDescription());
            Label category = new Label("Category: " + this.events.get(i).getCategory().getName());
            Label price = new Label("Price: " + this.events.get(i).getPrice());
            Label room = new Label("Room: " + this.events.get(i).getRoom().getID());
            HBox event = new HBox(name, description, category, price, room);
            for (int j = 0; j < this.events.get(i).getAttendees().size(); j++) {
                event.getChildren().add(new Label("    Name: " + this.events.get(i).getAttendees().get(j).getUsername()));
            }
            event.getChildren().add(new Label("Date of Event: " + this.events.get(i).getDate()));
            vbox.getChildren().add(event);
        }
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button Back = new Button("Back");
        Back.setOnAction(e -> Main.get_stage().setScene(dashboard()));
        root.setBottom(Back);
        root.setCenter(vbox);
        return new Scene(root);
    }
}
