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
    public static ArrayList<Room> rooms = new ArrayList<>(){
        //{
       //     add(new Room(10));
       //     add(new Room(100));
        //    add(new Room(1000));
        //}
    };
    public static ArrayList<User> users = new ArrayList<>(){
       // {
        //    add(new Admin("mradmin","0000",new Date(1,1,1), "owner"));
        //}
    };
    public static ArrayList<Admin> admins = new ArrayList<>(){
       // {
       //     add(new Admin("mradmin","0000",new Date(1,1,1), "owner"));
       // }
    };
    ;
    public static ArrayList<Organizer> organizers = new ArrayList<>(){
       // {
        //    add(new Organizer("mrorg","1234",new Date(1,1,1)));
        //}
    };
    public static ArrayList<Attendee> attendees = new ArrayList<>();
    // Event-related lists
    public static ArrayList<Event> events = new ArrayList<>(){
        //{
        //    add(new Event("big", "", new Category("big", ""), 4, Database.rooms.get(0), new Date(1,1,1),Database.organizers.get(0)));
        //}
    };
    public static ArrayList<Category> categories = new ArrayList<>();
    //public static ArrayList<Room> AvailableRooms = new ArrayList<Room>();
    // app creators wallet replica
    public static double appOwnerBalance;
}
