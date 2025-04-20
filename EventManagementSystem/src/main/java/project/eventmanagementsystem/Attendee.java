
package project.eventmanagementsystem;

import java.util.ArrayList;
import java.util.Date;

public class Attendee extends User {

    private Gender gender;
    private Wallet wallet;
    private ArrayList<String> interests;
    private ArrayList<Event> registeredEvents;
    private String address;
    public enum Gender {MALE, FEMALE}

    @Override
    public void showProfile() {
        System.out.println("--------------------Profile--------------------");
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Date of Birth: " + this.getDateOfBirth());
        System.out.println("Wallet Balance: " + this.getWallet().getBalance());
        for (int i = 0; i < interests.size(); i++) {
            System.out.println("Interest [" + i + "]: " + interests.get(i));
        }
        for (int i = 0; i < registeredEvents.size(); i++) {
            System.out.println("Event [" + i + "]:");
            System.out.println("    Event organizer: " + registeredEvents.get(i).getOrganizer().getUsername());
            System.out.println("    Event name: " + registeredEvents.get(i).getName());
            System.out.println("    Event date: " + registeredEvents.get(i).getDate());
        }
        this.showDashboard();
    }

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
        Database.attendees.add(this);
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
        Database.attendees.add(this);
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