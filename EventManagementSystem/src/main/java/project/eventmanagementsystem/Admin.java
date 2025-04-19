/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import javax.xml.crypto.Data;
import java.util.Scanner;
import java.util.Objects;

/**
 * @author omar
 */
public class Admin extends User {
    public static final Scanner in = new Scanner(System.in);
    private String role;


    public void Addroom() {
        System.out.println("Enter Price of room: ");
        int pr = in.nextInt();
        while (pr < 0) {
            System.out.println("Invalid Price. Try again.");
            pr = in.nextInt();
        }
        Room room = new Room(pr);
        Database.rooms.add(room);
        System.out.println("Room added successfully.");
        this.showDashboard();
    }

    @Override
    public void showDashboard() {
        System.out.println("--------------------Dashboard--------------------");
        System.out.println("[1] Show Profile");
        System.out.println("[2] Update Profile");
        System.out.println("[3] Manage Users");
        System.out.println("[4] Manage Events");
        System.out.println("[5] Manage Rooms");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                this.showProfile();
                break;
            } else if (choice == 2) {
                this.UpdateInformation();
                break;
            } else if (choice == 3) {
                this.ManageUsers();
                break;
            } else if (choice == 4) {
                this.ManageEvents();
                break;
            } else if (choice == 5) {
                this.ManageRooms();
                break;
            } else {
                System.out.println("Invalid input. Please try again: ");
                choice = in.nextInt();
            }
        }
    }

    @Override
    public void showProfile() {
        System.out.println("--------------------Profile--------------------");
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Date of Birth: " + this.getDateOfBirth());
        this.showDashboard();
    }

    public void ManageRooms() {
        System.out.println("--------------------Manage Rooms--------------------");
        System.out.println("[1] Add Room");
        System.out.println("[2] Delete Room");
        System.out.println("[3] Show Rooms");
        System.out.println("[4] Return to Dashboard");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                this.Addroom();
                break;
            } else if (choice == 2) {
                this.deleteRoom();
                break;
            } else if (choice == 3) {
                this.showRooms();
                break;
            } else if (choice == 4)
            {
                this.showDashboard();
                break;
            } else
            {
                System.out.println("Invalid input. Please try again: ");
                choice = in.nextInt();
            }
        }
    }

    private void showRooms() {
        System.out.println("There is currently " + Database.rooms.size() + " rooms.");
        for (int i = 0; i < Database.rooms.size(); i++) {
            System.out.println("\n");
            System.out.println("Room [" + i + "]:");
            for (int j = 0; j < Database.rooms.get(i).getEvents().size(); j++) {
                if (Objects.nonNull(Database.rooms.get(i).getEvents().get(j))) {
                    System.out.println("    Event organizer: " + Database.rooms.get(i).getEvents().get(j).getOrganizer().getUsername());
                    System.out.println("    Event name: " + Database.rooms.get(i).getEvents().get(j).getName());
                    System.out.println("    Event date: " + Database.rooms.get(i).getEvents().get(j).getDate());
                    System.out.println("    Attendees:");
                    for (int k = 0; k < Database.rooms.get(i).getEvents().get(j).getAttendees().size(); k++) {
                        System.out.println("        Attendee Name: " + Database.rooms.get(i).getEvents().get(j).getAttendees().get(k).getUsername());
                    }
                    System.out.println("    Event price: " + Database.rooms.get(i).getEvents().get(j).getPrice());
                    System.out.println("    Event category: " + Database.rooms.get(i).getEvents().get(j).getCategory().getName());
                    System.out.println("    Event description: " + Database.rooms.get(i).getEvents().get(j).getDescription());
                }
            }
        }
        this.showDashboard();
    }

    private void deleteRoom() {
        System.out.println("Enter Room ID: ");
        int id = in.nextInt();
        while (id > Database.rooms.size() || id < 0) {
            System.out.println("Invalid ID. Try again.");
            id = in.nextInt();
        }
        Database.rooms.remove(id);
        for (int i = 0; i < Database.rooms.size(); i++)
        {
            Database.rooms.get(i).setID();
        }
        System.out.println("Room removed successfully.");
        this.showDashboard();
    }

    public void ManageEvents() {
        System.out.println("--------------------Manage Events--------------------");
        System.out.println("[1] Delete Event");
        System.out.println("[2] See Events");
        System.out.println("[3] Return to Dashboard");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                this.deleteEvent();
                break;
            } else if (choice == 2) {
                this.seeEvents();
                break;
            } else if (choice == 3) {
                this.showDashboard();
                break;
            } else {
                System.out.println("Invalid input. Please try again: ");
                choice = in.nextInt();
            }
        }
    }

    private void seeEvents() {
        System.out.println("There is currently " + Database.events.size() + " events.");
        for (int i = 0; i < Database.events.size(); i++) {
            System.out.println("\n");
            System.out.println("Event [" + i + "]:");
            System.out.println("    Event organizer: " + Database.events.get(i).getOrganizer().getUsername());
            System.out.println("    Event name: " + Database.events.get(i).getName());
            System.out.println("    Event date: " + Database.events.get(i).getDate());
            System.out.println("    Attendees:");
            for (int k = 0; k < Database.events.get(i).getAttendees().size(); k++) {
                System.out.println("        Attendee Name: " + Database.events.get(i).getAttendees().get(k).getUsername());
            }
            System.out.println("    Event price: " + Database.events.get(i).getPrice());
            System.out.println("    Event category: " + Database.events.get(i).getCategory().getName());
            System.out.println("    Event description: " + Database.events.get(i).getDescription());
        }
        this.showDashboard();
    }

    private void deleteEvent() {
        System.out.println("Enter event ID: ");
        int id = in.nextInt();
        while (id > Database.events.size() || id > 0) {
            System.out.println("Invalid Input. Try again.");
            id = in.nextInt();
        }
        Database.events.remove(id);
        for (int i = 0; i < Database.events.size(); i++) {
            Database.events.get(i).setID();
        }
    }

    public void ManageUsers() {
        System.out.println("--------------------Manage Users--------------------");
        System.out.println("[1] Suspend User");
        System.out.println("[2] Unsuspend User");
        System.out.println("[3] Delete User");
        System.out.println("[4] Show all Users");
        System.out.println("[5] Return to Dashboard");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                this.suspendUser();
                break;
            } else if (choice == 2) {
                this.unsuspendUser();
                break;
            } else if (choice == 3) {
                this.deleteUser();
                break;
            } else if (choice == 4) {
                this.showUsers();
                break;
            } else if (choice == 5) {
                this.showDashboard();
                break;
            } else {
                System.out.println("Invalid input. Please try again: ");
                choice = in.nextInt();
            }
        }
    }

    private void showUsers() {
        System.out.println("There is currently " + Database.users.size() + " users.");
        System.out.println("\n");
        for (int i = 0; i < Database.users.size(); i++) {
            System.out.println("User [" + i + "]:");
            System.out.println("    User Type: " + Database.users.get(i).getClass().getName());
            System.out.println("    Username: " + Database.users.get(i).getUsername());
            System.out.println("    User ID: " + Database.users.get(i).getID());
            System.out.println("    Date of Birth: " + Database.users.get(i).getDateOfBirth());
            System.out.println("    Suspension Status: " + Database.users.get(i).getIsSuspended());
        }
        this.showDashboard();
    }

    private void deleteUser() {
        System.out.println("Enter User ID: ");
        int id = in.nextInt();
        while (id > Database.users.size() || id < 0) {
            System.out.println("Invalid ID. Try again.");
            id = in.nextInt();
        }
        Database.users.remove(id);
        for (int i = 0; i < Database.users.size(); i++)
        {
            Database.users.get(i).setID();
        }
        System.out.println("User deleted successfully.");
        this.showDashboard();
    }

    private void unsuspendUser() {
        System.out.println("Enter User ID: ");
        int id = in.nextInt();
        while (id > Database.users.size() || id < 0) {
            System.out.println("Invalid ID. Try again.");
            id = in.nextInt();
        }
        Database.users.get(id).setIsSuspended(false);
        System.out.println("User unsuspended successfully.");
        this.showDashboard();
    }

    private void suspendUser() {
        System.out.println("Enter User ID: ");
        int id = in.nextInt();
        while (id > Database.users.size() || id < 0) {
            System.out.println("Invalid ID. Try again.");
            id = in.nextInt();
        }
        Database.users.get(id).setIsSuspended(true);
        System.out.println("User suspended successfully.");
        this.showDashboard();
    }
}