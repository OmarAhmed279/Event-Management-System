package project.eventmanagementsystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;


public class AttendeeGUI {

   public static Scene AttendeeDashboard(Attendee attendee){
       BorderPane dashboard = new BorderPane();
       VBox BtnPane = new VBox();
       Label LDashBorad = new Label ("Dashboard");


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
                  Main.primaryStage.setScene(BrowseEvents(attendee));

       });

       /*Button BtnSeeRegisteredEvents = new Button("See Registered Events");//not finished
       BtnSeeRegisteredEvents.setPrefWidth(200);
              BtnSeeRegisteredEvents.setOnAction(e->{

       });*/

       Button BtnManagewallet = new Button("Manage wallet");//not finished
       BtnManagewallet.setPrefWidth(200);
              BtnManagewallet.setOnAction(e->{
                  Main.primaryStage.setScene(ManageWallet(attendee));


       });

       Button BtnLogout = new Button("Logout");
       BtnLogout.setPrefWidth(200);
              BtnLogout.setOnAction(e->{
          Main.primaryStage.setScene(Main.Home());
       });
       BtnPane.getChildren().addAll(BtnShowProfile/*,BtnUpdateProfile*/,BtnBrowseEvents/*,BtnSeeRegisteredEvents*/,BtnManagewallet,BtnLogout);
       BtnPane.setSpacing(10);
       BtnPane.setAlignment(Pos.CENTER);
    //   dashboard.getChildren().addAll(LDashBorad,BtnPane);
       dashboard.setCenter(BtnPane);
       dashboard.setTop(LDashBorad);
       BorderPane.setAlignment(LDashBorad,Pos.CENTER);
       return new Scene(dashboard,800,520);
    }


    public static Scene ShowProfile(Attendee attendee) {
        BorderPane profile = new BorderPane();

        Label Lprofile = new Label("Profile");

        Lprofile.setFont(Font.font("Charter", FontWeight.EXTRA_BOLD, 30));


        VBox Details = new VBox();
        Details.setSpacing(10.0);
        Details.setAlignment(Pos.CENTER);


        Label LUsername = new Label("Username: " + attendee.getUsername());
        TextField tfUsername = new TextField();
        tfUsername.setPrefSize(200, 20);
       // VBox.setVgrow(tfUsername, Priority.NEVER);
        tfUsername.setMaxWidth(Region.USE_PREF_SIZE);



        Label LPassword = new Label("Password: " + attendee.getPassword());
        TextField tfPassword = new TextField();
        tfPassword.setPrefSize(200, 20);
       // VBox.setVgrow(tfPassword, Priority.NEVER);
        tfPassword.setMaxWidth(Region.USE_PREF_SIZE);


        Label LDate = new Label("Date of Birth: " + attendee.getDateOfBirth());


        Label LBalance = new Label("Wallet Balance: " + attendee.getWallet().getBalance());


        Label statusUsername = new Label();
        Label statuspassword = new Label();


        Button Btnupdate = new Button("Update");
        Btnupdate.setPrefWidth(200);
        Btnupdate.setOnAction(e -> {
            //username
            if (!tfUsername.getText().isEmpty()) {
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
           /* try {//balance
                double newBalance = Double.parseDouble(tfBalance.getText());
                if (newBalance>0) {
                attendee.getWallet().setBalance(newBalance);
                }

            }
            catch (NumberFormatException ex) {
                statusbalance.setText("Error: Please enter a valid number");
                statusbalance.setStyle("-fx-text-fill: red;");
                return;
            }*/

            Main.primaryStage.setScene(ShowProfile(attendee));
        });


        Details.getChildren().addAll(LUsername, tfUsername, statusUsername, LPassword, tfPassword, statuspassword, LDate, LBalance,/*tfBalance,statusbalance,*/ Btnupdate);

        ScrollPane scrollPane=new ScrollPane();
        VBox vscroll = new VBox();
        for (int i = 0; i < attendee.getRegisteredEvents().size(); i++) {
            VBox vbox = new VBox();
            vscroll.getChildren().add(vbox);
            Label eventLabel = new Label("Event [" + i + "]:");
            Label organizerLabel = new Label("Event organizer: " + attendee.getRegisteredEvents().get(i).getOrganizer().getUsername());
            Label nameLabel = new Label("Event name: " + attendee.getRegisteredEvents().get(i).getName());
            Label dateLabel = new Label("Event date: " + attendee.getRegisteredEvents().get(i).getDate());

            vbox.getChildren().addAll(eventLabel, organizerLabel, nameLabel, dateLabel);

        }
        scrollPane.setContent(vscroll);
        Button BtnBack = new Button("Go back");

        BtnBack.setPrefWidth(200);
        BtnBack.setOnAction(e -> {
            Main.primaryStage.setScene(AttendeeDashboard(attendee));
        });

        Details.getChildren().addAll(scrollPane,BtnBack);
        // profile.getChildren().addAll(Lprofile,Details);
        profile.setTop(Lprofile);
        BorderPane.setAlignment(Lprofile, Pos.CENTER);

        //for tf not streched

      profile.setCenter(Details);

       return new Scene(profile,800,520);
    }
    public static Scene ManageWallet(Attendee attendee){
       BorderPane Pwallet= new BorderPane();
       Label Lwallet=new Label("Manage Wallet");
        Lwallet.setFont(Font.font("Charter", FontWeight.EXTRA_BOLD, 30));
        Pwallet.setTop(Lwallet);
        BorderPane.setAlignment(Lwallet,Pos.CENTER);
VBox Details = new VBox();
Details.setSpacing(10.0);
Details.setAlignment(Pos.CENTER);
Pwallet.setCenter(Details);

        TextField tfBalance = new TextField();
        tfBalance.setPrefSize(200, 20);;
        tfBalance.setMaxWidth(Region.USE_PREF_SIZE);
        Label statusbalance=new Label();
        Label LBalance=new Label("Wallet Balance: "+attendee.getWallet().getBalance() );
        Button Btnupdate= new Button("Update");
        Btnupdate.setPrefWidth(200);
        Btnupdate.setOnAction(e->{
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
            Main.primaryStage.setScene(ManageWallet(attendee));

        }); Button BtnBack = new Button("Go back");

        BtnBack.setPrefWidth(200);
        BtnBack.setOnAction(e->{
            Main.primaryStage.setScene(AttendeeDashboard(attendee));
        });
        Details.getChildren().addAll(LBalance,tfBalance,statusbalance,Btnupdate,BtnBack);
        return new Scene(Pwallet,800,580);
    }
    public static Scene BrowseEvents (Attendee attendee){
       BorderPane borderPane = new BorderPane();
       VBox vbox = new VBox();
        Label LBrowse=new Label("Browse Events");
        LBrowse.setFont(Font.font("Charter", FontWeight.EXTRA_BOLD, 30));
        borderPane.setTop(LBrowse);
        borderPane.setCenter(vbox);
        VBox vscroll =  new VBox();
        ScrollPane scrollPane=new ScrollPane();




                    for (int i = 0 ; i <Database.events.size();i++){
                        boolean isAdded = false;
                        for (int j = 0 ; j< attendee.getRegisteredEvents().size() ;j++){
                            if(Database.events.get(i).getName().equals(attendee.getRegisteredEvents().get(j).getName() )){
                                isAdded=true;
                            }
                        }
                        if(!isAdded) {
                            HBox temphbox = new HBox();


                            Label text = new Label("Name:" + Database.events.get(i).getName() + "  ,Description:" + Database.events.get(i).getDescription() + "\n" + "Category:" + Database.events.get(i).getCategory().getName() + "  ,Price:  " + Database.events.get(i).getPrice() + "\n" + "Room:" + Database.events.get(i).getRoom().getID() + "  ,Organizer:" + Database.events.get(i).getOrganizer().getUsername() + "\n" + "Date:" + Database.events.get(i).getDate());
                            Label error = new Label("");
                            Button BtnAdd = new Button("Add");
                            BtnAdd.setPrefWidth(50);

                            temphbox.getChildren().addAll(text,error,BtnAdd);
                            vscroll.getChildren().add(temphbox);

                            int tempindex = i;
                            BtnAdd.setOnAction(e -> {
                                if (attendee.getWallet().getBalance()<Database.events.get(tempindex).getPrice()) {
                                    error.setText("Not enough money");
                                    error.setStyle("-fx-text-fill:red;");
                                    return;
                                }
                                    Database.events.get(tempindex).addAttendee(attendee);
                                    attendee.getWallet().setBalance(attendee.getWallet().getBalance() - Database.events.get(tempindex).getPrice());
                                    attendee.getRegisteredEvents().add(Database.events.get(tempindex));
                                    temphbox.getChildren().clear();
                                    vscroll.getChildren().clear();
                                    Main.primaryStage.setScene(BrowseEvents(attendee));


                            });
                        }

                }

        scrollPane.setContent(vscroll);

        Button BtnBack = new Button("Go back");

        BtnBack.setPrefWidth(200);
        BtnBack.setOnAction(e->{
            Main.primaryStage.setScene(AttendeeDashboard(attendee));
        });
        vbox.getChildren().addAll(scrollPane);
        borderPane.setBottom(BtnBack);
       return new Scene(borderPane,800,580);
    }


}





