/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Wafaey
 */
public class Room {
    private int id;
    private long price;
    private final ArrayList<Event> events = new ArrayList<>();


    public Room(int id, long price) {
        this.id = id;
        this.price = price;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return List.copyOf(events);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }
    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public boolean IsAvailable(Date date) {
        for(Event event : events)
        {
            if(event.getDate() == date)
            {
                return false;
            }
        }
        return true;
    }
}
