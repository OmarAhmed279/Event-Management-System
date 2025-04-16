/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;
import java.util.ArrayList;
/**
 *
 * @author omar
 */
public class Room {
   private int id;
   private Event event;
   int getID(){
       return id;
   } 
   void setID(int id){
       this.id=id;
   }
    Event getEvent(){
       return event;
   } 
   void setEvent(Event event){
       this.event=event;
   }
}
