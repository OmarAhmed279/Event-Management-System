/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 *
 * @author youss
 */
import java.util.ArrayList;
 
public class Database 
{
    // User-related lists
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Organizer> organizers = new ArrayList<>();
    public static ArrayList<Attendee> attendees = new ArrayList<>();
    // Event-related lists
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Room> AvailableRooms = new ArrayList<Room>();
    // app creators wallet replica
    public static double appOwnerBalance;
}
