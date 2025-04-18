/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import javax.xml.crypto.Data;
import java.util.Scanner;

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
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                this.Addroom();
                break;
            } else if (choice == 2) {
                this.deleteRoom();
                break;
            } else if (choice == 3)
            {
                this.showRooms();
                break;
            } else {
                System.out.println("Invalid input. Please try again: ");
                choice = in.nextInt();
            }
        }
    }

    private void showRooms() {
        System.out.println("There is currently " + Database.rooms.size() + " rooms.");
        this.showDashboard();
    }

    private void deleteRoom() {
        System.out.println("Enter Room ID: ");
        int id = in.nextInt();
        while(id > Database.rooms.size() || id < 0)
        {
            System.out.println("Invalid ID. Try again.");
            id = in.nextInt();
        }
        Database.rooms.remove(id);
        System.out.println("Room removed successfully.");
        this.showDashboard();
    }

    public void ManageEvents() {
        
    }

    public void ManageUsers() {

    }
}