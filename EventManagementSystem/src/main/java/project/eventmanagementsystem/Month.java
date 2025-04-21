/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 *
 * @author youss
 */
public class Month {
    private final Day[] days = {new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day(),new Day()};
    public boolean isAvailable()
    {
        for(Day day : days)
        {
            if(!day.isAvailable())
            {
                return false;
            }
        }
        return true;
    }
    public Day[] getDay()
    {
        Day[] copy = new Day[days.length];
        for(int i = 0; i < days.length; i++)
        {
            copy[i] = new Day(days[i].getHours());
        }
        return copy;
    }
    public void setSlot(int day, int hour)
    {
        days[day].setHours(hour);
    }
    public void deleteSlot(int day, int hour)
    {
        days[day].deleteHours(hour);
    }
}
