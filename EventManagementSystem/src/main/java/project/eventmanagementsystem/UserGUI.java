package project.eventmanagementsystem;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
class UserGUI {
    public static Scene showHome() {
        Pane home = new Pane();
        Label salutation = new Label("Welcome to Event Management System");
        salutation.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 30));
        salutation.setLayoutX(20);
        salutation.setLayoutY(20);
        // Load the image (make sure it's in your resources folder)
        Image image = new Image(UserGUI.class.getResource("/Background1.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        home.getChildren().add(imageView);

        home.getChildren().add(salutation);
        return new Scene(home, 650, 500);
    }
}

