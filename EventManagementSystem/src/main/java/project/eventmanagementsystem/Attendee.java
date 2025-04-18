
package project.eventmanagementsystem;

import java.util.ArrayList;
import java.util.Date;

public class Attendee extends User {

    @Override
    public void showProfile() {

    }

    @Override
    public void UpdateInformation() {

    }

    public enum Gender {MALE, FEMALE}

    private Gender gender;
    private Wallet wallet;
    private ArrayList<String> interests;
    private ArrayList<Event> registeredEvents;
    private String address;

    public Attendee(String username, String password, Date dateOfBirth,
                    String gender, String address, String interest) {
        super(username, password, dateOfBirth);
        if (gender.equals("M")) {
            this.gender = Gender.MALE;
        } else {
            this.gender = Gender.FEMALE;
        }
        this.wallet = new Wallet(0);
        this.interests = new ArrayList<>();
        this.interests.add(interest);
        this.registeredEvents = new ArrayList<>();
        this.address = address;
    }

    public Attendee(String username, String password, Date dateOfBirth,
                    Gender gender, String address, ArrayList<String> interest) {
        super(username, password, dateOfBirth);
        this.gender = gender;
        this.wallet = new Wallet(0);
        this.interests = new ArrayList<>();
        this.interests = interest;
        this.registeredEvents = new ArrayList<>();
        this.address = address;
    }
    // Getters and setters
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public ArrayList<Event> getRegisteredEvents() {
        return registeredEvents;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
            event.getOrganizer().getWallet().setBalance(event.getOrganizer().getWallet().getBalance() + event.getPrice());
            Database.appOwnerBalance += event.getPrice();
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