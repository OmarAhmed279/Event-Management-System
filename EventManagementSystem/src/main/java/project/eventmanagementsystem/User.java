package project.eventmanagementsystem;
import java.util.Date;
import java.util.Scanner;


/**@author Seif Shehta**/

public abstract class User {
    private String UserName;
    private String Password;
    private Date dateOfbirth;
    private Boolean isSuspended = false;
    private int ID;
    Scanner in = new Scanner (System.in);
    
    public abstract void show_dashboard();
    public abstract void showProfile();
    public abstract void UpdateInformation();
    

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(Date dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public abstract void setIsSuspended(Object obj);
    public abstract void setActive(Object obj);

    public int getID() {
        return ID;
    }

    
    
        void signUp()
    {
        System.out.println("Welcome to our event managemnet system");
        System.out.println("Enter a user name: ");
        UserName = in.next();
        System.out.println("Enter a strong password: ");
        Password = in.next();
        System.out.println("Enter your Date of birth: ");
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
        dateOfbirth = new Date(year, month, day);
    }
        
        public void login()
        {
            System.out.println("Enter your user name:");
            String name;
            String password;
            name = in.next();
            if (name.equalsIgnoreCase(this.UserName))
            {
                System.out.println(" Valid user name");
            }
            else {System.out.println(" Invalid user name");}
            System.out.println("Enter your Password:");
            password = in.next();
            if(password.equalsIgnoreCase(this.Password))
            {
                System.out.println(" Valid Password");
            }
            else{System.out.println(" Invalid password");}
            if(name== this.UserName && password == this.Password)
            {
                show_dashboard();
            }
        }
        
        public void logOut()
        {
            login();
        }
                
    }


