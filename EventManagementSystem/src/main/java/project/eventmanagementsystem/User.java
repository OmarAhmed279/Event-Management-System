package project.eventmanagementsystem;

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
        Database.users.add(this);
        this.setID();
    }

    

    public User(String Username, String Password, Date dateOfbirth) {
        this.Username = Username;
        this.Password = Password;
        this.DateOfBirth = dateOfbirth;
        Database.users.add(this);
        this.setID();
    }

    public void setID() { //i changed all the set id in all classes due to a reason too long to explain so if you wanna know why ask me in call -omar
        this.ID = Database.users.indexOf(this);
    }

    public abstract void showDashboard();

    public abstract void showProfile();

    public void UpdateInformation() {
        System.out.println("----------------------Update Profile information------------------------");
        System.out.println("[1] Update username");
        System.out.println("[2] Update password");
        System.out.println("[3] Update date of birth");
        int choice;
        while (true) {
            try {
                choice = in.nextInt();
                if (choice == 1) {
                    System.out.println("Enter new Username :");
                    in.nextLine();
                    String newName = in.nextLine();
                    while (true) {
                        if (newName.isEmpty()) {
                            System.out.println("Username cannot be empty. Try again.");
                            newName = in.nextLine().trim();
                            continue;
                        }
                        if (!Character.isLetter(newName.charAt(0))) {
                            System.out.println("Username must start with a letter (A-Z, a-z). Try again.");
                            newName = in.nextLine().trim();
                            continue;
                        }
                        if (newName.contains(" ")) {
                            System.out.println("Username cannot contain spaces. Try again.");
                            newName = in.nextLine().trim();
                            continue;
                        }
                        if (newName.length() < 4 || newName.length() > 20) {
                            System.out.println("Username must be 4-20 characters long. Try again.");
                            newName = in.nextLine().trim();
                            continue;
                        }
                        this.setUsername(newName);
                        System.out.println("Username changed successfully.");
                        break;
                    }
                    break;
                } else if (choice == 2) {
                    String newPassword;
                    while (true) {
                        System.out.println("Enter New Password: ");
                        in.nextLine();
                        newPassword = in.nextLine().trim();
                        if (newPassword.isEmpty()) {
                            System.out.println("Password cannot be empty. Try again.");
                            continue;
                        }
                        if (newPassword.length() < 4 || newPassword.length() > 20) {
                            System.out.println("Password must be 4-20 characters long. Try again.");
                            continue;
                        }
                        this.setPassword(newPassword);
                        System.out.println("Password changed successfully.");
                        break;
                    }
                    break;
                } else if (choice == 3) {
                    System.out.println("Enter New Date of birth: ");
                    System.out.println("Year: ");
                    int year = 0;
                    while (true)
                    {
                        while (true) {
                            try {
                                year = in.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Try again.");
                                in.next();
                            }
                        }
                        if (year > 2015)
                        {
                            System.out.println("You are too young. Try again.");
                            continue;
                        }
                        break;
                    }
                    System.out.println("Month: ");
                    int month = 0;
                    while (true)
                    {
                        while (true) {
                            try {
                                month = in.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Try again.");
                                in.next();
                            }
                        }
                        if (month < 0 || month > 12)
                        {
                            System.out.println("Invalid input. Try again.");
                            continue;
                        }
                        break;
                    }
                    month--;
                    System.out.println("Day: ");
                    int NewDay = in.nextInt();
                    while (NewDay < 1 || NewDay > 31) {
                        System.out.println("Invalid Day. Try again.");
                        NewDay = in.nextInt();
                    }
                    Date NewDate = new Date(year, month, NewDay);
                    this.setDateOfBirth(NewDate);
                    System.out.println("Date changed successfully.");
                    break;

                } else {
                    System.out.println("Invalid input. Try Again.");
                    in.nextLine();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Try Again.");
                in.nextLine();
            }
        }
        this.showDashboard();
    }

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

    public void setIsSuspended(boolean sus) {
        this.isSuspended = sus; //sus?
    }

    public int getID() {
        return ID;
    }

    public static void Home() {
        System.out.println("Welcome to the Event Management System");
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
                in.nextLine();

                System.out.println("[1] Sign Up as Organizer");
                System.out.println("[2] Sign Up as Attendee");
                System.out.println("[3] Login");
            }
        }
    }

    public static void signUp(int x) {
        String UserName;
        String Password;
        Date dateOfbirth;
        boolean same = false;
        System.out.println("Enter Username (spaces before and after the username are neglected): ");
        in.nextLine();
        UserName = in.nextLine();
        while (true) {
            if (UserName.isEmpty()) {
                System.out.println("Username cannot be empty. Try again.");
                UserName = in.nextLine().trim();
                continue;
            }
            if (!Character.isLetter(UserName.charAt(0))) {
                System.out.println("Username must start with a letter (A-Z, a-z). Try again.");
                UserName = in.nextLine().trim();
                continue;
            }
            if (UserName.contains(" ")) {
                System.out.println("Username cannot contain spaces. Try again.");
                UserName = in.nextLine().trim();
                continue;
            }
            if (UserName.length() < 4 || UserName.length() > 20) {
                System.out.println("Username must be 4-20 characters long. Try again.");
                UserName = in.nextLine().trim();
                continue;
            }
            for (int i = 0; i < Database.users.size(); i++) {
                if (UserName.equals(Database.users.get(i).getUsername())) {
                    System.out.println("Username already exists. Try again.");
                    same = true;
                    break;
                }
            }
            if (same) {
                same = false;
                UserName = in.nextLine();
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
        while (year > 2015 || year < 1900) {
            if (year > 2015) {
                System.out.println("You are so young. please enter valid year: ");
                year = in.nextInt();
            } else if (year < 1900) {
                System.out.println("You are so old . please enter valid year: ");
                year = in.nextInt();
            }
        }
        System.out.println("Month: ");
        int month = in.nextInt();
        while (month > 12 || month < 1) {
            System.out.println("Invalid Month. Try again.");
            month = in.nextInt();
        }
        System.out.println("Day: ");
        int day = in.nextInt();
        while (day < 1 || day > 31) {
            System.out.println("Invalid Day. Try again.");
            day = in.nextInt();
        }
        //fix error handling
        System.out.println("Enter Address: ");
        String Ad = in.next();
        if (x == 1) {
            dateOfbirth = new Date(year, month, day);
            User newuser = new Organizer(UserName, Password, dateOfbirth);
            Database.users.add(newuser);
            Database.organizers.add((Organizer) newuser);
            System.out.println("User created Succesfully.");
            Home();
        } else if (x == 2) {
            dateOfbirth = new Date(year, month, day);
            System.out.println("Enter Gender: (M or F)");
            in.nextLine();
            String gen = in.nextLine();
            while (!gen.equalsIgnoreCase("M") && !gen.equalsIgnoreCase("F")) {
                System.out.println("Invalid input,must be M or F.");
                gen = in.nextLine();
            }
            for (int i = 0; i < Database.categories.size(); i++) {
                System.out.println("Interest [" + i + "]: ");
                System.out.println("    Name: " + Database.categories.get(i).getName());
                System.out.println("    Description: " + Database.categories.get(i).getDescription());
            }
            System.out.println("Enter Category ID: ");
            int id = 0;
            while (true)
            {
                while (true) {
                    try {
                        id = in.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Try again.");
                        in.next();
                    }
                }
                if(id < 0 || id > Database.categories.size())
                {
                    System.out.println("Invalid input. Try again.");
                    continue;
                }
                break;
            } // DONT FORGET TO ADD VALIDATION YA OMAAAAARRRRRRRRR YA AHMEDDDDD!!!!!!! done - omar
            User newuser = new Attendee(UserName, Password, dateOfbirth, gen, Ad, Database.categories.get(id));
            System.out.println("User created Successfully.");
            Home();
        }
    }

    public static void login() {
        System.out.println("Enter Username:");
        String name;
        String password;
        in.nextLine();
        name = in.nextLine();
        boolean isFound = false;
        User U = null;
        while (!isFound) {
            for (int i = 0; i < Database.users.size(); i++) {
                //System.out.println(Database.users.get(i).getUsername());
                if (name.equals(Database.users.get(i).getUsername())) {
                    isFound = true;
                    U = Database.users.get(i);
                    break;
                }
            }
            if (isFound) {
                break;
            }
            System.out.println("Invalid user name");
            System.out.println("Enter Username: ");
            name = in.nextLine();
        }
        System.out.println("Valid Username");
        isFound = false;
        System.out.println("Enter your Password: ");
        password = in.nextLine();
        while (true) {
            if (U.getPassword().equals(password)) {
                System.out.println("Valid Password. Welcome " + U.getUsername() + "!");
                break;
            } else {
                System.out.println("Invalid Password. Try again.");
                password = in.nextLine();
            }
        }
        /*while (!isFound) {
            for (int i = 0; i < Database.users.size(); i++) {
                if (password.equals(Database.users.get(i).Password)) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                break;
            }
            System.out.println("Invalid Password");
            System.out.println("Enter Password: ");
            password = in.next();
        }*/
        if (U.getIsSuspended()) {
            System.out.println("User is suspended.");
            Home();
        } else {
            if (U instanceof Attendee) {
                ((Attendee) U).showDashboard();
            } else if (U instanceof Organizer) {
                ((Organizer) U).showDashboard();
            } else {
                ((Admin) U).showDashboard();
            }
        }
    }

    public static void logOut() {
        Home();
    }

}



