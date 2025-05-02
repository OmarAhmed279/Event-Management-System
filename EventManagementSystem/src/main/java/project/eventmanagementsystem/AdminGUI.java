package project.eventmanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminGUI
{
    public static Scene dashboardScene(Admin admin)
    {
        Pane dashboardPane = new Pane();
        VBox AdminOptions = new VBox(20);
        AdminOptions.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome, Admin " + admin.getUsername());
        welcomeLabel.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 25));
        welcomeLabel.setLayoutX(250);
        welcomeLabel.setLayoutY(20);
        Button profileBtn = new Button("Manage Profile");
        profileBtn.setPrefSize(300, 30);
        Button manageUsersBtn = new Button("Manage Users");
        manageUsersBtn.setPrefSize(300, 30);
        Button manageEventsBtn = new Button("Manage Events");
        manageEventsBtn.setPrefSize(300, 30);
        Button manageRoomsBtn = new Button("Manage Rooms");
        manageRoomsBtn.setPrefSize(300, 30);
        Button manageCategoriesBtn = new Button("Manage Categories");
        manageCategoriesBtn.setPrefSize(300, 30);
        Button logoutBtn = new Button("Logout");
        logoutBtn.setPrefSize(300, 30);
        AdminOptions.getChildren().addAll(profileBtn, manageUsersBtn, manageEventsBtn, manageRoomsBtn, manageCategoriesBtn, logoutBtn );

        // Set button actions
//        profileBtn.setOnAction(e -> {
//            Main.get_stage().setScene(AdminProfileScene());
//        });
//
//        manageUsersBtn.setOnAction(e -> {
//            Main.get_stage().setScene(manageUsersScene());
//        });
//
//        manageEventsBtn.setOnAction(e -> {
//            Main.get_stage().setScene(manageEventsScene());
//        });
//
//        manageRoomsBtn.setOnAction(e -> {
//            Main.get_stage().setScene(manageRoomsScene());
//        });
//
//        manageCategoriesBtn.setOnAction(e -> {
//            Main.get_stage().setScene(manageCategoriesScene());
//        });

//        logoutBtn.setOnAction(e -> {
//            User.logOut();
//            Main.get_stage().setScene(EventManagementSystem.Home());
//        });

        dashboardPane.getChildren().addAll(
                welcomeLabel, AdminOptions
        );
       Scene AdminDashboard = new Scene(dashboardPane, 800, 520);
       return AdminDashboard;
    }
   public static Scene AdminProfileScene(Admin admin)
   {
       Pane ShowAdminProfile = new Pane();
       Label Title = new Label("Admin " + admin.getUsername() + " Profile");
       Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 25));
       Title.setLayoutX(250);
       Title.setLayoutY(20);
       Label AdminUserName = new Label("Username: ");
       AdminUserName.setLayoutX(20);
       AdminUserName.setLayoutY(50);
       AdminUserName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminUserName.setTextFill(Color.BLACK);
       TextField AdminUserNameField = new TextField();
       AdminUserNameField.setPrefSize(200, 30);
       AdminUserNameField.setLayoutX(80);
       AdminUserNameField.setLayoutY(50);
       AdminUserNameField.setText(admin.getUsername());
       AdminUserNameField.setEditable(Boolean.FALSE);
       Button EditNameBtn = new Button("Edit");
       EditNameBtn.setPrefSize(150, 30);
       EditNameBtn.setLayoutX(320);
       EditNameBtn.setLayoutY(50);
       EditNameBtn.setTextFill(Color.WHITE);
       EditNameBtn.setOnAction(e -> {
           AdminUserNameField.setEditable(Boolean.TRUE);
       });

       Label AdminPassword = new Label("Password: ");
       AdminPassword.setLayoutX(20);
       AdminPassword.setLayoutY(80);
       AdminPassword.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminPassword.setTextFill(Color.BLACK);
       TextField AdminPasswordField = new TextField();
       AdminPasswordField.setPrefSize(200, 30);
       AdminPasswordField.setLayoutX(80);
       AdminPasswordField.setLayoutY(80);
       AdminPasswordField.setText(admin.getPassword());
       AdminPasswordField.setEditable(Boolean.FALSE);
       Button EditPasswordBtn = new Button("Edit");
       EditNameBtn.setPrefSize(150, 30);
       EditNameBtn.setLayoutX(320);
       EditNameBtn.setLayoutY(80);
       EditNameBtn.setTextFill(Color.WHITE);
       EditNameBtn.setOnAction(e -> {
           AdminPasswordField.setEditable(Boolean.TRUE);
       });
       Label DateOfBirth = new Label("Date of Birth: ");
       AdminPassword.setLayoutX(20);
       AdminPassword.setLayoutY(110);
       AdminPassword.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminPassword.setTextFill(Color.BLACK);
       TextField AdminDateField = new TextField();
       AdminDateField.setPrefSize(200, 30);
       AdminDateField.setLayoutX(80);
       AdminDateField.setLayoutY(110);
       AdminDateField.setText(admin.getDateOfBirth());
       Button EditDateBtn = new Button("Edit");
       EditNameBtn.setPrefSize(150, 30);
       EditNameBtn.setLayoutX(320);
       EditNameBtn.setLayoutY(80);
       EditNameBtn.setTextFill(Color.WHITE);
       EditNameBtn.setOnAction(e -> {
           AdminDateField.setEditable(Boolean.TRUE);
       });
       ShowAdminProfile.getChildren().addAll( AdminUserNameField, AdminPasswordField, EditDateBtn, EditNameBtn, EditPasswordBtn, AdminDateField);
       Scene AdminDashboardScene = new Scene(ShowAdminProfile, 800, 520);
       return AdminDashboardScene;
   }

}
