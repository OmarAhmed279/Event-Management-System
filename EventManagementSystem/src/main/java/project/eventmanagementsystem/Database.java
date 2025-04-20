/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 * @author youss
 */

import javax.xml.crypto.Data;
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

    static {
        new Room(10);
        new Room(100);
        new Room(1000);

        User owner = new Admin("mradmin","0000",new Date(1,1,1), "owner");
        //users.add(new Admin("mradmin","0000",new Date(1,1,1), "owner"));

        new Organizer("mrorg","1234",new Date(1,1,1));

        new Attendee("baduser", "1234", new Date(1,1,1), "M", "G", "notpaying");

        //admins.add(new Admin("mradmin","0000",new Date(1,1,1), "owner"));

        new Event("big", "", new Category("big", ""), 4, Database.rooms.get(0), new Date(1,1,1),Database.organizers.get(0));

        users.get(2).setIsSuspended(true);
        events.get(0).addAttendee((Attendee) users.get(2));
    }
}
