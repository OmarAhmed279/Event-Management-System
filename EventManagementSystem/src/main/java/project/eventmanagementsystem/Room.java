/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Wafaey
 */
public class Room {
    private int id;
    private long price;
    private final ArrayList<Event> events = new ArrayList<>(); //why final?
    private final Month[] months = {new Month(), new Month(), new Month(), new Month(), new Month(), new Month(),new Month(), new Month(), new Month(), new Month(), new Month(), new Month()};

    public Room(long price) {
        this.price = price;
        Database.rooms.add(this);
        this.setID();
    }

    public Room(long price, Event e) {
        this(price);
        events.add(e);
    }

    public int getID() {
        return id;
    }

    public void setID() { //i changed all the set id in all classes due to a reason too long to explain so if you wanna know why ask me in call -omar

        this.id = Database.rooms.indexOf(this);
    }

    public List<Event> getEvents() {
        return List.copyOf(events);
    }

    public void addEvent(Event event) {
        this.events.add(event);
        months[event.getDate().getMonth()].setSlot(event.getDate().getDay(),event.getDate().getHours());
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
        months[event.getDate().getMonth()].deleteSlot(event.getDate().getDay(),event.getDate().getHours());
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public boolean IsAvailable(Date date) {
        for (Event event : events) {
            if (event.getDate() == date) {
                return false;
            }
        }
        return true;
    }
}
