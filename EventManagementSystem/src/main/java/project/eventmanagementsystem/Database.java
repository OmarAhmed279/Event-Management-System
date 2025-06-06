/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 * @author youss
 */

import java.util.ArrayList;
import java.util.Date;

public final class Database {
    // User-related lists
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Organizer> organizers = new ArrayList<>();
    public static ArrayList<Attendee> attendees = new ArrayList<>();
    // Event-related lists
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    //public static ArrayList<Room> AvailableRooms = new ArrayList<Room>();
    // app creators wallet replica
    public static double appOwnerBalance;

    public static User owner;

    public static User baduser;
    public static User seif;

    static {
        new Room(10);
        new Room(100);
        new Room(1000);

        owner = new Admin("mradmin", "0000", new Date(2000, 1, 1), "owner");
        //users.add(new Admin("mradmin","0000",new Date(1,1,1), "owner"));

        new Organizer("mrorg", "1234", new Date(2000, 1, 1));

        baduser = new Attendee("baduser", "1234", new Date(2000, 1, 1), "M", "G",new Category("notpaying","doesn't pay"));


        seif = new Attendee("seif", "1010", new Date(2007, 1, 10), "M", "ss",new Category("notpaying","doesn't pay"));

        //admins.add(new Admin("mradmin","0000",new Date(1,1,1), "owner"));

        for (int i = 0; i < 50; i++)
        {
            String name = "cool" + (i+1);
            System.out.println("Before addition -> size of Database: " + Database.categories.size());
            Category cat = new Category(name, "");
            System.out.println("After addition -> size of Database: " + Database.categories.size());
        }
        Event bigevent = new Event("big", "", Database.categories.get(1), 4, Database.rooms.get(0), new Date(2025, 1, 1), Database.organizers.get(0));
        Event smallevent = new Event("small", "", Database.categories.get(2), 4, Database.rooms.get(0), new Date(2025, 1, 1), Database.organizers.get(0));
        users.get(baduser.getID()).setIsSuspended(true); // baduser is suspended
        events.get(0).addAttendee((Attendee) users.get(2));
        ((Attendee) users.get(2)).getRegisteredEvents().add(bigevent);
        organizers.get(0).getEvents().add(bigevent);
        Database.categories.add(new Category("a","a"));
        Attendee gooduser = new Attendee("gooduser", "1234", new Date(2000, 1, 1), "M", "G",new Category("a","a"));
        System.out.println(Database.users.size());
    }
}
