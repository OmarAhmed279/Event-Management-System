/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author omar
 */
public class Organizer extends User {
    Wallet wallet;
    ArrayList<Event> events;
    static Scanner in = new Scanner(System.in);

    public Organizer(String name, String password, Date dateOfBirth) {
        super(name, password, dateOfBirth);
        this.wallet = new Wallet(0);
        events = new ArrayList<Event>();
    }

    @Override
    public void showDashboard() {
        System.out.println("Welcome to Dashboard");
        System.out.println("[1] Profile");
        System.out.println("[2] Manage Events");
        System.out.println("[3] See");
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

    @Override
    public void UpdateInformation() {

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
                break;
            } else {
                System.out.println("The Rooom number " + RoomNo + " is not available in that day");
            }
        }
    }

}
