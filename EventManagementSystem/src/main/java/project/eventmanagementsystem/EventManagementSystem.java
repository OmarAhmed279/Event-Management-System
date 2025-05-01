package project.eventmanagementsystem;

import javafx.application.Application;
import javafx.stage.Stage;

public class EventManagementSystem extends Application
{
//    public static void main(String[] args) {
//        launch(args);
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(UserGUI.showHome());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Event Management System");
        primaryStage.show();
    }
}

