/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import static project.eventmanagementsystem.User.login;
import static project.eventmanagementsystem.User.signUp;

/**
 * Made by Seif Shehta, Yousef Shehta and Omar Ahmed
 */
public class Organizer extends User {
    Wallet wallet;
    ArrayList<Event> events;
    ArrayList<Room> ReservedRooms;
    static Scanner in = new Scanner(System.in);

    public Organizer(String name, String password, Date dateOfBirth) {
        super(name, password, dateOfBirth);
        this.wallet = new Wallet(0);
        events = new ArrayList<Event>();
        ReservedRooms = new ArrayList<Room>();
    }

    @Override
    public void showDashboard() {
        System.out.println("Welcome to Dashboard");
        System.out.println("[1] Show Profile");
        System.out.println("[2] Update Profile");
        System.out.println("[3] Manage Events");
        System.out.println("[4] Manage Rooms");
        System.out.println("[5] Manage wallet");
        System.out.println("[6] Rent Rooms");
        int choice = in.nextInt();
        while  (true)
        {
            if(choice == 1)
            {
                this.showProfile();
                break;
            }
            else if(choice == 2 )
            {
                this.UpdateInformation();
                break;
            }
            else if(choice == 3)
            {
               this.ManageEvents();
               break;
            }
            else if(choice == 4)
            {
               this.ManageRooms();
               break;
            }
            else if(choice == 5)
            {
               this.ManageWallet();
               break;
            }
            else if(choice == 6)
            {
                this.RentRooms();
                break;
            }
            else
            {
                System.out.println("Invalid input. Please try again: ");
                choice = in.nextInt();
            }
        }
       
    }

    @Override
    public void showProfile() {
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Date of Birth: " + this.getDateOfBirth());
        System.out.println("Wallet Balance: " + this.getWallet().getBalance());
        for (int i = 0; i < this.events.size(); i++)
        {
            System.out.println("Name: " + this.events.get(i).getName());
            System.out.println("Description: " + this.events.get(i).getDescription());
            System.out.println("Name: " + this.events.get(i).getName());
            System.out.println("Category: " + this.events.get(i).getCategory().getName());
            System.out.println("Price: " + this.events.get(i).getPrice());
            System.out.println("Room: " + this.events.get(i).getRoom().getID());
            for (int j = 0; j < this.events.get(i).getAttendees().size(); j++)
            {
                System.out.println("    Name: " + this.events.get(i).getAttendees().get(j).getUsername());
            }
            System.out.println("Date of Event: " + this.events.get(i).getDate());
        }

    }

    public Wallet getWallet() {
        return wallet;
    }

    public void AddMoney(double wallet) {
        this.wallet.setBalance(wallet);
    }

    public ArrayList getEvents() {
        return events;
    }

