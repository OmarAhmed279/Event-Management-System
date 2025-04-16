package project.eventmanagementsystem;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Made by Seif Shehta, Yousef Shehta and Omar Ahmed
 **/

public abstract class User {
    private String Username;
    private String Password;
    private Date DateOfBirth;
    private Boolean isSuspended = false;
    private int ID;
    private int wallet;
    static private Scanner in = new Scanner(System.in);

    public User() {

    }

    public User(String Username, String Password, Date dateOfbirth) {
        this.Username = Username;
        this.Password = Password;
        this.DateOfBirth = dateOfbirth;
    }


    public abstract void showDashboard();

    public abstract void showProfile();

    public abstract void UpdateInformation();


    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public Boolean getIsSuspended() {
        return isSuspended;
    }

//    public abstract void setIsSuspended(Object obj);         for admin only
//    public abstract void setActive(Object obj);

    public int getID() {
        return ID;
    }

    public static void Home() {
        System.out.println("Welcome to the Event Managemnet System");
        System.out.println("[1] Sign Up as Organizer");
        System.out.println("[2] Sign Up as Attendee");
        System.out.println("[3] Login");
        boolean invaliddata = true;
        int num;
        while (invaliddata) {
            try {
                num = in.nextInt();
                if (num == 1 || num == 2) {
                    signUp(num);
                    invaliddata = false;
                } else if (num == 3) {
                    login();
                    invaliddata = false;
                } else {
                    System.out.println("invalid input");
                }
            } catch (InputMismatchException ex) {
                System.out.println("invalid input");
            }
        }
    }

    public static void signUp(int x) {
        String UserName;
        String Password;
        Date dateOfbirth;
        while (true) {
            System.out.println("Enter Username: ");
            UserName = in.nextLine().trim();
            if (UserName.isEmpty()) {
                System.out.println("Username cannot be empty. Try again.");
                continue;
            }
            if (!Character.isLetter(UserName.charAt(0))) {
                System.out.println("Username must start with a letter (A-Z, a-z). Try again.");
                continue;
            }
            if (UserName.contains(" ")) {
                System.out.println("Username cannot contain spaces. Try again.");
                continue;
            }
            if (UserName.length() < 4 || UserName.length() > 20) {
                System.out.println("Username must be 4-20 characters long. Try again.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Enter Password: ");
            Password = in.nextLine().trim();
            if (Password.isEmpty()) {
                System.out.println("Password cannot be empty. Try again.");
                continue;
            }
            if (Password.length() < 4 || Password.length() > 20) {
                System.out.println("Password must be 4-20 characters long. Try again.");
                continue;
            }
            break;
        }
        System.out.println("Enter Date of birth: ");
        System.out.println("Year: ");
        int year = in.nextInt();
        System.out.println("Month: ");
        int month = in.nextInt();
        while(month > 12 || month < 1)
        {
            System.out.println("Invalid Month. Try again.");
            month = in.nextInt();
        }
        System.out.println("Day: ");
        int day = in.nextInt();
        while (day < 1 || day > 31);
        {
            System.out.println("Invalid Day. Try again.");
            day = in.nextInt();
        }
        dateOfbirth = new Date(year, month, day); //fix error handling
        System.out.println("Enter Address: ");
        String Ad = in.nextLine();
        if (x == 1) {
            User newuser = new Organizer(UserName, Password, dateOfbirth);
            Database.users.add(newuser);
            Database.organizers.add((Organizer)newuser);
        } else if (x == 2) {
            System.out.println("Enter Gender: (M or F)");
            String gen = in.nextLine();
            System.out.println("Enter one Interest: ");
            String interest = in.nextLine();
            User newuser = new Attendee(UserName, Password, dateOfbirth, gen, Ad, interest);
            Database.users.add(newuser);
            Database.attendees.add((Attendee)newuser);
        }
    }

    public static void login() {
        System.out.println("Enter Username:");
        String name;
        String password;
        name = in.nextLine();
        boolean isFound = false;
        User U = null;
        while (!isFound)
        {
            for (int i = 0; i < Database.users.size(); i++) {
                if (name.equals(Database.users.get(i).Username)) {
                    isFound = true;
                    U = Database.users.get(i);
                    break;
                }
            }
            System.out.println("Invalid user name");
            System.out.println("Enter Username: ");
            name = in.nextLine();
        }
        System.out.println("Valid Username");
        isFound = false;
        System.out.println("Enter your Password: ");
        password = in.nextLine();
        while (!isFound)
        {
            for (int i = 0; i < Database.users.size(); i++) {
                if (password.equals(Database.users.get(i).Password)) {
                    isFound = true;
                    break;
                }
            }
            System.out.println("Invalid Password");
            System.out.println("Enter Password: ");
            password = in.nextLine();
        }
        System.out.println("Valid Password");
        System.out.println("Dashboard");
        if (U instanceof Attendee)
        {
            ((Attendee)U).showDashboard();
        } else {
            ((Organizer)U).showDashboard();
        }

    }

    public static void logOut() {
        Home();
    }

}



