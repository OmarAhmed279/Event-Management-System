/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 *
 * @author youss
 */
import java.util.Arrays;

public class Day {
    private boolean[] hours = new boolean[24];
    public Day(){}
    public Day(boolean[] hours)
    {
        this.hours = hours;
    }
    public boolean isAvailable()
    {
        for(int i = 0; i < 24; i++)
        {
            if(hours[i])
            {
                return false;
            }
        }
        return true;
    }
    public void setHours(int hour)
    {
        for(int i = 0; i <= 2; i++)
        {
            hours[hour+i] = true;
        }
    }
    public void deleteHours(int hour)
    {
        for(int i = 0; i <= 2; i++)
        {
            hours[hour+i] = false;
        }
    }
    public boolean[] getHours()
    {
        return Arrays.copyOf(hours, hours.length);
    }
}