    public void AddEvent() {
        System.out.println("To add event, please Enter the following Data: ");
        System.out.println("Enter the event name");
        String name = in.nextLine();
        System.out.println("Enter Category: ");
        Category category;
        for (int i = 0; i < Database.categories.size(); i++)
        {
            System.out.println(Database.categories.get(i).getName() + " " + (i+1));
        }

        int Catindex = 0;
        Catindex = in.nextInt();
        while(Catindex < 1 || Catindex > Database.categories.size())
        {
            System.out.println("Invalid input. Try again.");
            Catindex = in.nextInt();
        }
        System.out.println("Enter Description: ");
        String description = in.nextLine();
        System.out.println("Enter price: ");
        long price = in.nextLong();
        int RoomNo;
        Date DateOfEvent;
        while (true) {
            System.out.println("Enter room number: ");
            RoomNo = in.nextInt();
            System.out.println("Enter room Date of Event: ");
            System.out.println("Year: ");
            int year = in.nextInt();
            System.out.println("Month: ");
            int month = in.nextInt();
            while(month > 12 || month < 1)
            {
                System.out.println("Invalid Month. Try again.");
                month = in.nextInt();
            }
            System.out.println("Day: ");
            int day = in.nextInt();
            while(day < 1 || day > 31);
            {
                System.out.println("Invalid Day. Try again.");
                day = in.nextInt();
            }
            DateOfEvent = new Date(year, month, day);

            if (Database.rooms.get(RoomNo).IsAvailable(DateOfEvent)) {
                Event e1 = new Event(name, description, Database.categories.get(Catindex), price, Database.rooms.get(RoomNo), DateOfEvent);
                Database.events.add(e1);
                events.add(e1);
                ReservedRooms.add(Database.rooms.get(RoomNo));
                Database.rooms.get(RoomNo).isAvailable= false;
                break;
            } else {
                System.out.println("The Rooom number " + RoomNo + " is not available in that day");
            }
        }
    }
    public void ManageRooms()
    {}
    public void ManageWallet()
    {
        System.out.println("--------------------Manage wallet--------------------");
        System.out.println("[1] Add money to wallet");
        System.out.println("[2] see Balance");
        int choice = in.nextInt();
        while (true)
        {
            if (choice == 1)
            {
                this.AddMoney();
                break;
            }
            else if (choice == 2)
            {
                this.wallet.getBalance();
                break;
            }
            else
            {
                System.out.println("Invalid input, please try again");
                choice = in.nextInt();
            }
        }
    }
    public void ManageEvents()
    {
        System.out.println("[1] Add Event: " );
        System.out.println("[2] Delete Event: " );
        System.out.println("[3] See Attendees: " );
        
        int choice = in.nextInt();
        while (true){
        if (choice == 1) {
            this.AddEvent();
            break;
        }
        else if (choice ==2){
            this.DeleteEvent();
            break; 
        }
            else if (choice ==3){
            this.SeeAttendees();
            break; 
        }
        else {
            System.out.println(" Invalid input ");
            choice = in.nextInt();
        }
        }
        
        
    }
    public void RentRooms()
    {
        SeeRooms(); 
        System.out.println("Enter the RoomId of the room you want to rent");
        int choice; 
        while (true ){
            choice = in.nextInt();
            for (int i=0 ; i< Database.AvailableRooms.size(); i++ ){
                if (choice == Database.AvailableRooms.get(i).getID()){
                    Database.rooms.get(i).isAvailable= false; 
                    this.wallet.setBalance(this.wallet.getBalance() - Database.rooms.get(i).getPrice());
                        Database.appOwnerBalance+= Database.rooms.get(i).getPrice();
                        break;
                }
                else {
                    System.out.println("This Room isn't available");
                    choice=in.nextInt();
                }
                if (choice == Database.AvailableRooms.get(i).getID()){
                    break; 
                }
            }
        }
    }
        public void SeeRooms()
    {
        
        for (int i=0 ; i<Database.rooms.size() ; i++){
            if (Database.rooms.get(i).isAvailable){
                Database.AvailableRooms.add(Database.rooms.get(i)); 
                System.out.println("The avilable Rooms are : ");
            System.out.println("The room no" + Database.rooms.get(i).getID());
        }
        }
    }
    
    public void SeeAttendees ()
    {
    for (int i=0; i< this.events.size() ; i++){
        System.out.println("Event " + (i+1));
        System.out.println("Name: " + this.events.get(i).getName());
        for (int j=0 ; j< this.events.get(i).getAttendees().size(); j++)
        {
            System.out.println("Attendee " + (j+1) + " name: " + this.events.get(i).getAttendees().get(j).getUsername());
        }
        
     }
    }
    
    public void AddMoney()
    {
        System.out.println("Enter the amount of money you want to add to the wallet: ");
        int amount =in.nextInt();
        while(true)
        {
          if(amount > 0)
          {
              this.wallet.setBalance(amount);
              break;
          }
          else
          {
              System.out.println("Invalid amount. please try again: ");
              amount = in.nextInt();
          }
        }
    }
    
    
    public void DeleteEvent()
    {   
        System.out.println("Enter the RoomId of the event you want to delete : " );
        int roomId = in.nextInt(); 
        System.out.println("Enter the date of the event you want to delete : " );
            System.out.println("Year: ");
            int year = in.nextInt();
            System.out.println("Month: ");
            int month = in.nextInt();
            while(month > 12 || month < 1)
            {
                System.out.println("Invalid Month. Try again.");
                month = in.nextInt();
            }
            System.out.println("Day: ");
            int day = in.nextInt();
            while(day < 1 || day > 31);
            {
                System.out.println("Invalid Day. Try again.");
                day = in.nextInt();
            }
            Date DateOfEvent = new Date(year, month, day);
        for (int i = 0; i< this.events.size(); i++)
        {
            if(roomId == this.events.get(i).getRoom().getID() && DateOfEvent.equals(this.events.get(i).getDate()))
            {
                Database.events.remove(events.get(i)); 
                this. events.remove(events.get(i));
                Database.rooms.get(roomId).isAvailable = true;
                
            }
        }
    }
}
