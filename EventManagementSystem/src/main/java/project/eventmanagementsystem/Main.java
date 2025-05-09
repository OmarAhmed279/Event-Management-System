package project.eventmanagementsystem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Date;

import static project.eventmanagementsystem.AttendeeGUI.AttendeeDashboard;

public class Main extends Application {
    //   public static void main(String[] args) {
    //       launch(args);
//    }
    static Stage primaryStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = Main.primaryStage;
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Event Management System");
        primaryStage.setResizable(false);


// Make sure this is the ONLY statement for this button
// Don't put any other code after setOnAction that would be unreachable
        primaryStage.setScene(Home());
        primaryStage.show();
    }

    public static Stage get_stage()
    {
        return primaryStage;
    }

    public static Scene Home()
    {
        Pane home = new Pane();
        home.setPrefSize(800, 520);
        Label salutation = new Label("Welcome to Event Management System");
        salutation.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 45));
        salutation.setAlignment(Pos.CENTER);
        salutation.setTextFill(Color.DARKGOLDENROD);
        salutation.setLayoutX(100);
        salutation.setLayoutY(20);

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
            home.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Fallback to solid color if image fails to load
            home.setStyle("-fx-background-color: lightgray;");
        }

        VBox HomeOptions = new VBox(20);
        Button option1 = new Button("Sign Up as Organizer or Attendee");
        option1.setPrefSize(250, 50);
        option1.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 13));
        Button option2 = new Button("Login");
        option2.setPrefSize(250, 50);
        option2.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 16));
        HomeOptions.setAlignment(Pos.CENTER);
        HomeOptions.setLayoutX(50);
        HomeOptions.setLayoutY(100);
        HomeOptions.getChildren().addAll(option1, option2);
        home.getChildren().add(salutation);
        home.getChildren().add(HomeOptions);
        VBox NotesOptions = new VBox(60);
        Label l1 = new Label(" create new account as an organizer or attendee");
        l1.setTextFill(Color.WHITE);
        l1.setLayoutX(300);
        l1.setLayoutY(100);
        l1.setFont(Font.font("TimesNewRoman", FontWeight.SEMI_BOLD, 20));
        Label l3 = new Label("I already have account or I am an admin");
        l3.setTextFill(Color.WHITE);
        l3.setFont(Font.font("TimesNewRoman", FontWeight.SEMI_BOLD, 20));
        l3.setLayoutX(300);
        l3.setLayoutY(130);
        NotesOptions.getChildren().addAll(l1,  l3);
        home.getChildren().add(NotesOptions);
        NotesOptions.setLayoutX(320);
        NotesOptions.setLayoutY(105);

        ImageView imageView = new ImageView(UserGUI.class.getResource("/Hi.png").toExternalForm());
        imageView.setFitWidth(280);
        imageView.setFitHeight(280);
        imageView.setLayoutX(530);
        imageView.setLayoutY(320);
        home.getChildren().add(imageView);

        ImageView imageView2 = new ImageView(UserGUI.class.getResource("/image.png").toExternalForm());
        imageView2.setFitWidth(200);
        imageView2.setFitHeight(200);
        imageView2.setLayoutX(430);
        imageView2.setLayoutY(300);
        home.getChildren().add(imageView2);

        ImageView imageView4 = new ImageView(UserGUI.class.getResource("/celebration.png").toExternalForm());
        imageView4.setFitWidth(250);
        imageView4.setFitHeight(280);
        imageView4.setLayoutX(-20);
        imageView4.setLayoutY(340);
        home.getChildren().add(imageView4);

        Label Hi = new Label("Welcome");
        Hi.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 25));
        Hi.setLayoutX(475);
        Hi.setLayoutY(370);
        Hi.setTextFill(Color.BLACK);
        home.getChildren().add(Hi);

        Scene Home = new Scene(home, 800, 600);
        // Correct way to set button action
        option1.setOnAction(event -> {
            primaryStage.setScene(UserGUI.signupRegistration());
            primaryStage.setResizable(false);
            primaryStage.show();
        });
        option2.setOnAction(event -> {
            primaryStage.setScene(UserGUI.LoginScene());
            primaryStage.show();

        });
        return Home;
    }

}