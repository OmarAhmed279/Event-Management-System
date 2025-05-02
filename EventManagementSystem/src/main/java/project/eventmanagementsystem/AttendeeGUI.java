package project.eventmanagementsystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class AttendeeGUI {
   public static Scene attendee(){
        Pane dashboard = new Pane();
        Label LDashBorad = new Label ("Dashboard");

        LDashBorad.setLayoutX(320);
        LDashBorad.setFont(Font.font("Charter", FontWeight.EXTRA_BOLD, 30));

        Button BtnShowProfile = new Button("Show Profile");
       BtnShowProfile.setLayoutX(50);
       BtnShowProfile.setLayoutY(100);
       BtnShowProfile.setPrefWidth(200);
       BtnShowProfile.setOnAction(e->{

       });

       Button BtnUpdateProfile = new Button("Update Profile");
       BtnUpdateProfile.setLayoutX(50);
       BtnUpdateProfile.setLayoutY(150);
       BtnUpdateProfile.setPrefWidth(200);
       BtnUpdateProfile.setOnAction(e->{

       });

       Button BtnBrowseEvents = new Button("Browse Events");
       BtnBrowseEvents.setLayoutX(50);
       BtnBrowseEvents.setLayoutY(200);
       BtnBrowseEvents.setPrefWidth(200);
              BtnBrowseEvents.setOnAction(e->{

       });

       Button BtnSeeRegisteredEvents = new Button("See Registered Events");
       BtnSeeRegisteredEvents.setLayoutX(50);
       BtnSeeRegisteredEvents.setLayoutY(250);
       BtnSeeRegisteredEvents.setPrefWidth(200);
              BtnSeeRegisteredEvents.setOnAction(e->{

       });

       Button BtnManagewallet = new Button("Manage wallet");
       BtnManagewallet.setLayoutX(50);
       BtnManagewallet.setLayoutY(300);
       BtnManagewallet.setPrefWidth(200);
              BtnManagewallet.setOnAction(e->{

       });

       Button BtnLogout = new Button("Logout");
       BtnLogout.setLayoutX(50);
       BtnLogout.setLayoutY(350);
       BtnLogout.setPrefWidth(200);
              BtnLogout.setOnAction(e->{
          Main.primaryStage.setScene(Main.Home());
       });





       dashboard.getChildren().addAll(LDashBorad,BtnShowProfile,BtnUpdateProfile,BtnBrowseEvents,BtnSeeRegisteredEvents,BtnManagewallet,BtnLogout);
        Scene attendee = new Scene(dashboard,800,520);
        return attendee;
    }

}
