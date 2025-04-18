/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 * @author youss
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.List;

public class Event {
    private String name;
    private String description;
    private Category category;
    private long price;
    private Room room;
    private Organizer organizer;
    private ArrayList<Attendee> attendees = new ArrayList<>();
    private Date date;
    private int ID;

    public Event(String name, String description, Category category, long price, Room room, Date date, Organizer u) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.room = room;
        this.date = date;
        this.ID = Database.events.indexOf(this);
        this.organizer = u;
    }

    public Event(Event event) {
        this.name = event.name;
        this.description = event.description;
        this.category = event.category;
        this.price = event.price;
        this.room = event.room;
        this.date = new Date(event.date.getTime());
        this.attendees = event.attendees;
        this.ID = Database.events.indexOf(this);
    }

    public int getID() {
        return ID;
    }

    public void setID() //i changed all the set id in all classes due to a reason too long to explain so if you wanna know why ask me in call -omar
    {
        this.ID = Database.events.indexOf(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "Name cannot be null");
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = Objects.requireNonNull(category, "Category cannot be null");
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = Objects.requireNonNull(room, "Room cannot be null");
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = Objects.requireNonNull(organizer, "Organizer cannot be null");
    }

    public List<Attendee> getAttendees() {
        ArrayList <Attendee> attendees_copy = new ArrayList<>();
        for(Attendee attendee : attendees)
        {
            Attendee copy = new Attendee(attendee.getUsername(),attendee.getPassword(),attendee.getDateOfBirth(),attendee.getGender(),attendee.getAddress(),attendee.getInterests());
            attendees_copy.add(copy);
        }
        return List.copyOf(attendees_copy);
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(Objects.requireNonNull(attendee, "Attendee cannot be null"));
    }

    public boolean removeAttendee(Attendee attendee) {
        return attendees.remove(attendee);
    }

    public Date getDate() {
        Date def_copy_date = new Date(date.getTime());
        return def_copy_date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAttendeeCount() {
        return attendees.size();
    }

    public boolean hasAttendee(Attendee attendee) {
        return attendees.contains(attendee);
    }
}
