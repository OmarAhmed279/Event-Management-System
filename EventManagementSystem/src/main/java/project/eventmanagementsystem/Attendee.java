
package project.eventmanagementsystem;

import java.util.*;

public class Attendee extends User {

    private Gender gender;
    private Wallet wallet;
    private ArrayList<Category> interests;
    private ArrayList<Event> registeredEvents;
    private String address;
    public enum Gender {MALE, FEMALE}
    static final public Scanner in = new Scanner(System.in);

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
                    String gender, String address, Category interest) {
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
                    Gender gender, String address, ArrayList<Category> interest) {
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

    public ArrayList<Category> getInterests() {
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
        System.out.println("--------------------Dashboard--------------------");
        System.out.println("[1] Show Profile");
        System.out.println("[2] Update Profile");
        System.out.println("[3] Browse Events");
        System.out.println("[4] See Registered Events");
        System.out.println("[5] Manage wallet");
        System.out.println("[5] Logout");
        int choice = 0;
        boolean logout = false;
        while (true) {
            try {
                choice = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        while (true) {
            if (choice == 1) {
                this.showProfile();
                break;
            } else if (choice == 2) {
                this.UpdateInformation();
                break;
            } else if (choice == 3) {
                this.browseEvents();
                break;
            }else if (choice == 4) {
                this.viewRegisteredEvents();
                break;
            }else if (choice == 5) {
                this.ManageWallet();
                break;
            } else if (choice == 6) {
                logout = true;
                break;
            } else {
                System.out.println("Invalid input. Try again");
                in.next();
                choice = in.nextInt();
            }
        }
        if (logout)
        {
            User.logOut();
        } else {
            this.showDashboard();
        }
    }

    private void ManageWallet() {
        System.out.println("--------------------Manage wallet--------------------");
        System.out.println("[1] Add money to wallet");
        System.out.println("[2] see Balance");
        System.out.println("[3] Return to Dashboard");
        boolean back = false;
        int choice = 0;
        while (true) {
            try {
                choice = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        while (true) {
            if (choice == 1) {
                this.AddMoney();
                break;
            } else if (choice == 2) {
                System.out.println("Wallet Balance: " + this.wallet.getBalance());
                choice = in.nextInt();
            } else if (choice == 3) {
                back = true;
                break;
            } else {
                System.out.println("Invalid input, please try again.");
                choice = in.nextInt();
            }
        }
        if (back)
        {
            this.showDashboard();
        }
    }

    private void AddMoney() {
        System.out.println("Enter amount of money you wish to add to the wallet: ");
        int amount;
        while (true)
        {
            try {
                amount = in.nextInt();
                if (amount > 0) {
                    this.wallet.setBalance(amount);
                    break;
                } else {
                    System.out.println("Invalid amount. please try again: ");
                    in.next();
                }
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Try again.");
                in.next();
            }
        }
        this.ManageWallet();
    }

   /* private void ManageEvents(Event event) {
        if(buyTicket(event))
        {
            return;
        }
    }*/

    // Attendee-specific methods
    public boolean buyTicket(Event event) {
        if (wallet.getBalance() >= event.getPrice()) {
            wallet.setBalance(wallet.getBalance() - event.getPrice());
            registeredEvents.add(event);
            event.getOrganizer().getWallet().setBalance(event.getOrganizer().getWallet().getBalance() + event.getPrice());
            return true;
        }
        return false;
    }

    public void browseEvents() {
        ArrayList<Event> not_interesting_events = new ArrayList<>();
        for (int i = 0; i < Database.events.size(); i++)
        {
            boolean found = false;
            for(int j = 0; j < Database.categories.size();)
            {
                if(Database.events.get(i).getCategory().getName().equalsIgnoreCase(Database.categories.get(j).getName()))
                {
                    int index = Database.events.indexOf(Database.events.get(i))+1;
                    System.out.println( "[" + index + "]"+ " " + Database.events.get(i).getName() + " - " + Database.events.get(i).getDate() + " - " + Database.events.get(i).getPrice() + " - " + Database.events.get(i).getOrganizer().getUsername() + " - " + Database.events.get(i).getCategory().getName() + " - " + Database.events.get(i).getDescription());
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                not_interesting_events.add(Database.events.get(i));
            }
        }
        for (Event event : not_interesting_events)
        {
            int index = Database.events.indexOf(event)+1;
            System.out.println( "[" + index + "]"+ " " + event.getName() + " - " +event.getDate() + " - " + event.getPrice() + " - " + event.getOrganizer().getUsername() + " - " + event.getCategory().getName() + " - " + event.getDescription());
        }
        System.out.println("Enter Event number to register it eg.(1,2) or 0 to go back to the dashboard");
        boolean valid = false;
        int index = 0;
        while(!valid)
        {
            try
            {
                index = in.nextInt();
                if(index <= Database.events.size() && index >= 1)
                {
                    valid = true;
                }
                else if(index == 0)
                {
                    valid = true;
                    showDashboard();
                }
                else
                {
                    System.out.println("Invalid Event, please try again.");
                    in.next();
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Invalid Event, please try again.");
                in.next();
            }

        }
        if(buyTicket(Database.events.get(index - 1)))
        {
            System.out.println("Ticket bought :)");
            showDashboard();
        }
        else
        {
            System.out.println("Insufficient funds :(");
            showDashboard();
        }
    }

    public void viewRegisteredEvents() {
        for (Event event : registeredEvents) {
            System.out.println(event.getName() + " - " + event.getDate() + " - " + event.getOrganizer().getUsername() + " - " + event.getCategory().getName() + " - " + event.getDescription());
        }
    }
}