package project.eventmanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class AdminGUI
{
    public static Scene dashboardScene(Admin admin)
    {
        Pane dashboardPane = new Pane();
        VBox AdminOptions = new VBox(20);
        AdminOptions.setAlignment(Pos.CENTER);

        VBox DashboardVBox = new VBox(20);
        DashboardVBox.setLayoutY(80);
        DashboardVBox.setLayoutX(250);
        DashboardVBox.setPrefSize(800,520);
        DashboardVBox.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome, " + admin.getUsername());
        welcomeLabel.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 25));
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

        DashboardVBox.getChildren().addAll(profileBtn, manageUsersBtn, manageEventsBtn, manageRoomsBtn, manageCategoriesBtn, logoutBtn);
        AdminOptions.getChildren().addAll(welcomeLabel, DashboardVBox);

        // Set button actions
        profileBtn.setOnAction(e -> {
            Main.get_stage().setScene(AdminProfileScene(admin));
        });

        manageUsersBtn.setOnAction(e -> {
            Main.get_stage().setScene(manageUsersScene(admin));
        });
//
        manageEventsBtn.setOnAction(e -> {
            Main.get_stage().setScene(manageEventsScene(admin));
        });

//        manageRoomsBtn.setOnAction(e -> {
//            Main.get_stage().setScene(manageRoomsScene());
//        });
//
        manageCategoriesBtn.setOnAction(e -> {
            Main.get_stage().setScene(manageCategoriesScene(admin));
        });

        logoutBtn.setOnAction(e -> {
            Main.get_stage().setScene(Main.Home());
        });

        dashboardPane.getChildren().addAll(
                welcomeLabel, AdminOptions
        );
       Scene AdminDashboard = new Scene(dashboardPane, 800, 520);
       return AdminDashboard;
    }

    public static Scene manageEventsScene(Admin admin) {
        // Main container
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        // Error label
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        // Create a scroll pane to hold all events
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        VBox eventsContainer = new VBox(10);
        eventsContainer = refreshManageEventsPane(eventsContainer,admin,errorLabel);
        scrollPane.setContent(eventsContainer);
        // Main layout
        vbox.getChildren().addAll(errorLabel, scrollPane);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        Button back = new Button("Back");
        back.setOnAction(e -> Main.get_stage().setScene(dashboardScene(admin)));
        root.setBottom(back);
        root.setCenter(vbox);

        return new Scene(root, 800, 600);
    }

    public static Scene AdminProfileScene(Admin admin)
   {
       Pane ShowAdminProfile = new Pane();

       // Titel for Admin profile scene
       Label Title = new Label("Admin " + admin.getUsername() + " Profile");
       Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 25));
       Title.setLayoutX(250);
       Title.setLayoutY(20);

       Label nameError = new Label();
       nameError.setLayoutX(20);
       nameError.setLayoutY(220);
       nameError.setTextFill(Color.DARKRED);

       Label passwordError = new Label();
       passwordError.setLayoutX(20);
       passwordError.setLayoutY(240);
       passwordError.setTextFill(Color.DARKRED);

       Label errorMsgDay = new Label();
       errorMsgDay.setLayoutX(480);
       errorMsgDay.setLayoutY(130);
       errorMsgDay.setTextFill(Color.DARKRED);
       errorMsgDay.setVisible(false);

       Label errorMsgMonth = new Label();
       errorMsgMonth.setLayoutX(480);
       errorMsgMonth.setLayoutY(150);
       errorMsgMonth.setTextFill(Color.DARKRED);
       errorMsgMonth.setVisible(false);

       Label errorMsgYear = new Label();
       errorMsgYear.setLayoutX(480);
       errorMsgYear.setLayoutY(170);
       errorMsgYear.setTextFill(Color.DARKRED);
       errorMsgYear.setVisible(false);

       ShowAdminProfile.getChildren().addAll(nameError, passwordError, errorMsgDay, errorMsgMonth, errorMsgYear);

       // Flags to validate the new inputs
       AtomicReference<Boolean> NameOk = new AtomicReference<>(true);
       AtomicReference<Boolean> PasswordOk = new AtomicReference<>(true);
       AtomicReference<Boolean> DayOk = new AtomicReference<>(true);
       AtomicReference<Boolean> MonthOk = new AtomicReference<>(true);
       AtomicReference<Boolean> YearOk = new AtomicReference<>(true);
       AtomicReference<Boolean> NameOkChanged = new AtomicReference<>(false);
       AtomicReference<Boolean> PasswordOkChanged = new AtomicReference<>(false);
       AtomicReference<Boolean> DayOkChanged = new AtomicReference<>(false);
       AtomicReference<Boolean> MonthOkChanged = new AtomicReference<>(false);
       AtomicReference<Boolean> YearOkChanged = new AtomicReference<>(false);

       // Admin username data
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
           NameOkChanged.set(true);
           AdminUserNameField.setEditable(Boolean.TRUE);
           AdminUserNameField.textProperty().addListener((obs, oldVal, newVal) -> {
               if (newVal.isEmpty()) {
                   NameOk.set(false);
                   nameError.setText("Please enter username");
                   nameError.setVisible(true);
               } else if (!Character.isLetter(newVal.charAt(0))) {
                   NameOk.set(false);
                   nameError.setText("Must start with a letter");
                   nameError.setVisible(true);
               } else if (newVal.contains(" ")) {
                   NameOk.set(false);
                   nameError.setText("No spaces allowed");
                   nameError.setVisible(true);
               } else if (newVal.length() < 4 || newVal.length() > 20) {
                   NameOk.set(false);
                   nameError.setText("4-20 characters required");
                   nameError.setVisible(true);
               } else {
                   NameOk.set(true);
                   nameError.setVisible(false);
               }
           });
       });

       // Admin password data
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
       EditPasswordBtn.setPrefSize(150, 30);
       EditPasswordBtn.setLayoutX(320);
       EditPasswordBtn.setLayoutY(80);
       EditPasswordBtn.setTextFill(Color.WHITE);
       EditPasswordBtn.setOnAction(e -> {
           PasswordOkChanged.set(true);
           AdminPasswordField.setEditable(Boolean.TRUE);
           AdminPasswordField.textProperty().addListener((obs, oldVal, newVal) -> {
               if (newVal.isEmpty()) {
                   PasswordOk.set(false);
                   passwordError.setText("Please enter password");
                   passwordError.setVisible(true);
               } else if (newVal.length() < 4 || newVal.length() > 20) {
                   PasswordOk.set(false);
                   passwordError.setText("4-20 characters required");
                   passwordError.setVisible(true);
               } else {
                   PasswordOk.set(true);
                   passwordError.setVisible(false);
               }
           });
       });


       // Date of Birth label
       Label DateOfBirth = new Label("Date of Birth: ");
       DateOfBirth.setLayoutX(20);
       DateOfBirth.setLayoutY(110);
       DateOfBirth.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       DateOfBirth.setTextFill(Color.BLACK);


       // Day of Birth
       Label AdminDayOfBirth = new Label("Day: ");
       AdminDayOfBirth.setLayoutX(20);
       AdminDayOfBirth.setLayoutY(130);
       AdminDayOfBirth.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminDayOfBirth.setTextFill(Color.BLACK);
       TextField AdminDayOfBirthField = new TextField();
       AdminDayOfBirthField.setEditable(Boolean.FALSE);
       AdminDayOfBirthField.setPrefSize(200, 30);
       AdminDayOfBirthField.setLayoutX(80);
       AdminDayOfBirthField.setLayoutY(130);
       AdminDayOfBirthField.setText(String.valueOf(admin.getDateOfBirth().getDay()));
       Button EditDayOfBirthBtn = new Button("Edit");
       EditDayOfBirthBtn.setPrefSize(150, 30);
       EditDayOfBirthBtn.setLayoutX(320);
       EditDayOfBirthBtn.setLayoutY(130);
       EditDayOfBirthBtn.setTextFill(Color.WHITE);
       EditDayOfBirthBtn.setOnAction(e -> {
           DayOkChanged.set(true);
           AdminDayOfBirthField.setEditable(Boolean.TRUE);
           AdminDayOfBirthField.textProperty().addListener((obs, oldVal, newVal) -> {
               try {
                   int day = Integer.parseInt(newVal);
                   if (day < 1 || day > 31) {
                       DayOk.set(false);
                       errorMsgDay.setText("Invalid day (1-31)");
                       errorMsgDay.setVisible(true);
                   } else {
                       DayOk.set(true);
                       errorMsgDay.setVisible(false);
                   }
               } catch (NumberFormatException ex) {
                   DayOk.set(false);
                   errorMsgDay.setText("Numbers only");
                   errorMsgDay.setVisible(true);
               }
           });
       });

       //Month of Birth
       Label AdminMonthOfBirth = new Label("Month: ");
       AdminMonthOfBirth.setLayoutX(20);
       AdminMonthOfBirth.setLayoutY(150);
       AdminMonthOfBirth.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminMonthOfBirth.setTextFill(Color.BLACK);
       TextField AdminMonthOfBirthField = new TextField();
       AdminMonthOfBirthField.setEditable(Boolean.FALSE);
       AdminMonthOfBirthField.setPrefSize(200, 30);
       AdminMonthOfBirthField.setLayoutX(80);
       AdminMonthOfBirthField.setLayoutY(150);
       AdminMonthOfBirthField.setText(String.valueOf(admin.getDateOfBirth().getMonth()));
       Button EditMonthOfBirthBtn = new Button("Edit");
       EditMonthOfBirthBtn.setPrefSize(150, 30);
       EditMonthOfBirthBtn.setLayoutX(320);
       EditMonthOfBirthBtn.setLayoutY(150);
       EditMonthOfBirthBtn.setTextFill(Color.WHITE);
       EditMonthOfBirthBtn.setOnAction(e -> {
           MonthOkChanged.set(true);
           AdminMonthOfBirthField.setEditable(Boolean.TRUE);
           AdminMonthOfBirthField.textProperty().addListener((obs, oldVal, newVal) -> {
               try {
                   int month = Integer.parseInt(newVal);
                   if (month < 1 || month > 12) {
                       MonthOk.set(false);
                       errorMsgMonth.setText("Invalid month (1-12)");
                       errorMsgMonth.setVisible(true);
                   } else {
                       MonthOk.set(true);
                       errorMsgMonth.setVisible(false);
                   }
               } catch (NumberFormatException ex) {
                   MonthOk.set(false);
                   errorMsgMonth.setText("Numbers only");
                   errorMsgMonth.setVisible(true);
               }
           });
       });

       //Year of Birth
       Label AdminYearOfBirth = new Label("Year: ");
       AdminYearOfBirth.setLayoutX(20);
       AdminYearOfBirth.setLayoutY(170);
       AdminYearOfBirth.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminYearOfBirth.setTextFill(Color.BLACK);
       TextField AdminYearOfBirthField = new TextField();
       AdminYearOfBirthField.setEditable(Boolean.FALSE);
       AdminYearOfBirthField.setPrefSize(200, 30);
       AdminYearOfBirthField.setLayoutX(80);
       AdminYearOfBirthField.setLayoutY(170);
       AdminYearOfBirthField.setText(String.valueOf(admin.getDateOfBirth().getYear()));
       Button EditYearOfBirthBtn = new Button("Edit");
       EditYearOfBirthBtn.setPrefSize(150, 30);
       EditYearOfBirthBtn.setLayoutX(320);
       EditYearOfBirthBtn.setLayoutY(170);
       EditYearOfBirthBtn.setTextFill(Color.WHITE);
       EditYearOfBirthBtn.setOnAction(e -> {
           YearOkChanged.set(true);
           AdminYearOfBirthField.setEditable(Boolean.TRUE);
           AdminYearOfBirthField.textProperty().addListener((obs, oldVal, newVal) -> {
               try {
                   int year = Integer.parseInt(newVal);
                   if (year < 1900 || year > 2015) {
                       YearOk.set(false);
                       errorMsgYear.setText("Invalid year (1900-2015)");
                       errorMsgYear.setVisible(true);
                   } else {
                       YearOk.set(true);
                       errorMsgYear.setVisible(false);
                   }
               } catch (NumberFormatException ex) {
                   YearOk.set(false);
                   errorMsgYear.setText("Numbers only");
                   errorMsgYear.setVisible(true);
               }
           });
       });

       // Admin Role
       Label AdminRole = new Label("Role: ");
       AdminRole.setLayoutX(20);
       AdminRole.setLayoutY(190);
       AdminRole.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       AdminRole.setTextFill(Color.BLACK);
       TextField AdminRoleField = new TextField();
       AdminRoleField.setEditable(Boolean.FALSE);
       AdminRoleField.setPrefSize(200, 30);
       AdminRoleField.setLayoutX(80);
       AdminRoleField.setLayoutY(190);
       AdminRoleField.setText(admin.getRole());
       Button Back = new Button("Back");
       Back.setPrefSize(150, 30);
       Back.setLayoutX(20);
       Back.setLayoutY(400);
       Back.setTextFill(Color.WHITE);
       Back.setOnAction(e -> {
           Main.get_stage().setScene(dashboardScene(admin));
       });

       // Button save changes
       Button saveBtn = new Button("Save changes");
       saveBtn.setPrefSize(150, 30);
       saveBtn.setLayoutX(320);
       saveBtn.setLayoutY(260);
       saveBtn.setTextFill(Color.WHITE);
       saveBtn.setOnAction(e -> {
           System.out.println("HEY BUT OUTSIDE IF");
           if((NameOk.get() || !NameOkChanged.get()) && (PasswordOk.get() || !PasswordOkChanged.get()) && (DayOk.get() || !DayOkChanged.get()) && (MonthOk.get() || !MonthOkChanged.get()) && (YearOk.get() || !YearOkChanged.get()))
           {
             System.out.println("HEY");
             admin.setUsername(AdminUserNameField.getText());
             admin.setPassword(AdminPasswordField.getText());
             admin.setDateOfBirth(new Date(Integer.valueOf(AdminYearOfBirthField.getText()), Integer.valueOf(AdminMonthOfBirthField.getText()) , Integer.valueOf(AdminDayOfBirthField.getText())));
             Main.get_stage().setScene(dashboardScene(admin));
           }
       });
       ShowAdminProfile.getChildren().addAll(Back, saveBtn, AdminRole, AdminMonthOfBirth, AdminYearOfBirth, AdminRoleField,
               AdminDayOfBirth, AdminUserName,AdminUserNameField, EditNameBtn, AdminPassword, AdminPasswordField,
               EditPasswordBtn, DateOfBirth, EditDayOfBirthBtn, EditMonthOfBirthBtn, EditYearOfBirthBtn , AdminDayOfBirthField, AdminMonthOfBirthField, AdminYearOfBirthField );

       Scene AdminDashboardScene = new Scene(ShowAdminProfile, 800, 520);
       return AdminDashboardScene;
   }

   public static Scene manageUsersScene(Admin admin)
   {
       Pane Mangeuser = new Pane();
       // Titel for Admin profile scene
       Label Title = new Label("Manage Users");
       Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
       Title.setLayoutX(320);
       Title.setLayoutY(20);

       VBox MangeuserVBox = new VBox(20);
       MangeuserVBox.setAlignment(Pos.CENTER);
       Button ManageSuspension = new Button("Manage User suspensions ");
       ManageSuspension.setPrefSize(300, 40);
       Button returnbtn = new Button("Return to Dashboard");
       returnbtn.setPrefSize(300, 40);
       Button ShowUsersbtn = new Button("Show Users");
       ShowUsersbtn.setPrefSize(300, 40);

       MangeuserVBox.setLayoutX(300);
       MangeuserVBox.setLayoutY(150);

       returnbtn.setPrefSize(100, 30);
       returnbtn.setLayoutX(20);
       returnbtn.setLayoutY(480);
       returnbtn.setTextFill(Color.BLACK);


       Button logout = new Button("Logout");
       logout.setPrefSize(100, 30);
       logout.setLayoutX(140);
       logout.setLayoutY(480);
       logout.setTextFill(Color.BLACK);
       logout.setOnAction(ex -> {
           Main.get_stage().setScene(Main.Home());
       });
       MangeuserVBox.getChildren().addAll(ManageSuspension, ShowUsersbtn, returnbtn);

       Mangeuser.getChildren().addAll(MangeuserVBox, Title, logout, returnbtn);


       ManageSuspension.setOnAction(e -> {
           Main.get_stage().setScene(ManageUserSuspension(admin));
       });

       returnbtn.setOnAction(e -> {
           Main.get_stage().setScene(dashboardScene(admin));
       });



       ShowUsersbtn.setOnAction(e -> {
           Pane showUsers = new Pane();
           Label showUsersLabel = new Label("Show users");
           showUsersLabel.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
           showUsersLabel.setLayoutX(320);
           showUsersLabel.setLayoutY(20);


           Button retuenbtn = new Button("Return to Dashboard");
           retuenbtn.setPrefSize(150, 30);
           retuenbtn.setLayoutX(20);
           retuenbtn.setLayoutY(30);
           retuenbtn.setTextFill(Color.BLACK);
           retuenbtn.setOnAction(ex -> {
               Main.get_stage().setScene(dashboardScene(admin));
           });



           Button showAttendeesBtn = new Button("Show Attendees");
           showAttendeesBtn.setPrefSize(300, 40);
           showAttendeesBtn.setLayoutX(280);
           showAttendeesBtn.setLayoutY(180);
           showAttendeesBtn.setTextFill(Color.BLACK);

           Button ShowOrganizersBtn = new Button("Show Organizers");
           ShowOrganizersBtn.setPrefSize(300, 40);
           ShowOrganizersBtn.setLayoutX(280);
           ShowOrganizersBtn.setLayoutY(260);
           ShowOrganizersBtn.setTextFill(Color.BLACK);

           showAttendeesBtn.setOnAction(ex -> {
               Main.get_stage().setScene(showAttendees(admin));
                   });

           ShowOrganizersBtn.setOnAction(ex -> {
               Main.get_stage().setScene(showOrganizers(admin));
           });

           showUsers.getChildren().addAll(showAttendeesBtn, ShowOrganizersBtn, showUsersLabel, logout, retuenbtn, returnbtn);
           Main.get_stage().setScene(new Scene(showUsers, 800, 520));
       });

       Scene ManageUsersScene = new Scene(Mangeuser, 800, 520);
       return ManageUsersScene;

   }

    public static Scene showAttendees(Admin admin) {
        Pane showAttendeesPane = new Pane();

        Label Title = new Label("Attendees");
        Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        Title.setLayoutX(300);
        Title.setLayoutY(20);

        Button retuenbtn = new Button("Return to Dashboard");
        retuenbtn.setPrefSize(150, 30);
        retuenbtn.setLayoutX(20);
        retuenbtn.setLayoutY(30);
        retuenbtn.setTextFill(Color.BLACK);
        retuenbtn.setOnAction(ex -> {
            Main.get_stage().setScene(dashboardScene(admin));
        });

        // Create a VBox to hold all attendee information
        VBox attendeesContainer = new VBox(10);
        attendeesContainer.setPadding(new Insets(60, 10, 10, 10));

        for (Attendee attendee : Database.attendees) {
            // Create a pane for each attendee
            Pane attendeePane = new Pane();
            attendeePane.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-padding: 10;");
            attendeePane.setPrefSize(600, 300); // Increased height to accommodate more information
            attendeePane.setLayoutX(90);
            attendeePane.setLayoutY(80);

            // Create labels for each field
            Label usernameLabel = new Label("Username: " + attendee.getUsername());
            usernameLabel.setLayoutX(10);
            usernameLabel.setLayoutY(10);

            Label dobLabel = new Label("Date of Birth: " + attendee.getDateOfBirth().getDate() + " / " +
                    attendee.getDateOfBirth().getMonth() + " / " +
                    attendee.getDateOfBirth().getYear());
            dobLabel.setLayoutX(10);
            dobLabel.setLayoutY(40);

            Label genderLabel = new Label("Gender: " + attendee.getGender().toString());
            genderLabel.setLayoutX(10);
            genderLabel.setLayoutY(70);

            Label addressLabel = new Label("Address: " + attendee.getAddress());
            addressLabel.setLayoutX(10);
            addressLabel.setLayoutY(100);

            // Interests label and display
            Label interestsTitleLabel = new Label("Interests:");
            interestsTitleLabel.setLayoutX(10);
            interestsTitleLabel.setLayoutY(130);

            // Create a horizontal flow pane for interests
            FlowPane interestsPane = new FlowPane();
            interestsPane.setLayoutX(80);
            interestsPane.setLayoutY(130);
            interestsPane.setHgap(5);


            if (attendee.getInterests().isEmpty()) {
                Label noInterestsLabel = new Label("No interests");
                noInterestsLabel.setLayoutX(30);
                noInterestsLabel.setLayoutY(160);
                interestsPane.getChildren().add(noInterestsLabel);
            } else {
                HBox interestsBox = new HBox(5); // 5px spacing between elements
                interestsBox.setLayoutX(30);
                interestsBox.setLayoutY(160);

                for (int i = 0; i < attendee.getInterests().size(); i++) {
                    interestsBox.getChildren().add(new Label(attendee.getInterests().get(i).toString()));
                    if (i < attendee.getInterests().size() - 1) {
                        interestsBox.getChildren().add(new Label(" - "));
                    }
                }
                interestsPane.getChildren().add(interestsBox);
            }

            // Registered Events label and display
            Label eventsTitleLabel = new Label("Registered Events:");
            eventsTitleLabel.setLayoutX(10);
            eventsTitleLabel.setLayoutY(160);

            // Create a vertical box for events
            VBox eventsBox = new VBox(5);
            eventsBox.setLayoutX(120);
            eventsBox.setLayoutY(160);

            if (attendee.getRegisteredEvents().isEmpty()) {
                eventsBox.getChildren().add(new Label("None"));
            } else {
                for (Event event : attendee.getRegisteredEvents()) {
                    eventsBox.getChildren().add(new Label("- " + event.toString())); // Assuming Event has a good toString()
                }
            }

            attendeePane.getChildren().addAll(
                    usernameLabel, dobLabel, genderLabel,
                    addressLabel, interestsTitleLabel, interestsPane,
                    eventsTitleLabel, eventsBox
            );

            // Add the attendee pane to the container
            attendeesContainer.getChildren().add(attendeePane);
        }

        // Create a scroll pane and set the attendees container as its content
        ScrollPane scrollPane = new ScrollPane(attendeesContainer);
        scrollPane.setLayoutX(90);
        scrollPane.setLayoutY(80);
        scrollPane.setPrefSize(620, 450); // Slightly larger to accommodate more content
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Only vertical scrolling

        // Add all components to the main pane
        showAttendeesPane.getChildren().addAll(Title, scrollPane, retuenbtn);
        return new Scene(showAttendeesPane, 800, 600);
    }

    public static Scene showOrganizers(Admin admin) {
        Pane showOrganizersPane = new Pane();

        Label Title = new Label("Organizers");
        Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        Title.setLayoutX(300);
        Title.setLayoutY(20);

        Button retuenbtn = new Button("Return to Dashboard");
        retuenbtn.setPrefSize(150, 30);
        retuenbtn.setLayoutX(20);
        retuenbtn.setLayoutY(30);
        retuenbtn.setTextFill(Color.BLACK);
        retuenbtn.setOnAction(ex -> {
            Main.get_stage().setScene(dashboardScene(admin));
        });

        // Create a VBox to hold all attendee information
        VBox organizersContainer = new VBox(10);
        organizersContainer.setPadding(new Insets(60, 10, 10, 10));

        for (Organizer organizer : Database.organizers) {
            // Create a pane for each attendee
            Pane organizerPane = new Pane();
            organizerPane.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-padding: 10;");
            organizerPane.setPrefSize(600, 300); // Increased height to accommodate more information
            organizerPane.setLayoutX(90);
            organizerPane.setLayoutY(80);

            // Create labels for each field
            Label usernameLabel = new Label("Username: " + organizer.getUsername());
            usernameLabel.setLayoutX(10);
            usernameLabel.setLayoutY(10);

            Label dobLabel = new Label("Date of Birth: " + organizer.getDateOfBirth().getDate() + " / " +
                    organizer.getDateOfBirth().getMonth() + " / " +
                    organizer.getDateOfBirth().getYear());
            dobLabel.setLayoutX(10);
            dobLabel.setLayoutY(40);

            // Interests label and display
            Label EventsLabel = new Label("Events Made:  ");
            EventsLabel.setLayoutX(10);
            EventsLabel.setLayoutY(70);

            // Create a horizontal flow pane for interests
            FlowPane EventsPane = new FlowPane();
            EventsPane.setLayoutX(90);
            EventsPane.setLayoutY(70);
            EventsPane.setHgap(5);

            if (organizer.getEvents().isEmpty()) {
                Label noEventsLabel = new Label("No events made yet");
                noEventsLabel.setLayoutX(30);
                noEventsLabel.setLayoutY(100);
                EventsPane.getChildren().add(noEventsLabel);
            } else {
                HBox EventsBox = new HBox(5);
                EventsBox.setLayoutX(30);
                EventsBox.setLayoutY(100);

                for (int i = 0; i < organizer.getEvents().size(); i++) {
                    EventsBox.getChildren().add(new Label(organizer.getEvents().get(i).toString()));
                    if (i < organizer.getEvents().size() - 1) {
                        EventsBox.getChildren().add(new Label(" - "));
                    }
                }
                EventsPane.getChildren().add(EventsBox);
            }

            Label ReservedRoomsTitleLabel = new Label("Reserved Rooms:");
            ReservedRoomsTitleLabel.setLayoutX(10);
            ReservedRoomsTitleLabel.setLayoutY(130);

            // Create a vertical box for Rooms
            VBox RoomsBox = new VBox(5);
            RoomsBox.setLayoutX(110);
            RoomsBox.setLayoutY(130);

            if (organizer.getRooms().isEmpty()) {
                RoomsBox.getChildren().add(new Label("None"));
            } else {
                for (Room room : organizer.getRooms()) {
                    RoomsBox.getChildren().add(new Label("- " + room.toString()));
                }
            }

            organizerPane.getChildren().addAll(
                    usernameLabel, dobLabel,
                    EventsLabel, EventsPane,
                    ReservedRoomsTitleLabel, RoomsBox
            );

            organizersContainer.getChildren().add(organizerPane);
        }

        ScrollPane scrollPane = new ScrollPane(organizersContainer);
        scrollPane.setLayoutX(90);
        scrollPane.setLayoutY(80);
        scrollPane.setPrefSize(620, 450); // Slightly larger to accommodate more content
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Only vertical scrolling

        // Add all components to the main pane
        showOrganizersPane.getChildren().addAll(Title, scrollPane, retuenbtn);

        // Return the scene
        return new Scene(showOrganizersPane, 800, 600);
    }

    public static Scene ManageUserSuspension(Admin admin) {
        Pane usersuspensions = new Pane();
        Label usersSuspension = new Label("Users suspensions");
        usersSuspension.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        usersSuspension.setLayoutX(300);
        usersSuspension.setLayoutY(20);

        ScrollPane usersuspensionsScrollPane = new ScrollPane();
        usersuspensionsScrollPane.setFitToHeight(true);
        usersuspensionsScrollPane.setFitToWidth(true);
        usersuspensionsScrollPane.setLayoutX(100);
        usersuspensionsScrollPane.setLayoutY(100);
        usersuspensionsScrollPane.setPrefSize(600, 300);

        Button Back = new Button("Back");
        Back.setPrefSize(100, 30);
        Back.setLayoutX(20);
        Back.setLayoutY(480);
        Back.setTextFill(Color.BLACK);
        Back.setOnAction(e -> {
            Main.get_stage().setScene(manageUsersScene(admin));
        });

        Button returnbtn = new Button("Return to Dashboard");
        returnbtn.setPrefSize(150, 30);
        returnbtn.setLayoutX(20);
        returnbtn.setLayoutY(30);
        returnbtn.setTextFill(Color.BLACK);
        returnbtn.setOnAction(e -> {
            Main.get_stage().setScene(dashboardScene(admin));
        });

        usersuspensions.getChildren().addAll(usersSuspension, usersuspensionsScrollPane, Back, returnbtn);

        VBox Vscroll = new VBox(10);
        Vscroll.setPadding(new Insets(10));

        for (int i = 0; i < Database.users.size(); i++) {
            User currentUser = Database.users.get(i);
            HBox userEntry = new HBox(10);
            userEntry.setAlignment(Pos.CENTER_LEFT);

            Button userButton = new Button(currentUser.getUsername());
            userButton.setPrefWidth(150);

            // Create action buttons but hide them initially
            Button suspendButton = new Button("Suspend");
            Button unsuspendButton = new Button("Unsuspend");
            Button deleteButton = new Button("Delete");

            suspendButton.setVisible(false);
            unsuspendButton.setVisible(false);
            deleteButton.setVisible(false);

            // Create status label for this user
            Label statusLabel = new Label();
            statusLabel.setPrefWidth(200);
            statusLabel.setLayoutX(350);
            statusLabel.setLayoutY(100 + (i * 40));

            // Set actions for each button
            suspendButton.setOnAction(e -> {
                currentUser.setIsSuspended(true);
                statusLabel.setText("Suspended: " + currentUser.getUsername());
                statusLabel.setTextFill(Color.DARKRED);

                // Hide action buttons after operation
                suspendButton.setVisible(false);
                unsuspendButton.setVisible(false);
                deleteButton.setVisible(false);
            });

            unsuspendButton.setOnAction(e -> {
                currentUser.setIsSuspended(false);
                statusLabel.setText("Unsuspended: " + currentUser.getUsername());
                statusLabel.setTextFill(Color.GREEN);

                // Hide action buttons after operation
                suspendButton.setVisible(false);
                unsuspendButton.setVisible(false);
                deleteButton.setVisible(false);
            });

            deleteButton.setOnAction(e -> {
                // Store the deleted user's ID before removal
                int deletedId = currentUser.getID();

                // Remove the user from database
                Database.users.remove(currentUser);

                statusLabel.setText("Deleted: " + currentUser.getUsername());
                statusLabel.setTextFill(Color.RED);
                for (User user : Database.users) {
                    user.setID();
                }
                // Hide action buttons after operation
                suspendButton.setVisible(false);
                unsuspendButton.setVisible(false);
                deleteButton.setVisible(false);
            });
            // Show/hide action buttons when user button is clicked
            userButton.setOnAction(e -> {
                boolean showActions = !suspendButton.isVisible();
                suspendButton.setVisible(showActions);
                unsuspendButton.setVisible(showActions);
                deleteButton.setVisible(showActions);
            });

            userEntry.getChildren().addAll(userButton, suspendButton, unsuspendButton, deleteButton, statusLabel);
            Vscroll.getChildren().add(userEntry);
        }

        usersuspensionsScrollPane.setContent(Vscroll);
        Scene usersuspensionsScene = new Scene(usersuspensions, 800, 520);
        return usersuspensionsScene;
    }

    public static Scene manageCategoriesScene(Admin admin) {
        Pane manageCategoriesPane = new Pane();
        Label Title = new Label("Manage Categories");
        Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        Title.setLayoutX(300);
        Title.setLayoutY(20);

        Button Back = new Button("Back");
        Back.setPrefSize(100, 30);
        Back.setLayoutX(20);
        Back.setLayoutY(480);
        Back.setTextFill(Color.BLACK);
        Back.setOnAction(e -> {
            Main.get_stage().setScene(dashboardScene(admin));
        });

        Button logout = new Button("Logout");
        logout.setPrefSize(100, 30);
        logout.setLayoutX(140);
        logout.setLayoutY(480);
        logout.setTextFill(Color.BLACK);
        logout.setOnAction(ex -> {
            Main.get_stage().setScene(Main.Home());
        });

        Button AddCategoriesBtn = new Button("Add Categories");
        AddCategoriesBtn.setPrefSize(300, 40);
        AddCategoriesBtn.setLayoutX(280);
        AddCategoriesBtn.setLayoutY(180);
        AddCategoriesBtn.setTextFill(Color.BLACK);

        Button ShowCategoriesBtn = new Button("Show Categories");
        ShowCategoriesBtn.setPrefSize(300, 40);
        ShowCategoriesBtn.setLayoutX(280);
        ShowCategoriesBtn.setLayoutY(260);
        ShowCategoriesBtn.setTextFill(Color.BLACK);

        AddCategoriesBtn.setOnAction(ex -> {
            Main.get_stage().setScene(AddCategoriesScene(admin));
        });

        ShowCategoriesBtn.setOnAction(ex -> {
            Main.get_stage().setScene(ShowCategoriesScene(admin));
        });
        manageCategoriesPane.getChildren().addAll(Title, Back, logout, AddCategoriesBtn, ShowCategoriesBtn);
        Scene manageCategoriesScene = new Scene(manageCategoriesPane, 800, 520);
        return manageCategoriesScene;
    }

    public static Scene AddCategoriesScene(Admin admin) {
        Pane AddCategoriesPane = new Pane();
        Label Title = new Label("Add Categories");
        Title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        Title.setLayoutX(300);
        Title.setLayoutY(20);

        Label nameLabel = new Label("Category Name:");
        nameLabel.setLayoutX(200);
        nameLabel.setLayoutY(120);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter category name (letters only)");
        nameField.setLayoutX(300);
        nameField.setLayoutY(120);
        nameField.setPrefWidth(300);


        Label descLabel = new Label("Description:");
        descLabel.setLayoutX(200);
        descLabel.setLayoutY(160);

        TextArea descArea = new TextArea();
        descArea.setPromptText("Enter category description");
        descArea.setLayoutX(300);
        descArea.setLayoutY(160);
        descArea.setPrefWidth(300);
        descArea.setPrefHeight(100);
        descArea.setWrapText(true);

        Button submitButton = new Button("Add Category");
        submitButton.setPrefSize(140, 35);
        submitButton.setLayoutX(350);
        submitButton.setLayoutY(300);

        // Status message
        Label statusLabel = new Label();
        statusLabel.setLayoutX(300);
        statusLabel.setLayoutY(350);


        Button backButton = new Button("Back");
        backButton.setPrefSize(100, 30);
        backButton.setLayoutX(20);
        backButton.setLayoutY(480);
        backButton.setTextFill(Color.BLACK);
        backButton.setOnAction(e -> {
            Main.get_stage().setScene(manageCategoriesScene(admin));
        });

        Button returnbtn = new Button("Return to Dashboard");
        returnbtn.setPrefSize(150, 30);
        returnbtn.setLayoutX(20);
        returnbtn.setLayoutY(30);
        returnbtn.setTextFill(Color.BLACK);
        returnbtn.setOnAction(e -> {
            Main.get_stage().setScene(dashboardScene(admin));
        });


        Button logout = new Button("Logout");
        logout.setPrefSize(100, 30);
        logout.setLayoutX(140);
        logout.setLayoutY(480);
        logout.setTextFill(Color.BLACK);
        logout.setOnAction(ex -> {
            Main.get_stage().setScene(Main.Home());
        });

        // Validation patterns
        String namePattern = "^[a-zA-Z\\s]+$"; // Only letters and spaces
        String descPattern = "^[a-zA-Z0-9\\s.,!?-]+$"; // Letters, numbers, and basic punctuation

        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String description = descArea.getText().trim();

            statusLabel.setTextFill(Color.DARKRED);

            // Validate name
            if (name.isEmpty()) {
                statusLabel.setText("Category name cannot be empty!");
                return;
            }

            if (!name.matches(namePattern)) {
                statusLabel.setText("Name can only contain letters and spaces!");
                return;
            }

            if (name.length() < 3) {
                statusLabel.setText("Name must be at least 3 characters!");
                return;
            }

            // Validate description
            if (description.isEmpty()) {
                statusLabel.setText("Description cannot be empty!");
                return;
            }

            if (!description.matches(descPattern)) {
                statusLabel.setText("Description contains invalid characters!");
                return;
            }

            if (description.length() < 10) {
                statusLabel.setText("Description must be at least 10 characters!");
                return;
            }

            // Check if category exists
            for (Category category : Database.categories) {
                if (category.getName().equals(name)) {
                    statusLabel.setText("Category already exists!");
                    return;
                }
            }

            new Category(name, description);
            statusLabel.setText("Category added successfully!");
            statusLabel.setStyle("-fx-text-fill: #00aa00;");
            nameField.clear();
            descArea.clear();
        });



        AddCategoriesPane.getChildren().addAll(Title, nameLabel, nameField, descLabel, descArea, submitButton, statusLabel, backButton, returnbtn, logout);

        return new Scene(AddCategoriesPane, 800, 520);
    }
    public static Scene ShowCategoriesScene(Admin admin) {
        Pane showCategoriesPane = new Pane();

        // Title
        Label titleLabel = new Label("Categories");
        titleLabel.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        titleLabel.setLayoutX(300);  // Centered
        titleLabel.setLayoutY(20);

        // Scroll pane for categories list
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(100);
        scrollPane.setLayoutY(100);
        scrollPane.setPrefSize(600, 300);
        scrollPane.setFitToWidth(true);

        // Back button (bottom left)
        Button backButton = new Button("Back");
        backButton.setPrefSize(100, 30);
        backButton.setLayoutX(20);
        backButton.setLayoutY(530);
        backButton.setTextFill(Color.BLACK);
        backButton.setOnAction(e -> Main.get_stage().setScene(manageCategoriesScene(admin)));

        // Return to dashboard button (top left)
        Button returnBtn = new Button("Return to Dashboard");
        returnBtn.setPrefSize(150, 30);
        returnBtn.setLayoutX(20);
        returnBtn.setLayoutY(30);
        returnBtn.setTextFill(Color.BLACK);
        returnBtn.setOnAction(e -> Main.get_stage().setScene(dashboardScene(admin)));

        // Save Changes button (bottom right)
        Button saveButton = new Button("Save Changes");
        saveButton.setPrefSize(120, 30);
        saveButton.setLayoutX(650);  // Right-aligned (800px width - 120px button - 30px margin)
        saveButton.setLayoutY(530);  // Same Y as Back button
        saveButton.setVisible(false); // Initially hidden

        // Container for categories
        VBox categoriesContainer = new VBox(5);
        categoriesContainer.setPadding(new Insets(5));

        // Edit panel (centered)
        VBox editPanel = new VBox(10);
        editPanel.setLayoutX(250);
        editPanel.setLayoutY(400);
        editPanel.setVisible(false);

        TextField editNameField = new TextField();
        editNameField.setPromptText("Category Name");
        editNameField.setPrefWidth(300);

        TextArea editDescArea = new TextArea();
        editDescArea.setPromptText("Category Description");
        editDescArea.setPrefWidth(300);
        editDescArea.setPrefHeight(100);
        editDescArea.setWrapText(true);

        editPanel.getChildren().addAll(
                new Label("Edit Category:"),
                editNameField,
                editDescArea
        );

        // Status message
        Label statusLabel = new Label();
        statusLabel.setLayoutX(300);
        statusLabel.setLayoutY(380);

        // Load categories
        loadCategories(categoriesContainer, editPanel, editNameField, editDescArea, saveButton, statusLabel);

        // Save button action
        saveButton.setOnAction(e -> {
            String newName = editNameField.getText().trim();
            String newDesc = editDescArea.getText().trim();

            if (newName.isEmpty()) {
                statusLabel.setText("Category name cannot be empty!");
                statusLabel.setTextFill(Color.RED);
                return;
            }

            Category categoryToEdit = (Category) saveButton.getUserData();
            boolean nameExists = Database.categories.stream()
                    .anyMatch(c -> c != categoryToEdit && c.getName().equalsIgnoreCase(newName));

            if (nameExists) {
                statusLabel.setText("Category name already exists!");
                statusLabel.setTextFill(Color.RED);
                return;
            }

            // Update and refresh
            categoryToEdit.setName(newName);
            categoryToEdit.setDescription(newDesc);
            statusLabel.setText("Category updated successfully!");
            statusLabel.setTextFill(Color.GREEN);
            loadCategories(categoriesContainer, editPanel, editNameField, editDescArea, saveButton, statusLabel);
            editPanel.setVisible(false);
            saveButton.setVisible(false); // Hide after saving
        });

        scrollPane.setContent(categoriesContainer);
        showCategoriesPane.getChildren().addAll(
                titleLabel, scrollPane, backButton, returnBtn, editPanel, statusLabel, saveButton
        );

        return new Scene(showCategoriesPane, 800, 600);
    }

    private static void loadCategories(VBox container, VBox editPanel,
                                       TextField editNameField, TextArea editDescArea,
                                       Button saveButton, Label statusLabel) {
        container.getChildren().clear();

        for (Category category : Database.categories) {
            HBox categoryBox = new HBox(20);
            categoryBox.setAlignment(Pos.CENTER_LEFT);
            categoryBox.setPrefWidth(550);

            Label nameLabel = new Label(category.getName());
            nameLabel.setPrefWidth(300);
            nameLabel.setStyle("-fx-font-size: 14px;");

            Button editButton = new Button("Edit");
            editButton.setPrefSize(80, 30);
            editButton.setStyle("-fx-font-size: 12px;");

            Button deleteButton = new Button("Delete");
            deleteButton.setPrefSize(80, 30);
            deleteButton.setStyle("-fx-font-size: 12px;");

            editButton.setOnAction(e -> {
                editNameField.setText(category.getName());
                editDescArea.setText(category.getDescription());
                saveButton.setUserData(category);
                editPanel.setVisible(true);
                saveButton.setVisible(true); // Show when editing
                statusLabel.setText("");
            });

            deleteButton.setOnAction(e -> {
                Database.categories.remove(category);
                statusLabel.setText("Deleted: " + category.getName());
                statusLabel.setTextFill(Color.RED);
                loadCategories(container, editPanel, editNameField, editDescArea, saveButton, statusLabel);
                editPanel.setVisible(false);
                saveButton.setVisible(false); // Hide after deletion
            });

            categoryBox.getChildren().addAll(nameLabel, editButton, deleteButton);
            container.getChildren().add(categoryBox);
        }
    }
    public static VBox refreshManageEventsPane(VBox vbox, Admin admin, Label error) {
        vbox.getChildren().clear();

        for (Event evt : Database.events) {
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
                    evt.getOrganizer().getEvents().remove(evt);

                    // Remove from database
                    Database.events.remove(evt);

                    // Update IDs - this might need reconsideration
                    for (int j = 0; j < Database.events.size(); j++) {
                        Database.events.get(j).setID();
                    }

                    // Refresh the UI
                    error.setText("");
                } catch (Exception ex) {
                    error.setText("Error deleting event: " + ex.getMessage());
                }
                refreshManageEventsPane(vbox,admin,error);
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

            vbox.getChildren().add(eventBox);
        }
        return  vbox;
    }
}




