/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author omar
 */
public class Organizer extends User {
    double wallet;
    ArrayList events;
    
    public Organizer(String name, String password, Date dateOfBirth)
    {
        this.wallet=0.0f;
        events = new ArrayList<Event>();
        setUsername(name);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
    }

    @Override
    public void showDashboard() {

    }

    @Override
    public void showProfile() {

    }

    @Override
    public void UpdateInformation() {

    }

    public double getWallet() {
        return wallet;
    }

    public void AddMoney(double wallet) {
        this.wallet = wallet;
    }

    public ArrayList getEvents() {
        return events;
    }    
    
    public void AddEvent()
    {
        Scanner in = new Scanner (System.in);
        System.out.println("To add event, please Enter the following Data: ");
        System.out.println("Enter the event name");
        String name = in.nextLine();
        System.out.println("Enter Category:");
        String category = in.nextLine();
        System.out.println("Enter Discribtion");
        String description = in.nextLine();
        System.out.println("Enter price: ");
        long price = in.nextLong();
        int RoomNo;
        Date DateOfEvent;
        while (true)
        {
           System.out.println("Enter room number: ");
           RoomNo =in.nextInt();
           System.out.println("Enter room Date of Event: ");
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
           DateOfEvent = new Date(year, month, day);
          
           if(isAvailable(RoomNo, DateOfEvent))
           {
             
               
               
               Event e1= new Event( name, description,  category,  price,  RoomNo,  DateOfEvent);
               Database.events.add(e1);
               break;
           }
           else
           {
               System.out.println("The Rooom number " + RoomNo + " is not available in that day");
           }
        }
    }
    
}
