package project.eventmanagementsystem;
import java.util.Date;
import java.util.Scanner;


/**@author Seif Shehta**/

public abstract class User {
    private String Username;
    private String Password;
    private Date DateOfBirth;
    private Boolean isSuspended = false;
    private int ID;
    private int wallet;
    static private Scanner in = new Scanner (System.in);
     
    public User (){
        
    }
    public User(String Username, String Password, Date dateOfbirth) {
        this.Username = Username;
        this.Password = Password;
        this.DateOfBirth = dateOfbirth;
    }
    
    
    
    public abstract void show_dashboard();
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

    public abstract void setIsSuspended(Object obj);
    public abstract void setActive(Object obj);

    public int getID() {
        return ID;
    }

    public static void Home()
    {
     System.out.println("Welcome to the Event Managemnet System");
     System.out.println("[1] Sign Up as Organizer");
     System.out.println("[2] Sign Up as Attendee");
     System.out.println("[3] Login");
     int num = in.nextInt();
     if (num == 1 || num == 2)
     {
         signUp(num);
     } else if(num == 3)
     {
        login();
     }
    }
    
    public static void signUp(int x)
    {
     System.out.println("Enter Username: ");
     String UserName = in.nextLine();
     System.out.println("Enter Password: ");
     String Password = in.nextLine();
     System.out.println("Enter Date of birth: ");
     System.out.println("Year: ");
     int year = in.nextInt();
     System.out.println("Month: ");
     int month;
     do{
         month = in.nextInt();   
        }while( month < 1 || month > 12);
         System.out.println("Day: ");
         int day;
         do{
         day = in.nextInt();
        }while (day < 1 || day > 31);
       Date dateOfbirth = new Date(year, month, day); //fix error handling
          if (x == 1)
            {
                //User newuser = new Organizer(UserName, Password, dateOfbirth);
                //Database.users.add(newuser);
                //Database.organizers.add((Organizer)newuser);
            }
            else if (x == 2)
           {
               //System.out.println("Enter Gender: (M or F)");
               //String gen = in.nextLine();
               //ask about interest
               //User newuser = new Attendee(UserName, Password, dateOfbirth, gen, interest);
               //Database.users.add(newuser);
               //Database.attendees.add((Attendee)newuser);
           }
    }
    
    public static void login()
     {
            System.out.println("Enter Username:");
            String name;
            String password;
            name = in.nextLine();
            boolean isFound = false;
            while(!isFound);
            {
                for(int i = 0; i < DataBase.users.size(); i++)
                {
                    if (name.equals(DataBase.users.get(i).Username))
                    {
                        isFound = true;
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
            while(!isFound);
            {
                for(int i = 0; i < DataBase.users.size(); i++)
                {
                    if (password.equals(DataBase.users.get(i).Password))
                    {
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
            //show_dashboard();
        }
        
        public static void logOut()
        {
          Home();
        }
                
    }



