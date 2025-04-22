/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import javax.xml.crypto.Data;
import java.awt.*;
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
        Database.organizers.add(this);
        this.setID();
    }

    public Organizer(String user, String pass, Date d, Event e) {
        this(user, pass, d);
        this.events.add(e);
        this.ReservedRooms.add(Database.events.get(Database.events.indexOf(e)).getRoom());
        Database.organizers.add(this);
        this.setID();
    }

    @Override
    public void showDashboard() {
        System.out.println("--------------------Dashboard--------------------");
        System.out.println("[1] Show Profile");
        System.out.println("[2] Update Profile");
        System.out.println("[3] Manage Events");
        //System.out.println("[4] Manage Rooms");
        System.out.println("[4] Manage wallet");
        //System.out.println("[6] Rent Rooms");
        System.out.println("[5] Logout");
        int choice = 0;
        boolean logout = false;
        while (true) {
            try {
                choice = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        while (true) {
            if (choice == 1) {
                this.showProfile();
                break;
            } else if (choice == 2) {
                this.UpdateInformation();
                break;
            } else if (choice == 3) {
                this.ManageEvents();
                break;
            } else if (choice == 4) {
                this.ManageWallet();
                break;
            } else if (choice == 5) {
                logout = true;
                break;
            } else {
                System.out.println("Invalid input. Try again");
                choice = in.nextInt();
            }
        }
        if (logout)
        {
            User.logOut();
        } else {
            this.showDashboard();
        }
    }

    @Override
    public void showProfile() {
        System.out.println("--------------------Profile--------------------");
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Date of Birth: " + this.getDateOfBirth());
        System.out.println("Wallet Balance: " + this.getWallet().getBalance());
        for (int i = 0; i < this.events.size(); i++) {
            System.out.println("Name: " + this.events.get(i).getName());
            System.out.println("Description: " + this.events.get(i).getDescription());
            System.out.println("Name: " + this.events.get(i).getName());
            System.out.println("Category: " + this.events.get(i).getCategory().getName());
            System.out.println("Price: " + this.events.get(i).getPrice());
            System.out.println("Room: " + this.events.get(i).getRoom().getID());
            for (int j = 0; j < this.events.get(i).getAttendees().size(); j++) {
                System.out.println("    Name: " + this.events.get(i).getAttendees().get(j).getUsername());
            }
            System.out.println("Date of Event: " + this.events.get(i).getDate());
        }
    }

    public Wallet getWallet() {
        return wallet;
    }

    /*public void AddMoney(double wallet) {
        if(wallet <=0)
        {
            System.out.println("invalid amount!");
            return;
        }
        this.wallet.setBalance(wallet);
    }*/

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void AddEvent() {
        boolean valid = false;
        while(!valid) {
            try {
                System.out.println("To add event, please Enter the following Data: ");
                System.out.println("Enter Event Name: ");
                in.nextLine();
                String name = in.nextLine();
                System.out.println("Enter Category ID: ");
                //Category category;
                for (int i = 0; i < Database.categories.size(); i++) {
                    System.out.println(Database.categories.get(i).getName() + " [" + (i + 1) + "]");
                }
                int Catindex = in.nextInt();
                while (Catindex < 1 || Catindex > Database.categories.size()) {
                    System.out.println("Invalid input. Try again.");
                    Catindex = in.nextInt();
                }
                System.out.println("Enter Description: ");
                in.nextLine();
                String description = in.nextLine();
                System.out.println("Enter price: ");
                long price = in.nextLong();
                int RoomNo;
                Date DateOfEvent;
                while (true) {
                    System.out.println("Enter Date of Event: ");
                    System.out.println("Year: ");
                    int year = in.nextInt();
                    System.out.println("Month: ");
                    int month = in.nextInt();
                    while (month > 12 || month < 1) {
                        System.out.println("Invalid Month. Try again.");
                        month = in.nextInt();
                    }
                    System.out.println("Day: ");
                    int day = in.nextInt();
                    while (day < 1 || day > 31) {
                        System.out.println("Invalid Day. Try again.");
                        day = in.nextInt();
                    }
                    DateOfEvent = new Date(year, month, day);
                    RoomNo = RentRooms(DateOfEvent);
                    if (Database.rooms.get(RoomNo).IsAvailable(DateOfEvent)) {
                        Event e1 = new Event(name, description, Database.categories.get(Catindex - 1), price, Database.rooms.get(RoomNo), DateOfEvent, this);
                        //Database.events.add(e1);
                        events.add(e1);
                        ReservedRooms.add(Database.rooms.get(RoomNo));
                        //Database.rooms.get(RoomNo).addEvent(e1);
                        this.wallet.setBalance(this.wallet.getBalance() - Database.rooms.get(RoomNo).getPrice());
                        Database.appOwnerBalance += Database.rooms.get(RoomNo).getPrice();
                        break;
                    } else {
                        System.out.println("The Room number " + RoomNo + " is not available.");
                        System.out.println("\n");
                    }
                }
                valid=true;
            }
            catch(InputMismatchException ex){
                System.out.println("invalid input try again");
            }
        }
        this.ManageEvents();
    }

    public void ManageWallet() {
        System.out.println("--------------------Manage wallet--------------------");
        System.out.println("[1] Add money to wallet");
        System.out.println("[2] see Balance");
        System.out.println("[3] Return to Dashboard");
        boolean back = false;
        int choice = 0;
        while (true) {
            try {
                choice = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        while (true) {
            if (choice == 1) {
                this.AddMoney();
                break;
            } else if (choice == 2) {
                System.out.println("Wallet Balance: " + this.wallet.getBalance());
                break;
            } else if (choice == 3) {
                back = true;
                break;
            } else {
                System.out.println("Invalid input, please try again.");
                choice = in.nextInt();
            }
        }
        if (back)
        {
            this.showDashboard();
        }
    }

    public void ManageEvents() {
        System.out.println("--------------------Manage Events--------------------");
        System.out.println("[1] Add Event");
        System.out.println("[2] Delete Event");
        System.out.println("[3] Show Events");
        System.out.println("[4] Return to Dashboard");
        boolean back = false;
        int choice = 0;
        while (true) {
            try {
                choice = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        while (true) {
            if (choice == 1) {
                this.AddEvent();
                break;
            } else if (choice == 2) {
                this.DeleteEvent();
                break;
            } else if (choice == 3) {
                this.SeeAttendees();
                break;
            } else if (choice == 4) {
                back = true;
                break;
            } else {
                System.out.println(" Invalid input ");
                choice = in.nextInt();
            }
        }
        if (back)
        {
            this.showDashboard();
        }
    }

    public int RentRooms(Date d) {
        SeeRooms(d);
        System.out.println("Enter Room Id of the room you wish to rent.");
        /**
         boolean isfound = false;
         while (true) {
         choice = in.nextInt();
         for (int i = 0; i < Database.AvailableRooms.size(); i++) {
         if (choice == Database.AvailableRooms.get(i).getID()) {
         Database.rooms.get(i).isAvailable = false;
         this.wallet.setBalance(this.wallet.getBalance() - Database.rooms.get(i).getPrice());
         Database.appOwnerBalance += Database.rooms.get(i).getPrice();
         isfound = true;
         break;
         }
         }
         if (isfound)
         {
         isfound = false;
         break;
         } else {
         System.out.println("Room isn't available. Try again.");
         }

         }**/
        while (true) {
            int choice = in.nextInt();
            if (choice > Database.rooms.size() || choice < 0) {
                System.out.println("Invalid input. Try again.");
            } else if (Database.rooms.get(choice).IsAvailable(d)) {
                /**Database.rooms.get(choice).isAvailable = false;
                 this.wallet.setBalance(this.wallet.getBalance() - Database.rooms.get(choice).getPrice());
                 Database.appOwnerBalance += Database.rooms.get(choice).getPrice();**/
                return choice;
            } else {
                System.out.println("Room isn't Available. Try again.");
            }
        }
    }

    public void SeeRooms(Date d) {
        System.out.println("--------------------Rent Room--------------------");
        for (int i = 0; i < Database.rooms.size(); i++) {
            if (Database.rooms.get(i).IsAvailable(d)) {
                //Database.AvailableRooms.add(Database.rooms.get(i));
                System.out.println("Room Number (" + Database.rooms.get(i).getID() + ")");
            }
        }
    }

    public void SeeAttendees() {
        for (int i = 0; i < this.events.size(); i++) {
            System.out.println("Event [" + (i + 1) + "]");
            System.out.println("    Name: " + this.events.get(i).getName());
            System.out.println("    Attendees: ");
            for (int j = 0; j < this.events.get(i).getAttendees().size(); j++) {
                System.out.println("        Attendee [" + (j + 1) + "]" + " Name: " + this.events.get(i).getAttendees().get(j).getUsername());
            }
        }
        this.ManageEvents();
    }

    public void AddMoney() {
        System.out.println("Enter amount of money you wish to add to the wallet: ");
        int amount;
        while (true)
        {
            try {
                amount = in.nextInt();
                if (amount > 0) {
                    this.wallet.setBalance(this.wallet.getBalance()+amount);
                    break;
                } else {
                    System.out.println("Invalid amount. please try again: ");
                    in.next();
                }
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        this.ManageWallet();
    }

    public void DeleteEvent() {
        //System.out.println("Enter the Room Id of the event you wish to delete: ");
        //int roomId = in.nextInt();
        //System.out.println("Enter the date of the event you wish to delete: ");
        //System.out.println("Year: ");
        //int year = in.nextInt();
        //System.out.println("Month: ");
        //int month = in.nextInt();
        //while (month > 12 || month < 1) {
        //    System.out.println("Invalid Month. Try again.");
        //    month = in.nextInt();
        //}
        //System.out.println("Day: ");
        //int day = in.nextInt();
        //while (day < 1 || day > 31) {
        //    System.out.println("Invalid Day. Try again.");
        //    day = in.nextInt();
        //}
        //Date DateOfEvent = new Date(year, month, day);
        System.out.println("Enter event ID: ");
        int id = in.nextInt();
        while (id > Database.events.size() || id > 0) {
            System.out.println("Invalid Input. Try again.");
            id = in.nextInt();
        }
        Database.events.get(id).getRoom().removeEvent(Database.events.get(id));
        this.events.remove(Database.events.get(id));
        Database.events.remove(id);
        for (int i = 0; i < Database.events.size(); i++) {
            Database.events.get(i).setID();
        }
        //for (int i = 0; i < this.events.size(); i++) {
        //if (roomId == this.events.get(i).getRoom().getID() && DateOfEvent.equals(this.events.get(i).getDate())) {
        //Database.events.remove(events.get(i));
        //this.events.remove(events.get(i));
        //Database.rooms.get(roomId).removeEvent(events.get(i));
        //      }
        //}
        this.ManageEvents();
    }
}

