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
   private int id;
   private Event event;
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
   public boolean IsAvailable(Date date)
   {
       return true;
   }
}
