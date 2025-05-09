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
        salutation.setFont(Font.font("Monotype Corsiva", FontWeight.EXTRA_BOLD, 35));
        salutation.setAlignment(Pos.CENTER);
        salutation.setTextFill(Color.WHITE);
        salutation.setLayoutX(90);
        salutation.setLayoutY(20);

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
        l1.setTextFill(Color.BEIGE);
        l1.setFont(Font.font("TimesNewRoman", FontWeight.SEMI_BOLD, 14));
        Label l3 = new Label("I already have account or I am an admin");
        l3.setTextFill(Color.BEIGE);
        l3.setFont(Font.font("TimesNewRoman", FontWeight.SEMI_BOLD, 14));
        NotesOptions.getChildren().addAll(l1,  l3);
        home.getChildren().add(NotesOptions);
        NotesOptions.setLayoutX(320);
        NotesOptions.setLayoutY(105);

        ImageView imageView = new ImageView(UserGUI.class.getResource("/Hi.png").toExternalForm());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setLayoutX(610);
        imageView.setLayoutY(320);
        home.getChildren().add(imageView);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(550);
        rectangle.setHeight(60);
        rectangle.setFill(Color.BEIGE);
        rectangle.setX(80);
        rectangle.setY(350);
        home.getChildren().add(rectangle);
        Scene Home = new Scene(home, 800, 520);
        // Correct way to set button action
        option1.setOnAction(event -> {
            primaryStage.setScene(UserGUI.signupRegistration());
            primaryStage.show();
        });
        option2.setOnAction(event -> {
            primaryStage.setScene(UserGUI.LoginScene());
            primaryStage.show();

        });
        return Home;
    }

}