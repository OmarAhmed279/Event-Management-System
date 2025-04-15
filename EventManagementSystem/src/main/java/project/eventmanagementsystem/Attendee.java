/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;
/**
 *
 * @author Omars
 */
import java.util.ArrayList;

public class Attendee extends User {
    public enum Gender { MALE, FEMALE}
    
    private Gender gender;
    private Wallet wallet;
    private ArrayList<String> interests;
    private ArrayList<Event> registeredEvents;
    private String address;
    
    public Attendee(String username, String password, Date dateOfBirth, 
                   Gender gender, String address) {
        super(username, password, dateOfBirth);
        this.gender = gender;
        this.wallet = new Wallet(0);
        this.interests = new ArrayList<>();
        this.registeredEvents = new ArrayList<>();
        this.address = address;
        Database.attendees.add(this);
        Database.users.add(this);
    }
    
    // Getters and setters
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    
    public Wallet getWallet() { return wallet; }
    
    public ArrayList<String> getInterests() { return interests; }
    
    public ArrayList<Event> getRegisteredEvents() { return registeredEvents; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    @Override
    public void showDashboard() {
        System.out.println("Attendee Dashboard");
        System.out.println("Registered Events:");
        for (Event event : registeredEvents) {
            System.out.println("- " + event.getName());
        }
        System.out.println("Wallet Balance: " + wallet.getBalance());
    }
    
    // Attendee-specific methods
    public boolean buyTicket(Event event) {
        if (wallet.getBalance() >= event.getPrice()) {
            wallet.setBalance(wallet.getBalance() - event.getPrice());
            registeredEvents.add(event);
            event.getOrganizer().getWallet().setBalance(
                event.getOrganizer().getWallet().getBalance() + event.getPrice());
            Database.creatorsBalance += event.getPrice();
            return true;
        }
        return false;
    }
    
    public void browseEvents() {
        for (Event event : Database.events) {
            System.out.println(event.getName() + " - " + event.getPrice());
        }
    }
    
    public void viewRegisteredEvents() {
        for (Event event : registeredEvents) {
            System.out.println(event.getName());
        }
    }
}