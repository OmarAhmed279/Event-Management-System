/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author omar
 */
public class Room {
   boolean isAvailable = true;
   private int id;
   private long price;
   private Event event;
   public Room(int id, long price, Event event)
   {
       this.id = id;
       this.price = price;
       this.event = event;
   }
   public int getID(){
       return id;
   } 
   public void setID(int id){
       this.id=id;
   }
    public Event getEvent(){
       Event event_copy = new Event(event);
        return event_copy;
   } 
   public void setEvent(Event event){
       this.event=event;
   }
   public void setPrice(long price){
       this.price = price;
   }
   public long getPrice(){
       return price;
   } 
   public void setAvailability(boolean isAvailable)
   {
       this.isAvailable = isAvailable;
   }
   public boolean IsAvailable(Date date)
   {
       return isAvailable;
   }
}
