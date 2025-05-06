package project.eventmanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        profileBtn.setOnAction(e -> {
            Main.get_stage().setScene(AdminProfileScene(admin));
        });

        manageUsersBtn.setOnAction(e -> {
            Main.get_stage().setScene(manageUsersScene(admin));
        });
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
//
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
           AdminDayOfBirthField.setEditable(Boolean.TRUE);
           AdminDayOfBirthField.textProperty().addListener((obs, oldVal, newVal) -> {
               try {
                   int day = Integer.parseInt(newVal);
                   if (day < 1 || day > 31) {
                       DayOk.set(false);
                       errorMsgDay.setText("Invalid day (1-31)");
                       errorMsgDay.setVisible(true);
                   } else {
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
           AdminMonthOfBirthField.setEditable(Boolean.TRUE);
           AdminMonthOfBirthField.textProperty().addListener((obs, oldVal, newVal) -> {
               try {
                   int month = Integer.parseInt(newVal);
                   if (month < 1 || month > 12) {
                       MonthOk.set(false);
                       errorMsgMonth.setText("Invalid month (1-12)");
                       errorMsgMonth.setVisible(true);
                   } else {
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
           AdminYearOfBirthField.setEditable(Boolean.TRUE);
           AdminYearOfBirthField.textProperty().addListener((obs, oldVal, newVal) -> {
               try {
                   int year = Integer.parseInt(newVal);
                   if (year < 1900 || year > 2015) {
                       YearOk.set(false);
                       errorMsgYear.setText("Invalid year (1900-2015)");
                       errorMsgYear.setVisible(true);
                   } else {
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
           if(NameOk.get() && PasswordOk.get() && DayOk.get() && MonthOk.get() && YearOk.get())
           {
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

//       System.out.println("--------------------Manage Users--------------------");
//       System.out.println("[1] Suspend User");
//       System.out.println("[2] Unsuspend User");
//       System.out.println("[3] Delete User");
//       System.out.println("[4] Show all Users");
//       System.out.println("[5] Return to Dashboard");

       VBox MangeuserVBox = new VBox(20);
       MangeuserVBox.setAlignment(Pos.CENTER);
       Button ManageSuspension = new Button("Manage User suspensions ");
       ManageSuspension.setPrefSize(300, 40);
       Button returnbtn = new Button("Return to Dashboard");
       returnbtn.setPrefSize(300, 40);
       Button logoutBtn = new Button("Logout");
       logoutBtn.setPrefSize(300, 40);
       Button ShowUsersbtn = new Button("Show Users");
       ShowUsersbtn.setPrefSize(300, 40);

       MangeuserVBox.setLayoutX(300);
       MangeuserVBox.setLayoutY(150);

       Button Back = new Button("Back");
       Back.setPrefSize(100, 30);
       Back.setLayoutX(20);
       Back.setLayoutY(480);
       Back.setTextFill(Color.BLACK);
       Back.setOnAction(ex -> {
           Main.get_stage().setScene(manageUsersScene(admin));
       });


       Button logout = new Button("Logout");
       logout.setPrefSize(100, 30);
       logout.setLayoutX(140);
       logout.setLayoutY(480);
       logout.setTextFill(Color.BLACK);
       logout.setOnAction(ex -> {
           Main.get_stage().setScene(Main.Home());
       });
       MangeuserVBox.getChildren().addAll(ManageSuspension, ShowUsersbtn, returnbtn, logoutBtn);

       Mangeuser.getChildren().addAll(MangeuserVBox, Title, logout, Back);


       ManageSuspension.setOnAction(e -> {
           Main.get_stage().setScene(ManageUserSuspension(admin));
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

           showUsers.getChildren().addAll(showAttendeesBtn, ShowOrganizersBtn, showUsersLabel, logout, retuenbtn, Back);
           Main.get_stage().setScene(new Scene(showUsers, 800, 520));
       });

       Scene ManageUsersScene = new Scene(Mangeuser, 800, 520);
       return ManageUsersScene;

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

        Button retuenbtn = new Button("Return to Dashboard");
        retuenbtn.setPrefSize(150, 30);
        retuenbtn.setLayoutX(20);
        retuenbtn.setLayoutY(30);
        retuenbtn.setTextFill(Color.BLACK);
        retuenbtn.setOnAction(e -> {
            Main.get_stage().setScene(dashboardScene(admin));
        });


        Button logout = new Button("Logout");
        logout.setPrefSize(100, 30);
        logout.setLayoutX(140);
        logout.setLayoutY(480);
        logout.setTextFill(Color.BLACK);
        logout.setOnAction(e -> {
            Main.get_stage().setScene(Main.Home());
        });

        usersuspensions.getChildren().addAll(usersSuspension, usersuspensionsScrollPane, Back, logout, retuenbtn);

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

                if (currentUser instanceof Organizer) {
                    for (User user : Database.users) {
                        if (user instanceof Organizer && user.getID() > deletedId) {
                            user.setID((user.getID() - 1));
                        }
                    }
                }
                // If the deleted user was an Attendee, update other Attendees' IDs
                else if (currentUser instanceof Attendee) {
                    for (User user : Database.users) {
                        if (user instanceof Attendee && user.getID() > deletedId) {
                            user.setID(user.getID() - 1);
                        }
                    }
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

                // Clear status when showing actions
                if (showActions) {
                    statusLabel.setText("");
                }
            });

            userEntry.getChildren().addAll(userButton, suspendButton, unsuspendButton, deleteButton, statusLabel);
            Vscroll.getChildren().add(userEntry);
        }

        usersuspensionsScrollPane.setContent(Vscroll);
        Scene usersuspensionsScene = new Scene(usersuspensions, 800, 520);
        return usersuspensionsScene;
    }



}
