package project.eventmanagementsystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;




public class AttendeeGUI {

   public static Scene AttendeeDashboard(Attendee attendee){
       BorderPane dashboard = new BorderPane();
       VBox BtnPane = new VBox();
       Label LDashBorad = new Label ("Dashboard");

       LDashBorad.setLayoutX(320);
       LDashBorad.setFont(Font.font("Charter", FontWeight.EXTRA_BOLD, 30));

       Button BtnShowProfile = new Button("Show Profile");//not finished
       BtnShowProfile.setPrefWidth(200);
       BtnShowProfile.setOnAction(e->{
           Main.primaryStage.setScene(ShowProfile(attendee));
       });

       /*Button BtnUpdateProfile = new Button("Update Profile");//not finished
       BtnUpdateProfile.setPrefWidth(200);
       BtnUpdateProfile.setOnAction(e->{

       });*/

       Button BtnBrowseEvents = new Button("Browse Events");//not finished
       BtnBrowseEvents.setPrefWidth(200);
              BtnBrowseEvents.setOnAction(e->{

       });

       Button BtnSeeRegisteredEvents = new Button("See Registered Events");//not finished
       BtnSeeRegisteredEvents.setPrefWidth(200);
              BtnSeeRegisteredEvents.setOnAction(e->{

       });

       Button BtnManagewallet = new Button("Manage wallet");//not finished
       BtnManagewallet.setPrefWidth(200);
              BtnManagewallet.setOnAction(e->{

       });

       Button BtnLogout = new Button("Logout");
       BtnLogout.setPrefWidth(200);
              BtnLogout.setOnAction(e->{
          Main.primaryStage.setScene(Main.Home());
       });
       BtnPane.getChildren().addAll(BtnLogout,BtnShowProfile/*,BtnUpdateProfile*/,BtnSeeRegisteredEvents,BtnBrowseEvents,BtnSeeRegisteredEvents,BtnManagewallet);
       BtnPane.setSpacing(10);
       BtnPane.setAlignment(Pos.CENTER);
       dashboard.getChildren().addAll(LDashBorad,BtnPane);
       dashboard.setCenter(BtnPane);
       dashboard.setTop(LDashBorad);
       return new Scene(dashboard,800,520);
    }


    public static Scene ShowProfile(Attendee attendee){
       BorderPane profile = new BorderPane();

        Label Lprofile = new Label ("Profile");
        Lprofile.setLayoutX(320);
        Lprofile.setFont(Font.font("Charter", FontWeight.EXTRA_BOLD, 30));


        VBox Details = new VBox();
        Details.setSpacing(10.0);
        Details.setAlignment(Pos.CENTER);


        Label LUsername=new Label("Username: "+attendee.getUsername() );
        TextField tfUsername = new TextField();

        Label LPassword=new Label("Password: " +attendee.getPassword() );
        TextField tfPassword = new TextField();

        Label LDate=new Label("Date of Birth: "+ attendee.getDateOfBirth() );


        Label LBalance=new Label("Wallet Balance: "+attendee.getWallet().getBalance() );
        TextField tfBalance = new TextField();

        Label statusUsername=new Label();
        Label statuspassword=new Label();
        Label statusbalance=new Label();



        Button Btnupdate= new Button("Update");
        Btnupdate.setOnAction(e->{
             //username
                if(!tfUsername.getText().isEmpty()){
                    if (!Character.isLetter(tfUsername.getText().charAt(0))) {
                        System.out.println("Username must start with a letter (A-Z, a-z). Try again.");
                        statusUsername.setText("Username must start with a letter (A-Z, a-z). Try again.");
                        statusUsername.setStyle("-fx-text-fill: red;");
                        return;


                    }
                    if (tfUsername.getText().contains(" ")) {
                        System.out.println("Username cannot contain spaces. Try again.");
                        statusUsername.setText("Username cannot contain spaces. Try again.");
                        statusUsername.setStyle("-fx-text-fill: red;");
                        return;


                    }
                    if (tfUsername.getText().length() < 4 || tfUsername.getText().length() > 20) {
                        System.out.println("Username must be 4-20 characters long. Try again.");
                        statusUsername.setText("Username must be 4-20 characters long. Try again.");
                        statusUsername.setStyle("-fx-text-fill: red;");
                        return;

                    }
                    attendee.setUsername(tfUsername.getText());
                    System.out.println("Username changed successfully.");
                    statusUsername.setText("Updated");
                    statusUsername.setStyle("-fx-text-fill: green;");

                }

            if (!tfPassword.getText().isEmpty()) {//password


                if (tfPassword.getText().length() < 4 || tfPassword.getText().length() > 20) {
                    System.out.println("Password must be 4-20 characters long. Try again.");
                    statuspassword.setText("Password must be 4-20 characters long. Try again.");
                    statuspassword.setStyle("-fx-text-fill: red;");
                    return;
                }
                attendee.setPassword(tfPassword.getText());
                statuspassword.setText("Updated");
                statuspassword.setStyle("-fx-text-fill: green;");
            }
            try {//balance
                double newBalance = Double.parseDouble(tfBalance.getText());
                if (newBalance>0) {
                attendee.getWallet().setBalance(newBalance);
                }

            }
            catch (NumberFormatException ex) {
                statusbalance.setText("Error: Please enter a valid number");
                statusbalance.setStyle("-fx-text-fill: red;");
                return;
            }


        });


        Details.getChildren().addAll(LUsername,tfUsername,LPassword,tfPassword,LDate,LBalance,tfBalance);


        for (int i = 0; i < attendee.getRegisteredEvents().size(); i++) {
            Label eventLabel = new Label("Event [" + i + "]:");
            Label organizerLabel = new Label("Event organizer: " + attendee.getRegisteredEvents().get(i).getOrganizer().getUsername());
            Label nameLabel = new Label("Event name: " + attendee.getRegisteredEvents().get(i).getName());
            Label dateLabel = new Label("Event date: " + attendee.getRegisteredEvents().get(i).getDate());

            Details.getChildren().addAll(eventLabel, organizerLabel, nameLabel, dateLabel);

        }

        Button BtnBack = new Button("Go back");
        BtnBack.setLayoutX(50);
        BtnBack.setPrefWidth(200);
        BtnBack.setOnAction(e->{
            Main.primaryStage.setScene(AttendeeDashboard(attendee));
        });

        Details.getChildren().addAll(BtnBack);
        profile.getChildren().addAll(Lprofile,Details);
        profile.setTop(Lprofile);
        profile.setCenter(Details);
       return new Scene(profile,800,520);
    }



}





